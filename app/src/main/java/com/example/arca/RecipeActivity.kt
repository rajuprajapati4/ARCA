package com.example.arca

//import android.widget.Toast

import android.content.Intent
import android.os.Bundle
import android.sax.RootElement
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewParent
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.activity_recipe.*
import kotlinx.android.synthetic.main.content_recipe.*
import kotlinx.android.synthetic.main.list_view_row.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream


class RecipeActivity : AppCompatActivity() {

    var id_arr = arrayListOf<Int>()             //an array to store all the recipe ids' from the dataset
    var name_arr = arrayListOf<String>()        //an array to store the names of all recipes from the dataset
    var img_arr = arrayListOf<String>()         //an array to store all the image links from the dataset
    var i_arr: String = ""                      //a string to store ingredient data individual Recipe among all the recipes from the dataset
    var in_arr = arrayListOf<String>()          //an array to store name of the ingredients in individual recipe

    var temp_arr = arrayListOf<String>()        //an array to store all the recipe names after the prioritising the recipes on the basis of selected ingredients

    var rowArr = ArrayList<RowItem>()           //for storing data in custom array adaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        setSupportActionBar(recipe_toolbar)

        val search = findViewById<EditText>(R.id.recipe_search)
        var ingreList: ArrayList<String> = arrayListOf()

