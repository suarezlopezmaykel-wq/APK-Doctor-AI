package com.apkdoctor.ai

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView

class MainActivity : Activity() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val titulo = TextView(this).apply {
        text = "APK Doctor AI"
        textSize = 28f
        gravity = Gravity.CENTER
    }

    setContentView(titulo)
}

}