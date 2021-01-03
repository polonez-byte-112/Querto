package com.querto.models.Cart

import com.querto.models.Dodatek.Dodatek
import com.querto.models.Sos.Sos

data class CartItem(
        val i_id: String,
        val i_name : String,
        val i_size: String,
        var i_amount: String,
        var i_price: String,
        var i_sosy:ArrayList<Sos>,
        var i_dodatki:ArrayList<Dodatek>,

        )