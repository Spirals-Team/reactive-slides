https://stackedit.io/editor#!provider=couchdb&id=M7OP5sobwNAh9VomXmgvYJ1M

Manuel utilisateur
===================

L'objectif de ce manuel est d'expliquer comment mettre en place une présentation interactive à l'aide de ce framework.

----------

[TOC]

Installation et configuration
-------------

#### 1. Play avec activator

Pour utiliser ce framework, il vous faut avant tout installer le framework **Play avec activator**.
Suivez ce lien pour se faire : https://www.playframework.com/download#activator
Si l'installation s'est bien passée, vous devriez alors pouvoir faire la commande ```activator``` dans le terminal.

#### 2. Base de données MySQL

Il faut, ensuite, mettre en place une **base de données MySQL**. Celle-ci doit être créé selon les paramètres spécifiés dans le fichier **/examples/conf/application.conf** en particulier les lignes suivantes (~ lignes 45-50) :

```
# Default database configuration using MySQL database engine

db.default.driver=com.mysql.jdbc.Driver
db.default.url="jdbc:mysql://localhost:3306/database_name"
db.default.username=database_username
db.default.password="database_password"
```

Il faut remplacer database_name, database_username et database_password par leurs valeurs correspondantes.

Une fois la base de données créées, pour finir, il est nécessaire d'importer le modèle de données qui se trouve dans le fichier **database.sql** à la racine du projet. 
Plus d'informations pour faire cela : https://dev.mysql.com/doc/refman/5.7/en/mysql-batch-commands.html

#### 3. Lancer le serveur

Pour lancer le serveur en local, il vous suffira alors de lancer la commande ```activator start``` à la racine du framework. 
Par défaut, il vous est alors possible de voir la présentation à l'adresse ```http://localhost:9000``` une fois le serveur bien lancé.

Plus tard, il sera nécessaire de mettre en place votre présentation sur un serveur en ligne pour pouvoir utiliser l'aspect intéractif de l'application.


----------


Création de la présentation
-------------------

Nous allons maintenant voir comment créer une présentation avec ce framework.

#### <i class="icon-file"></i> Fichier de la présentation

L'écriture de toute la présentation se fera dans le fichier **/examples/app/views/IndexView.scala** plus particulièrement dans le bloc suivant :

```
def apply(title: String, theme: String) = {
	presentation(title, theme,
		// Contenu de la présentation
	)
}
```

#### <i class="icon-cog"></i> Paramètres
Il existe aussi deux paramètres qui se trouve dans le fichier **/examples/app/controllers/PresentationController.scala** correspondant aux lignes suivantes :
```
def presentationTitle = "reveal.js - The HTML Presentation Framework"
def theme = "black"
```
"presentationTitle" précise le titre de la présentation, c'est à dire le texte qui sera écrit sur l'onglet de la page web de la présentation et sur la fenêtre du navigateur si l'onglet est selectionné.

"theme" précise le thème de la présentation, cela permet de changer le visuel des diapositives. Il s'agit ici des thèmes proposés par Reveal.js, le framework sur lequel on s'appuie pour générer la présentation. 
Vous trouverez la liste des thèmes disponibles ici : https://github.com/hakimel/reveal.js#theming.

#### <i class="icon-pencil"></i> Ecrire sa présentation

L'écriture de la présentation se fera principalement à l'aide d'un langage dédié qui s'appuie sur ScalaTags.
> **Note :** Pour en savoir plus sur ScalaTags : http://www.lihaoyi.com/scalatags/

Voici ci-dessous un exemple qui permet d'avoir une simple diapositive avec écrit en gros au milieu "Titre".
```
slide(
	title1("Titre")
)
```
Ainsi, "slide" sera le mot-clé permettant de créer une diapositive, "title1" sera le mot-clé permettant de créer un gros titre.

