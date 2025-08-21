package com.restart.jetpack_compose_examples

class Calculator {

    fun sum(x: Int, y: Int): Int {
        val s = Summation(x, y)
        return s.evaluateSummation()
    }

}


class Summation(private val x: Int, private val y: Int) {
    fun evaluateSummation(): Int {
        return x + y
    }
}