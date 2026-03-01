plugins {
    id("com.android.application") version "8.10.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0" apply false

    // Update KSP to match Kotlin 2.2.0 (check for latest compatible version)
    id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false

}