package views.interactivePresentation

object Question2View {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        surveyOf3("Question2", 3, "Which device are you using ?","Smartphone", "Tablet", "Laptop")
      )
    )
  }
}
