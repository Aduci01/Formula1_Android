package com.fmgames.formula1.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path

data class Circuits(
    val response: List<CircuitsModel>
)

data class CircuitsModel(
    val name: String,
    val image: String,
)
