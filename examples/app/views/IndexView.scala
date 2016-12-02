package views

object IndexView {
  import scalatags.Text.all._
  import scalatags.Text.tags2.section
  import scalatags.Text.tags2.title
  import scalatags._
  import dsl.Dsl

  // ---------------------------------------------------------------------------------------
  // ----------------------------------- Définition du DSL - Début -----------------------------------
  // ---------------------------------------------------------------------------------------


  def presentation(titleText: String, theme: String, array: scalatags.Text.Modifier*) = // Ne pas renommer array en content, provoque un bug
    Seq(
      html(
        head(
          meta(charset := "utf-8"),
          meta(name := "viewport", content := "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"),

          title(titleText),

          link(rel := "stylesheet", href := "assets/stylesheets/reveal.css"),
          link(rel := "stylesheet", href := "assets/stylesheets/theme/"+theme+".css"),
          link(rel := "stylesheet", href := "assets/stylesheets/lib/zenburn.css")
        ), // Fermeture head

        body(
          div(`class` := "reveal",
            div(`class` := "slides", array)
          ),
          script(src := "assets/javascripts/lib/head.min.js")
        ), // Fermeture body
        script(src := "assets/javascripts/reveal.js")
      ), // Fermeture html
      script("Reveal.initialize();")
    ) // Fermeture Seq



  def title1(content: scalatags.Text.Modifier*) =
    h1(content)

  def title2(content: scalatags.Text.Modifier*) =
    h2(content)

  def slide(content: scalatags.Text.Modifier*) =
    section(content)

  def title3(content: scalatags.Text.Modifier*) =
    h3(content)

  def title4(content: scalatags.Text.Modifier*) =
    h4(content)

  def title5(content: scalatags.Text.Modifier*) =
    h5(content)

  def textLine(content: scalatags.Text.Modifier*) =
    p(content)

  def textLineFragment(content: scalatags.Text.Modifier*) =
    p(attr("class"):="fragment",content)

  def textFragment(content: scalatags.Text.Modifier*) =
    span(attr("class"):="fragment", content)



  def unorderedList(content: scalatags.Text.Modifier*) =
    ul(content)

  def orderedList(content: scalatags.Text.Modifier*) =
    ol(content)

  def listItem(content: scalatags.Text.Modifier*) =
    li(content)

  def alink(content: scalatags.Text.Modifier*) =
    a(content)

  def tableHead(content: scalatags.Text.Modifier*) =
    thead(content)

  def tableBody(content: scalatags.Text.Modifier*) =
    tbody(content)

  def tableLine(content: scalatags.Text.Modifier*) =
    tr(content)

  def tableBox(content: scalatags.Text.Modifier*) =
    td(content)


  // ---------------------------------------------------------------------------------------
  // ----------------------------------- Définition du DSL - Fin -----------------------------------
  // ---------------------------------------------------------------------------------------


