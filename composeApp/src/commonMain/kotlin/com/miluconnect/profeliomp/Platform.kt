package com.miluconnect.profeliomp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform