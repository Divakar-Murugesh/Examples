package com.example.activities

import android.animation.AnimatorSet
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.R


class AnimationExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        val l = LayoutTransition()
        l.enableTransitionType(LayoutTransition.CHANGING)
        val linearLayout1 = findViewById(R.id.layout1) as LinearLayout
        linearLayout1.layoutTransition = l

        val textView1 = findViewById(R.id.textView1) as TextView
        val button1 = findViewById(R.id.button1) as Button
        val button2 = findViewById(R.id.button2) as Button
        val button3 = findViewById(R.id.button3) as Button

        // Start Action
        button1.animate().translationX(350f).setDuration(1000).withStartAction {
            textView1.animate().translationX((500 - textView1.width / 2).toFloat()).setDuration(1000).withLayer()

            // End Action
            button1.animate().alpha(0f).withEndAction {
                // Remove view from parent layout
                linearLayout1.removeView(button1)
            }
        }

        button2.setOnClickListener { linearLayout1.addView(Button(this@AnimationExampleActivity)) }

        button3.setOnClickListener { view ->
            val intent = Intent(this@AnimationExampleActivity, SecondActivity::class.java)
            val options = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.width, view.height)
            startActivity(intent, options.toBundle())
        }

    }

    fun startAnimation(view: View) {
        var dest = 0f
        val aniView = findViewById(R.id.imageView1) as ImageView
        when (view.id) {

            R.id.Button01 -> {
                dest = 360f
                if (aniView.rotation == 360f) {
                    println(aniView.alpha)
                    dest = 0f
                }
                val animation1 = ObjectAnimator.ofFloat(aniView, "rotation", dest)
                animation1.duration = 2000
                animation1.start()
            }

            R.id.Button02 -> {
                // shows how to define a animation via code
                // also use an Interpolator (BounceInterpolator)
                val paint = Paint()
                val aniTextView = findViewById(R.id.textView1) as TextView
                val measureTextCenter = paint.measureText(aniTextView.text.toString())
                dest = 0 - measureTextCenter
                if (aniTextView.x < 0) {
                    dest = 0f
                }
                val animation2 = ObjectAnimator.ofFloat(aniTextView, "x", dest)
                animation2.duration = 2000
                animation2.start()
            }

            R.id.Button03 -> {
                // demonstrate fading and adding an AnimationListener

                dest = 1f
                if (aniView.alpha > 0) {
                    dest = 0f
                }
                val animation3 = ObjectAnimator.ofFloat(aniView, "alpha", dest)
                animation3.duration = 2000
                animation3.start()
            }

            R.id.Button04 -> {

                val fadeOut = ObjectAnimator.ofFloat(aniView, "alpha", 0f)
                fadeOut.duration = 2000
                val mover = ObjectAnimator.ofFloat(aniView, "translationX", -500f, 0f)
                mover.duration = 2000
                val fadeIn = ObjectAnimator.ofFloat(aniView, "alpha", 0f, 1f)
                fadeIn.duration = 2000
                val animatorSet = AnimatorSet()

                animatorSet.play(mover).with(fadeIn).after(fadeOut)
                animatorSet.start()
            }

            else -> {
            }
        }

    }
}