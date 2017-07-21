package com.example.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.R
import com.example.adapters.FragmentAdapter
import com.example.fragments.FirstFragment
import com.example.fragments.SecondFragment
import com.example.fragments.ThirdFragment
import com.example.interfaces.FragmentCommunicator
import java.util.*

class FragmentsActivity : AppCompatActivity() {

    var fragmentCommunicators: List<FragmentCommunicator> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        val spinner1 = findViewById(R.id.spinner1) as Spinner
        spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOf("Value One", "Value Two", "Value Three"))
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {

                for (fragmentCommunicator in fragmentCommunicators) {
                    fragmentCommunicator.passDataToFragment(spinner1.selectedItem.toString())
                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }

        val viewPager1 = findViewById(R.id.viewPager1) as ViewPager
        val adapter = FragmentAdapter(supportFragmentManager)
        adapter.addFragment(FirstFragment(), "FirstFragment")
        adapter.addFragment(SecondFragment(), "SecondFragment")
        adapter.addFragment(ThirdFragment(), "ThirdFragment")
        viewPager1.adapter = adapter

        viewPager1.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                for (fragmentCommunicator in fragmentCommunicators) {
                    fragmentCommunicator.passDataToFragment(spinner1.selectedItem.toString())
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}
