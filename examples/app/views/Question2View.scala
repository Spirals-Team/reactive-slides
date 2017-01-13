package views

object Question2View {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String) = {
    presentation(title, "black",
      slide(
        survey("Which device ?","Smartphone", "Tablet", "Laptop")
      )
    )
  }
}
