package com.kanu.bankingapp.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kanu.bankingapp.data.WalletState
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyQrScreen(
    onBackClick: () -> Unit
) {
    var amountToRequest by remember { mutableStateOf("") }
    var isMagicMode by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    val currentBalance = WalletState.balance
    val requestedAmountDouble = amountToRequest.toDoubleOrNull() ?: 0.0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Magic QR Transfer", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Info
            AsyncImage(
                model = "https://ui-avatars.com/api/?name=${WalletState.userName}&background=6200EE&color=fff&size=128",
                contentDescription = "Profile",
                modifier = Modifier.size(64.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = WalletState.userName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            // Enhanced Bank Information
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "SAVINGS",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "A/C •••• 9012",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = "Global Digital Bank • SWIFT: GDBKUS33",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                modifier = Modifier.padding(top = 2.dp)
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            // Magic QR Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isMagicMode) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f) else MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedContent(targetState = isMagicMode, label = "HeaderState") { magic ->
                        if (magic) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "MONEY LOCKED IN QR",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Text(
                                    text = String.format(Locale.US, "$%,.2f", requestedAmountDouble),
                                    style = MaterialTheme.typography.displaySmall,
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        } else {
                            Text(
                                text = "READY TO SEND",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // QR Code Visual Representation
                    Box(
                        modifier = Modifier
                            .size(220.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // In a real app, this would be a generated Bitmap using ZXing or similar
                        Icon(
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "QR Code",
                            modifier = Modifier.fillMaxSize(),
                            tint = if (isMagicMode) MaterialTheme.colorScheme.primary else Color.DarkGray
                        )
                        
                        if (isMagicMode) {
                            Surface(
                                modifier = Modifier.align(Alignment.BottomEnd).padding(4.dp),
                                shape = CircleShape,
                                color = Color(0xFF4CAF50),
                                border = androidx.compose.foundation.BorderStroke(2.dp, Color.White)
                            ) {
                                Icon(
                                    Icons.Default.AutoAwesome, 
                                    "Magic", 
                                    tint = Color.White, 
                                    modifier = Modifier.size(24.dp).padding(4.dp)
                                )
                            }
                        }
                    }
                    
                    if (!isMagicMode) {
                        Spacer(modifier = Modifier.height(24.dp))
                        OutlinedTextField(
                            value = amountToRequest,
                            onValueChange = { 
                                if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d{0,2}$"))) {
                                    amountToRequest = it 
                                    errorMessage = null
                                }
                            },
                            placeholder = { Text("0.00") },
                            prefix = { Text("$", color = MaterialTheme.colorScheme.primary) },
                            label = { Text("Amount to transfer") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            isError = errorMessage != null,
                            supportingText = { errorMessage?.let { Text(it) } }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons
            if (!isMagicMode) {
                Button(
                    onClick = {
                        val amount = amountToRequest.toDoubleOrNull() ?: 0.0
                        if (amount <= 0) {
                            errorMessage = "Please enter a valid amount"
                        } else if (amount > currentBalance) {
                            errorMessage = "Insufficient balance ($${String.format(Locale.US, "%.2f", currentBalance)})"
                } else {
                    WalletState.sendMoney(amount, "Locked in QR")
                    isMagicMode = true
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.ConfirmationNumber, null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Confirm", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                        onClick = { 
                            val amount = amountToRequest.toDoubleOrNull() ?: 0.0
                            WalletState.receiveMoney(amount, "QR Cancelled Refund")
                            isMagicMode = false
                            amountToRequest = ""
                        },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Cancel")
                    }
                    
                    // Simulated "Success" for testing purposes
                    Button(
                        onClick = {
                            // In a real flow, the OTHER device would scan this.
                            // Here we just clear the state as if it was successfully scanned.
                            isMagicMode = false
                            amountToRequest = ""
                            onBackClick()
                        },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Finish")
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "While sending your wallet balance will be locked for a moment.\nFunds are transferred only when the code is scanned.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                lineHeight = 18.sp
            )
        }
    }
}

// Helper for Row spacing
@Composable
fun RowScope.Gap(width: Int) {
    Spacer(modifier = Modifier.width(width.dp))
}
