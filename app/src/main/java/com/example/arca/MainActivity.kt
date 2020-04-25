package com.example.arca

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val signup_fb = findViewById<Button>(R.id.signup_fb)
        //val signup_g = findViewById<Button>(R.id.signup_g)
        val btnIngredient = findViewById<Button>(R.id.main_ingredient)
        val btnRecipe = findViewById<Button>(R.id.main_recipe)

        btnIngredient.setOnClickListener {
            val intent1 = Intent(this, IngredientActivity::class.java)
            startActivity(intent1) // startActivity allow you to move
        }
        btnRecipe.setOnClickListener {
            val intent2 = Intent(this, RecipeActivity::class.java)
            startActivity(intent2) // startActivity allow you to move
        }

    }
}


