package com.kanu.bankingapp.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class QuickAction(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun QuickActionsSection(
    modifier: Modifier = Modifier,
    onActionClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    
    val actions = listOf(
        QuickAction("Send", Icons.AutoMirrored.Filled.Send) {
            onActionClick("send")
            Toast.makeText(context, "Navigating to Send Money...", Toast.LENGTH_SHORT).show()
        },
        QuickAction("Pay", Icons.Default.Payments) {
            onActionClick("pay")
            Toast.makeText(context, "Opening Bill Payments...", Toast.LENGTH_SHORT).show()
        },
        QuickAction("Scan", Icons.Default.QrCodeScanner) {
            onActionClick("scan")
            Toast.makeText(context, "Opening QR Scanner...", Toast.LENGTH_SHORT).show()
        },
        QuickAction("Top Up", Icons.Default.Add) {
            onActionClick("topup")
            Toast.makeText(context, "Navigating to Top Up...", Toast.LENGTH_SHORT).show()
        },
        QuickAction("History", Icons.Default.History) {
            onActionClick("history")
            Toast.makeText(context, "Opening Transaction History...", Toast.LENGTH_SHORT).show()
        }
    )

    Column(modifier = modifier) {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(actions) { action ->
                QuickActionItem(action = action)
            }
        }
    }
}

@Composable
fun QuickActionItem(
    action: QuickAction
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { action.onClick() }
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = action.title,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = action.title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }
}
