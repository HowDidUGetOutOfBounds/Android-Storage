package com.example.storage.utill

import com.example.storage.R
import com.example.storage.entities.Tank

fun getImageResourceFromNation(tank: Tank): Int {
    return when (tank.tankNation) {
        "СССР" -> R.drawable.ussr
        "Германия" -> R.drawable.germ
        "Великобритания" -> R.drawable.brit
        "США" -> R.drawable.usa
        else -> R.drawable.germ
    }
}