Manuel utilisateur
===================

L'objectif de ce manuel est d'expliquer comment mettre en place une présentation interactive à l'aide de ce framework.

----------

Sommaire
-------------

   * [Manuel utilisateur](#manuel-utilisateur)  
      * [Installation et configuration](#installation-et-configuration)  
            * [<i></i> 1. Installation de sbt](#1-installation-de-sbt)  
            * [<i></i> 2. Configuration de la base de données](#2-configuration-de-la-base-de-données)  
            * [<i></i> 3. Compilation et exécution de l'application](#3-compilation-et-exécution-de-lapplication)
      * [Création de la présentation](#création-de-la-présentation)  
            * [<i></i> Fichier de la présentation](#-fichier-de-la-présentation)  
            * [<i></i> Paramètres](#-paramètres)  
            * [<i></i> Ecrire sa présentation](#-ecrire-sa-présentation)  
            * [<i></i> Exemple](#-exemple)  
            * [<i></i> Liste des éléments de notre langage dédié](#-liste-des-éléments-de-notre-langage-dédié)  
      * [Poser une question et utiliser les réponses](#poser-une-question-et-utiliser-les-réponses)  
            * [<i></i> Configurer une nouvelle question](#-configurer-une-nouvelle-question)  
            * [<i></i> Ajouter sa question à la présentation](#-ajouter-sa-question-à-la-présentation)  
            * [<i></i> Visualiser les réponses](#-visualiser-les-réponses)  

Installation et configuration
-------------

Suivez les instructions dans le fichier README.md pour bien installer et configurer le framework.  
Ci-dessous, vous retrouverez les mêmes instructions en français.

Pour commencer, cloner le projet.

Pour compiler le projet et exécuter la présentation exemple, suivez ces étapes :

#### 1. Installation de sbt

Assurez-vous d'avoir [sbt](http://www.scala-sbt.org/) installé. C'est la plateforme sur laquelle les outils de compilation tourne.  

#### 2. Configuration de la base de données

Pour la présentation exemple, nous avons utilisé un serveur local MySQL. Par défaut, le port de la base de données est **3306**.

Pour la personnaliser, vous devez modifier les paramètres spécifiés dans le fichier **/examples/conf/application.conf** en particulier les lignes suivantes (~ lignes 45-50) :


```
db.default.driver=com.mysql.jdbc.Driver
db.default.url="jdbc:mysql://localhost:3306/database_name"
db.default.username=database_username
db.default.password="database_password"
```

Il faut remplacer database_name, database_username et database_password par leurs valeurs correspondantes.

Une fois la base de données créée, il est nécessaire d'importer le modèle de données qui se trouve dans le fichier **database.sql** dans **/examples/public/db/**.  
Plus d'informations pour faire cela : https://dev.mysql.com/doc/refman/5.7/en/mysql-batch-commands.html

#### 3. Compilation et exécution de l'application

A partir du dossier du projet, faites les commandes suivantes :

a - Supprimer tous les fichiers générés et compiler les fichiers sources :
```
sbt clean compile 
```
 
 b - Compiler et exécuter tous les tests :
```
sbt test  
```
 
 c - Créer un fichier jar contenant les fichiers et les classes compilées :
```
sbt package 
```
  
  

> **Note :** Si vous changez le fichier build.sbt, vous devrez recharger le projet. Vous pouvez faire cela avec la commande `sbt reload`

Pour lancer la présentation exemple, faites la commande suivante : 
```
sbt run
```
  
  Cela lancera la classe principale du projet dans la même machine virtuelle que sbt.
  
Il vous est possible de voir la présentation à l'adresse [http://localhost:9000](http://localhost:9000). Vous pouvez changer le code qui se trouve dans **/examples/app/** et le navigateur devrait se rafraîchir automatiquement quand vous sauvegardez les fichers.


----------


Création de la présentation
-------------------

Dans cette partie, il sera expliqué comment créer une présentation avec ce framework.

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

      // Diapositive avec une image et un lien
      slide(
	      title1("Ma superbe image"),
	      img(
		      // Si l'image est introuvable, c'est ça qui sera affiché
		      altAttr("l'image a disparu"),
		      // Lien vers l'image
		      sourceAttr("http://site.com/image.png")
	      ),
	      alink(
		      // URL vers lequel le lien pointe
		      linkURL("https://www.google.fr/"),
		      "Lien vers le moteur de recherche Google"
	      )
      ),

      // Diapositive avec une citation
      slide(
        title2("Faire une citation"),
        
        blockquote("\"For years there has been a theory that millions of monkeys typing at random on millions of of typewriters would reproduce the entire works of Shakespeare. The Internet has proven this theory to be untrue.\"")
      )
```


#### <i class="icon-list"></i> Liste des éléments de notre langage dédié

<table>

<tr>
		<td rowspan="3">slide</td>
		<td>Création d'une <b>diapositive</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs autres éléments qui seront le contenu de la diapositive en question</td>
	</tr>
	
	<tr>
		<td>Exemple : slide(title1("Titre de la diapositive"), textLine("Texte de la diapositive"))</td>
	</tr>
	<tr><td></td></tr>

<tr>
		<td rowspan="3">title1</td>
		<td>Création d'un <b>titre</b> (le plus gros)</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : title1("Mon gros titre")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">title2</td>
		<td>Création d'un <b>titre</b> (plus petit que title1)</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : title2("Mon titre")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">title3</td>
		<td>Création d'un <b>titre</b> (plus petit que title2)</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : title3("Mon titre moyen")</td>
	</tr>

	<tr><td></td></tr>

<tr>
		<td rowspan="3">title4</td>
		<td>Création d'un <b>titre</b> (plus petit que title3)</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : title4("Mon petit titre")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">title5</td>
		<td>Création d'un <b>titre</b> (plus petit que title4)</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : title5("Mon plus petit titre")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">textLine</td>
		<td>Création d'une <b>ligne de texte</b> (déclenche un retour à la ligne)</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : textLine("Une ligne de texte")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">textLineFragment</td>
		<td>Création d'un <b>fragment de ligne de texte</b> (déclenche un retour à la ligne) mais qui apparaît après, lorsqu'on avance dans la présentation</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : textLineFragment("Ligne de texte qui apparaît après")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">textFragment</td>
		<td>Création d'un <b>fragment de texte</b> (sans retour à la ligne) mais qui apparaît après, lorsqu'on avance dans la présentation</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : textFragment("texte qui apparaît plus tard")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">italic</td>
		<td>Met le texte en <b>italique</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : italic("Texte en italique")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">bold</td>
		<td>Met le texte en <b>gras</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : bold("Texte en gras")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">emptyLine</td>
		<td>Pour <b>sauter une ligne</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Aucun</td>
	</tr>
	
	<tr>
		<td>Exemple : emptyLine</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">unorderedList</td>
		<td>Création d'une <b>liste à puces</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une suite d'éléments listItem</td>
	</tr>
	
	<tr>
		<td>Exemple : unorderedList(listItem("1er élément"), listItem("2ème élément"))</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">orderedList</td>
		<td>Création d'une <b>liste numérotée</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une suite d'éléments listItem</td>
	</tr>
	
	<tr>
		<td>Exemple : orderedList(listItem("1er élément"), listItem("2ème élément"))</td>
	</tr>

	<tr><td></td></tr>

<tr>
		<td rowspan="3">listItem</td>
		<td>Création d'un <b>élément d'une liste</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : listItem("un élément de liste")</td>
	</tr>
	
	<tr><td></td></tr>

<tr>
		<td rowspan="3">alink</td>
		<td>Création d'un <b>lien</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, avec normalement un élément linkURL</td>
	</tr>
	
	<tr>
		<td>Exemple : alink(linkURL("https://www.google.fr/"), "Lien externe")</td>
	</tr>
	
	<tr><td></td></tr>
	
<tr>
		<td rowspan="3">tableRow</td>
		<td>Création d'une <b>ligne de tableau</b>, doit être placé dans un élément table</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, contient normalement un ou des éléments tableBox</td>
	</tr>
	
	<tr>
		<td>Exemple : tableRow(tableBox("Un élement de ma ligne"), tableBox("Un second élément de ma ligne"))</td>

	<tr><td></td></tr>


<tr>
		<td rowspan="3">tableHead</td>
		<td>Création d'un <b>élément d'entête de tableau</b>, doit être placé dans un élément tableRow</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : tableHead("Entête de tableau")</td>
	</tr>
	
	<tr><td></td></tr>
	
<tr>
		<td rowspan="3">tableBox</td>
		<td>Création d'un <b>élément de tableau</b>, doit être placé dans un élément tableRow</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Un ou plusieurs éléments, le plus souvent une chaîne de caractères</td>
	</tr>
	
	<tr>
		<td>Exemple : tableBox("Le contenu d'une case de tableau")</td>
	</tr>


	<tr><td></td></tr>

<tr>
		<td rowspan="3">linkURL</td>
		<td>Création d'un <b>attribut de lien hypertexte</b>, principalement utilisé dans un élément alink</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Chaîne de caractères, il s'agit de l'URL vers lequel le lien pointe</td>
	</tr>
	
	<tr>
		<td>Exemple : linkURL("https://www.google.fr/")</td>
	</tr>


	<tr><td></td></tr>

		<tr>
		<td rowspan="3">sourceAttr</td>
		<td>Création d'un <b>attribut source</b>, doit être placé dans un élément, par exemple, un élement img</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Chaîne de caractères, il s'agit du chemin vers la source ciblée</td>
	</tr>
	
	<tr>
		<td>Exemple : img(sourceAttr("https://s3.amazonaws.com/hakim-static/reveal-js/arrow.png"), altAttr("Up arrow"))</td>
	</tr>

	<tr><td></td></tr>
	
	<tr>
		<td rowspan="3">altAttr</td>
		<td>Création d'un <b>attribut alt</b>, peut être placé uniquement dans un élément img</td>
		
	</tr>
	<tr>
		
		<td>Contenu : Chaîne de caractères, il s'agit du texte à afficher pour un élément img si l'image ne peut pas être affichée</td>
	</tr>
	
	<tr>
		<td>Exemple : img(sourceAttr("https://s3.amazonaws.com/hakim-static/reveal-js/arrow.png"), altAttr("Up arrow"))
</td>
	</tr>


	<tr><td></td></tr>

	<tr>
		<td rowspan="3">codeQuote</td>
		<td>Création d'un <b>bloc pour afficher du code</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Chaîne de caractères, il s'agit du code à afficher</td>
	</tr>
	
	<tr>
		<td>Exemple : codeQuote("function linkify( selector ) {\n\tif( supports3DTransforms ) {\n\t\tvar nodes = document.querySelectorAll( selector );\n\t}\n}")</td>
	</tr>

	<tr><td></td></tr>

	<tr>
		<td rowspan="3">generateQuestionQRCode</td>
		<td>Génère et affiche un <b>QR code qui donne accès à un questionnaire</b></td>
		
	</tr>
	<tr>
		
		<td>Contenu : Chaîne de caractères correspondant au numéro/intitulé de la question sous la forme "Question1", "Question2"...</td>
	</tr>
	
	<tr>
		<td>Exemple : slide(generateQuestionQRCode("Question1"))</td>
	</tr>


	<tr><td></td></tr>	

	<tr>
		<td rowspan="3">displayGraph</td>
		
		<td> Affiche un <b>graphe affichant les réponses d'une question donnée</b></td>
	</tr>
	<tr>
	
		<td>Contenu : Chaîne de caractères correspondant au numéro/intitulé de la question sous la forme "Question1", "Question2"... et une chaîne de caractère correspondant au type de graphe souhaité, il s'agit des types proposés par Chart.js (ex : "bar", "line", "pie")</td>
	</tr>
	
	<tr>
		<td>Exemple : slide(generateQuestionQRCode("Question1"))</td>
	</tr>
	
</table>



En plus de tous ces élements, il est possible d'utiliser n'importe quel autre élement HTML. Vous trouverez une liste très bien documentée de ces élements sur ces pages :   
- http://www.w3schools.com/tags/ 
- https://developer.mozilla.org/fr/docs/Web/HTML/Element

Bien évidemment, on peut les écrire en utilisant la syntaxe de notre langage dédié.  
Par exemple, si on veut écrire un élément <button>, il suffira d'écrire :
```
button("Texte du bouton")
```
Au lieu de :
```
<button>Texte du bouton</button>
```
De plus, pour ajouter un attribut class à un élement, il faudra écrire :
```
attr("class"):="myClass"
```
Par exemple, si on veut ajouter cet attribut à notre élément button précédent :
```
button(attr("class"):="myClass", "Texte du bouton")
```

----------


Poser une question et utiliser les réponses
-------------

Nous allons maintenant voir comment mettre en place le système de questions-réponses entre le présentateur et l'auditoire. Cela va se faire en trois temps :  
- Configuration de la question  
- Ajout de la question à la présentation  
- Visualisation des réponses  

La configuration de la question est l'étape la plus fastidieuse. Une fois faite, le reste est très simple.

#### <i class="icon-cog"></i> Configurer une nouvelle question

Pour configurer une nouvelle question, il faudra procéder à quatres étapes.

**1ère étape : Création de la vue de la question**

Tout d'abord, il faut créer la vue de la question.  
C'est à dire, créer un fichier dans **/examples/app/views/** dont le nom sera sous la forme "QuestionXView.scala", X étant le numéro de la question.

Le contenu de ce fichier doit ressembler à ça : 
```
package views

object Question1View {

  import dsl.Dsl._
  import scalatags.Text.all._

  def apply(title: String, theme: String) = {
    presentation(title, theme,
      slide(
        survey("Question1", "Are you working ?","Yes", "No")
      )
    )
  }
}
```
**Important :**
Il faut s'assurer à la ligne 3 que le nom de l'objet correspond bien au nom du fichier (sans l'extension), dans notre exemple, le fichier s’appellerait donc "Question1View.scala".

Ensuite, la seule chose à modifier est le contenu de la méthode survey(...).  
La première chaîne de caractères correspond à l'intitulé de la question. L'idéal est de le faire correspondre au nom du fichier ou de l'objet sans le "View" à la fin, c'est pourquoi dans notre exmple, on a "Question1". 

Le reste correspond à la question et aux réponses, l'ordre est important, la seconde chaîne de caractères de la méthode survey(...) correspond à l'intitulé de la question, toutes les autres chaînes qui suivent sont les différentes réponses possibles pour cette question.

> **Note :** Pour le moment, les questions doivent avoir exactement 2 réponses. C'est une contrainte à régler.

Sachez ainsi que la méthode survey(...) permet la création d'une diapositive contenant un questionnaire, l'auditoire accédera à celle-ci sur son périphérique après avoir scanné le QR code correspondant.

Voilà pour cette première étape de la configuration d'une nouvelle question.

**2ème étape : Importation et définition dans le contrôleur**

A cette étape, on va modifier le contrôleur. C'est le fichier **/examples/app/controllers/PresentationController.scala**  

*En premier lieu :*  
On importe la vue, pour se faire, on modifie la ligne commençant par **import views.{** qui doit se trouver au début du fichier du contrôleur. 
On va ajouter le nom du fichier de la vue créé à l'étape précédente. Par exemple, si la ligne ressemble à :
```
import views.{AnswerView, PresentationView, MainView}
```
On la modifiera pour qu'elle ressemble à :
```
import views.{AnswerView, PresentationView, MainView, Question1View}
```
Dans la cas où on ajoute Question1View.

*En second lieu :*  
On définit cette vue dans le contrôleur, il s'agit d'ajouter dans "class PresentationController ... {" :
```
def showQuestion1 = {
	ok(Question1View(presentationTitle, theme))
}
```
Encore une fois, dans cette exemple, c'est dans le cas où l'on ajoute Question1View. Idéalement, il faut que l'intitulé de la définition soit numéroté comme la vue, ici par exemple, on a "showQuestion1".

**3ème étape : Mise en place du routing**

On met en place le routing dans le fichier **/examples/conf/routes**  
On associe la chemin de l'URL souhaitée à la fonction définie dans le contrôleur. C'est à dire, on ajouter la ligne suivante au fichier de routing :
```
GET	/Question1	@controllers.PresentationController.showQuestion1
```
Une nouvelle fois, on modifie selon le numéro de la question. Pour les autres questions, on aura /Question2 et showQuestion2, /Question3 et showQuestion3, etc.



**4ème étape : Création du fichier pour les réponses**

Il faut tout simplement créer un fichier texte vide dans **/examples/public/charts/** nommé selon l'intitulé de la question. Par exemple **Question1.txt**  
C'est dans ce fichier que seront enregistrées les réponses obtenues à l'issue du questionnaire afin de les afficher à l'aide d'un graphe.

On a terminé la configuration d'une question, il ne reste plus qu'à ajouter la diapositive avec le QR code et celle pour afficher le graphique des réponses si l'on souhaite montrer les résultats.

#### <i class="icon-comment-empty"></i> Ajouter sa question à la présentation

Pour ajouter sa question à la présentation, il faut créer une diapositive avec un élément generateQuestionQRcode.  
On aura alors une diapositive avec un QR code qui peut être scanné par l'auditoire. Voici un exemple de code pour cela :
```
slide(
	generateQuestionQRcode("Question1")
)
```
La chaîne de caractères dans l'élément generateQuestionQRcode correspond à l'intitulé de la question à laquelle l'auditoire va répondre.

#### <i class="icon-reply"></i> Visualiser les réponses

Si vous souhaitez montrer les réponses obtenues à l'auditoire, cela est possible à travers plusieurs types de graphes.  
Pour cela, on créé une diapositive avec un élément displayChart. Un exemple de code :
```
slide(
	displayChart("Question1", "pie")
)
```
On a deux chaînes de caractères dans l'élément displayChart.  
Le premier correspond à l'intitulé de la question dont on veut afficher les réponses.  
Le second correspond au type de graphe que l'on souhaite utiliser.  
On peut avoir trois types de graphes : "pie", "bar" et "line".  
> **Note :** Pour le moment, seul le type "pie" fonctionne correctement.

Voici un aperçu de chacun de ces graphes :

Un graphe de type "pie" :

![Image of pie chart](http://i.imgur.com/AtuITJ8.png)

Un graphe de type "bar" : 
A venir...

Un graphe de type "line" :
A venir...

----------




