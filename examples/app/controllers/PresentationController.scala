package controllers

import javax.inject.Inject
import java.io._

import dsl.Dsl._
import models.QuestionFormService
import play.api.db._
import play.api.http._
import play.api.mvc._
import views.{IndexView, MainView}

import scala.collection.mutable.ListBuffer
import scalatags.Text.all._
import scalatags._

class PresentationController @Inject()(db: Database, questionFormService: QuestionFormService)(implicit env: play.Environment) extends Controller {

  def presentationTitle = "reveal.js - The HTML Presentation Framework"

  def index = {
    var elements = new ListBuffer[Text.TypedTag[String]]()
    elements += title2("Résultats")

    val conn = db.getConnection()

    try {
      val stmt = conn.createStatement
      val query = "SELECT reponse, COUNT(reponse) as nb FROM question_reponse WHERE question = 'How old are you ?' GROUP BY reponse"
      val rs = stmt.executeQuery(query)

      var firstLine = ""
      while (rs.next()) {
        firstLine += ", " + rs.getString("reponse")
      }

      rs.beforeFirst()

      var secondLine = "My first dataset"
      while(rs.next()){

        secondLine += ", " + rs.getString("nb")
      }

      val pw = new PrintWriter(new File("examples/public/charts/chart_content.txt"))
      pw.write(firstLine+"\n"+secondLine)
      pw.close

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