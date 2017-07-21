package com.example.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.R


class FirstImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_image)

        val sharedImage = findViewById(R.id.sharedimage) as ImageView
        sharedImage.setOnClickListener {
            //This is where the magic happens.
            // makeSceneTransitionAnimation takes a context, view,
            // a name for the target view.
            var options: ActivityOptions? = null
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                options = ActivityOptions.makeSceneTransitionAnimation(this@FirstImageActivity, sharedImage, "sharedImage")
            }
            val intent = Intent(this@FirstImageActivity, SecondImageActivity::class.java)
            if (options != null) {
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }
        }
    }
}