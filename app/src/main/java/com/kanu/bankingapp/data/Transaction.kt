package com.kanu.bankingapp.data

data class Transaction(
    val id: Int,
    val name: String,
    val date: String,
    val amount: Double,
    val isExpense: Boolean,
    val icon: Int // drawable resource
)
