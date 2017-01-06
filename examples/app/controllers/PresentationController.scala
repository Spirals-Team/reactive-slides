package controllers

import javax.inject.Inject

import models.QuestionFormService
import play.api.db._
import play.api.http._
import play.api.mvc._
import views.{IndexView, MainView, Question1View}

import scalatags._

class PresentationController @Inject()(db: Database, questionFormService: QuestionFormService)(implicit env: play.Environment) extends Controller {

  def ok(view: Seq[Text.TypedTag[String]]) = Action {
    implicit val codec = Codec.utf_8
    Ok(MainView(view).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

  def presentationTitle = "reveal.js - The HTML Presentation Framework"

  def index = {
    ok(IndexView(presentationTitle))
  }

  def showQuestion1 = {
    ok(Question1View(presentationTitle))
  }
}