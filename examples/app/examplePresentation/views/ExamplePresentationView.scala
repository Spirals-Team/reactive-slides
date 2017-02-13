package examplePresentation.views

object ExamplePresentationView {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, description: String, author: String, theme: String) = {
    presentation(title, description, author, theme,
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
              |I lived for half a year in Reims, then I moved to Paris for a 5 months internship, and now here I am in Lille.""".stripMargin)
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
              |I wanted to meet new friends from all over europe. I love discovering new cultures.""".stripMargin)
        )
      ),
      slide(attr("data-background") := "http://i.giphy.com/90F8aUepslB84.gif",
        title2("What a DUO!")
      ),
      slide(css("text-align") := "left",
        title1("THE END"),
        textLine(alink(attr("href") := "https://github.com/Spirals-Team/reactive-slides", "Our Repo in Github"),
          textLine(
            "- ",
            alink(linkURL("/"), "Go Back to reactive-slides Home Page")
          )
        )
      )
    )
  }
}
