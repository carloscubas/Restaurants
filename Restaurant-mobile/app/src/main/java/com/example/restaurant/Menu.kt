package com.example.restaurant

data class Menu(
    var id : Int,
    var name : String,
    var value : Double
) {
    override fun toString(): String {
        return "$name : preço = $value"
    }
}
