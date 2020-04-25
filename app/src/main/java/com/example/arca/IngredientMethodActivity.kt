package com.example.arca

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IngredientMethodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_method)

        val btnDetection = findViewById<Button>(R.id.ingre_meth_detection)
        val btnSelection = findViewById<Button>(R.id.ingre_meth_selection)

        btnDetection.setOnClickListener {
            val intent1 = Intent(this, DetectionActivity::class.java)
            startActivity(intent1) // startActivity allow you to move
        }
        btnSelection.setOnClickListener {
            val intent2 = Intent(this, IngredientActivity::class.java)
            startActivity(intent2) // startActivity allow you to move
        }
    }
}
