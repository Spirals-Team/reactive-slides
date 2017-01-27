package views

import java.io.{File, PrintWriter}
import play.api.db.Database

object AnswerView {

  import dsl.Dsl._

  import scalatags.Text.all._

  def apply(title: String, theme: String, reponse: String, db: Database) = {

    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement

      // On insère dans la DB la réponse donnée
      val query = "insert into question_reponse (question,reponse) values ('Are you working ?','"+reponse+"')"
      stmt.executeUpdate(query)

      // On met dans un fichier les résultats de la DB pour mettre à jour le graphe des réponses
      val query2 = "SELECT reponse, COUNT(reponse) as nb FROM question_reponse WHERE question = 'Are you working ?' GROUP BY reponse"
      val rs = stmt.executeQuery(query2)

      var firstLine = ""
      while (rs.next()) {
        firstLine += ", " + rs.getString("reponse")
      }
      rs.beforeFirst()

      var secondLine = "My first dataset"
      while (rs.next()) {
        secondLine += ", " + rs.getString("nb")
      }

      val pw = new PrintWriter(new File("examples/public/charts/chart_content.txt"))
      pw.write(firstLine + "\n" + secondLine)
      pw.close

    } finally {
      conn.close()
    }

    // On affiche un écran de remerciement
    presentation(title, theme,
      slide(
        title3("Merci pour votre réponse")
      )
    )
  }
}
