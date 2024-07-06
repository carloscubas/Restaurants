package com.example.restaurant

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoint {

    @GET("/menu")
    fun getItems() : Call<List<Menu>>

    @POST("/order")
    fun makeOrder(@Body orders: ArrayList<Long>) : Call<Int>
}
