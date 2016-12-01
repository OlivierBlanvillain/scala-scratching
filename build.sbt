name := "hello"

// https://github.com/denisftw/advanced-scala-code/blob/master/build.sbt#L60

version := "1.0"

scalaVersion := "2.11.8"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum" % "0.7.0",
  "org.typelevel" %% "cats" % "0.7.2"
)

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.7"

libraryDependencies +=  "org.scalaz" %% "scalaz-concurrent" % "7.2.7"