L'écriture de n'importe quel élément se fera toujours suivant le même format : ```mot_clé(contenu)``` où "mot_clé" correspond à un élément de la liste des mots-clés retrouvables plus bas ou à un mot-clé d'une balise HTML (http://www.codeshttp.com/baliseh.htm) et "contenu" pourra correspondre à une chaîne de caractères (du texte entouré par des guillemets droits doubles), un élément ou une suite d’éléments comme ci-dessous.
```
unorderedList(
	listItem("premier élément de la liste"), 
	listItem("second élément de la liste")
) 
```
Deux éléments à la suite doivent être séparés par une virgule.

Vous pouvez ainsi créer votre présentation de différentes manières. Il vous est très bien possible d'utiliser les mots-clés des balises HTML ou ceux que l'on propose avec notre langage dédié.
Un exemple pour illustrer cela :
```
slide(
	title1("Titre de ma diapositive"),
	title3("Sous titre de ma diapositive"),
	textLine("Texte de ma diapositive")
)
```
Il est possible d'écrire ce même contenu de la manière suivante en s'appuyant sur le HTML :
```
section(
	h1("Titre de ma diapositive"),
	h3("Sous titre de ma diapositive"),
	p("Texte de ma diapositive")
)
```
S'il est possible de faire cela, c'est parce que ce framework utilise **Reveal.js** qui s'écrit, à la base, à l'aide du langage HTML.
> **Note :** Plus d'informations sur Reveal.js : http://lab.hakim.se/reveal-js/#/ et sa documentation très complète (dans le README) là : https://github.com/hakimel/reveal.js

#### <i class="icon-code"></i> Exemple
Pour finir, voici un bon exemple squelette fonctionnel d'une présentation avec ce framework :
```
      // Diapositive avec du simple texte
      slide(
        title2("Un titre"),
        textLine("Une ligne de texte")
      ),

      // Diapositive avec une liste à puces
      slide(
        title2("Liste"),
        unorderedList(
          listItem("Un élément de la liste"),
          listItem("Un élément de la liste"),
          listItem("Un élément de la liste"),
          listItem("Un élément de la liste")
        )
      ),

      // Diapositive avec une liste numérotée
      slide(
        title2("Liste ordonnée"),
        orderedList(
          listItem("Premier élément de la liste"),
          listItem("Second élément"),
          listItem("Troisième élément")
        )
      ),

      // Diapositive avec un tableau
      slide(
        title2("Table"),
        table(
          tableRow(
            tableHead("Nom"),
            tableHead("Valeur"),
            tableHead("Quantité")
          ),
          tableRow(
            tableBox("Pommes"),
            tableBox("1 €"),
            tableBox("7")
          ),
          tableRow(
            tableBox("Limonade"),
            tableBox("2 €"),
            tableBox("18")
          )
        )
      ),

      // Diapositive avec une image
      slide(
	      title1("Ma superbe image"),
	      img(
		      // Si l'image est introuvable, c'est ça qui sera affiché
		      attr("alt") := "l'image a disparu",
		      // Lien vers l'image
		      sourceAttr("http://site.com/image.png")
	      )
      ),

      // Diapositive avec une citation
      slide(
        title2("Faire une citation"),
        
        blockquote("\"For years there has been a theory that millions of monkeys typing at random on millions of of typewriters would reproduce the entire works of Shakespeare. The Internet has proven this theory to be untrue.\"")
      )
```


#### <i class="icon-list"></i> Liste des éléments de notre langage dédié

| Element                 | Contenu                        | Description              | Exemple
 :-----------------: | :----------------------------: | :------------------: | :------------------:
