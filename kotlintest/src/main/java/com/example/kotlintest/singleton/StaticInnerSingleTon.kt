package com.example.kotlintest.singleton

class StaticInnerSingleton private constructor(){

    private object SingleHolder {
        val staticSingleton = StaticInnerSingleton()
    }

    companion object {
//        fun getInstance(): StaticInnerSingleton {
//            return SingleHolder.staticSingleton
//        }

        val instance = SingleHolder.staticSingleton
    }

    fun show(){
        val name = "hello"
        print("$name hello")
    }
}
