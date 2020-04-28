package com.example.arca

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIngredient = findViewById<View>(R.id.btn_ingredient_select)
        val btnRecipe = findViewById<View>(R.id.btn_recipe_select)
        val btnIngre = findViewById<TextView>(R.id.txt_ingredient_select)
        val btnRec = findViewById<TextView>(R.id.txt_recipe_select)
        val intent1 = Intent(this, IngredientActivity::class.java)
        val intent2 = Intent(this, RecipeActivity::class.java)

        btnIngredient.setOnClickListener {
            startActivity(intent1) // startActivity allow you to move
        }
        btnIngre.setOnClickListener {
            startActivity(intent1) // startActivity allow you to move
        }
        btnRecipe.setOnClickListener {
            startActivity(intent2) // startActivity allow you to move
        }
        btnRec.setOnClickListener {
            startActivity(intent2) // startActivity allow you to move
        }
    }
}


