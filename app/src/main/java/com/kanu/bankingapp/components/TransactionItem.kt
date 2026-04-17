package com.kanu.bankingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kanu.bankingapp.data.Transaction
import com.kanu.bankingapp.ui.theme.BankingTheme
import com.kanu.bankingapp.ui.theme.GreenIncome
import com.kanu.bankingapp.ui.theme.RedExpense

@Composable
fun TransactionItem(
    transaction: Transaction,
    modifier: Modifier = Modifier,
    onTransactionClick: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTransactionClick(transaction.id) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Box
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = getIconForTransaction(transaction.name),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Name and Date
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = transaction.date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }

        // Amount
        Text(
            text = (if (transaction.isExpense) "-" else "+") + "$" + transaction.amount,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = if (transaction.isExpense) RedExpense else GreenIncome
        )
    }
}

private fun getIconForTransaction(name: String) = when {
    name.contains("Netflix", ignoreCase = true) -> Icons.Default.Subscriptions
    name.contains("Salary", ignoreCase = true) -> Icons.Default.Payments
    name.contains("Grocery", ignoreCase = true) -> Icons.Default.ShoppingCart
    name.contains("Amazon", ignoreCase = true) -> Icons.Default.ShoppingCart
    name.contains("Freelance", ignoreCase = true) -> Icons.Default.Work
    else -> Icons.Default.CreditCard
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    BankingTheme {
        TransactionItem(
            transaction = Transaction(
                id = 1,
                name = "Netflix Subscription",
                date = "Oct 12, 2023",
                amount = 15.99,
                isExpense = true,
                icon = 0
            )
        )
    }
}
