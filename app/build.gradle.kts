import org.apache.commons.io.output.ByteArrayOutputStream
import java.nio.charset.Charset

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

private val gitCommitsCount: Int by lazy {
    when {
        System.getProperty("os.name").contains("Windows", ignoreCase = true) -> 1
        else -> {
            val stdout = ByteArrayOutputStream()
            exec {
                commandLine("git", "rev-list", "--count", "HEAD")
                standardOutput = stdout
            }
            stdout.toString(Charset.defaultCharset()).trim().toInt()
        }
    }
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

android {
    namespace = "org.michaelbel.aliases"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "org.michaelbel.aliases"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
        versionCode = gitCommitsCount
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }
}

base {
    archivesName.set("Aliases-v${android.defaultConfig.versionName}(${android.defaultConfig.versionCode})")
}

dependencies {
    api(libs.google.material)
    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.core.splashscreen)
}

tasks.register("printVersionName") {
    doLast {
        println(android.defaultConfig.versionName)
    }
}

tasks.register("printVersionCode") {
    doLast {
        println(android.defaultConfig.versionCode.toString())
    }
}