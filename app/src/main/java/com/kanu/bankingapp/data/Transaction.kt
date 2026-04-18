package com.kanu.bankingapp.data

data class Transaction(
    val id: Int,
    val name: String,
    val date: String,
    val amount: Double,
    val isExpense: Boolean,
    val icon: Int, // drawable resource
    val category: TransactionCategory = TransactionCategory.OTHER
)

enum class TransactionCategory {
    SUBSCRIPTION,
    SHOPPING,
    FOOD,
    WORK,
    HEALTH,
    ENTERTAINMENT,
    TRANSPORT,
    BILLS,
    EDUCATION,
    OTHER
}
