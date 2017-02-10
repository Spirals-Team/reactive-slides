# reactive-slides [![Build Status](https://travis-ci.org/rbadr/reactive-slides.svg?branch=master)](https://travis-ci.org/rbadr/reactive-slides) [![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)
A web framework for building interactive presentations.

This framework allow you to create several questions through your presentation shared by QR code so that audience can answer with their smartphones, tablets...  

That give you a new way to make your presentation interactive and attractive.

## Get Started

To start, clone the project.

To build the project and run the presentation example , follow these steps :

1. Ensure that [sbt](http://www.scala-sbt.org/) is installed. This provides the platform on which the build tooling runs. 
2. Setup a database :

For the example presentation, we used a local MySQL server. The databasePort by default is **3306** .

To customize your configuration you need to browse the `application.conf` file and save your personal database parameters:

- db.default.url : "jdbc:mysql://localhost:**yourDatabasePort**/**yourDatabaseName**"
- db.default.username : **yourDatabaseUserName**
- db.default.password : "**yourDatabasePassword**"

> **Note:** Use the database.sql file to import the database model. You can find it in `examples/public/db`.

3. From the project folder, execute the following commands:  

a - Delete all generated files and Compile the main sources :
 ```
sbt clean compile 
 ```
 
 b - Compile and run all tests :
 ```
sbt test  
 ```
 
 c - Create a jar file containing the files and the compiled classes :
  ```
sbt package 
 ```
  
  

> **Note:** If you change the build definition, you need to reload the project. You can do this by executing `sbt reload` which will reload the new build definition.

4. To run the presentation example, execute the following command:
 ```
sbt run
 ```
  
  It will run the main class of the project in the same virtual machine as sbt.
  
5. Browse to [http://localhost:9000](http://localhost:9000) to see the presentation. You can make changes in the code found under `examples/app` and the browser should auto-refresh itself as you save files.

## Create your own Interactive Presentation

Before creating your own Interactive Presentation, take your time to read the   
User Manual available in the root of the project. It will guide you step by step. ( Available only in French)

#### Useful informations :
- The HTML presentation framework used is [reveal.js](http://lab.hakim.se/reveal-js/#/)
- Also using a Scala library named [scalatags](http://www.lihaoyi.com/scalatags/) (version 0.6.1)
- For rendering the audience's response, [Chart.js](http://www.chartjs.org/) is used.