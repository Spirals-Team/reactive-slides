package views

object AnswerView {

  import dsl.Dsl._
  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        title3("Merci pour votre réponse")
      )
    )
  }
}
