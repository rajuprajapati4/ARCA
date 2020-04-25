package com.example.arca

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class FTBActivity : AppCompatActivity() {

    var land = 0

    var sampleImages = intArrayOf(
        R.drawable.car0,
        R.drawable.car1,
        R.drawable.car2
    )

    var sampleImagesLand = intArrayOf(
    //    R.drawable.carLand0,
        R.drawable.ma,
        R.drawable.ia
    )
/* THE LANDSCAPE FUNCTIONAITY IS NOT YET FINISHED */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        var temp=land;
        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
            temp++
        }
        land=temp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ftb)

        val btnNext = findViewById<Button>(R.id.ftb_next)
        val carouselView = findViewById<CarouselView>(R.id.carouselView)
        if (land==0) {
            carouselView.setPageCount(sampleImages.size)
            carouselView.setImageListener(imageListener)
        }else if (land!=0) {
            carouselView.setPageCount(sampleImagesLand.size)
            carouselView.setImageListener(imageListenerLand)
        }

        btnNext.setOnClickListener {
            /*  Intent is just like glue which helps to navigate one activity to another.   */
            val intent = Intent(this, MainActivity::class.java)
            /*
            intent.putExtra("EXTRA_SESSION_ID", sessionId)
            intent2.putExtra("totalCA", "$labelTotalE")
            setResult(Activity.RESULT_OK, intent2)
            startActivityForResult(intent2 , MY_REQUEST_ID);
            finish()
            */
            startActivity(intent) // startActivity allow you to move
        }
    }
    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView){
            //Can also add Glide or Picasso here
            //Picasso.get().load(sampleImages[position]).into(imageView)
            imageView.setImageResource(sampleImages[position])
        }
    }
    var imageListenerLand: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView){
            imageView.setImageResource(sampleImagesLand[position])
        }
    }
}