        var c = 0
        val cou = intent.getIntExtra("Choice", 0)
        if (c != cou) {
            ingreList = intent.getStringArrayListExtra("IngredientList")
            c = 2
        }
        val s = ""
        read_json(c, s, ingreList)
        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                val count = 1 // for triggering search function
                val str = s.toString() // search text
                val listLi = arrayListOf<String>()
                read_json(count, str, listLi)
            }
        })
    }

    fun read_json(count: Int, str: String, list: ArrayList<String>) {
        var recipeList = findViewById<ListView>(R.id.recipe_list)
        var json: String? = null

        try {
            val inputStream: InputStream = assets.open("datasetRecipe.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonarr = JSONArray(json)
            for (i in 0..jsonarr.length()-1) {
                var jsonobj = jsonarr.getJSONObject(i)
                id_arr.add(jsonobj.getInt("id"))
                name_arr.add(jsonobj.getString("name"))
                img_arr.add(jsonobj.getString("imageURL"))
                temp_arr.add(jsonobj.getString("name"))
            }
        // if function for triggering search function
            if (count == 1) {
                var k = 0   //temporary variable needed for further operations
                for (i in 0 until temp_arr.size-1) {
                    for (j in k until name_arr.size-1) {
                        if (name_arr[j].toLowerCase().contains(str.toLowerCase(), false)) {
                            temp_arr[i] = name_arr[j]
                            k += 1
                            break
                        }
                    }
                    if (k == name_arr.size-1)
                        break
                }
                for (i in k until temp_arr.size-1) {
                    temp_arr.removeAt(k)
                }
                val nonDuplicate = temp_arr.distinct()  //an array to store all the recipe names after the search
                temp_arr.clear()
                var adptAfterSearch = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, nonDuplicate)
                recipe_list.adapter = adptAfterSearch

                recipe_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    var item: String = recipe_list.adapter.getItem(position).toString()
                    Toast.makeText(applicationContext, "$item selected", Toast.LENGTH_LONG).show()
                    //to parse the id number of the selected recipe by the user to the next activity
                    var temp = 0
                    for (l in 0..name_arr.size-1) {
                        if (nonDuplicate[position] == name_arr[l]) {
                            temp = l
                            break
                        }
                    }
                    val i1 = Intent(this, ResRecActivity::class.java)
                    i1.putExtra("ItemPosition", temp)
                    startActivity(i1)
                }
            }
            else if (count == 0) {
                //rowArr = fillArrayList(name_arr,img_arr,name_arr)
                //val adpt = CustomAdapter(applicationContext, rowArr)
                val adpt = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, name_arr)
                recipe_list.adapter = adpt

                recipe_list.setOnItemClickListener { parent, view, position, id ->
                    val item: String = recipe_list.adapter.getItem(position).toString()
                    Toast.makeText(applicationContext, "$item selected", Toast.LENGTH_LONG).show()
                    //to parse the id number of the selected recipe by the user to the next activity
                    val i = Intent(this, ResRecActivity::class.java)
                    i.putExtra("ItemPosition", position)
                    startActivity(i)
                }
            }
        // triggering if function for giving priorities to recipes on user's selected ingredients
            else if (count == 2) {
                var ingreMatch = arrayListOf<String>()
                var matched = arrayListOf<String>() //array to store the ingredient names that are matched with user selected for all recipes
                val matchedSize = arrayListOf<Int>() //array to store the total number of ingredients matched with the user selected for all recipes
                var highMatch: Int = list.size // Number of highest matched ingredients
                var prior = arrayListOf<Int>() //array to store the priorities with respect to recipe position
                var cnt1 = 1
                var cnt = 0
                temp_arr.clear()

                for (i in 0 until jsonarr.length()) {
                    i_arr = jsonarr.getJSONObject(i).getString("ingredients")
                    var iJson_arr = JSONArray(i_arr) //different json array for the ingredients
                    for (j in 0..iJson_arr.length()-1) { //fetching ingredient data for individual recipe
                        var iJsonobj = iJson_arr.getJSONObject(j)
                        in_arr.add(iJsonobj.getString("iName"))
                    }
                    for (j in 0 until list.size) { //list is the cache list of ingredients selected by user
                        for (k in 0 until in_arr.size) { //in_arr is array of ingredients required for a recipe
                            if (in_arr[k].toLowerCase().contains(list[j].toLowerCase(), false)) {
                                ingreMatch.add(list[j]) //ingreMatch is the list of Matched ingredients for individual recipe
                                break
                            }
                        }
                    }
                    matched.add(ingreMatch.joinToString(" ")) //all matched ingredients of all recipes
                    matchedSize.add(ingreMatch.size) //number of ingredients matched

                    in_arr.clear()
                    ingreMatch.clear()
                }
                for (m in 0..matchedSize.size-1){
                    prior.add(m,0)
                }
                while (highMatch>-1) { //function to set priorities for the individual recipe
                    for (i in 0..matchedSize.size-1) {
                        if (highMatch==0){
                            break
                        } else if (matchedSize[i]==highMatch) {
                            prior[i]=cnt
                            cnt++
                        }
                    }
                    highMatch--
                }
                for (i in 0 until name_arr.size) {
                    for (j in 0 until prior.size) {
                        if (prior[j] == cnt1) {
                            temp_arr.add(name_arr[j])
                            cnt1++
                        }
                    }
                }
                if (temp_arr.isEmpty()){
                    val builder = AlertDialog.Builder(this)
                    val alertTitle = "Recipes"
                    val alertMessage = "No Recipes found with the selected ingredients. \nPlease select other Ingredients or try again after an update."
                    builder.setTitle(alertTitle)
                    builder.setMessage(alertMessage)
                    builder.setIcon(android.R.drawable.ic_dialog_alert)

                    builder.setPositiveButton("OK"){dialogInterface, which ->
                        val i3= Intent(this, IngredientActivity::class.java)
                        startActivity(i3)
                    }
                    builder.setNeutralButton("Cancel"){dialogInterface , which -> }

                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(true)
                    alertDialog.show()
                }
                //val myAdapter = CustomAdapter(this, temp_arr)
                var adptAfterParsing = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, temp_arr)
                recipe_list.adapter = adptAfterParsing

                recipe_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    var item: String = recipe_list.adapter.getItem(position).toString()
                    Toast.makeText(applicationContext, "$item selected", Toast.LENGTH_LONG).show()
                    //to parse the id number of the selected recipe by the user to the next activity
                    for (k in 0 until name_arr.size) {
                        if (temp_arr[position] == name_arr[k]) {
                            val i = Intent(this, ResRecActivity::class.java)
                            i.putExtra("SearchedItemPosition", k)
                            startActivity(i)
                        }
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
