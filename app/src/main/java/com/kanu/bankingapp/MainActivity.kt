package com.kanu.bankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kanu.bankingapp.screens.*
import com.kanu.bankingapp.ui.theme.BankingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankingApp()
        }
    }
}

@Composable
fun BankingApp() {
    BankingTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "splash"
        ) {
            composable("splash") {
                SplashScreen(onNextScreen = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                })
            }
            composable("home") {
                HomeScreen(
                    onTransactionClick = { id ->
                        navController.navigate("detail/$id")
                    },
                    onActionClick = { action ->
                        navController.navigate(action)
                    }
                )
            }
            composable("send") {
                SendMoneyScreen(onBackClick = { navController.popBackStack() })
            }
            composable("pay") {
                PaymentScreen(onBackClick = { navController.popBackStack() })
            }
            composable("scan") {
                ScanScreen(onBackClick = { navController.popBackStack() })
            }
            composable("my_qr") {
                MyQrScreen(onBackClick = { navController.popBackStack() })
            }
            composable("profile") {
                ProfileScreen(onBackClick = { navController.popBackStack() })
            }
            composable("topup") {
                TopUpScreen(onBackClick = { navController.popBackStack() })
            }
            composable("history") {
                HistoryScreen(
                    onBackClick = { navController.popBackStack() },
                    onTransactionClick = { id ->
                        navController.navigate("detail/$id")
                    }
                )
            }
            composable(
                route = "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                TransactionDetailScreen(
                    transactionId = id,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BankingAppPreview() {
    BankingApp()
}
