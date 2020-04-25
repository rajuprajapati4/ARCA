package com.example.arca

public class RowItem {
    private var heading: String? = null
    private var ImageName : String? = null
    private var matchedIngredients : String? = null

    fun setHeading(theHeading: String?) {
        heading = theHeading
    }

    fun getHeading(): String? {
        return heading
    }

    fun setImageName(theImage: String?) {
        ImageName = theImage
    }

    fun getImageName(): String? {
        return ImageName
    }

    fun setMatchedIngredients(matchedIngre: String?) {
        matchedIngredients = matchedIngre
    }

    fun getMatchedIngredients() : String? {
        return matchedIngredients
    }

}