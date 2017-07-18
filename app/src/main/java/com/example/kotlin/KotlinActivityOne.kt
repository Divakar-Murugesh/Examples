package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.R

class KotlinActivityOne : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_one)

        val button = findViewById(R.id.buttonPassValues) as Button
        val editText = findViewById(R.id.editText1) as EditText

        button.setOnClickListener { view ->
            val intent = Intent(this, KotlinActivityTwo::class.java)
            intent.putExtra("KEY", editText.text.toString())
            startActivity(intent)
        }
    }
}
