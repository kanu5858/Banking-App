package com.kanu.bankingapp.data

import androidx.compose.ui.graphics.Color

data class BankCard(
    val cardHolder: String,
    val cardNumber: String,
    val expiryDate: String,
    val cardType: String,
    val backgroundColor: Color
)
