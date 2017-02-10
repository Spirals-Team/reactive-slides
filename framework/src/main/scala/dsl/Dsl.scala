package dsl

/**
  * Fichier de définition du DSL (Domain Specific Language)
  */
object Dsl {

  /**
    * Le DSL est écrit à l'aide de ScalaTags, on fait donc les importations nécessaires
    */
  import scalatags.Text.all._
  import scalatags.Text.tags2.{section, title}

  /**
    * Création d'une présentation, on utilise qu'une seule fois cette fonction.
    * @param titleText Le titre de la page internet
    * @param description La description de la page internet
    * @param author L'auteur de la présentation
    * @param theme Le thème de la présentation, il s'agit des thèmes proposés par Reveal.js (black,white,league,sky,beige,simple,serif,blood,night,moon,solarized)
    *              Liste des thèmes sur la doc Reveal.js : https://github.com/hakimel/reveal.js#theming
    * @param array Le contenu de la présentation, on retrouve toutes les diapositives et leurs contenus
    * @return
    */
  def presentation(titleText: String, description: String, author: String, theme: String, array: scalatags.Text.Modifier*) =
    Seq(
      html(
        head(
          meta(charset := "utf-8"),
          title(titleText),
          meta(name := "description", content := description),
          meta(name := "author", content := author),
          meta(name := "apple-mobile-web-app-capable", content := "yes"),
          meta(name := "apple-mobile-web-app-status-bar-style", content := "black-translucent"),
          meta(name := "viewport", content := "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"),

          link(rel := "stylesheet", href := "assets/stylesheets/reveal.css"),
          link(rel := "stylesheet", href := "assets/stylesheets/theme/"+theme+".css", id := "theme"),
          link(rel := "stylesheet", href := "assets/stylesheets/lib/zenburn.css")
        ),

        body(
          div(`class` := "reveal",
            div(`class` := "slides", array)
          ),
          script(src := "assets/javascripts/lib/head.min.js")
        ),

        script(src := "assets/javascripts/reveal.js")
      ),

      script(scala.io.Source.fromFile("framework/public/reveal_initialize.txt").mkString)
    )

  /**
    * Création d'une diapositive
    * @param content Le contenu de la diapositive
    * @return
    */
  def slide(content: scalatags.Text.Modifier*) =
    section(content)

  /**
    * Titre le plus grand (1)
    * @param content Le contenu du titre, le plus souvent une chaine de caractères
    * @return
    */
  def title1(content: scalatags.Text.Modifier*) =
    h1(content)

  /**
    * Titre grand (2)
    * @param content Le contenu du titre, le plus souvent une chaine de caractères
    * @return
    */
  def title2(content: scalatags.Text.Modifier*) =
    h2(content)

  /**
    * Titre moyen (3)
    * @param content Le contenu du titre, le plus souvent une chaine de caractères
    * @return
    */
  def title3(content: scalatags.Text.Modifier*) =
    h3(content)

  /**
    * Titre petit (4)
    * @param content Le contenu du titre, le plus souvent une chaine de caractères
    * @return
    */
  def title4(content: scalatags.Text.Modifier*) =
    h4(content)

  /**
    * Titre le plus petit (5)
    * @param content Le contenu du titre, le plus souvent une chaine de caractères
    * @return
    */
  def title5(content: scalatags.Text.Modifier*) =
    h5(content)

  /**
    * Création d'un paragraphe
    * @param content Le contenu du paragraphe, le plus souvent une chaine de caractères
    * @return
    */
  def textLine(content: scalatags.Text.Modifier*) =
    p(content)

  /**
    * Création d'un paragraphe mais qui n'apparaitra qu'après avoir fait "suivant" (flèche vers la diapo suivante)
    * @param content Le contenu du paragraphe, le plus souvent une chaine de caractères
    * @return
    */
  def textLineFragment(content: scalatags.Text.Modifier*) =
    p(attr("class"):="fragment",content)

  /**
    * Création d'un bloc mais qui n'apparaitra qu'après avoir fait "suivant" (flèche vers la diapo suivante)
    * @param content Le contenu du bloc, le plus souvent une chaine de caractères
    * @return
    */
  def textFragment(content: scalatags.Text.Modifier*) =
    span(attr("class"):="fragment", content)

  /**
    * Met du texte en italique
    * @param content Le texte à transformer
    * @return
    */
  def italic(content: String) =
    em(content)

  /**
    * Met du texte en gras
    * @param content Le texte à transformer
    * @return
    */
  def bold(content: String) =
    strong(content)

  /**
    * Création d'une ligne vide, permet de faire un saut de ligne
    * @return
    */
  def emptyLine =
    br

  /**
    * Création d'une liste à puces qui sera normalement composé de listItem (voir plus bas)
    * @param content Le contenu de la liste : un ou plusieurs éléments, le plus souvent une suite d'éléments listItem
    * @return
    */
  def unorderedList(content: scalatags.Text.Modifier*) =
    ul(content)

