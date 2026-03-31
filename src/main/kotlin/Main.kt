package com.closedbrain

import org.freedesktop.dbus.annotations.DBusInterfaceName
import org.freedesktop.dbus.connections.impl.DBusConnectionBuilder
import org.freedesktop.dbus.interfaces.DBusInterface

@DBusInterfaceName("org.example.Calculator")
interface Calculator : DBusInterface {
    fun add(a: Int, b: Int): Int
    fun getGreeting(name: String): String
}

fun main() {
    val connection = DBusConnectionBuilder.forSessionBus().build()

    val calc = connection.getRemoteObject(
        "org.example.CalculatorService",
        "/org/example/Calculator",
        Calculator::class.java
    )

    val sum = calc.add(10, 32)
    println("Client: Received sum: $sum")

    val message = calc.getGreeting("Kotlin Developer")
    println("Client: Received message: $message")

    connection.close()
}