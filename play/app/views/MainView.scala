package views

object MainView {
  import scalatags.Text.all._

  def apply(content: Seq[Modifier])(implicit env: play.Environment) = {
    html(
      body(content)
    )
  }
}
