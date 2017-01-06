package controllers

import play.api.http._
import play.api.mvc._
import play.api.db._

import scalatags._
import javax.inject.Inject

import views.IndexView
import views.MainView

import scalatags.Text.all._
import scalatags.Text.tags2.section
import scalatags.Text.tags2.title
import dsl.Dsl._

import scala.collection.mutable.ListBuffer

class Application @Inject()(db: Database)(implicit env: play.Environment) extends Controller {

  def presentationTitle = "reveal.js - The HTML Presentation Framework"

  def index = {
    var elements = new ListBuffer[Text.TypedTag[String]]()
    elements += title2("Résultats")

    val conn = db.getConnection()

    try {
      val stmt = conn.createStatement
      val query = "SELECT reponse, COUNT(reponse) as nb FROM question_reponse WHERE question = 'Are you working ?' GROUP BY reponse"
      val rs = stmt.executeQuery(query)

      while (rs.next()) {
        elements += textLine(rs.getString("reponse")+" : "+rs.getString("nb"))
      }
    } finally {
      conn.close()
    }

    val elementsList = elements.toList
    ok(IndexView(presentationTitle, elementsList))
  }

  def ok(view: Seq[Text.TypedTag[String]]) = Action {
    implicit val codec = Codec.utf_8

    Ok(MainView(view).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

}
