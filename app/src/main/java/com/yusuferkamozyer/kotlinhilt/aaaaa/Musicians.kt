package com.yusuferkamozyer.kotlinhilt.aaaaa

import com.yusuferkamozyer.kotlinhilt.aaaaa.Band
import com.yusuferkamozyer.kotlinhilt.aaaaa.Instrument
import javax.inject.Inject

class Musicians
    @Inject constructor(band: Band, instrument: Instrument) {
    fun sing(){
        println("Singing")
    }
}