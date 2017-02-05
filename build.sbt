scalaVersion in ThisBuild := "2.11.8"

lazy val appVersion = "1.0"

def project(id: String, versionV: String) = Project(id = id, base = file(id))
  .settings(
    scalacOptions ++= Seq(
      "-deprecation",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-unchecked",
      "-Xlint",
      "-Xlint:missing-interpolator",
      "-Yno-adapted-args",
      "-Ywarn-unused",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Xfuture",
      "-Ywarn-unused-import"
    )
  )

lazy val examples = project("examples", appVersion).enablePlugins(PlayScala).dependsOn(framework)

lazy val framework = project("framework", appVersion)
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "scalatags" % "0.6.1",
      jdbc,
      "mysql" % "mysql-connector-java" % "5.1.36"
    )
  )
