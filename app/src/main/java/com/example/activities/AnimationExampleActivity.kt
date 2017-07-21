package com.example.activities

import android.animation.*
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.R
import com.sdsmdg.harjot.vectormaster.VectorMasterView
import com.sdsmdg.harjot.vectormaster.models.PathModel
import java.util.*


class AnimationExampleActivity : AppCompatActivity() {

    private lateinit var animation1: ObjectAnimator
    private lateinit var animation2: ObjectAnimator
    private lateinit var button: Button
    private lateinit var random: Random
    private lateinit var set: AnimatorSet

    private var searchBackState = 0
    private var circleTrimEnd = 1f
    private var stemTrimStart = 0f
    private var stemTrimEnd = 0.185f
    private var arrowHeadBottomTrimEnd = 0f
    private var arrowHeadTopTrimEnd = 0f

    private lateinit var vectorMasterView: VectorMasterView
    private lateinit var searchCircle: PathModel
    private lateinit var stem: PathModel
    private lateinit var arrowUp: PathModel
    private lateinit var arrowDown: PathModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_example)

        random = Random()

        set = createAnimation()
        set.start()
        set.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                val nextX: Float = random.nextInt(500).toFloat()
                val nextY: Float = random.nextInt(150).toFloat()
                animation1 = ObjectAnimator.ofFloat(button, "x", button.x, nextX)
                animation1.duration = 1400
                animation2 = ObjectAnimator.ofFloat(button, "y", button.y, nextY)
                animation2.duration = 1400
                set.playTogether(animation1, animation2)
                set.start()
            }
        })

        vectorMasterView = findViewById(R.id.vector_view) as VectorMasterView

        searchCircle = vectorMasterView.getPathModelByName("search_circle")
        stem = vectorMasterView.getPathModelByName("stem")
        arrowUp = vectorMasterView.getPathModelByName("arrow_head_top")
        arrowDown = vectorMasterView.getPathModelByName("arrow_head_bottom")
        vectorMasterView.setOnClickListener { _ ->
            if (searchBackState == 0) {
                animateSearchToBack()
            } else {
                animateBackToSearch()
            }
        }

        val l = LayoutTransition()
        l.enableTransitionType(LayoutTransition.CHANGING)
        val scrollView = findViewById(R.id.scrollView) as ScrollView
        val linearLayout = findViewById(R.id.linearLayout) as LinearLayout
        linearLayout.layoutTransition = l

        val textView1 = findViewById(R.id.textView1) as TextView
        val button1 = findViewById(R.id.button1) as Button
        val button2 = findViewById(R.id.button2) as Button

        // Start Action
        button1.animate().translationX(350f).setDuration(1000).withStartAction {
            textView1.animate().translationX((500 - textView1.width / 2).toFloat()).setDuration(1000).withLayer()

            // End Action
            button1.animate().alpha(0f).withEndAction {
                // Remove view from parent layout
                linearLayout.removeView(button1)
            }
        }

        button2.setOnClickListener { _ ->
            val newButton = Button(this@AnimationExampleActivity)
            newButton.text = "Button Added"
            linearLayout.addView(newButton)

            Handler().postDelayed({
                scrollView.fullScroll(ScrollView.FOCUS_DOWN)
            }, 500)
        }

        val sharedImage = findViewById(R.id.imageView1) as ImageView
        sharedImage.setOnClickListener { _ ->
            //This is where the magic happens.
            // makeSceneTransitionAnimation takes a context, view,
            // a name for the target view.
            var options: ActivityOptions? = null
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                options = ActivityOptions.makeSceneTransitionAnimation(this@AnimationExampleActivity, sharedImage, "sharedImage")
            }
            val intent = Intent(this@AnimationExampleActivity, DataPreviewActivity::class.java)
            if (options != null) {
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }
        }

    }

    fun onClick(view: View) {
        val string = (view as Button).text.toString()
        val hitTarget = Integer.valueOf(string)!! + 1
        view.text = hitTarget.toString()
    }

    private fun createAnimation(): AnimatorSet {
        val nextX: Float = random.nextInt(500).toFloat()
        val nextY: Float = random.nextInt(150).toFloat()
        button = findViewById(R.id.button) as Button
        animation1 = ObjectAnimator.ofFloat(button, "x", nextX)
        animation1.duration = 1400
        animation2 = ObjectAnimator.ofFloat(button, "y", nextY)
        animation2.duration = 1400
        val set = AnimatorSet()
        set.playTogether(animation1, animation2)
        return set
    }

    fun animateSearchToBack() {

        Timer().schedule(object : TimerTask() {
            override fun run() {

                circleTrimEnd -= 1.0f / 20
                stemTrimStart += 0.75f / 20
                stemTrimEnd += (1 - 0.185f) / 20
                arrowHeadBottomTrimEnd += 1.0f / 20
                arrowHeadTopTrimEnd += 1.0f / 20

                if (circleTrimEnd <= 0) {
                    searchBackState = 1
                    cancel()
                }

                searchCircle.trimPathEnd = circleTrimEnd
                stem.trimPathEnd = stemTrimEnd
                stem.trimPathStart = stemTrimStart
                arrowUp.trimPathEnd = arrowHeadTopTrimEnd
                arrowDown.trimPathEnd = arrowHeadBottomTrimEnd

                runOnUiThread { vectorMasterView.update() }

            }
        }, 0, 1000 / 60)

    }

    fun animateBackToSearch() {

        Timer().schedule(object : TimerTask() {
            override fun run() {

                arrowHeadBottomTrimEnd -= 1.0f / 20
                arrowHeadTopTrimEnd -= 1.0f / 20
                stemTrimStart -= 0.75f / 20
                stemTrimEnd -= (1 - 0.185f) / 20
                circleTrimEnd += 1.0f / 20

                if (circleTrimEnd >= 1) {
                    searchBackState = 0
                    cancel()
                }

                searchCircle.trimPathEnd = circleTrimEnd
                stem.trimPathEnd = stemTrimEnd
                stem.trimPathStart = stemTrimStart
                arrowUp.trimPathEnd = arrowHeadTopTrimEnd
                arrowDown.trimPathEnd = arrowHeadBottomTrimEnd

                runOnUiThread { vectorMasterView.update() }

            }
        }, 0, 1000 / 60)

    }

    fun startAnimation(view: View) {
        var dest: Float
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
