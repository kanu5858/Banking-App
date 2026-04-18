package com.kanu.bankingapp.data

import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import java.util.*

object WalletState {
    var userName by mutableStateOf("Boru GB") // Default name
    var balance by mutableDoubleStateOf(10454.00)
    
    private val _transactions = mutableStateListOf<Transaction>().apply {
        addAll(SampleData.transactions)
    }
    val transactions: List<Transaction> get() = _transactions

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    fun sendMoney(amount: Double, contactName: String) {
        if (balance >= amount) {
            balance -= amount
            val newTransaction = Transaction(
                id = (_transactions.maxOfOrNull { it.id } ?: 0) + 1,
                name = if (contactName == "Locked in QR") "QR Payment Locked" else "Sent to $contactName",
                date = getCurrentDate(),
                amount = -amount,
                isExpense = true,
                icon = 0 // Icon will be handled by the UI based on name or category
            )
            _transactions.add(0, newTransaction)
        }
    }

    fun receiveMoney(amount: Double, fromName: String) {
        balance += amount
        val newTransaction = Transaction(
            id = (_transactions.maxOfOrNull { it.id } ?: 0) + 1,
            name = if (fromName == "QR Cancelled Refund") "QR Refund" else "Received from $fromName",
            date = getCurrentDate(),
            amount = amount,
            isExpense = false,
            icon = 0
        )
        _transactions.add(0, newTransaction)
    }
}
