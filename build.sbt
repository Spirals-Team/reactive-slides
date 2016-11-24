import sbt.Project.projectToRef

lazy val scalaV = "2.11.8"

lazy val playserver = (project in file("play")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "com.lihaoyi" %% "scalatags" % "0.6.1",
    "org.webjars" % "jquery" % "3.0.0"
  )
).enablePlugins(PlayScala)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(scalaVersion := scalaV).
  jsConfigure(_ enablePlugins ScalaJSPlay)
//  jsSettings(sourceMapsBase := baseDirectory.value / "..")

// loads the Play project at sbt startup
onLoad in Global := (Command.process("project playserver", _: State)) compose (onLoad in Global).value

// for Eclipse users
EclipseKeys.skipParents in ThisBuild := false


fork in run := true
