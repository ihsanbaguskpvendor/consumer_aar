package com.example.consumeaar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

const val FLUTTER_ENGINE_ID = "flutter_engine"
const val CHANNEL = "deasy.newwg"

class FlutterXActivity : AppCompatActivity() {
    private lateinit var flutterEngine: FlutterEngine
    private lateinit var channel: MethodChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        channel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        )
        appConfig()
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ENGINE_ID, flutterEngine)
        startActivity(FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID).build(this))
        finish()
    }

    private fun appConfig() {
        val maap = mapOf(
            "app_config" to mapOf(
                "statusBarColor" to "#3700B3",
                "appBarColor" to "#FFFFFF",
                "buttonPrimaryColor" to "#FFBC00"
            ),
            "auth_config" to mapOf("appSource" to "KREDIT PLUS"),
            "network_config" to mapOf("baseUrl" to "https://google.com")
        )
        channel.invokeMethod(
            "base_config",
            maap
        )
    }
}