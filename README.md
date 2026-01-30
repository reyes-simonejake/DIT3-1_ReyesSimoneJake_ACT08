# Simple Location Tracker App
**Mobile Development - Activity 08**  
**Student:** Simone Jake Reyes

## 📱 App Description
A modern Android location tracking application that displays the user's real-time GPS location on an interactive map. The app features a clean, Material Design interface with live location updates and an intuitive card-based information display.

## ✨ Features
- **Real-time Location Tracking** - Continuous GPS location updates every 3 seconds
- **Interactive Map Display** - Google Maps integration with animated camera movements
- **Modern UI Design** - Material Design 3 with card-based layout and floating action button
- **Location Data Display** - Shows latitude, longitude, and accuracy in a clean format
- **Status Indicators** - Visual feedback for tracking status and update count
- **Permission Handling** - Runtime permission requests with user-friendly messaging

## 🔐 Permissions Used
The app requires the following permissions:

- `ACCESS_FINE_LOCATION` - For precise GPS location data
- `ACCESS_COARSE_LOCATION` - For approximate location (fallback)
- `INTERNET` - For loading Google Maps tiles

These permissions are requested at runtime when the app first launches, following Android best practices for user privacy.

## 📍 How GPS Location is Obtained

### Location Services
The app uses Android's **FusedLocationProviderClient** from Google Play Services, which:
1. Combines data from GPS satellites, Wi-Fi, and cellular networks
2. Provides the most accurate location with optimal battery usage
3. Automatically selects the best location provider

### Location Update Process
1. App requests location permissions from the user
2. Once granted, the FusedLocationProviderClient is initialized
3. Location updates are triggered every 3 seconds using a Handler
4. Each update provides:
   - **Latitude** - Geographic coordinate (north/south)
   - **Longitude** - Geographic coordinate (east/west)
   - **Accuracy** - Precision radius in meters

### Map Integration
- Uses Google Maps Android SDK
- Displays user location with a marker
- Animates camera to follow location changes
- Zoom level set to 15 for optimal street-level view

## 🎨 UI Design Features

### Modern Material Design 3
- **Enhanced Card Layout** - Large, elevated card with rounded corners (24dp radius)
- **Icon-Based Display** - Each metric has a colorful emoji icon in a circular background
  - 📍 Blue circle for Latitude
  - 🌍 Green circle for Longitude  
  - 🎯 Orange circle for Accuracy
- **Visual Separators** - Clean dividers between data rows
- **Status Indicators** - Pulsing green dot showing active tracking
- **Update Counter** - Styled badge with green border showing update count
- **Timestamp Display** - Shows exact time of last update
- **Floating Action Button** - Large, elevated FAB for manual refresh
- **Color Scheme** - Vibrant blue primary (#2196F3) with green accents

### Layout Components
- **CoordinatorLayout** - Enables smooth animations and interactions
- **MaterialCardView** - Clean, elevated information display with gradient
- **Icon Backgrounds** - Colored circular backgrounds for visual appeal
- **Grid Layout** - Organized data presentation with icons, labels and values
- **Monospace Font** - For precise coordinate display
- **Gradient Overlay** - Subtle purple gradient on top for depth

### Typography
- **Large Status Text** - 20sp bold for main status
- **Subtitle** - "Real-time GPS tracking" for context
- **Uppercase Labels** - 12sp with letter spacing for clarity
- **Bold Values** - 18sp monospace for coordinates
- **Time Display** - Live timestamp with clock emoji

## 📸 Screenshots

### Permission Request
![Permission Request](screenshot/permission_request.jpg)
*Initial permission dialog for location access*

### Map with Location
![Map Location](screenshot/map_location.jpg)
*Google Maps displaying current user location*

### Location Updates
![Location Update](screenshot/location_update.jpg)
*Real-time location data with coordinates and accuracy*

## 🛠 Technical Implementation

### Technologies Used
- **Language:** Kotlin
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 36
- **Architecture:** Single Activity with Fragment

### Key Dependencies
```gradle
// Google Maps & Location Services
implementation("com.google.android.gms:play-services-maps:18.2.0")
implementation("com.google.android.gms:play-services-location:21.1.0")

// Material Design Components
implementation("com.google.android.material:material:1.13.0")
```

### Project Structure
```
app/
├── src/main/
│   ├── java/com/example/simplelocationtrackerapp/
│   │   └── MainActivity.kt
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   ├── values/
│   │   │   ├── colors.xml
│   │   │   ├── strings.xml
│   │   │   └── themes.xml
│   │   └── drawable/
│   │       ├── status_indicator.xml
│   │       └── counter_background.xml
│   └── AndroidManifest.xml
└── build.gradle.kts
```

## 🚀 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/SimoneReyes/MobileDev-StudentName.git
   ```

2. **Add Google Maps API Key**
   - Get an API key from [Google Cloud Console](https://console.cloud.google.com/)
   - Create `local.properties` in project root
   - Add: `MAPS_API_KEY=your_api_key_here`

3. **Build and Run**
   - Open project in Android Studio
   - Sync Gradle files
   - Run on emulator or physical device with GPS enabled

## 📚 Learning Outcomes

This project demonstrates:
- ✅ Integration of device GPS sensors
- ✅ Runtime permission handling
- ✅ Google Maps SDK implementation
- ✅ Material Design principles
- ✅ Real-time data updates
- ✅ Modern Android UI/UX patterns

## 👨‍💻 Developer
**Simone Jake Reyes**  
Mobile Development Course  
Activity 08 - Device Features & Sensors
