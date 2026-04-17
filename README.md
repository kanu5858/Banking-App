# Banking App

## Troubleshooting

### App Crash on Launch (Missing INTERNET Permission)
**Issue:** The app would crash immediately after launch when attempting to perform network operations. The logcat showed a `java.lang.SecurityException: Permission denied (missing INTERNET permission?)`.

**Fix:** Added `<uses-permission android:name="android.permission.INTERNET" />` to the `AndroidManifest.xml` file.
