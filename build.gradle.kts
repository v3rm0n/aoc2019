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

fun removeImportsAndPackage(content: String): String =
    content.lines().filter { !it.contains("import") && !it.contains("package") }.joinToString("\n") {
        it.replace("<", "&lt;").replace(">", "&gt;")
    }

tasks {
    val generateHTML by creating {
        file("$rootDir/src/main/kotlin/advent").list()!!.filter { it != "Advent.kt" }.map {
            copy {
                val name = it.removeSuffix(".kt")
                val content = file("$rootDir/src/main/kotlin/advent/$name.kt").readText()
                val testContent = file("$rootDir/src/test/kotlin/advent/${name}Test.kt").readText()
                from("$rootDir/src/main/resources/template.html") {
                    expand(
                        "CLASS" to removeImportsAndPackage(content),
                        "TESTCLASS" to removeImportsAndPackage(testContent)
                    )
                }
                into("$buildDir/html")
                rename { "$name.html" }
            }
        }
    }


    processResources {
        dependsOn(generateHTML)
    }
}

application {
    mainClassName = "advent.AdventKt"
}
