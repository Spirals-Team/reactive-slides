package views

object SubmissionConfirmationView {

  import dsl.Dsl._
  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        title3("Thank you for your answer")
      )
    )
  }
}
