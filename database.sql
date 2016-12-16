CREATE TABLE IF NOT EXISTS question_reponse (
    PK_question_reponse int(11) NOT NULL AUTO_INCREMENT,
    question varchar(100) NOT NULL,
    reponse varchar(100) NOT NULL,
    PRIMARY KEY (PK_question_reponse)
);

INSERT INTO question_reponse (PK_question_reponse, question, reponse) VALUES
(1, "Are you working ?", "Yes"),
(2, "Are you working ?", "Yes"),
(3, "Are you working ?", "Yes"),
(4, "Are you working ?", "No"),
(5, "Are you working ?", "No"),
(6, "Are you working ?", "Yes"),
(7, "Are you working ?", "Yes"),
(8, "Are you working ?", "No"),
(9, "Are you working ?", "Yes"),
(10, "Are you working ?", "Yes"),
(11, "Are you working ?", "No");