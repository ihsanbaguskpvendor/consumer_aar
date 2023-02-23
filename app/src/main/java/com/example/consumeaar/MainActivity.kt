package com.example.consumeaar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flutter: Button = findViewById(R.id.flutter)
        flutter.setOnClickListener {
            startActivity(Intent(this, FlutterXActivity::class.java))
        }
    }
}