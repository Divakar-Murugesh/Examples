package com.example.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.R
import kotlinx.android.synthetic.main.activity_kotlin_two.*

class KotlinActivityTwo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_two)

        val value: String = intent.getStringExtra("KEY")

        textView1.text = value
    }
}
