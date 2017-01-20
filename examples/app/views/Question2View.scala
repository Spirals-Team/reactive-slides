package views

object Question2View {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, theme: String) = {
    presentation(title, theme,
      slide(
        survey("Which device ?","Smartphone", "Tablet", "Laptop")
      )
    )
  }
}
