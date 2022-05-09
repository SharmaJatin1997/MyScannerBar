package com.app.myscannerbar

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    var text: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.scanner)

        text!!.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setPrompt("Please barcode inside the frame to scan. Please keep your device steady when scanning to ensure accurate result.")
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setBeepEnabled(true)
            intentIntegrator.setCameraId(0)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setTorchEnabled(true)
            intentIntegrator.captureActivity = CapturePortraitActivity::class.java
            intentIntegrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result != null) {
            if (result.contents != null)
                Toast.makeText(this, result.contents, Toast.LENGTH_SHORT).show()
        }
    }
}