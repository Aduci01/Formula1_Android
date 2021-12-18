package com.fmgames.formula1.repository

import com.fmgames.formula1.model.Circuits
import com.fmgames.formula1.model.NewsFeed

interface CircuitsRepository {
    fun getCircuits() : Circuits?
}