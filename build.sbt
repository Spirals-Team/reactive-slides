import sbt.Project.projectToRef

lazy val scalaV = "2.11.8"

lazy val examples = (project in file("examples")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "com.lihaoyi" %% "scalatags" % "0.6.1"
  )
).enablePlugins(PlayScala)

lazy val framework = (project in file("framework")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "com.lihaoyi" %% "scalatags" % "0.6.1"
  )
).enablePlugins(PlayScala)

// loads the Play project at sbt startup
onLoad in Global := (Command.process("project examples", _: State)) compose (onLoad in Global).value

// for Eclipse users
EclipseKeys.skipParents in ThisBuild := false


fork in run := true
