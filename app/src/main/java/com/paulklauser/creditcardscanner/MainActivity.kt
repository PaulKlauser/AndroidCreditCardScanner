package com.paulklauser.creditcardscanner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val number = findViewById<TextView>(R.id.card_number)

        val contract = registerForActivityResult(
            object : ActivityResultContract<Nothing?, String>() {
                override fun createIntent(context: Context, input: Nothing?): Intent {
                    return Intent(this@MainActivity, CameraXLivePreviewActivity::class.java)
                }

                override fun parseResult(resultCode: Int, intent: Intent?): String {
                    return intent?.extras?.getString("card")!!
                }

            },
            ActivityResultCallback { result ->
                number.text = result
            }
        )

        findViewById<Button>(R.id.capture).setOnClickListener {
            contract.launch(null)
        }
    }
}