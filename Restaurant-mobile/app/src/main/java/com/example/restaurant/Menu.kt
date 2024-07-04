package com.example.restaurant

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("value")
    var value : Double
) {
    override fun toString(): String {
        return "$name : preço = $value"
    }
}
