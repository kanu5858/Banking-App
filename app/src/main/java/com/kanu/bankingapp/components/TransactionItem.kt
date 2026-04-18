package com.kanu.bankingapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kanu.bankingapp.data.Transaction
import com.kanu.bankingapp.data.TransactionCategory
import com.kanu.bankingapp.ui.theme.BankingTheme
import com.kanu.bankingapp.ui.theme.GreenIncome
import com.kanu.bankingapp.ui.theme.RedExpense
import java.util.Locale

@Composable
fun TransactionItem(
    transaction: Transaction,
    modifier: Modifier = Modifier,
    onTransactionClick: (Int) -> Unit = {}
) {
    // Optimization: Calculate accessibility description once
    val accessibilityLabel = remember(transaction) {
        "${if (transaction.isExpense) "Expense" else "Income"}: ${transaction.name}, ${transaction.amount}"
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onTransactionClick(transaction.id) }
            .semantics { contentDescription = accessibilityLabel }
            .padding(vertical = 12.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Enhanced Icon Box with Category Tints
        val categoryColor = getCategoryColor(transaction.category)
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(categoryColor.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = getIconForCategory(transaction.category),
                contentDescription = null,
                tint = categoryColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Name and Date
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.2.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = transaction.date,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Amount with Proper Signage
        val amountColor = if (transaction.isExpense) RedExpense else GreenIncome
        val sign = if (transaction.isExpense) "-" else "+"
        
        Text(
            text = "$sign${String.format(Locale.US, "$%,.2f", Math.abs(transaction.amount))}",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = amountColor
        )
    }
}

/**
 * Returns a specific brand color for each category to improve visual scanning
 */
private fun getCategoryColor(category: TransactionCategory): Color = when (category) {
    TransactionCategory.SUBSCRIPTION -> Color(0xFF673AB7) // Purple
    TransactionCategory.SHOPPING -> Color(0xFFFF9800)    // Orange
    TransactionCategory.FOOD -> Color(0xFFE91E63)        // Pink
    TransactionCategory.WORK -> Color(0xFF4CAF50)        // Green
    TransactionCategory.HEALTH -> Color(0xFFF44336)      // Red
    TransactionCategory.ENTERTAINMENT -> Color(0xFF00BCD4) // Cyan
    TransactionCategory.TRANSPORT -> Color(0xFF3F51B5)   // Indigo
    TransactionCategory.BILLS -> Color(0xFF607D8B)       // Blue Grey
    TransactionCategory.EDUCATION -> Color(0xFF795548)   // Brown
    TransactionCategory.OTHER -> Color(0xFF9E9E9E)       // Grey
}

private fun getIconForCategory(category: TransactionCategory): ImageVector = when (category) {
    TransactionCategory.SUBSCRIPTION -> Icons.Default.Subscriptions
    TransactionCategory.SHOPPING -> Icons.Default.ShoppingCart
    TransactionCategory.FOOD -> Icons.Default.Restaurant
    TransactionCategory.WORK -> Icons.Default.Work
    TransactionCategory.HEALTH -> Icons.Default.FitnessCenter
    TransactionCategory.ENTERTAINMENT -> Icons.Default.TheaterComedy
    TransactionCategory.TRANSPORT -> Icons.Default.DirectionsBus
    TransactionCategory.BILLS -> Icons.Default.Receipt
    TransactionCategory.EDUCATION -> Icons.Default.School
    TransactionCategory.OTHER -> Icons.Default.Payments
}

private val Double.sp get() = androidx.compose.ui.unit.TextUnit(this.toFloat(), androidx.compose.ui.unit.TextUnitType.Sp)

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    BankingTheme {
        Column(Modifier.padding(16.dp)) {
            TransactionItem(
                transaction = Transaction(
                    id = 1,
                    name = "Netflix Subscription",
                    date = "Oct 12, 2026",
                    amount = -15.99,
                    isExpense = true,
                    icon = 0,
                    category = TransactionCategory.SUBSCRIPTION
                )
            )
            TransactionItem(
                transaction = Transaction(
                    id = 2,
                    name = "Salary Deposit",
                    date = "Oct 10, 2026",
                    amount = 4500.00,
                    isExpense = false,
                    icon = 0,
                    category = TransactionCategory.WORK
                )
            )
        }
    }
}
