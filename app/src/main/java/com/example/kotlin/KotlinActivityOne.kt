package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.R
import kotlinx.android.synthetic.main.activity_kotlin_one.*

class KotlinActivityOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_one)

        buttonPassValues.setOnClickListener { view ->
            val intent = Intent(this, KotlinActivityTwo::class.java)
            intent.putExtra("KEY", editText1.text.toString())
            startActivity(intent)
        }
    }
}
