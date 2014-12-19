package controllers

import scala.concurrent._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
import scala.concurrent.Future
import scala.slick.driver.PostgresDriver.simple._
import models.User
import models.Users
import scala.concurrent.duration._


object Application extends Controller {
  	def index = Action {
		  Ok(views.html.index(foo))
	}
	
	def foo = {
	    val holder: WSRequestHolder = WS.url("https://api.meetup.com/2/open_events?&sign=true&photo-host=public&state=PA&city=Pittsburgh&text=scala&page=20")
	    val result2 = holder.withQueryString("sign" -> "true")
	          .withQueryString("photo-host" -> "public")
	          .withQueryString("state" -> "PA")
	          .withQueryString("city" -> "Pittsburgh")
	          .withQueryString("text" -> "scala")
	          .get()
	    Await.result(result2, 5.second)
	}
}
