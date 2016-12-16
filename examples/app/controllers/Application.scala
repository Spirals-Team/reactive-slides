package controllers

import play.api.http._
import play.api.mvc._
import play.api.db._

import scalatags._
import javax.inject.Inject

import views.IndexView
import views.MainView

class Application @Inject()(db: Database)(implicit env: play.Environment) extends Controller {

  def presentationTitle = "reveal.js - The HTML Presentation Framework"

  def index = {

    var outString = "Number is "
    val conn = db.getConnection()

    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT 9 as testkey ")

      while (rs.next()) {
        outString += rs.getString("testkey")
      }
    } finally {
      conn.close()
    }

    ok(IndexView(presentationTitle, outString))
  }

  def ok(view: Seq[Text.TypedTag[String]]) = Action {
    implicit val codec = Codec.utf_8

    Ok(MainView(view).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

}
