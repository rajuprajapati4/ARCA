package com.example.arca

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso


class CustomAdapter(context: Context, aRow: ArrayList<RowItem>): BaseAdapter() {

    private var singleRow: ArrayList<RowItem> = aRow
    private var thisInflater: LayoutInflater = LayoutInflater.from(context)
    private var contex: Context = context


    override fun getItem(position: Int): Any {
        return singleRow[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return singleRow.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
         if (convertView==null) {
             val rowView = thisInflater.inflate(com.example.arca.R.layout.list_view_row, parent, false)

             val theHeading = rowView!!.findViewById(com.example.arca.R.id.recipe_name) as TextView
             val theImage: ImageView = rowView.findViewById(com.example.arca.R.id.recipe_image) as ImageView
             //val theButton = rowView.findViewById(com.example.arca.R.id.show_ingredients) as Button

             val currentRow = getItem(position) as RowItem

             theHeading.text = currentRow.getHeading()
             //theImage.setImageResource(currentRow.getSmallImageName())
             Picasso.with(contex).load(currentRow.getImageName()).into(theImage)
             /*
             theButton.setOnClickListener{
                 var matchedIngre : String? = currentRow.getMatchedIngredients()
                 var list: ArreyList<String> = matchedIngre.split(" ")
                 val menu = PopupMenu(contex, rowView)
                 for(i in 0..list.size){
                     menu.menu.add(list[i])
                 }
                 menu.show()
                 //Creating the instance of PopupMenu
                 val popup = PopupMenu(contex, theButton)
                 //Inflating the Popup using xml file
                 popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu())
                 //registering popup with OnMenuItemClickListener
                 //popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener() {
                 //    fun onMenuItemClick(item: MenuItem): Boolean {
                 //        return true
                 //    }
                 //})
                 popup.show()
             }      */
             return rowView
         }else{
             return convertView
         }
    }
}