  /**
    * Création d'une liste numérotée qui sera normalement composé de listItem (voir plus bas)
    * @param content Le contenu de la liste : le plus souvent une suite d'éléments listItem
    * @return
    */
  def orderedList(content: scalatags.Text.Modifier*) =
    ol(content)

  /**
    * Création d'un élément d'une liste
    * @param content Le contenu de l'élément de liste : le plus souvent une chaine de caractères
    * @return
    */
  def listItem(content: scalatags.Text.Modifier*) =
    li(content)

  /**
    * Création d'un lien
    * @param content Le contenu du lien, contient normalement un élément linkURL
    * @return
    */
  def alink(content: scalatags.Text.Modifier*) =
    a(content)

  /**
    * Création d'une ligne de tableau, doit être placé dans un élément table
    * @param content Le contenu de la ligne de tableau, il s'agira normalement d'un ou de plusieurs tableBox(...)
    * @return
    */
  def tableRow(content: scalatags.Text.Modifier*) =
    tr(attr("class"):="reveal", content)

  /**
    * Création d'un élément d'entête de tableau, doit être placé dans un élément tableRow
    * @param content Le contenu de l'élément d'entête de tableau, le plus souvent une chaine de caractères
    * @return
    */
  def tableHead(content: scalatags.Text.Modifier*) =
    th(content)

  /**
    * Création d'un élément de tableau, doit être placé dans un élément tableRow
    * @param content Le contenu de l'élément de tableau, le plus souvent une chaine de caractères
    * @return
    */
  def tableBox(content: scalatags.Text.Modifier*) =
    td(content)

  /**
    * Création d'un attribut de lien hypertexte, utilisé principalement dans un élément alink
    * @param content L'URL vers lequel le lien pointe
    * @return
    */
  def linkURL(content: String) =
    attr("href"):=content

  /**
    * Création d'un attribut source
    * @param content Le chemin vers la source ciblée
    * @return
    */
  def sourceAttr(content: String) =
    attr("src"):=content

  /**
    * Création d'un attribut alt, peut être placé uniquement dans un élément img
    * @param content Le texte affiché si l'image d'un élément img ne peut pas être affichée
    * @return
    */
  def altAttr(content: String) =
    attr("alt"):=content

  /**
    * Création d'un bloc pour afficher du code
    * @param content Le code à afficher
    * @return
    */
  def codeQuote(content: String) =
    pre(
      code(
        attr("data-trim"):="data-trim",
        attr("data-noescape"):="data-noescape",
        attr("class"):="hljs",
        content
      )
    )
  
  /**
    * Création d'une diapositive contenant un questionnaire, le plus souvent c'est une diapositive sur laquelle l'auditoire accède après avoir scanné un QR code à partir de generateQuestionQRCode
    * @param number Le numéro/intitulé de la question sous la forme "Question1", "Question2", "Question3"...
    * @param content La liste des réponses possibles
    * @return
    */
  def survey(number: String, choices: Int, content: scalatags.Text.Modifier*) =
    Seq(
      form(
        title3(
          input(
            `type` := "hidden",
            `name` := "question",
            `value` := content(0).toString().substring(11, content(0).toString().length()-1),
            content(0)
          )
        ),
        div(
          `class` := "radio",
          label(
            input(
              `type` := "radio",
              `name` := "reponse",
              `value` := content(1).toString().substring(11, content(1).toString().length()-1),
              content(1)
            )
          )
        ),
        div(
          `class` := "radio",
          label(
            input(
              `type` := "radio",
              `name` := "reponse",
              `value` := content(2).toString().substring(11, content(2).toString().length()-1),
              content(2)
            )
          )
        ),
        button(`type` :="submit", `name` :="number", `value` :=number, `formmethod` :="post", `formaction` :="SubmitAnswer", "Submit")
      )
    )

  /**
    * Génère et affiche un QR code qui donne accès à un questionnaire défini par la fonction survey(...)
    * @param questionLink Le lien de la question sous la forme "https://monserver.com/Question1" ...
    * @return
    */
  def generateQuestionQRCode(questionLink: String) =
    img(
      sourceAttr("https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl="+questionLink)
    )

  /**
    * Affiche un graphe permettant d'afficher les réponses d'une question donnée
    * @param number Le numéro/intitulé de la question sous la forme "Question1", "Question2", "Question3"...
    * @param chartType Le type de graphe souhaité, il s'agit ici des types proposés par le plugin Chart.js (pie, line, bar)
    * @return
    */
  def displayGraph(number: String, chartType: String) =
    canvas( attr("data-chart"):=chartType,
      scala.io.Source.fromFile("examples/public/charts/"+number+".txt").mkString
    )
}
