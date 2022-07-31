package com.paulklauser.creditcardscanner

interface RecognitionListener {
    fun recognized(cardNumber: String)
}