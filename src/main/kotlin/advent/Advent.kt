package advent

import java.io.File
import java.util.ServiceLoader

interface Advent {
    fun firstTask(): String
    fun secondTask(): String
}

fun main() {
    println("******Advent of code 2019******")
    ServiceLoader.load(Advent::class.java).forEach {
        println(it.javaClass.simpleName)
        println("First task: ${it.firstTask()}")
        println("Second task: ${it.secondTask()}")
        println("*******************************")
    }
    println("************Goodbye!***********")
}

fun readFile(filename: String) = File(Advent::class.java.getResource(filename).toURI()).readLines()
