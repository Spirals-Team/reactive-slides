package views

import play.api.db.Database

object AnswerView {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, theme: String, reponse: String, db: Database) = {

    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement
      val query = "insert into question_reponse (question,reponse) values ('Are you working ?','"+reponse+"')"
      stmt.executeUpdate(query)
    } finally {
      conn.close()
    }

    presentation(title, theme,
      slide(
        title3("Merci pour votre réponse")
      )
    )
  }
}