  def apply(title: String) = {
    presentation(title, "black",

      slide(
        title1("Reveal.js"),
        title3("The HTML Presentation Framework")
      ),

      slide(
        title2("Hello There"),
        textLine("reveal.js enables you to create beautiful interactive slide decks using HTML. This presentation will show you examples of what it can do.")
      ),

      slide(
        slide(
          title2("Vertical Slides"),
          textLine("Slides can be nested inside each other."),
          textLine("Use the ", Dsl.italic("Space")," key to navigate through all slides."),
          Dsl.emptyLine(),
          alink(
            attr("class"):="navigate-down",
            attr("enabled"):="enabled",
            attr("href"):="#",
            img(
              attr("alt"):="Down arrow",
              source("https://s3.amazonaws.com/hakim-static/reveal-js/arrow.png")
            )
          )
        ),
        slide(
          title2("Basement level 1"),
          textLine("Nested slides are useful for adding additional detail underneath a high level horizontal slide.")
        ),
        slide(
          title2("Basement level 2"),
          textLine("That's it, time to go back up."),
          Dsl.emptyLine(),
          alink(
            Dsl.linkURL("#/2"),
            img(
              attr("alt"):="Up arrow",
              attr("style"):="transform: rotate(180deg);",
              source("https://s3.amazonaws.com/hakim-static/reveal-js/arrow.png")
            )
          )
        )
      ),

      slide(
        title2("Slides"),
        textLine(
          "Not a coder? Not a problem. There's a fully-featured visual editor for authoring these, try it out at ",
          alink(Dsl.linkURL("http://slides.com"), attr("target"):="_blank","http://slides.com"),
          "."
        )
      ),

      slide(
        title2("Touch Optimized"),
        textLine("Presentations look great on touch devices, like mobile phones and tablets. Simply swipe through your slides.")
      ),

      slide(
        title2("Point of View"),
        textLine("Press ", Dsl.bold("ESC"), " to enter the slide overview."),
        textLine(
          "Hold down alt and click on any element to zoom in on it using ",
          alink(Dsl.linkURL("http://lab.hakim.se/zoom-js"), "zoom.js"),
          ". Alt + Click anywhere to zoom back out."
        )
      ),

      slide(
        title2("Mardown support"),
        textLine(
          "Write content using inline or external Markdown. Instructions and more info available in the ",
          alink(
            Dsl.linkURL("https://github.com/hakimel/reveal.js#markdown"),
            "readme"
          ),
          "."
        ),
        Dsl.codeQuote("<section data-markdown>## Markdown support. Write content using inline or external Markdown. Instructions and more info available in the [readme](https://github.com/hakimel/reveal.js#markdown).</section>")
      ),

      slide(
        title2("Fragments"),
        textLine("Hit the next arrow..."),
        textLineFragment("... to step through ..."),
        textLine(textFragment("... a"), textFragment(" fragmented"), textFragment(" slide."))
      ),

      slide(
        title2("Transition Styles"),
        textLine(
          "You can select from different transitions, like:",
          Dsl.emptyLine(),
          alink(
            Dsl.linkURL("?transition=none#/transitions"),
            "None"
          ),
          " - ",
          alink(
            Dsl.linkURL("?transition=fade#/transitions"),
            "Fade"
          ),
          " - ",
          alink(
            Dsl.linkURL("?transition=slide#/transitions"),
            "Slide"
          ),
          " - ",
          alink(
            Dsl.linkURL("?transition=convex#/transitions"),
            "Convex"
          ),
          " - ",
          alink(
            Dsl.linkURL("?transition=concave#/transitions"),
            "Concave"
          ),
          " - ",
          alink(
            Dsl.linkURL("?transition=zoom#/transitions"),
            "Zoom"
          )
        )

      ),

      slide(
        title2("Themes"),
        textLine(
          "reveal.js comes with a few themes built in:",
          Dsl.emptyLine(),
          textLine("Swap themes after the page has loaded is not flexible and only intended for the reveal.js demo deck.")
        )
      ),

      slide(
        slide(
          attr("data-background"):="#dddddd",
          title2("Slide backgrounds"),
          textLine(
            "Set ",
            code("data-background=\"#dddddd\""),
            " on a slide to change the background color. All CSS color formats are supported"
          ),
          alink(
            attr("class"):="navigate-down",
            attr("class"):=" enabled",
            Dsl.linkURL("#"),
            img(
              attr("alt"):="Down arrow",
              attr("src"):="https://s3.amazonaws.com/hakim-static/reveal-js/arrow.png"
            )
          )
        ),
        slide(
          attr("data-background"):="https://s3.amazonaws.com/hakim-static/reveal-js/image-placeholder.png",
          title2("Image Backgrounds")
        )
      ),

      // A revoir, ne fonctionne pas correctement
      slide(
        attr("data-markdown"):="data-markdown",
        "## Test slideMarkDown  A paragraph with some text and a [link](http://hakim.se)."
      ),

      slide(

        slide(

          title2("Collaborator Number 1"),

          Dsl.emptyLine,

          title3("Nicolas Vasseur", alink(attr("href") := "https://github.com/Tiplok", " -Tiplok")),

          textLine(small("Master 2 e-services"))

        ),

        slide(title2("Who Am I ?"),

          textLine("My name is Nicolas, I'm 22 and I live in Tourcoing, Northern France.")

        ),

        slide(title2("What's my story ?"),

          textLine("I was born in Tourcoing, city in the north of France. I always live around this place.")

        ),

        slide(title2("Academic background"),

          unorderedList(

            listItem("2012 : Bachelor of Engineering Science"),

            listItem("2014 : Technology University Degree"),

            listItem("2015 : Licence Degree in Computer Science"),

            listItem("Currently : Master 2 in electronic services")

          )

        )

      ),

      slide(

        slide(title2("Collaborator Number 2"),

          Dsl.emptyLine,

          title3("Rahal Badr", alink(attr("href") := "https://github.com/rbadr", " -rbadr")),

          textLine(small("Master 2 IAGL : Infrastructures Applicatives et Génie Logiciel"))

        ),

        slide(title2("Who Am I ?"),

          textLine("My name is Badr, I'm 24 years old and I Live in Lille, Northern France.")

        ),

        slide(title2("What's my story ?"),

          textLine("I was born in Rabat, the capital of Morocco. I lived there untill my 22nd birthday.")

        ),

        slide(title2("Why did I leave ?"),

          textLine(

            """I got my Licence degree in Computer science and mathematics, then I decided it was time to look for new opportunities abroad.

            I lived for half a year in Reims, then I moved to Paris for a 5 months internship, and now here I am in Lille.""")

        ),

        slide(title2("Academic Background"),

          unorderedList(

            listItem("2010 : Bachelor in mathematical science, Rabat-Morocco."),

            listItem("2014 : Licence Degree in mathematics and computer science, Rabat-Morocco."),

            listItem("2015 : Master 1 Degree in computer science, Reims-France."),

            listItem("Currently : Master 2 in software engineering, Lille-France")

          )

        ),

        slide(title2("What do I love in life?"),

          textLine(

            """I love traveling and meeting new people from different cultures, that's the reason I joined an international association when I moved to Lille,

            I wanted to meet new friends from all over europe. I love discovering new cultures.""")

        )

      ),

      slide(attr("data-background") := "http://i.giphy.com/90F8aUepslB84.gif",

        title2("What a DUO!")

      ),

      slide(css("text-align") := "left",

        title1("THE END"),

        textLine(alink(attr("href") := "https://github.com/rbadr/PFENicolas-Badr", "Our Repo in Github"))

      )

    ) // Fermeture presentation

  } // Fermeture def apply

} // Fermeture object IndexView
