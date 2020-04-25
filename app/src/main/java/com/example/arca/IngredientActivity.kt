package com.example.arca

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ingredient.*
import android.widget.*
import android.view.View
import java.io.*
import java.lang.StringBuilder

class IngredientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient)
        setSupportActionBar(ingrdient_toolbar)

    }

    var brie = 0; var butter = 0; var cheese = 0; var condensedMilk = 0; var cream = 0; var custard = 0; var egg = 0; var feta = 0; var frosting = 0; var ghee = 0; var gruyere = 0; var halloumi = 0
    var icecream = 0; var mascarpone = 0; var mayonaise = 0; var milk = 0; var mozzarella = 0; var pepperJack = 0; var provolone = 0; var ricotta = 0; var romano = 0; var sourCream = 0 ; var yogurt = 0

    var artichoke = 0; var arugula = 0; var asparagus = 0; var avocado = 0; var basil = 0; var beet = 0; var bellPepper = 0; var broccoli = 0; var brusselSprout = 0; var burdock = 0; var butternut = 0
    var cabbage = 0; var cannedTomato = 0; var caper = 0; var capsicum = 0; var carrot = 0; var cauliflower = 0; var celery = 0; var chard = 0; var chayote = 0; var chiaSeeds = 0; var chilliPepper = 0
    var cilantro = 0; var corn = 0; var cucumber = 0; var daikon = 0; var dill = 0; var dulse = 0; var garlic = 0; var ginger = 0; var greenBeans = 0; var kale = 0; var leek = 0; var mint = 0; var mushroom = 0
    var okra = 0; var olive = 0; var onion = 0; var parsley = 0; var pickle = 0; var pimiento = 0; var porcini = 0; var potato = 0; var pumpkin = 0; var radish = 0; var redOnion = 0; var rosemary = 0
    var sauerkraut = 0; var scallion = 0; var shallot = 0; var snowPeas = 0; var spinach = 0; var squash = 0; var sweetPepper = 0; var sweetPotato = 0; var tomato = 0; var yam = 0; var zenBamboo = 0; var zucchini = 0

    var almond = 0; var cashew = 0; var peanutButter = 0; var pistachio = 0; var walnut = 0

    var count = 0

    val t: ArrayList<String> = arrayListOf()

    fun onCheckboxClicked(view: View) {

        val selection = findViewById<Button>(R.id.ingre_selection)
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.id_Brie -> {
                    if (checked) {
                        brie = 1;t.add("brie")
                    } else {
                        brie = 0;t.remove("brie")
                    }
                }
                R.id.id_Butter -> {
                    if (checked) {
                        butter = 1;t.add("butter")
                    } else {
                        butter = 0;t.remove("butter")
                    }
                }
                R.id.id_Cheese -> {
                    if (checked) {
                        cheese = 1;t.add("cheese")
                    } else {
                        cheese = 0;t.remove("cheese")
                    }
                }
                R.id.id_CondensedMilk -> {
                    if (checked) {
                        condensedMilk = 1;t.add("condensed milk")
                    } else {
                        condensedMilk = 0;t.remove("condensed milk")
                    }
                }
                R.id.id_Cream -> {
                    if (checked) {
                        cream = 1;t.add("cream")
                    } else {
                        cream = 0;t.remove("cream")
                    }
                }
                R.id.id_Custard -> {
                    if (checked) {
                        custard = 1;t.add("custard")
                    } else {
                        custard = 0;t.remove("custard")
                    }
                }
                R.id.id_Egg -> {
                    if (checked) {
                        egg = 1;t.add("egg")
                    } else {
                        egg = 0;t.remove("egg")
                    }
                }
                R.id.id_Feta -> {
                    if (checked) {
                        feta = 1;t.add("feta")
                    } else {
                        feta = 0;t.remove("feta")
                    }
                }
                R.id.id_Frosting -> {
                    if (checked) {
                        frosting = 1;t.add("frosting")
                    } else {
                        frosting = 0;t.remove("frosting")
                    }
                }
                R.id.id_Ghee -> {
                    if (checked) {
                        ghee = 1;t.add("ghee")
                    } else {
                        ghee = 0;t.remove("ghee")
                    }
                }
                R.id.id_Gruyere -> {
                    if (checked) {
                        gruyere = 1;t.add("gruyere")
                    } else {
                        gruyere = 0;t.remove("gruyere")
                    }
                }
                R.id.id_Halloumi -> {
                    if (checked) {
                        halloumi = 1;t.add("halloumi")
                    } else {
                        halloumi = 0;t.remove("halloumi")
                    }
                }
                R.id.id_Icecream -> {
                    if (checked) {
                        icecream = 1;t.add("ice cream")
                    } else {
                        icecream = 0;t.remove("ice cream")
                    }
                }
                R.id.id_Mascarpone -> {
                    if (checked) {
                        mascarpone = 1;t.add("mascarpone")
                    } else {
                        mascarpone = 0;t.remove("mascarpone")
                    }
                }
                R.id.id_Mayonaise -> {
                    if (checked) {
                        mayonaise = 1;t.add("mayonaise")
                    } else {
                        mayonaise = 0;t.remove("mayonaise")
                    }
                }
                R.id.id_Milk -> {
                    if (checked) {
                        milk = 1;t.add("milk")
                    } else {
                        milk = 0;t.remove("milk")
                    }
                }
                R.id.id_Mozzarella -> {
                    if (checked) {
                        mozzarella = 1;t.add("mozzarella")
                    } else {
                        mozzarella = 0;t.remove("mozzarella")
                    }
                }
                R.id.id_PepperJack -> {
                    if (checked) {
                        pepperJack = 1;t.add("pepper jack")
                    } else {
                        pepperJack = 0;t.remove("pepper jack")
                    }
                }
                R.id.id_Provolone -> {
                    if (checked) {
                        provolone = 1;t.add("provolone")
                    } else {
                        provolone = 0;t.remove("provolone")
                    }
                }
                R.id.id_Ricotta -> {
                    if (checked) {
                        ricotta = 1;t.add("ricotta")
                    } else {
                        ricotta = 0;t.remove("ricotta")
                    }
                }
                R.id.id_Romano -> {
                    if (checked) {
                        romano = 1;t.add("romano")
                    } else {
                        romano = 0;t.remove("romano")
                    }
                }
                R.id.id_SourCream -> {
                    if (checked) {
                        sourCream = 1;t.add("sour cream")
                    } else {
                        sourCream = 0;t.remove("sour cream")
                    }
                }
                R.id.id_Yogurt -> {
                    if (checked) {
                        yogurt = 1;t.add("yogurt")
                    } else {
                        yogurt = 0;t.remove("yogurt")
                    }
                }

                R.id.iv_Artichoke -> {
                    if (checked) {
                        artichoke = 1;t.add("artichoke")
                    } else {
                        artichoke = 0;t.remove("artichoke")
                    }
                }
                R.id.iv_Arugula -> {
                    if (checked) {
                        arugula = 1;t.add("arugula")
                    } else {
                        arugula = 0;t.remove("arugula")
                    }
                }
                R.id.iv_Asparagus -> {
                    if (checked) {
                        asparagus = 1;t.add("asparagus")
                    } else {
                        asparagus = 0;t.remove("asparagus")
                    }
                }
                R.id.iv_Avocado -> {
                    if (checked) {
                        avocado = 1;t.add("avocado")
                    } else {
                        avocado = 0;t.remove("avocado")
                    }
                }
                R.id.iv_Basil -> {
                    if (checked) {
                        basil = 1;t.add("basil")
                    } else {
                        basil = 0;t.remove("basil")
                    }
                }
                R.id.iv_Beet -> {
                    if (checked) {
                        beet = 1;t.add("beet")
                    } else {
                        beet = 0;t.remove("beet")
                    }
                }
                R.id.iv_BellPepper -> {
                    if (checked) {
                        bellPepper = 1;t.add("bell pepper")
                    } else {
                        bellPepper = 0;t.remove("bell pepper")
                    }
                }
                R.id.iv_Broccoli -> {
                    if (checked) {
                        broccoli = 1;t.add("broccoli")
                    } else {
                        broccoli = 0;t.remove("broccoli")
                    }
                }
                R.id.iv_BrusselSprout -> {
                    if (checked) {
                        brusselSprout = 1;t.add("brussel sprout")
                    } else {
                        brusselSprout = 0;t.remove("brussel sprout")
                    }
                }
                R.id.iv_Burdock -> {
                    if (checked) {
                        burdock = 1;t.add("burdock")
                    } else {
                        burdock = 0;t.remove("burdock")
                    }
                }
                R.id.iv_Butternut -> {
                    if (checked) {
                        butternut = 1;t.add("butternut")
                    } else {
                        butternut = 0;t.remove("butternut")
                    }
                }
                R.id.iv_Cabbage -> {
                    if (checked) {
                        cabbage = 1;t.add("cabbage")
                    } else {
                        cabbage = 0;t.remove("cabbage")
                    }
                }
                R.id.iv_CannedTomato -> {
                    if (checked) {
                        cannedTomato = 1;t.add("canned tomato")
                    } else {
                        cannedTomato = 0;t.remove("canned tomato")
                    }
                }
                R.id.iv_Caper -> {
                    if (checked) {
                        caper = 1;t.add("caper")
                    } else {
                        caper = 0;t.remove("caper")
                    }
                }
                R.id.iv_Capsicum -> {
                    if (checked) {
                        capsicum = 1;t.add("capsicum")
                    } else {
                        capsicum = 0;t.remove("capsicum")
                    }
                }
                R.id.iv_Carrot -> {
                    if (checked) {
                        carrot = 1;t.add("carrot")
                    } else {
                        carrot = 0;t.remove("carrot")
                    }
                }
                R.id.iv_Cauliflower -> {
                    if (checked) {
                        cauliflower = 1;t.add("cauliflower")
                    } else {
                        cauliflower = 0;t.remove("cauliflower")
                    }
                }
                R.id.iv_Celery -> {
                    if (checked) {
                        celery = 1;t.add("celery")
                    } else {
                        celery = 0;t.remove("celery")
                    }
                }
                R.id.iv_Chard -> {
                    if (checked) {
                        chard = 1;t.add("chard")
                    } else {
                        chard = 0;t.remove("chard")
                    }
                }
                R.id.iv_Chayote -> {
                    if (checked) {
                        chayote = 1;t.add("chayote")
                    } else {
                        chayote = 0;t.remove("chayote")
                    }
                }
                R.id.iv_ChiaSeeds -> {
                    if (checked) {
                        chiaSeeds = 1;t.add("chia seeds")
                    } else {
                        chiaSeeds = 0;t.remove("chia seeds")
                    }
                }
                R.id.iv_ChilliPepper -> {
                    if (checked) {
                        chilliPepper = 1;t.add("chilli pepper")
                    } else {
                        chilliPepper = 0;t.remove("chilli pepper")
                    }
                }
                R.id.iv_Cilantro -> {
                    if (checked) {
                        cilantro = 1;t.add("cilantro")
                    } else {
                        cilantro = 0;t.remove("cilantro")
                    }
                }
                R.id.iv_Corn -> {
                    if (checked) {
                        corn = 1;t.add("corn")
                    } else {
                        corn = 0;t.remove("corn")
                    }
                }
                R.id.iv_Cucumber -> {
                    if (checked) {
                        cucumber = 1;t.add("cucumber")
                    } else {
                        cucumber = 0;t.remove("cucumber")
                    }
                }
                R.id.iv_Daikon -> {
                    if (checked) {
                        daikon = 1;t.add("daikon")
                    } else {
                        daikon = 0;t.remove("daikon")
                    }
                }
                R.id.iv_Dill -> {
                    if (checked) {
                        dill = 1;t.add("dill")
                    } else {
                        dill = 0;t.remove("dill")
                    }
                }
                R.id.iv_Dulse -> {
                    if (checked) {
                        dulse = 1;t.add("dulse")
                    } else {
                        dulse = 0;t.remove("dulse")
                    }
                }
                R.id.iv_Garlic -> {
                    if (checked) {
                        garlic = 1;t.add("garlic")
                    } else {
                        garlic = 0;t.remove("garlic")
                    }
                }
                R.id.iv_Ginger -> {
                    if (checked) {
                        ginger = 1;t.add("ginger")
                    } else {
                        ginger = 0;t.remove("ginger")
                    }
                }
                R.id.iv_GreenBeans -> {
                    if (checked) {
                        greenBeans = 1;t.add("green beans")
                    } else {
                        greenBeans = 0;t.remove("green beans")
                    }
                }
                R.id.iv_Kale -> {
                    if (checked) {
                        kale = 1;t.add("kale")
                    } else {
                        kale = 0;t.remove("kale")
                    }
                }
                R.id.iv_Leek -> {
                    if (checked) {
                        leek = 1;t.add("leek")
                    } else {
                        leek = 0;t.remove("leek")
                    }
                }
                R.id.iv_Mint -> {
                    if (checked) {
                        mint = 1;t.add("mint")
                    } else {
                        mint = 0;t.remove("mint")
                    }
                }
                R.id.iv_Mushroom -> {
                    if (checked) {
                        mushroom = 1;t.add("mushroom")
                    } else {
                        mushroom = 0;t.remove("mushroom")
                    }
                }
                R.id.iv_Okra -> {
                    if (checked) {
                        okra = 1;t.add("okra")
                    } else {
                        okra = 0;t.remove("okra")
                    }
                }
                R.id.iv_Olive -> {
                    if (checked) {
                        olive = 1;t.add("olive")
                    } else {
                        olive = 0;t.remove("olive")
                    }
                }
                R.id.iv_Onion -> {
                    if (checked) {
                        onion = 1;t.add("onion")
                    } else {
                        onion = 0;t.remove("onion")
                    }
                }
                R.id.iv_Parsley -> {
                    if (checked) {
                        parsley = 1;t.add("parsley")
                    } else {
                        parsley = 0;t.remove("parsley")
                    }
                }
                R.id.iv_Pickle -> {
                    if (checked) {
                        pickle = 1;t.add("pickle")
                    } else {
                        pickle = 0;t.remove("pickle")
                    }
                }
                R.id.iv_Pimiento -> {
                    if (checked) {
                        pimiento = 1;t.add("pimiento")
                    } else {
                        pimiento = 0;t.remove("pimiento")
                    }
                }
                R.id.iv_Porcini -> {
                    if (checked) {
                        porcini = 1;t.add("porcini")
                    } else {
                        porcini = 0;t.remove("porcini")
                    }
                }
                R.id.iv_Potato -> {
                    if (checked) {
                        potato = 1;t.add("potato")
                    } else {
                        potato = 0;t.remove("potato")
                    }
                }
                R.id.iv_Pumpkin -> {
                    if (checked) {
                        pumpkin = 1;t.add("pumpkin")
                    } else {
                        pumpkin = 0;t.remove("pumpkin")
                    }
                }
                R.id.iv_Radish -> {
                    if (checked) {
                        radish = 1;t.add("radish")
                    } else {
                        radish = 0;t.remove("radish")
                    }
                }
                R.id.iv_RedOnion -> {
                    if (checked) {
                        redOnion = 1;t.add("red onion")
                    } else {
                        redOnion = 0;t.remove("red onion")
                    }
                }
                R.id.iv_Rosemary -> {
                    if (checked) {
                        rosemary = 1;t.add("rosemary")
                    } else {
                        rosemary = 0;t.remove("rosemary")
                    }
                }
                R.id.iv_Sauerkraut -> {
                    if (checked) {
                        sauerkraut = 1;t.add("sauerkraut")
                    } else {
                        sauerkraut = 0;t.remove("sauerkraut")
                    }
                }
                R.id.iv_Scallion -> {
                    if (checked) {
                        scallion = 1;t.add("scallion")
                    } else {
                        scallion = 0;t.remove("scallion")
                    }
                }
                R.id.iv_Shallot -> {
                    if (checked) {
                        shallot = 1;t.add("shallot")
                    } else {
                        shallot = 0;t.remove("shallot")
                    }
                }
                R.id.iv_SnowPeas -> {
                    if (checked) {
                        snowPeas = 1;t.add("snow peas")
                    } else {
                        snowPeas = 0;t.remove("snow peas")
                    }
                }
                R.id.iv_Spinach -> {
                    if (checked) {
                        spinach = 1;t.add("spinach")
                    } else {
                        spinach = 0;t.remove("spinach")
                    }
                }
                R.id.iv_Squash -> {
                    if (checked) {
                        squash = 1;t.add("squash")
                    } else {
                        squash = 0;t.remove("squash")
                    }
                }
                R.id.iv_SweetPepper -> {
                    if (checked) {
                        sweetPepper = 1;t.add("sweet pepper")
                    } else {
                        sweetPepper = 0;t.remove("sweet pepper")
                    }
                }
                R.id.iv_SweetPotato -> {
                    if (checked) {
                        sweetPotato = 1;t.add("sweet potato")
                    } else {
                        sweetPotato = 0;t.remove("sweet potato")
                    }
                }
                R.id.iv_Tomato -> {
                    if (checked) {
                        tomato = 1;t.add("tomato")
                    } else {
                        tomato = 0;t.remove("tomato")
                    }
                }
                R.id.iv_Yam -> {
                    if (checked) {
                        yam = 1;t.add("yam")
                    } else {
                        yam = 0;t.remove("yam")
                    }
                }
                R.id.iv_ZenBamboo -> {
                    if (checked) {
                        zenBamboo = 1;t.add("zen bamboo")
                    } else {
                        zenBamboo = 0;t.remove("zen bamboo")
                    }
                }
                R.id.iv_Zucchini -> {
                    if (checked) {
                        zucchini = 1;t.add("zucchini")
                    } else {
                        zucchini = 0;t.remove("zucchini")
                    }
                }

                R.id.in_Almond -> {
                    if (checked) {
                        almond = 1;t.add("almond")
                    } else {
                        almond = 0;t.remove("almond")
                    }
                }
                R.id.in_Cashew -> {
                    if (checked) {
                        cashew = 1;t.add("cashew")
                    } else {
                        cashew = 0;t.remove("cashew")
                    }
                }
                R.id.in_PeanutButter -> {
                    if (checked) {
                        peanutButter = 1;t.add("peanut butter")
                    } else {
                        peanutButter = 0;t.remove("peanut butter")
                    }
                }
                R.id.in_Pistachio -> {
                    if (checked) {
                        pistachio = 1;t.add("pistachio")
                    } else {
                        pistachio = 0;t.remove("pistachio")
                    }
                }
                R.id.in_Walnut -> {
                    if (checked) {
                        walnut = 1;t.add("walnut")
                    } else {
                        walnut = 0;t.remove("walnut")
                    }
                }
            }
        }
        selection.setOnClickListener {
            count = brie + butter + cheese + condensedMilk + custard + cream + egg + feta + frosting + ghee + gruyere + halloumi + icecream + mascarpone + mayonaise + milk + mozzarella + pepperJack + provolone + ricotta + romano + sourCream + yogurt + artichoke + arugula + asparagus + avocado + basil + beet + bellPepper + broccoli + brusselSprout + burdock + butternut + cabbage + cannedTomato + caper + capsicum + carrot + cauliflower + celery + chard + chayote + chiaSeeds + chilliPepper + cilantro + corn + cucumber + daikon + dill + dulse + garlic + ginger + greenBeans + kale + leek + mint + mushroom + okra + olive + onion + parsley + pickle + pimiento + porcini + potato + pumpkin + radish + redOnion + rosemary + sauerkraut + scallion + shallot + snowPeas + spinach + squash + sweetPepper + sweetPotato + tomato + yam + zenBamboo + zucchini + almond + cashew + peanutButter + pistachio + walnut
            if (count<4){
                val builder = AlertDialog.Builder(this)
                val alertTitle = "Insufficient Ingredients Selected"
                val alertMessage = "Please select atleast 4 ingredients to proceed."
                builder.setTitle(alertTitle)
                builder.setMessage(alertMessage)
                builder.setIcon(android.R.drawable.ic_dialog_alert)

                builder.setPositiveButton("OK"){dialogInterface, which -> }
                builder.setNeutralButton("Cancel"){dialogInterface , which -> }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(true)
                alertDialog.show()
            } else {
                selection.text = ("Selected Ingredients = $count")
                next(t)
            }
        }
    }

    fun next(text: ArrayList<String>) {
        Toast.makeText(this, "Please select the desired recipe from the list.", Toast.LENGTH_LONG).show()
        val i2 = Intent(this, RecipeActivity::class.java)
        i2.putExtra("Choice", 2)
        i2.putStringArrayListExtra("IngredientList", text)
        startActivity(i2) // startActivity allow you to move
    }
}
