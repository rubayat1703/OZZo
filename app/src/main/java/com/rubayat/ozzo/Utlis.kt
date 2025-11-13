package com.rubayat.ozzo

import android.widget.EditText

fun EditText.isEmpty(): Boolean{

    return if (this.text.toString().isEmpty()){
        this.error = "this place need to fill up...!"
        true
    }else{
        false
    }

}