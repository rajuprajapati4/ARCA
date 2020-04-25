package com.example.arca

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_res_rec.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.URL
import java.net.URLConnection
import java.util.*
import kotlin.collections.ArrayList
import kotlin.text.StringBuilder

class ResRecActivity : AppCompatActivity() {
    var id_arr = arrayListOf<Int>()         //an array to store all the recipe ids' from the dataset
    var name_arr = arrayListOf<String>()    //an array to store the names of all recipes from the dataset
    var img_arr = arrayListOf<String>()     //an array to store all the image links from the dataset
    var i_arr: String = ""      //a string to store ingredient data individual Recipe among all the recipes from the dataset
    var iq_arr = arrayListOf<String>()      //an array to store quantity of the ingredients in individual recipe
    var in_arr = arrayListOf<String>()      //an array to store name of the ingredients in individual recipe
    var s_arr: String = ""      //a string to store steps data individual recipe among all the recipes from the dataset

    // Used for Assistant Feature
    var com_recipeName: String? =null
    var com_ingredients: String? = null
    var com_instructions: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_rec)
        setSupportActionBar(toolbar)

        val posit: Int = intent.getIntExtra("ItemPosition", 0)
        val pos: Int = intent.getIntExtra("SearchedItemPosition",-1)
        if (pos != -1){
            readJson(pos)
        }else {
            readJson(posit)
        }

        val btnSpeak = findViewById<Button>(R.id.resrec_voice)
        btnSpeak.setOnClickListener {
            val intent1 = Intent(this, ListeningActivity::class.java)
            intent1.putExtra("ResrecName", com_recipeName)
            intent1.putExtra("ResRecIngredients",com_ingredients)
            intent1.putExtra("ResRecInstructions",com_instructions)
            startActivity(intent1)
        }
    }

    fun readJson(position: Int) {
        var json: String? = ""

        try {
            val inputStream: InputStream =
                assets.open("datasetRecipe.json")    //giving the location and opening the file
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonarr = JSONArray(json)
            for (i in 0..jsonarr.length()-1) {
                val jsonobj = jsonarr.getJSONObject(i)
                id_arr.add(jsonobj.getInt("id"))
                name_arr.add(jsonobj.getString("name"))
                img_arr.add(jsonobj.getString("imageURL"))
            }

            val newName = name_arr[position]
            val newImage = img_arr[position]

            //For the Recipe Title
            val heading = findViewById<CollapsingToolbarLayout>(R.id.resrec_head)
            com_recipeName = newName
            heading.title = newName

            //For the Recipe image retrieval
            val image = findViewById<ImageView>(R.id.resrec_img)
            Picasso.with(this).load(newImage).into(image)

            val sb = StringBuilder()
            val i = position        //for getting selected recipe's data only

            //For ingredients retrieval of each individual recipe
            val ingredients = findViewById<TextView>(R.id.resrec_ingredients)
            i_arr = jsonarr.getJSONObject(i).getString("ingredients")
            val iJson_arr = JSONArray(i_arr) //different json array for the ingredients
            for (j in 0..iJson_arr.length()-1) { //fetching ingredient data for individual recipe
                val iJsonobj = iJson_arr.getJSONObject(j)
                iq_arr.add(iJsonobj.getString("quantity"))
                in_arr.add(iJsonobj.getString("iName"))
            }
            for (k in 0 until in_arr.size){
                val ingres : String = iq_arr[k]+" "+in_arr[k]
                val l = k+1
                if (k<in_arr.size && k>0){
                    sb.append("\n").append("$l]. ").append(ingres)
                }else{
                    sb.append("$l]. ").append(ingres)
                }
            }
            in_arr.clear()
            iq_arr.clear()
            com_ingredients = sb.toString()
            ingredients.text = sb.toString()

            //For steps retrieval of each individual recipe
            val steps = findViewById<TextView>(R.id.resrec_steps)
            sb.clear()
            s_arr = jsonarr.getJSONObject(i).getString("steps")
            val sJson_arr = JSONArray(s_arr) //different json array for the steps
            for (j in 0..sJson_arr.length()-1) { //fetching step data for individual recipe
                val sJsonobj: String = sJson_arr.getString(j)
                val k = j + 1
                if (j<sJson_arr.length() && j>0){
                    sb.append("\n").append("$k]. ").append(sJsonobj)
                } else {
                    sb.append("$k]. ").append(sJsonobj)
                }
            }
            com_instructions = sb.toString()
            steps.text = sb.toString()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
