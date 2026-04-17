# Modern Banking App 🏦

A robust, professionally designed Android banking application built with **Jetpack Compose**, following modern Material 3 design principles. This app features a clean user interface, seamless navigation, and real-time UI updates based on user interaction and time of day.

## ✨ Key Features

### 🕒 Dynamic Experience
- **Time-Aware Greetings**: Personalized home screen greetings (Good Morning, Afternoon, Evening) that adapt based on the device's current time.
- **Interactive UI**: Ripple effects, smooth transitions, and state-driven UI components.

### 💳 Card Management
- **Horizontal Cards Section**: Swipe through multiple bank cards (Visa, MasterCard, Amex) with branded logos and custom styling.
- **Visual Accuracy**: High-quality card layouts displaying masked numbers, holder names, and expiry dates.

### 💸 Financial Actions
- **Send Money**: Integrated flow with contact search and a custom numerical amount input screen.
- **Quick Actions**: One-tap access to Pay Bills, Scan QR, and Top Up services.
- **Payments Hub**: Dedicated screen for utility payments (Electricity, Water, Internet, etc.) with categorized service icons.

### 📜 Transaction Intelligence
- **Detailed History**: Complete transaction log with sophisticated filtering (All, Income, Expenses).
- **Transaction Details**: In-depth view for every transaction including date, status, and amount.
- **Spending Insights**: Visual chart representing weekly spending patterns.

## 🛠️ Technical Stack

- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Navigation**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/compose/) for optimized contact avatars.
- **Language**: Kotlin
- **Architecture**: Modern Android Architecture patterns.

## 🚀 Getting Started

### Prerequisites
- Android Studio Jellyfish or newer.
- Android SDK 34 or higher.

### Installation
1. Clone the repository.
2. Sync the project with Gradle files.
3. Run the app on an emulator or physical device.

## 🔧 Troubleshooting & Performance

- **Network Security**: `INTERNET` permission is configured for contact avatar loading.
- **Asset Optimization**: High-resolution drawables are named according to Android resource standards (snake_case) to prevent build-time errors.
- **ANR Prevention**: All heavy resource operations and list rendering are optimized using `Lazy` components to ensure a smooth 60fps experience.

## 📄 License

This project is for educational purposes and demonstrates modern Android development practices.
