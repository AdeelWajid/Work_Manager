package com.example.workmanager.data.worker.factory

sealed class  Test{
    class A:Test()
    class B:Test()
}

fun main() {
    Test.A()
}