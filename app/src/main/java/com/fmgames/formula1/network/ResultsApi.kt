package com.fmgames.formula1.network

import com.fmgames.formula1.model.Results
import com.fmgames.formula1.model.ResultsModel
import retrofit2.Call
import retrofit2.http.*

interface ResultsApi {
    @GET("/rankings/drivers")
    fun getResults(@HeaderMap headers: Map<String, String>, @Query("season") year: Int) : Call<Results?>
}