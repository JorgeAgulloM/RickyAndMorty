package org.example.rickyandmorty

expect fun getCurrentTarget(): Target

enum class Target {
    IOS, ANDROID, DESKTOP
}

fun isDesktop() = getCurrentTarget() == Target.DESKTOP
fun isIOS() = getCurrentTarget() == Target.IOS
fun isAndroid() = getCurrentTarget() == Target.ANDROID