package controllers

import javax.inject.Inject
import models.QuestionFormService
import play.api.data._
import play.api.data.Forms._
import play.api.db._
import play.api.http._
import play.api.mvc._
import views.{AnswerView, PresentationView, MainView, Question1View, Question2View}

import scalatags._

class PresentationController @Inject()(db: Database, questionFormService: QuestionFormService)(implicit env: play.Environment) extends Controller {

  def ok(view: Seq[Text.TypedTag[String]]) = Action {
    implicit val codec = Codec.utf_8
    Ok(MainView(view).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

  def presentationTitle = "reactive-slides - The Interactive Presentations Framework"
  def description = "A web framework for building interactive presentations"
  def author = "Rahal Badr & Nicolas Vasseur"
  def theme = "black"

  def index = {
    ok(PresentationView(presentationTitle, description, author, theme))
  }

  def showQuestion1 = {
    ok(Question1View(presentationTitle, description, author, theme))
  }

  def showQuestion2 = {
    ok(Question2View(presentationTitle, description, author, theme))
  }

  case class QuestionData(question: String, reponse: String, number: String)
  val questionForm = Form(
    mapping(
      "question" -> Forms.text,
      "reponse" -> Forms.text,
      "number" -> Forms.text
    )(QuestionData.apply)(QuestionData.unapply)
  )

  def saveAnswer = Action { implicit request =>
    val questionData = questionForm.bindFromRequest.get
    questionFormService.extractAnswer(questionData.question, questionData.reponse, questionData.number)
    Ok(MainView(AnswerView(presentationTitle, description, author, theme)).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }
}