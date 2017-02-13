package interactivePresentation.views

object InteractivePresentationView {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        slide(
          title3("Are You Paying Attention ?"),
          generateQuestionQRCode("Question1")
        ),
        slide(
          title3("Are You Paying Attention ?"),
          displayGraph("Question1", "pie")
        )
      ),
      slide(
        slide(
          title3("Which device are you using ?"),
          generateQuestionQRCode("Question2")
        ),
        slide(
          title3("Which device are you using ?"),
          displayGraph("Question2", "pie")
        )
      ),
      slide(css("text-align") := "left",
        title1("THE END"),
          textLine(
            "- ",
            alink(linkURL("/"), "Go Back to reactive-slides Home Page")
          )
        )
    )
  }
}
