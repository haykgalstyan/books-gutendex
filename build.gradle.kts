plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.ksp) apply false

    alias(libs.plugins.hilt.android) apply false

    alias(libs.plugins.kotlin.serialization) apply false

    alias(libs.plugins.androidx.navigation.safe.args) apply false
}