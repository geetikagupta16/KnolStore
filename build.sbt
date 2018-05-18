lazy val akkaHttpVersion = "10.1.1"
lazy val akkaVersion    = "2.5.12"

val slickVersion = "3.2.1"
val googleInjectVersion = "3.0"
val akkaHttpCoreVersion = "0.2.2"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.knoldus",
      scalaVersion    := "2.12.5"
    )),
    name := "CanteenVala",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
      "com.typesafe.slick"%%  "slick"               % slickVersion,
      "mysql"             %   "mysql-connector-java"% "latest.release",
      "ch.megard"          %% "akka-http-cors"     % akkaHttpCoreVersion,
      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test,
      "com.google.inject" % "guice"                 % googleInjectVersion,
"ch.megard"                    %%  "akka-http-cors"                 % "0.2.2"
    )
  )
