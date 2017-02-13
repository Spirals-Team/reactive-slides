package examplePresentation.controllers

import javax.inject.Inject

import examplePresentation.views.ExamplePresentationView

/**
  * Manage the interactive presentation
  */
class examplePresentationController @Inject()(implicit env: play.Environment) extends reactiveSlides.controllers.PresentationController {

  /**
    * Display the example Demo Presentation
    */
  def displayExampleDemo = {
    ok(ExamplePresentationView(presentationTitle, description, author, theme))
  }
}