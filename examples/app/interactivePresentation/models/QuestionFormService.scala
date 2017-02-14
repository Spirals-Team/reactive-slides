package interactivePresentation.models

import java.io.{File, PrintWriter}
import javax.inject.Inject
import scala.io.Source
import java.io._

import play.api.db.Database

/**
  * The Class representing the questions .
  */
case class QuestionData(question: String,
                        response: String,
                        number: String)

@javax.inject.Singleton
class QuestionFormService @Inject()(db: Database){

  /**
    * Extract the answer's results .
    */
  def extractAnswer(question: String, response: String, number: String) = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement

      // Insert the answer in the database
      val query = "insert into question_reponse (question,reponse) values ('"+question+"','"+response+"')"
      stmt.executeUpdate(query)

      // Extract the result from the database and put it in a .txt file.
      val query2 = "SELECT reponse, COUNT(reponse) as nb FROM question_reponse WHERE question = '"+question+"' GROUP BY reponse"
      val rs = stmt.executeQuery(query2)

      var firstLine = ""
      while (rs.next()) {
        firstLine += ", " + rs.getString("reponse")
      }
      rs.beforeFirst()

      var secondLine = "Audience Response"
      while (rs.next()) {
        secondLine += ", " + rs.getString("nb")
      }

      val yourFile = new File("examples/public/charts/"+number+".txt")
      yourFile.createNewFile()
      val pw = new PrintWriter(yourFile)
      pw.write(firstLine + "\n" + secondLine)
      pw.close

    } finally {
      conn.close()
    }
  }

  /**
    * Reset the database
    */
  def resetDatabase() = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement

      // reset database
      val query = "truncate table question_reponse"
      stmt.executeUpdate(query)

    } finally {
      conn.close()
    }

    val folder = new File("examples/public/charts")
    folder.listFiles().foreach(file => {
      val pw = new PrintWriter(file)
      pw.write("")
      pw.close()
    })
  }
}
