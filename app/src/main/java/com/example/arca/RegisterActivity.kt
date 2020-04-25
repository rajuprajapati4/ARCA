package com.example.arca

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Button

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(recipe_toolbar)

        //val signup_fb = findViewById<Button>(R.id.signup_fb)
        //val signup_g = findViewById<Button>(R.id.signup_g)
        val signup = findViewById<Button>(R.id.register_em)



        signup.setOnClickListener {
            /*  Intent is just like glue which helps to navigate one activity to another.   */
            val intent = Intent(baseContext, MainActivity::class.java)
            //intent.putExtra("EXTRA_SESSION_ID", sessionId)
            //intent2.putExtra("totalCA", "$labelTotalE")
            //setResult(Activity.RESULT_OK, intent2)
            //startActivityForResult(intent2 , MY_REQUEST_ID);
            //finish()
            startActivity(intent) // startActivity allow you to move
        }
/*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        } */
    }

}