| slide | Un ou plusieurs autres éléments qui seront le contenu de la diapositive en question            | Création d'une **diapositive** | slide(title1("Titre de la diapositive"), textLine("Texte de la diapositive")) |
| title1           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères            | Création d'un **titre** (le plus gros) | title1("Mon gros titre")
| title2           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères | Création d'un **titre** (plus petit que title1) | title2("Mon titre")
| title3           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères | Création d'un **titre** (plus petit que title2) | title3("Mon titre moyen")
| title4           | Un ou plusieurs éléments, le plus souvent une chaîne de caractère | Création d'un **titre** (plus petit que title3) | title4("Mon petit titre")
| title5           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères | Création d'un **titre** (plus petit que title4) | title5("Mon plus petit titre")
| textLine           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères | Création d'une **ligne de texte** (provoque un retour à la ligne) | textLine("Une ligne de texte")
| textLineFragment           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères | Création d'un **fragment de ligne de texte** (provoque un retour à la ligne) mais qui apparaît après, lorsqu'on avance dans la présentation | textLineFragment("Ligne de texte qui apparaît après")
| textFragment           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères | Création d'un **fragment de texte** (sans retour à la ligne) mais qui apparaît après, lorsqu'on avance dans la présentation | textFragment("text qui apparaît plus tard")
| italic           | Chaîne de caractères | Met le texte en **italique** | italic("Texte en italique")
| bold           | Chaîne de caractères | Met le texte en **gras** | bold("Texte en gras")
| emptyLine           | Aucun | Pour **sauter une ligne** | emptyLine
| unorderedList           | Un ou plusieurs éléments, le plus souvent une suite d'éléments listItem | Création d'une **liste à puces** | unorderedList(listItem("1er élément"), listItem("2ème élément"))
| orderedList           | Un ou plusieurs éléments, le plus souvent une suite d'éléments listItem | Création d'une **liste numérotée** | orderedList(listItem("1er élément"), listItem("2ème élément"))
| listItem           | Un ou plusieurs éléments, le plus souvent une chaîne de caractères | Création d'un **élément d'une liste** | listItem("un élément de liste")


----------


Poser une question et utiliser les réponses
-------------

Intro... (partie non terminée)

#### <i class="icon-cog"></i> Configurer une nouvelle question

En trois étapes
: Création de la vue dans /examples/app/views/ sous la forme "QuestionXView.scala", X étant le numéro de la question  
: Importation et définition dans le contrôleur, c'est le fichier /examples/app/controllers/PresentationController.scala On importe la vue et on créé une fonction qui renvoie vers la vue
: Mise en place du routing dans le fichier /examples/conf/routes On associe le chemin de l'URL souhaitée à la fonction définie dans le contrôleur

#### <i class="icon-comment-empty"></i> Ajouter sa question à la présentation

slide(
        questionQRcode("Question1")
      ),

#### <i class="icon-reply"></i> Exploiter les réponses
A l'aide d'un graphe..
      slide(
        answersChart("Question1", "pie")
      ),

----------

(La suite ne sera pas dans le rapport - peut-être utile pour rédiger ce qui reste)

Markdown Extra
--------------------


### Tables

**Markdown Extra** has a special syntax for tables:

Item     | Value
-------- | ---
Computer | $1600
Phone    | $12
Pipe     | $1

You can specify column alignment with one or two colons:

| Item     | Value | Qty   |
| :------- | ----: | :---: |
| Computer | $1600 |  5    |
| Phone    | $12   |  12   |
| Pipe     | $1    |  234  |


### Fenced code blocks

GitHub's fenced code blocks are also supported with **Highlight.js** syntax highlighting:

```
// Foo
var bar = 0;
```



### Footnotes

You can create footnotes like this[^footnote].

  [^footnote]: Here is the *text* of the **footnote**.


### SmartyPants

SmartyPants converts ASCII punctuation characters into "smart" typographic punctuation HTML entities. For example:

|                  | ASCII                        | HTML              |
 ----------------- | ---------------------------- | ------------------
| Single backticks | `'Isn't this fun?'`            | 'Isn't this fun?' |
| Quotes           | `"Isn't this fun?"`            | "Isn't this fun?" |
| Dashes           | `-- is en-dash, --- is em-dash` | -- is en-dash, --- is em-dash |


### UML diagrams

You can also render sequence diagrams like this:

```sequence
Alice->Bob: Hello Bob, how are you?
Note right of Bob: Bob thinks
Bob-->Alice: I am good thanks!
```

And flow charts like this:

```flow
st=>start: Start
e=>end
op=>operation: My Operation
cond=>condition: Yes or No?

st->op->cond
cond(yes)->e
cond(no)->op
```

> **Note:** You can find more information:

> - about **Sequence diagrams** syntax [here][7],
> - about **Flow charts** syntax [here][8].


