# Banking App

## Planned Features

### 1. Cards Section
Displays bank cards visually using a horizontal scroll.
- **UI:** `LazyRow` + `Card`
- **Details:** Masked card number, card holder name, and expiry date.

### 2. Transactions List
A list of recent transactions.
- **UI:** `LazyColumn` with custom list items.
- **Details:** Title (e.g., Netflix, Amazon), date, and amount (positive/negative).

### 3. Transaction Details Click
Implementing `onTransactionClick = {}` to handle user interactions.
- **Actions:** Open details screen, show dialog, or navigate to a new page.

### 4. Quick Actions / Services
Action buttons for common banking tasks.
- **Actions:** Send money, Pay bills, Top up.
- **UI:** Compose `Button` and `Icon` components.

### 5. Custom Theme
A consistent and modern look using `BankingTheme`.
- **Details:** Custom colors, typography, and support for Dark/Light mode.

## Troubleshooting

### App Crash on Launch (Missing INTERNET Permission)
**Issue:** The app would crash immediately after launch when attempting to perform network operations. The logcat showed a `java.lang.SecurityException: Permission denied (missing INTERNET permission?)`.

**Fix:** Added `<uses-permission android:name="android.permission.INTERNET" />` to the `AndroidManifest.xml` file.
