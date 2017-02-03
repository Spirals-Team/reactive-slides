package views

object Question1View {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, theme: String) = {
    presentation(title, theme,
      slide(
        survey("Question1", "Are you working ?","Yes", "No")
      )
    )
  }
}
