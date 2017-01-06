package models

import java.io.{File, PrintWriter}
import javax.inject.Inject

import play.api.db.Database

case class Question(id: Option[Long] = None,
                    question: String,
                    reponse: String)

@javax.inject.Singleton
class QuestionFormService @Inject()(db: Database){

  def getQuestion1Answer = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement
      val query = "SELECT reponse, COUNT(reponse) as nb FROM question_reponse WHERE question = 'How old are you ?' GROUP BY reponse"
      val rs = stmt.executeQuery(query)

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
  }

  def save(result : String) = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement
      val query = "insert into question_reponse (question,reponse) values ('Are you working ?','"+result+"')"
      stmt.executeQuery(query)
    } finally {
      conn.close()
    }
    getQuestion1Answer
  }
}
