package com.example.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.R
import java.util.*


class SecondActivity : AppCompatActivity() {

    private var animation1: ObjectAnimator? = null
    private var animation2: ObjectAnimator? = null
    private var button: Button? = null
    private var random: Random? = null
    private var set: AnimatorSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        random = Random()

        set = createAnimation()
        set!!.start()
        set!!.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                val nextX: Float = random!!.nextInt(500).toFloat()
                val nextY: Float = random!!.nextInt(500).toFloat()
                animation1 = ObjectAnimator.ofFloat(button, "x", button!!.x, nextX)
                animation1!!.duration = 1400
                animation2 = ObjectAnimator.ofFloat(button, "y", button!!.y, nextY)
                animation2!!.duration = 1400
                set!!.playTogether(animation1, animation2)
                set!!.start()
            }
        })
    }

    fun onClick(view: View) {
        val string = button!!.text.toString()
        val hitTarget = Integer.valueOf(string)!! + 1
        button!!.text = hitTarget.toString()

        startActivity(Intent(this@SecondActivity, FirstImageActivity::class.java))
    }

    private fun createAnimation(): AnimatorSet {
        val nextX: Float = random!!.nextInt(500).toFloat()
        val nextY: Float = random!!.nextInt(500).toFloat()
        button = findViewById(R.id.button1) as Button
        animation1 = ObjectAnimator.ofFloat(button, "x", nextX)
        animation1!!.duration = 1400
        animation2 = ObjectAnimator.ofFloat(button, "y", nextY)
        animation2!!.duration = 1400
        val set = AnimatorSet()
        set.playTogether(animation1, animation2)
        return set
    }
}