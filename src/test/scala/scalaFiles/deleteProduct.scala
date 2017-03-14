
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import com.typesafe.config._
class deleteProduct extends Simulation {

	val conf = ConfigFactory.load()
	val baseUrl = conf.getString("url")
	val noOfUsers: Int = conf.getInt("noOfUsers")
	val productId: Int = conf.getInt("productId")
	val httpProtocol = http
		.baseURL(baseUrl)
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("en-US,en;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")

	val headers_0 = Map("Origin" -> "https://product-microservice.mybluemix.net")

    val uri1 = "https://product-microservice.mybluemix.net/products/4"

	val scn = scenario("deleteProduct")
		.exec(http("request_0")
			.delete("/products/"+productId)
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(noOfUsers))).protocols(httpProtocol)
}