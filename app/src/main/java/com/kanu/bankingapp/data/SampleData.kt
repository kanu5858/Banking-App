package com.kanu.bankingapp.data

import androidx.compose.ui.graphics.Color
import com.kanu.bankingapp.R

object SampleData {
    val cards = listOf(
        BankCard(
            cardHolder = "Boru GB",
            cardNumber = "4532 1234 5678 9012",
            expiryDate = "12/26",
            cardType = "Visa",
            backgroundColor = Color(0xFF1A1A1A),
            logoRes = R.drawable.visa
        ),
        BankCard(
            cardHolder = "Boru GB",
            cardNumber = "5412 8765 4321 0987",
            expiryDate = "05/25",
            cardType = "MasterCard",
            backgroundColor = Color(0xFF3F51B5),
            logoRes = R.drawable.master_card
        ),
        BankCard(
            cardHolder = "Boru GB",
            cardNumber = "3782 9999 8888 7777",
            expiryDate = "09/27",
            cardType = "Amex",
            backgroundColor = Color(0xFF009688),
            logoRes = R.drawable.amex
        )
    )

    val transactions = listOf(
        Transaction(1, "Netflix Subscription", "Oct 12, 2026", -15.99, true, 0),
        Transaction(2, "Salary Deposit", "Oct 10, 2026", 4500.00, false, 0),
        Transaction(3, "Grocery Store", "Oct 09, 2026", -84.50, true, 0),
        Transaction(4, "Amazon Purchase", "Oct 08, 2026", -120.00, true, 0),
        Transaction(5, "Freelance Payment", "Oct 05, 2026", 800.00, false, 0),
        Transaction(6, "Apple Music", "Oct 04, 2026", -9.99, true, 0),
        Transaction(7, "Uber Trip", "Oct 03, 2026", -24.30, true, 0),
        Transaction(8, "Starbucks Coffee", "Oct 02, 2026", -5.50, true, 0),
        Transaction(9, "Rent Payment", "Oct 01, 2026", -1200.00, true, 0),
        Transaction(10, "Gym Membership", "Sep 30, 2026", -45.00, true, 0),
        Transaction(11, "Dividend Payout", "Sep 28, 2026", 150.00, false, 0),
        Transaction(12, "Spotify Family", "Sep 27, 2026", -14.99, true, 0),
        Transaction(13, "Electricity Bill", "Sep 25, 2026", -110.20, true, 0),
        Transaction(14, "Gas Station", "Sep 24, 2026", -65.00, true, 0),
        Transaction(15, "Birthday Gift", "Sep 22, 2026", 100.00, false, 0)
    )

    val contacts = listOf(
        Contact("John Doe", "https://i.pravatar.cc/150?u=1"),
        Contact("Jane Smith", "https://i.pravatar.cc/150?u=2"),
        Contact("Mike Ross", "https://i.pravatar.cc/150?u=3"),
        Contact("Rachel Zane", "https://i.pravatar.cc/150?u=4"),
        Contact("Harvey Specter", "https://i.pravatar.cc/150?u=5"),
        Contact("Donna Paulsen", "https://i.pravatar.cc/150?u=6"),
        Contact("Louis Litt", "https://i.pravatar.cc/150?u=7")
    )
}
