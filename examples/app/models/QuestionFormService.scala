package models

import javax.inject.Inject
import play.api.db.Database

case class Question(id: Option[Long] = None,
                    question: String,
                    reponse: String)

@javax.inject.Singleton
class QuestionFormService @Inject()(db: Database){

  def save(result : String) = {
    val conn = db.getConnection()
    try {
      val stmt = conn.createStatement
      val query = "insert into question_reponse (question,reponse) values ('Are you working ?','No')"
      stmt.executeQuery(query)
    } finally {
      conn.close()
    }
  }
}
