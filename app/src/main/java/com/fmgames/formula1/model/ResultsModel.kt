package com.fmgames.formula1.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path

data class Results(
    val response: List<ResultsModel>
)

data class ResultsModel(
    val position: Int,
    val points: Float,
    val wins: Int,

    val driver: DriverModel,
    val team: TeamModel,
)

data class DriverModel (
    val id: Int,
    val name: String,
    val image: String,
)

data class TeamModel (
    val id: Int,
    val name: String,
    val logo: String,
)
