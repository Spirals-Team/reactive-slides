package controllers

import javax.inject.Inject

import models.QuestionFormService
import play.api.data._
import play.api.data.Forms._
import play.api.db._
import play.api.http._
import play.api.mvc._
import views.{AnswerView, IndexView, MainView, Question1View, Question2View}

import scalatags._

class PresentationController @Inject()(db: Database, questionFormService: QuestionFormService)(implicit env: play.Environment) extends Controller {

  def ok(view: Seq[Text.TypedTag[String]]) = Action {
    implicit val codec = Codec.utf_8
    Ok(MainView(view).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

  def presentationTitle = "reveal.js - The HTML Presentation Framework"
  def theme = "black"

  def index = {
    ok(IndexView(presentationTitle, theme))
  }

  def showQuestion1 = {
    ok(Question1View(presentationTitle, theme))
  }

  def showQuestion2 = {
    ok(Question2View(presentationTitle, theme))
  }

  val questionForm = Form(new Tuple2("reponse", play.api.data.Forms.text))

  def saveAnswer = Action { implicit request =>
    val reponse = questionForm.bindFromRequest.get
    Ok(MainView(AnswerView(presentationTitle, theme, reponse, db)).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }
}