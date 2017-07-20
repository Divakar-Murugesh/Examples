package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.R
import com.sdsmdg.harjot.vectormaster.VectorMasterView
import com.sdsmdg.harjot.vectormaster.models.PathModel
import java.util.*


class SplashActivity : AppCompatActivity() {

    var searchBackState = 0
    var circleTrimEnd = 1f
    var stemTrimStart = 0f
    var stemTrimEnd = 0.185f
    var arrowHeadBottomTrimEnd = 0f
    var arrowHeadTopTrimEnd = 0f

    lateinit var vectorMasterView: VectorMasterView
    lateinit var searchCircle: PathModel
    lateinit var stem: PathModel
    lateinit var arrowUp: PathModel
    lateinit var arrowDown: PathModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        vectorMasterView = findViewById(R.id.vector_view) as VectorMasterView

        searchCircle = vectorMasterView.getPathModelByName("search_circle")
        stem = vectorMasterView.getPathModelByName("stem")
        arrowUp = vectorMasterView.getPathModelByName("arrow_head_top")
        arrowDown = vectorMasterView.getPathModelByName("arrow_head_bottom")
        vectorMasterView.setOnClickListener { view ->
            if (searchBackState == 0) {
                animateSearchToBack()
            } else {
                animateBackToSearch()
            }
        }

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, KotlinActivityOne::class.java))
            finish()
        }, 10000)
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

                searchCircle.setTrimPathEnd(circleTrimEnd)
                stem.setTrimPathEnd(stemTrimEnd)
                stem.setTrimPathStart(stemTrimStart)
                arrowUp.setTrimPathEnd(arrowHeadTopTrimEnd)
                arrowDown.setTrimPathEnd(arrowHeadBottomTrimEnd)

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

                searchCircle.setTrimPathEnd(circleTrimEnd)
                stem.setTrimPathEnd(stemTrimEnd)
                stem.setTrimPathStart(stemTrimStart)
                arrowUp.setTrimPathEnd(arrowHeadTopTrimEnd)
                arrowDown.setTrimPathEnd(arrowHeadBottomTrimEnd)

                runOnUiThread { vectorMasterView.update() }

            }
        }, 0, 1000 / 60)

    }
}
