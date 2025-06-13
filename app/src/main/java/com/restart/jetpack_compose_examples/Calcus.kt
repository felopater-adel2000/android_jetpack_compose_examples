package com.restart.jetpack_compose_examples

class Calcus {

    suspend operator fun invoke(x: Int, y: Int): Int {
        return x + y
    }
}