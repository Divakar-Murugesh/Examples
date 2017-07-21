package com.example.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.R
import com.example.activities.FragmentsActivity
import com.example.interfaces.FragmentCommunicator


class ThirdFragment : Fragment(), FragmentCommunicator {

    private var textView1: TextView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_third, container, false)

        textView1 = view.findViewById<View>(R.id.textView1) as TextView

        if (activity is FragmentsActivity) {
            (activity as FragmentsActivity).fragmentCommunicators += this
        }

        return view
    }

    override fun passDataToFragment(data: String) {

        textView1!!.text = data
    }
}