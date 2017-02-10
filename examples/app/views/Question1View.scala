package views

object Question1View {

  import dsl.Dsl._
  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        survey("Question1", 2, "Are You Paying Attention ?","Yes", "No")
      )
    )
  }
}
