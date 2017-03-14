
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import com.typesafe.config._

class postProduct extends Simulation {
	val conf = ConfigFactory.load()
	val baseUrl = conf.getString("url")
	val noOfUsers: Int = conf.getInt("noOfUsers")
	val httpProtocol = http
		.baseURL(baseUrl)
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.8")
		.contentTypeHeader("application/json")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")

	val headers_0 = Map("Origin" -> "https://product-microservice.mybluemix.net")

    val uri1 = "https://product-microservice.mybluemix.net/products"

	val scn = scenario("postProduct")
		.exec(http("request_0")
			.post("/products")
			.headers(headers_0)
			.body(RawFileBody("addProduct.txt")))

	setUp(scn.inject(atOnceUsers(noOfUsers))).protocols(httpProtocol)
}