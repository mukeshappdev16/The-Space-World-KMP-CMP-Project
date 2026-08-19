# The Space World

A modern Kotlin Multiplatform (KMP) application for exploring space activities, launches, and celestial events. Built with Compose Multiplatform, targeting Android, iOS, and Desktop (JVM).

## Features

- **Space Launches**: Comprehensive list and detailed information about upcoming and past missions.
- **Space Events**: Track celestial events, conferences, and press briefings (New).
- **Offline First**: Local persistence using Room Database.
- **Unified Design**: consistent space-themed UI across all platforms.
- **Paginated Data**: Efficient data loading from The Space Devs API.

## Architecture

The project follows a clean, multi-layered architecture to ensure maintainability and testability:

- **Presentation Layer**: Compose Multiplatform for UI, using `StateFlow` and Koin-powered ViewModels.
- **Domain Layer**: Pure Kotlin business logic and use cases.
- **Data Layer**: 
  - **Remote**: Ktor for networking with centralized error handling.
  - **Local**: Room Database for offline caching.
  - **Mappers**: Clear transformation logic between DTOs, Entities, and Domain models.

## Project Structure

* [**androidApp**](./androidApp): Android-specific entry point and configuration.
* [**iosApp**](./iosApp): iOS SwiftUI entry point (shared UI via Compose).
* [**desktopApp**](./desktopApp): Desktop (JVM) entry point with support for hot reload.
* [**shared**](./shared/src): The core of the application.
  - [**commonMain**](./shared/src/commonMain/kotlin): Shared UI, business logic, and data handling.
  - [**androidMain**](./shared/src/androidMain/kotlin): Android-specific implementations.
  - [**iosMain**](./shared/src/iosMain/kotlin): iOS-specific implementations.
  - [**jvmMain**](./shared/src/jvmMain/kotlin): Desktop-specific implementations.

## Running the apps

### Android
```bash
./gradlew :androidApp:assembleDebug
```

### Desktop
- **Standard Run**: `./gradlew :desktopApp:run`
- **Hot Reload**: `./gradlew :desktopApp:hotRun --auto`

### iOS
Open the [iosApp](./iosApp) directory in Xcode and run the project.

## Tech Stack

- **UI**: Compose Multiplatform
- **DI**: Koin
- **Networking**: Ktor
- **Database**: Room (KMP)
- **Concurrency**: Kotlin Coroutines & Flow
- **Serialization**: Kotlinx Serialization
- **Images**: Coil 3

---
Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html).
