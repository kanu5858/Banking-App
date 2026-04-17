package com.kanu.bankingapp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kanu.bankingapp.ui.theme.BankingTheme
import com.kanu.bankingapp.ui.theme.Mint

@Composable
fun SpendingChart(
    modifier: Modifier = Modifier
) {
    val spendingData = listOf(
        SpendingData("Mon", 0.4f),
        SpendingData("Tue", 0.7f),
        SpendingData("Wed", 0.5f),
        SpendingData("Thu", 0.9f),
        SpendingData("Fri", 0.6f),
        SpendingData("Sat", 0.3f),
        SpendingData("Sun", 0.5f)
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Spending This Week",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                spendingData.forEach { data ->
                    BarItem(data = data, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun BarItem(
    data: SpendingData,
    modifier: Modifier = Modifier
) {
    val barColor = Mint

    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxHeight(data.value)
                .fillMaxWidth(0.5f)
        ) {
            drawRoundRect(
                color = barColor,
                size = Size(size.width, size.height),
                cornerRadius = CornerRadius(12f, 12f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = data.day,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

data class SpendingData(
    val day: String,
    val value: Float // 0.0 to 1.0
)

@Preview(showBackground = true)
@Composable
fun SpendingChartPreview() {
    BankingTheme {
        SpendingChart()
    }
}
