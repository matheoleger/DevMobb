package com.example.devmobb.ui.defibrillator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.devmobb.R
import defibrillatorSelected

class DefibrillatorDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_defibrillator_detail)
        val defibrillator = intent.getStringExtra("defibrillator")
        val defibrillatorDesignation = findViewById<TextView>(R.id.defibrillatorDesignation)
        val buttonOpen = findViewById<Button>(R.id.buttonOpenMap)

        defibrillatorDesignation.text = defibrillator

        defibrillatorSelected?.let {defibrillator ->
            defibrillatorDesignation.text = defibrillator.designation

            buttonOpen.setOnClickListener {
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${defibrillator.latitude},${defibrillator.longitude}(${defibrillator.designation})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }


}