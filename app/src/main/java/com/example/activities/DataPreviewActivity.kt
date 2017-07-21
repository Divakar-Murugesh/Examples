package com.example.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.R
import kotlinx.android.synthetic.main.activity_data_preview.*


class DataPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_preview)

        val value: String? = intent.getStringExtra("KEY")

        if (value == null) {
            sharedimage.visibility = View.VISIBLE
            textView1.visibility = View.GONE
        } else {
            sharedimage.visibility = View.GONE
            textView1.visibility = View.VISIBLE
            textView1.text = value
        }
    }

}