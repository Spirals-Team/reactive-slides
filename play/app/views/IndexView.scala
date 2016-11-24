package views

object IndexView {
  import scalatags.Text.all._
  import scalatags.Text.tags2.section
  import scalatags.Text.tags2.title

  def presentationTitle = "Présentation exemple avec reveal.js"
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

  def slide(content: scalatags.Text.Modifier*) =
    section(content)

  def title1(content: scalatags.Text.Modifier*) =
    h1(content)

  def title2(content: scalatags.Text.Modifier*) =
    h2(content)

  def title3(content: scalatags.Text.Modifier*) =
    h3(content)

  def title4(content: scalatags.Text.Modifier*) =
    h4(content)

  def title5(content: scalatags.Text.Modifier*) =
    h5(content)

  def textLine(content: scalatags.Text.Modifier*) =
    p(content)

  def emptyLine =
    br

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

  def apply(title: String) = {
    presentation(title, "blood",

      slide(
        title1("Welcome")

      ),

      slide(
        title1("Table"),
        table(
          thead(
            tr(
              td("Entête 1"),
              td("Entête 2")
            )
          ),
          tbody(
            tr(
              td("test"),
              td("test1")
            ),
            tr(
              td("toast"),
              td("taost")
            )
          )
        )
      ),

      slide(

        slide(

          title2("Collaborator Number 1"),

          emptyLine,

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

          emptyLine,

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

  }

}
