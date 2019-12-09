plugins {
    kotlin("jvm") version "1.3.61"
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

fun resolveCalculator(content: String): String {
    val calculator = file("$rootDir/src/main/kotlin/intcode/Calculator.kt").readText()
    return content.replace("import intcode.Calculator", calculator)
}

fun removeImportsAndPackage(content: String): String =
    resolveCalculator(content).lines().filter { !it.contains("import") && !it.contains("package") }
        .joinToString("\n") {
            it.replace("<", "&lt;").replace(">", "&gt;")
        }

tasks {
    val generateHTML by creating {
        val items = mutableListOf<String>()
        file("$rootDir/src/main/kotlin/advent").list()!!.filter { it != "Advent.kt" }.map {
            copy {
                val name = it.removeSuffix(".kt")
                items.add(name)
                val content = file("$rootDir/src/main/kotlin/advent/$name.kt").readText()
                val testContent = file("$rootDir/src/test/kotlin/advent/${name}Test.kt").readText()
                from("$rootDir/src/main/resources/template.html") {
                    expand(
                        "CLASS" to removeImportsAndPackage(content),
                        "TESTCLASS" to removeImportsAndPackage(testContent)
                    )
                }
                into("docs")
                rename { "${name.toLowerCase()}.html" }
            }
        }
        copy {
            from("$rootDir/src/main/resources/index.html") {
                expand(
                    "MENU" to items.sorted().joinToString { "\"${it}\"" }
                )
            }
            into("docs")
        }
    }


    processResources {
        dependsOn(generateHTML)
    }
}

application {
    mainClassName = "advent.AdventKt"
}
