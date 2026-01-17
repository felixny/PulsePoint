# PulsePoint 🎯

A high-performance, multi-module Android template demonstrating 60 FPS real-time data streaming, MVI architecture, and production-grade scalability with dynamic industry-specific UI skins.

## ✨ Features

### 🏗️ Architecture
- **MVI (Model-View-Intent)** architecture for predictable state management
- **Multi-module** project structure for scalability
- **Hilt** dependency injection for clean dependency management
- **Jetpack Compose** for modern, declarative UI

### 🎨 Dynamic UI Skins
Switch between three industry-specific themes with smooth animations:

- **💼 Fintech Mode**: Cryptocurrency trading interface
  - Blue/Dark theme with professional aesthetics
  - Sparkline charts for price trends
  - Volatility badges
  - Crypto pair display (BTC/USD, ETH/USD, etc.)

- **⚽ Sports Mode**: Live sports scores
  - Orange theme with energetic colors
  - Live match indicators with pulsing badges
  - Game clock and quarter display
  - Team vs Team scorecards

- **⭐ Rewards Mode**: Points and rewards system
  - Purple/Green theme with inviting colors
  - Points earned display with progress indicators
  - Tier progress visualization
  - Gamified animations for point changes

### ⚡ Performance Features
- **High-frequency data streaming** (300ms intervals)
- **Flow sampling** toggle for CPU/memory optimization
- **60 FPS animations** with smooth transitions
- **Animated content** for value changes (slide-up/down with color flashes)
- **Recomposition tracking** capabilities for performance profiling

### 🎯 Technical Highlights
- Kotlin 2.1.0 with modern language features
- Android Gradle Plugin 8.8.0
- Material 3 Design System with dynamic theming
- WCAG-compliant color palettes for accessibility
- Custom sparkline charts using Canvas API

## 🏗️ Project Structure

```
PulsePoint/
├── app/                          # Main application module
├── core/
│   ├── ui/                      # Material 3 theme, design system
│   ├── network/                 # Fake ticker provider, data models
│   ├── data/                    # Repository layer, data flow
│   └── navigation/              # Navigation setup (future)
├── feature/
│   └── dashboard/               # MVI dashboard with ticker cards
└── gradle/
    └── libs.versions.toml       # Version catalog for dependencies
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 17 or higher
- Android SDK (API 24+)

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/felixny/PulsePoint.git
   cd PulsePoint
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the PulsePoint directory
   - Wait for Gradle sync to complete

3. **Build and Run**
   - Click the "Run" button or press `Shift + F10`
   - Select a device or emulator
   - The app will build and launch

## 📱 Usage

### Switching Modes
1. Open the app to see the Dashboard
2. Use the mode toggle at the top of the screen
3. Select between:
   - **Sports Mode** (Team Scores & Odds)
   - **Rewards Mode** (Points & Multipliers)
   - **Fintech Mode** (Crypto & Volatility)

### Viewing Real-time Data
- Ticker cards automatically update every 300ms
- Values animate smoothly when they change:
  - **Green flash + slide up** = Increase
  - **Red flash + slide down** = Decrease
- Switch modes to see industry-specific content instantly

### Performance Tuning
The app includes a sampling toggle (currently in repository) that can be extended to the UI to optimize CPU/memory usage by sampling data at 500ms intervals instead of 300ms.

## 🛠️ Technologies Used

### Core Libraries
- **Kotlin** 2.1.0
- **Jetpack Compose** BOM 2024.12.01
- **Material 3** Design System
- **Hilt** 2.57.1 for dependency injection
- **Coroutines** & **Flow** for asynchronous data streams
- **Lifecycle ViewModel Compose** for state management

### Build Tools
- **Android Gradle Plugin** 8.8.0
- **Gradle** Version Catalog for dependency management
- **Kotlin Serialization** for data models

## 🎨 Design System

The app uses a centralized design system in `:core:ui` with:

- **Dynamic Color Schemes** based on `AppMode`
- **Semantic Color Tokens** (onSurface, onSecondaryContainer, etc.)
- **WCAG-compliant** color palettes for accessibility
- **Container variants** for softer backgrounds with better readability
- **Theme-aware** components that adapt to light/dark mode

## 📊 Architecture Overview

### MVI Pattern
```
User Intent → ViewModel → Repository → Data Source
                ↓
            UiState + UiEffect
                ↓
              UI (Compose)
```

### Data Flow
```
FakeTickerProvider (300ms Flow)
    ↓
TickerRepository (with optional sampling)
    ↓
DashboardViewModel (MVI state management)
    ↓
DashboardScreen (Compose UI)
```

### Dependency Injection
- **Hilt** manages all dependencies
- Modules are organized by layer (Network, Data, UI)
- ViewModels are automatically injected using `@HiltViewModel`

## 🔧 Customization

### Adding New Modes
1. Add enum value to `AppConfig` in `:feature:dashboard`
2. Create corresponding `AppMode` in `:core:ui`
3. Add color scheme in `Color.kt`
4. Update `Theme.kt` with new color scheme
5. Create new card content composable in `IndustryCardContent.kt`
6. Add case in `AnimatedTickerCard.kt` factory pattern

### Modifying Update Frequency
Edit `FakeTickerProvider.kt`:
```kotlin
delay(300) // Change to desired interval in milliseconds
```

### Customizing Animations
Modify transition specs in `AnimatedTickerCard.kt` and `IndustryCardContent.kt`:
```kotlin
transitionSpec = {
    // Customize animation duration and effects
    fadeIn(animationSpec = tween(400)) + slideInVertically(...)
}
```

## 📝 License

This project is available for use as a template for building production-ready Android applications.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 👤 Author

**felixny**
- GitHub: [@felixny](https://github.com/felixny)

## 🙏 Acknowledgments

- Built with Jetpack Compose and Material 3
- Inspired by modern Android architecture best practices
- Designed for performance and scalability

---

**Note**: This is a template project demonstrating high-performance Android app architecture. Adapt it to your specific use case and requirements.
