DROP TABLE IF EXISTS question_reponse;

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
(11, "Are you working ?", "No"),
(12, "How old are you ?", "10-20"),
(13, "How old are you ?", "10-20"),
(14, "How old are you ?", "20-30"),
(15, "How old are you ?", "20-30"),
(16, "How old are you ?", "40-50"),
(17, "How old are you ?", "60-70"),
(18, "How old are you ?", "70+"),
(19, "How old are you ?", "50-60"),
(20, "How old are you ?", "40-50"),
(21, "How old are you ?", "10-20"),
(22, "How old are you ?", "20-30"),
(23, "How old are you ?", "30-40"),
(24, "How old are you ?", "20-30"),
(25, "How old are you ?", "40-50");
