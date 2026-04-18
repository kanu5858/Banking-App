# Banking App - Jetpack Compose

A modern, functional banking application built with Jetpack Compose, featuring real-time wallet state management, peer-to-peer "Magic QR" transfers, and a polished dark-themed UI.

## 📱 Screenshots

<p align="center">
  <img src="app/src/main/res/drawable/splash.png" width="30%" alt="Splash Screen">
  <img src="app/src/main/res/drawable/home.png" width="30%" alt="Home Screen">
  <img src="app/src/main/res/drawable/txn_history.png" width="30%" alt="Transaction History">
</p>

<p align="center">
  <img src="app/src/main/res/drawable/transfer.png" width="30%" alt="Transfer Confirmation">
  <img src="app/src/main/res/drawable/profile.png" width="30%" alt="Profile Settings">
</p>

## ✨ Key Features

- **Centralized Reactive Wallet**: Managed by `WalletState` using `Double` for precise balance tracking.
- **Magic QR Transfers**: Encode payment data into QR codes. Funds are locked in the sender's wallet and credited instantly upon scanning.
- **Transaction History**: Real-time logging of all expenses, income, and QR-based movements with category-based icons.
- **Security & Privacy**: 
  - Balance privacy toggle (eye icon) to hide/show sensitive funds.
  - Masked account and card numbers across the UI.
  - Profile customization with bank detail visibility.
- **Modern UI**: Full Dark Mode support, smooth Compose animations, and Material 3 components.

## 🛠️ Technical Highlights

- **State Management**: Singleton Pattern with Compose `mutableStateOf` and `mutableStateListOf`.
- **Camera & Scanning**: Integrated CameraX with Google ML Kit for high-speed QR code processing.
- **Lifecycle Management**: Memory-safe thread handling using `DisposableEffect` for camera executors.
- **Asynchronous Operations**: Coroutines for splash animations and simulated processing states.
- **UI Components**: custom reusable components like `BalanceCard`, `TransactionItem`, and `QuickActionsSection`.

## 🏗️ Project Structure

- `data/`: State management and data models (`WalletState`, `Transaction`, `SampleData`).
- `screens/`: Individual Compose screens (Home, History, Scan, Profile, etc.).
- `components/`: Modular UI widgets used across the app.
- `ui/theme/`: Custom Material 3 color schemes and typography.

## 🚀 Getting Started

1. Clone the repository.
2. Open in Android Studio (Koala or newer).
3. Build and run on an Android device or emulator (API 24+).
