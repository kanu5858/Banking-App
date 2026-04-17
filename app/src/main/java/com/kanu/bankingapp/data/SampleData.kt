package com.kanu.bankingapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star

object SampleData {
    val transactions = listOf(
        Transaction(1, "Netflix Subscription", "Oct 12, 2023", 15.99, true, 0),
        Transaction(2, "Salary Deposit", "Oct 10, 2023", 4500.00, false, 0),
        Transaction(3, "Grocery Store", "Oct 09, 2023", 84.50, true, 0),
        Transaction(4, "Amazon Purchase", "Oct 08, 2023", 120.00, true, 0),
        Transaction(5, "Freelance Payment", "Oct 05, 2023", 800.00, false, 0)
    )

    val contacts = listOf(
        Contact("John Doe", "https://i.pravatar.cc/150?u=1"),
        Contact("Jane Smith", "https://i.pravatar.cc/150?u=2"),
        Contact("Mike Ross", "https://i.pravatar.cc/150?u=3"),
        Contact("Rachel Zane", "https://i.pravatar.cc/150?u=4")
    )
}
