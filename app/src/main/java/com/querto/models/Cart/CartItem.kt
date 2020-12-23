package com.querto.models.Cart

data class CartItem(
        val i_id: String,
        val i_name : String,
        val i_size: String,
        var i_amount: String,
        val i_summary: String,
)