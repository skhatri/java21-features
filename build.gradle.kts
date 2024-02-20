plugins {
  id("java")
  id("idea")
}

dependencies {
}

val feature = project.ext["feature"]

task("runApp", JavaExec::class) {
    mainClass = "${feature}.App"
    classpath = sourceSets["main"].runtimeClasspath
    jvmArgs = listOf(
            "-Xms512m", "-Xmx512m"
    )
}

