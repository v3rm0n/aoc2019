package advent

import java.io.File
import java.util.ServiceLoader

interface Advent {
    fun firstTask(input: List<String>): Any
    fun secondTask(input: List<String>): Any
}

fun main() {
    println("******Advent of code 2019******")
    ServiceLoader.load(Advent::class.java).forEach {
        val advent = it.javaClass.simpleName.toLowerCase()
        println(advent)
        val input = readFile("/$advent")
        println("First task: ${it.firstTask(input)}")
        println("Second task: ${it.secondTask(input)}")
        println("*******************************")
    }
    println("************Goodbye!***********")
}

fun debugPrint(message: Any?) = System.getProperty("debug")?.let { if (it.toBoolean()) println(message) }

fun readFile(filename: String) = File(Advent::class.java.getResource(filename).toURI()).readLines()
