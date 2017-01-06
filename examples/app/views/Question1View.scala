package views

object Question1View {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String) = {
    presentation(title, "black",
      slide(
        survey("Are you working ?","Yes", "No")
      )
    )
  }
}
