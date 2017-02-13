package reactiveSlides.views

import dsl.Dsl._

object HomePageView {
  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
      slide(
        attr("style"):="text-align: left;",
        title1("WELCOME TO REACTIVE-SLIDES !"),
        textLine(
          "- ",
          alink(linkURL("/RevealJsDemo"),"Reveal.js Demo Presentation")
        ),
        textLine(
          "- ",
          alink(linkURL("/InteractiveDemo"),"Interactive Demo Presentation")
        ),
        textLine(
          "- ",
          alink(linkURL("/ExampleDemo"), "Example Demo Presentation")
        )
      )
    )
  }
}