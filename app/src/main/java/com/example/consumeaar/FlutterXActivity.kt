package com.example.consumeaar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.embedding.engine.loader.FlutterLoader
import io.flutter.plugin.common.MethodChannel

const val FLUTTER_ENGINE_ID = "flutter_engine"
const val CHANNEL = "deasy.newwg"

class FlutterXActivity : AppCompatActivity() {
    private lateinit var flutterEngine: FlutterEngine
    private lateinit var channel: MethodChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FlutterLoader().startInitialization(this)
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
//        val cacheActivity = FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID).build(this)
//        val newActivity =
//        FlutterActivity.withNewEngine().initialRoute("/verification-customer").build(this)
        startActivity(FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID).build(this))
        finish()
    }

    private fun appConfig() {
        val maap = mapOf(
            "app_config" to mapOf(
                "status_bar_color" to "#4670E7",
                "app_bar_color" to "#FFFFFF",
                "stepper_active_color" to "#F46363",
                "button_primary_color" to "#4670E7"
            ),
            "auth_config" to mapOf("app_source" to "SALLY"),
            "network_config" to mapOf(
//                "baseUrl" to "https://private-d36162-kphubnewwg.apiary-mock.com/api/v2/",
                "environment" to "dev",
            )
        )
        channel.invokeMethod(
            "base_config",
            maap
        )
    }
}