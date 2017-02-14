package interactivePresentation.controllers

import javax.inject.Inject

import interactivePresentation.models._
import interactivePresentation.views._
import play.api.data.Forms._
import play.api.data._
import play.api.http._
import play.api.mvc._
import reactiveSlides.views._
import reactiveSlides.controllers.PresentationController

/**
  * Manage the interactive presentation
  */
class interactivePresentationController @Inject()(questionFormService: QuestionFormService) (implicit env: play.Environment) extends PresentationController {

  /**
    * Display the interactive Demo Presentation
    */
  def displayInteractiveDemo = {
    ok(InteractivePresentationView(presentationTitle, description, author, theme))
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
  val questionForm = Form(
    mapping(
      "question" -> Forms.text,
      "reponse" -> Forms.text,
      "number" -> Forms.text
    )(QuestionData.apply)(QuestionData.unapply)
  )

  /**
    * Handle saving answers and rendering the submission confirmation view.
    */
  def saveAnswer = Action { implicit request =>
    val questionData = questionForm.bindFromRequest.get
    questionFormService.extractAnswer(questionData.question, questionData.response, questionData.number)
    Ok(MainView(SubmissionConfirmationView(presentationTitle, description, author, theme)).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

  /**
    * Reset the database.
    */
  def resetAnswers = Action { implicit request =>
    questionFormService.resetDatabase()
    Ok(MainView(ResetDatabaseConfirmationView(presentationTitle, description, author, theme)).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }
}