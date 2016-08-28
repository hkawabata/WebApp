name := "WebApp"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "net.arnx" % "jsonic" % "1.3.10",
  // https://mvnrepository.com/artifact/junit/junit
  "junit" % "junit" % "4.12" % "test"
)

enablePlugins(JettyPlugin)
