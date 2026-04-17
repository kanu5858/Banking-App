package com.kanu.bankingapp.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import coil.compose.AsyncImage
import com.kanu.bankingapp.data.Contact
import com.kanu.bankingapp.data.SampleData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class SendFlowStep {
    SELECT_CONTACT,
    ENTER_AMOUNT,
    CONFIRMATION,
    SUCCESS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(
    onBackClick: () -> Unit
) {
    var currentStep by remember { mutableStateOf(SendFlowStep.SELECT_CONTACT) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val filteredContacts = SampleData.contacts.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentStep) {
                            SendFlowStep.SELECT_CONTACT -> "Send Money"
                            SendFlowStep.ENTER_AMOUNT -> "Enter Amount"
                            SendFlowStep.CONFIRMATION -> "Review Transfer"
                            SendFlowStep.SUCCESS -> "Transfer Success"
                        },
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    if (currentStep != SendFlowStep.SUCCESS) {
                        IconButton(onClick = {
                            when (currentStep) {
                                SendFlowStep.SELECT_CONTACT -> onBackClick()
                                SendFlowStep.ENTER_AMOUNT -> currentStep = SendFlowStep.SELECT_CONTACT
                                SendFlowStep.CONFIRMATION -> currentStep = SendFlowStep.ENTER_AMOUNT
                                else -> {}
                            }
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            AnimatedContent(
                targetState = currentStep,
                transitionSpec = {
                    if (targetState.ordinal > initialState.ordinal) {
                        slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
                    } else {
                        slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
                    }
                },
                label = "SendFlow"
            ) { step ->
                when (step) {
                    SendFlowStep.SELECT_CONTACT -> SelectContactStep(
                        searchQuery = searchQuery,
                        onSearchChange = { searchQuery = it },
                        contacts = filteredContacts,
                        onContactSelect = {
                            selectedContact = it
                            currentStep = SendFlowStep.ENTER_AMOUNT
                        }
                    )
                    SendFlowStep.ENTER_AMOUNT -> EnterAmountStep(
                        contact = selectedContact!!,
                        amount = amount,
                        onAmountChange = { amount = it },
                        onNext = { currentStep = SendFlowStep.CONFIRMATION }
                    )
                    SendFlowStep.CONFIRMATION -> ConfirmationStep(
                        contact = selectedContact!!,
                        amount = amount,
                        note = note,
                        onNoteChange = { note = it },
                        onConfirm = {
                            scope.launch {
                                // Simulate network delay
                                currentStep = SendFlowStep.SUCCESS
                            }
                        }
                    )
                    SendFlowStep.SUCCESS -> SuccessStep(
                        contact = selectedContact!!,
                        amount = amount,
                        onDone = onBackClick
                    )
                }
            }
        }
    }
}

@Composable
fun SelectContactStep(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    contacts: List<Contact>,
    onContactSelect: (Contact) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search contact or enter name") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Text(
            text = "Recent Contacts",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(contacts) { contact ->
                ContactRow(contact = contact, onClick = { onContactSelect(contact) })
            }
        }
    }
}

@Composable
fun EnterAmountStep(
    contact: Contact,
    amount: String,
    onAmountChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = contact.avatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Sending to ${contact.name}", style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.height(48.dp))
        
        TextField(
            value = amount,
            onValueChange = { if (it.all { char -> char.isDigit() || char == '.' }) onAmountChange(it) },
            textStyle = MaterialTheme.typography.headlineLarge.copy(
                textAlign = TextAlign.Center,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            ),
            placeholder = {
                Text(
                    "$0.00",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 48.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            enabled = amount.isNotEmpty() && amount.toDoubleOrNull() ?: 0.0 > 0
        ) {
            Text("Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ConfirmationStep(
    contact: Contact,
    amount: String,
    note: String,
    onNoteChange: (String) -> Unit,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = contact.avatarUrl,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp).clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = contact.name, fontWeight = FontWeight.Bold)
                        Text(text = "Savings Account", style = MaterialTheme.typography.bodySmall)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "$$amount", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedTextField(
            value = note,
            onValueChange = onNoteChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Add a note (optional)") },
            placeholder = { Text("e.g. Dinner, Rent") },
            shape = RoundedCornerShape(12.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Fee information
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Transfer Fee", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            Text(text = "Free", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onConfirm,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Confirm & Send", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SuccessStep(
    contact: Contact,
    amount: String,
    onDone: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color(0xFF4CAF50)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Transfer Successful!", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "You've successfully sent $$amount to ${contact.name}.",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onDone,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Done", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ContactRow(contact: Contact, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = contact.avatarUrl,
            contentDescription = contact.name,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Last sent: Yesterday",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
