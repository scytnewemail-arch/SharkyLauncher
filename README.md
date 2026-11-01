# Sharky Launcher — Android project starter

A native Kotlin + Jetpack Compose Android application shell for Sharky Launcher.

## Current status (0.1.0)
- Modern black UI with Home, Versions, and Settings screens.
- Version selection is local UI state only.
- Play button deliberately reports that the game engine is not connected.
- This is **not yet a functioning Minecraft launcher** and does not include Minecraft files, Java runtimes, Microsoft authentication, mod loaders, or rendering libraries.

## Target
- Android 8.0+ (minSdk 26)
- ARM64 target device: Samsung Galaxy Tab A9 (Helio G99)
- Java 17 / Android Gradle Plugin 8.7.3 / Kotlin 2.0.21

## Build
Open this folder in Android Studio or a compatible Android IDE, allow Gradle sync, then build the `app` debug APK.
The first build needs internet to download Gradle and Maven dependencies.

## Real game engine integration
The intended engine base is the maintained Amethyst Android project:
https://github.com/AngelAuraMC/Amethyst-Android

Amethyst's documented source build:
```bash
git clone --recursive https://github.com/AngelAuraMC/Amethyst-Android.git
./gradlew :app_pojavlauncher:assembleDebug
```
The engine integration is a separate milestone: rebrand the Amethyst Android app, preserve its runtime/native components, and connect the Sharky UI to its real account, version, installation, and launch flows.

## Licensing
Amethyst is GNU LGPLv3. Any distributed derivative must comply with the applicable LGPLv3 terms, preserve required notices, and provide corresponding source for covered modifications. Review all bundled dependency licenses before distribution.
