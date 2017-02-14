package interactivePresentation.views

object ResetDatabaseConfirmationView {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        title3("The Database has been reset !")
      )
    )
  }
}
