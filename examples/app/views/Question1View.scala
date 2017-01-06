package views

object Question1View {

  import scalatags.Text.all._
  import dsl.Dsl._

  def apply(title: String) = {
    presentation(title, "black",
      slide(
        survey("Are you working ?","Yes", "No")
      )
    )
  }
}
