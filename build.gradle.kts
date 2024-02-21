plugins {
  id("java")
  id("idea")
}

dependencies {
}

val feature = project.ext["feature"]

val appArgs = "${project.ext["app-args"]}".split(" ").filter{x -> x != ""}

task("runApp", JavaExec::class) {
    mainClass = "${feature}.App"
    classpath = sourceSets["main"].runtimeClasspath
    jvmArgs = listOf(
            "-Xms32m", "-Xmx32m","-XX:+UseZGC","-XX:+ZGenerational"
    )
    args = appArgs
}
