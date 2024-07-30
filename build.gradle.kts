import org.gradle.api.internal.initialization.ClassLoaderIds.buildScript

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application")  version "8.1.0"  apply  false

}
buildscript{
    dependencies{ classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")

    }
    repositories{
        google()
        mavenCentral()
    }

}


