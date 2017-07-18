package com.example.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.R

class KotlinActivityTwo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_two)

        val value: String = intent.getStringExtra("KEY")

        val textView = findViewById(R.id.textView1) as TextView
        textView.text = value
    }
}
