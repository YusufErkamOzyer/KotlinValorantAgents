package com.yusuferkamozyer.kotlinhilt

import kotlin.random.Random
import kotlin.random.nextInt

fun main(){
    val arrayList= arrayListOf<Int>()

    while (true){
        if (arrayList.size==4){
            break
        }
        var value= Random.nextInt(0,4)
        if (!(value in arrayList)){
            arrayList.add(value)
        }
    }
    println(arrayList)

}