package controllers

import play.api.http._
import play.api.mvc._
import play.api.db._
import java.io._

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

    val conn2 = db.getConnection()

    try {
      val stmt2 = conn2.createStatement
      val chartquery = "SELECT COUNT(reponse) as nb_yes, 1 as nb_no FROM question_reponse WHERE question = 'Are you working ?' AND reponse = 'Yes'"
      val rschart = stmt2.executeQuery(chartquery)

      while (rschart.next()){
        val pw = new PrintWriter(new File("examples/public/charts/Areyouworking.txt" ))
        pw.write(", Non, Oui\n My first dataset, "+rschart.getString("nb_no")+", "+rschart.getString("nb_yes"))
        pw.close
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
