package com.fmgames.formula1.repository

import com.fmgames.formula1.model.Results

interface ResultsRepository {
    fun getResults() : Results?
}