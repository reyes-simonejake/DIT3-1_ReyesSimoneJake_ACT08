# Quick Start Guide - Location Tracker App

## 🚀 Running the App

### Prerequisites
- Android Studio (latest version)
- Android device or emulator with Google Play Services
- Google Maps API Key

### Setup Steps

1. **Get Google Maps API Key**
   ```
   1. Go to: https://console.cloud.google.com/
   2. Create a new project or select existing
   3. Enable "Maps SDK for Android"
   4. Create credentials → API Key
   5. Copy the API key
   ```

2. **Add API Key to Project**
   
   Create or edit `local.properties` in project root:
   ```properties
   MAPS_API_KEY=YOUR_API_KEY_HERE
   ```

3. **Build & Run**
   ```
   1. Open project in Android Studio
   2. Sync Gradle files (File → Sync Project with Gradle Files)
   3. Connect device or start emulator
   4. Click Run (▶️) or press Shift+F10
   ```

---

## 📱 Using the App

### First Launch
1. App requests location permission
2. Tap "Allow" or "While using the app"
3. Map loads with your location
4. Info card shows coordinates

### Features
- **Auto-Update**: Location refreshes every 3 seconds
- **Manual Refresh**: Tap the blue FAB button (📍)
- **Map Controls**: Pinch to zoom, drag to pan
- **Update Counter**: Shows number of updates (#N)

---

## 🎨 UI Components

### Info Card (Top)
```
● Tracking Active              #12
─────────────────────────────────
Latitude     14.599500
Longitude    120.984200
Accuracy     12.3 m
```

### Floating Action Button (Bottom Right)
- Blue circular button with location icon
- Tap to manually refresh location

### Map (Background)
- Full-screen Google Maps
- Red marker shows current location
- Auto-centers on location updates

---

## 🔧 Customization

### Change Update Interval
In `MainActivity.kt`, line ~120:
```kotlin
handler.postDelayed(this, 3000)  // 3000ms = 3 seconds
```

### Change Map Zoom Level
In `MainActivity.kt`, line ~145:
```kotlin
map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
// Change 15f to desired zoom (1-20)
```

### Change Colors
Edit `app/src/main/res/values/colors.xml`:
```xml
<color name="primary">#2196F3</color>  <!-- Main blue -->
<color name="accent">#FF5722</color>   <!-- Orange accent -->
```

### Change Starting Location
In `MainActivity.kt`, lines ~30-31:
```kotlin
private var currentLat = 14.5995   // Manila latitude
private var currentLng = 120.9842  // Manila longitude
```

---

## 🐛 Troubleshooting

### Map Not Loading
- Check internet connection
- Verify API key in `local.properties`
- Ensure Google Play Services installed on device

### Permission Denied
- Go to Settings → Apps → Location Tracker
- Enable Location permission
- Restart app

### Build Errors
```bash
# Clean and rebuild
./gradlew clean
./gradlew build

# Or in Android Studio:
Build → Clean Project
Build → Rebuild Project
```

### Gradle Sync Issues
```bash
# Invalidate caches
File → Invalidate Caches / Restart
```

---

## 📂 Key Files

| File | Purpose |
|------|---------|
| `MainActivity.kt` | Main app logic |
| `activity_main.xml` | UI layout |
| `colors.xml` | Color definitions |
| `strings.xml` | Text resources |
| `themes.xml` | App theme |
| `AndroidManifest.xml` | Permissions & config |

---

## 🎯 Testing Checklist

- [ ] App launches without crashes
- [ ] Permission dialog appears
- [ ] Map loads successfully
- [ ] Location marker appears
- [ ] Info card shows coordinates
- [ ] Update counter increments
- [ ] FAB button works
- [ ] Auto-updates every 3 seconds
- [ ] Manual refresh works
- [ ] Map zoom/pan works

---

## 📸 Screenshots Required

For submission, capture:
1. **Permission Request** - Initial dialog
2. **Map with Location** - Full screen with marker
3. **Location Updates** - Info card with data

Save to `screenshot/` folder:
- `permission_request.jpg`
- `map_location.jpg`
- `location_update.jpg`

---

## 🎓 Learning Points

This app demonstrates:
- ✅ GPS location services
- ✅ Runtime permissions
- ✅ Google Maps integration
- ✅ Material Design UI
- ✅ Real-time updates
- ✅ Handler for periodic tasks
- ✅ FloatingActionButton
- ✅ MaterialCardView

---

## 📚 Resources

- [Android Location Guide](https://developer.android.com/training/location)
- [Google Maps Android SDK](https://developers.google.com/maps/documentation/android-sdk)
- [Material Design](https://material.io/design)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)

---

**Need Help?**
Check `DESIGN_CHANGES.md` for detailed UI documentation
Check `UI_STRUCTURE.txt` for visual layout guide

**Good luck, Simone! 🚀**
