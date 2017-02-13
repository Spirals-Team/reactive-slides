package interactivePresentation.views

object Question1View {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        surveyOf2("Question1", "Are You Paying Attention ?","Yes", "No")
      )
    )
  }
}
