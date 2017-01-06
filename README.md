# reactive-slides [![Build Status](https://travis-ci.org/rbadr/reactive-slides.svg?branch=master)](https://travis-ci.org/rbadr/reactive-slides) [![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)
A web framework for building interactive presentations.

This framework allow you to create several questions through your presentation shared by QR code so that audience can answer with their smartphones, tablets...  

That give you a new way to make your presentation interactive and attractive.

### To run the server :
- [Play with activator](https://www.playframework.com/download) is required, currently working with Play 2.5.4 and activator 1.3.12 that requires JDK 1.8
- Setup the database (see below)
- At the project root, open a terminal and tap '$ activator start'
- Go to localhost:9000 in a browser

#### To setup the database, you need a MySQL server running with these parameters :
- Port : 3306 (default)
- DB_name : playdb
- username : root
- password : password
- Use the database.sql file to import the database model


#### Useful informations :
- The HTML presentation framework used is [reveal.js](http://lab.hakim.se/reveal-js/#/)
- Also using a library for Scala named [scalatags](http://www.lihaoyi.com/scalatags/) (version 0.6.1)
- Don't work with the activator ui
- The command '$ activator clean stage' can be useful to recompile


--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Un framework web pour créer des présentations intéractives.

Ce framework vous permet de poser des questions tout au long de votre présentation partagées via un QR code afin que l'audience puisse y répondre à l'aide de leur smartphone, tablette...  

Cela vous offre une nouvelle manière de rendre votre présentation intéractive et attractive.

### Pour lancer le serveur :
- [Play with activator](https://www.playframework.com/download) est nécessaire, fonctionne actuellement avec Play 2.5.4 et activator 1.3.12 qui requiert le JDK 1.8
- Mettre en place la Base de données (voir ci-dessous)
- A la racine du projet, ouvrir un terminal et taper '$ activator start'
- Aller sur localhost:9000 via un navigateur

#### Pour mettre en place la base de données, vous avez besoin d'un serveur MySQL qui tourne avec ces paramètres :
- Port : 3306 (default)
- nom de la base de données : playdb
- nom d'utilisateur : root
- mot de passe : password
- Utilisez le fichier database.sql pour importer le modèle de la base de données


#### Informations utiles :
- Le framework de présentation HTML utilisé est [reveal.js](http://lab.hakim.se/reveal-js/#/)
- Utilise aussi une librairie pour Scala nommée [scalatags](http://www.lihaoyi.com/scalatags/) (version 0.6.1)
- Ne fonctionne pas avec activator ui
- La commande '$ activator clean stage' peut-être utile pour recompiler
