name := "WebApp"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "net.arnx" % "jsonic" % "1.3.10",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "junit" % "junit" % "4.12" % "test",
  // sbt で JUnit を使うために必要
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

enablePlugins(JettyPlugin)
