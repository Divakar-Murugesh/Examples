package com.example.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ExpandableListView
import com.example.R
import com.example.adapters.ExpandableListViewAdapter
import java.util.*

class AndroidUIControlsActivity : AppCompatActivity() {

    lateinit var expandableListView: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_ui_controls)

        expandableListView = findViewById(R.id.expandableListView) as ExpandableListView

        val modelList = ArrayList<String>()
        modelList.add("Widgets")
        modelList.add("Text")
        modelList.add("Layouts")
        modelList.add("Containers")
        modelList.add("Images")
        modelList.add("Date")
        modelList.add("Transitions")
        modelList.add("Advanced")
        modelList.add("Google")
        modelList.add("Design")
        modelList.add("AppCompat")

        expandableListView.setAdapter(ExpandableListViewAdapter(this@AndroidUIControlsActivity, modelList))
    }

}