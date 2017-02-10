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

/**
  * Manage the interactive presentation
  */
class PresentationController @Inject()(db: Database, questionFormService: QuestionFormService)(implicit env: play.Environment) extends Controller {

  /**
    * Manage the interactive presentation parameters
    */
  def presentationTitle = "reactive-slides - The Interactive Presentations Framework"
  def description = "A web framework for building interactive presentations"
  def author = "Rahal Badr & Nicolas Vasseur"
  def theme = "black"

  /**
    * Defines the function rendering the scalatags views
    * @param view the view to render
    * @return
    */
  def ok(view: Seq[Text.TypedTag[String]]) = Action {
    implicit val codec = Codec.utf_8
    Ok(MainView(view).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

  /**
    * This result directly redirect to the presentation home.
    */
  def index = {
    ok(PresentationView(presentationTitle, description, author, theme))
  }

  /**
    * Display the Question 1.
    */
  def showQuestion1 = {
    ok(Question1View(presentationTitle, description, author, theme))
  }

  /**
    * Display the Question 2.
    */
  def showQuestion2 = {
    ok(Question2View(presentationTitle, description, author, theme))
  }

  /**
    * Describe the question form .
    */
  case class QuestionData(question: String,
                          response: String,
                          number: String)

  val questionForm = Form(
    mapping(
      "question" -> Forms.text,
      "reponse" -> Forms.text,
      "number" -> Forms.text
    )(QuestionData.apply)(QuestionData.unapply)
  )

  /**
    * Handle saving answers.
    */
  def saveAnswer = Action { implicit request =>
    val questionData = questionForm.bindFromRequest.get
    questionFormService.extractAnswer(questionData.question, questionData.response, questionData.number)
    Ok(MainView(AnswerView(presentationTitle, description, author, theme)).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }
}