package controllers

import play.api.http._
import play.api.mvc._

import scalatags._
import javax.inject.Inject

import views.IndexView
import views.MainView

class Application @Inject()(implicit env: play.Environment)
    extends Controller {

  def presentationTitle = "reveal.js - The HTML Presentation Framework"
  def index = ok(IndexView(presentationTitle))

  def ok(view: Seq[Text.TypedTag[String]]) = Action {
    implicit val codec = Codec.utf_8
    Ok(MainView(view).toString).withHeaders(CONTENT_TYPE -> ContentTypes.HTML)
  }

}
