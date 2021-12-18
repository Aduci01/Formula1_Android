package com.fmgames.formula1.network

import com.fmgames.formula1.model.Circuits
import com.fmgames.formula1.model.Results
import retrofit2.Call
import retrofit2.http.*

interface CircuitsApi {
    @GET("/circuits")
    fun getResults(@HeaderMap headers: Map<String, String>) : Call<Circuits?>
}