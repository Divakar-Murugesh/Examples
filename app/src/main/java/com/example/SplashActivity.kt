package com.example

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sdsmdg.harjot.vectormaster.VectorMasterView
import java.util.*

class SplashActivity : AppCompatActivity() {

    internal lateinit var typeface: Typeface
    internal lateinit var text: TextView

    internal lateinit var lightningView: VectorMasterView
    internal lateinit var hourglassView: VectorMasterView
    internal lateinit var searchBackView: VectorMasterView
    internal lateinit var rainView: VectorMasterView

    internal var trimStart = 0f
    internal var trimEnd = 0f
    internal var countLightning = 60

    internal var translationY = 0f
    internal var rotation = 0f
    internal var state = 0

    internal var stemTrimStart = 0f
    internal var stemTrimEnd = 0.185f

    internal var circleTrimEnd = 1f

    internal var arrowHeadTopTrimEnd = 0f
    internal var arrowHeadBottomTrimEnd = 0f

    internal var searchBackState = 0
    internal var count = 60

    internal var rainTrimStart_C = 0f
    internal var rainTrimEnd_C = 0f
    internal var rainCount_C = 60
    internal var rainTrimStart_L = 0f
    internal var rainTrimEnd_L = 0f
    internal var rainCount_L = 55
    internal var rainTrimStart_R = 0f
    internal var rainTrimEnd_R = 0f
    internal var rainCount_R = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        typeface = Typeface.createFromAsset(assets, "fonts/adequate.ttf")
        text = findViewById(R.id.text) as TextView
        text.typeface = typeface

        lightningView = findViewById(R.id.vector_master) as VectorMasterView
        animateLightning()

        hourglassView = findViewById(R.id.vector_master_1) as VectorMasterView
        animateHourglass()

        searchBackView = findViewById(R.id.vector_master_2) as VectorMasterView
        animateSearchBack()

        rainView = findViewById(R.id.vector_master_3) as VectorMasterView
        animateRain()

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 5000)
    }

    internal fun animateLightning() {
        val cloudModel = lightningView.getPathModelByName("cloud")
        cloudModel.strokeColor = Color.parseColor("#5D5D5D")
        val lightningModel = lightningView.getPathModelByName("lightning")
        lightningModel.strokeColor = Color.parseColor("#FFD700")
        lightningModel.trimPathEnd = 0.0f
        lightningModel.trimPathStart = 0.0f

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                countLightning++
                if (countLightning >= 60) {
                    if (trimEnd < 1) {
                        trimEnd += 0.04f
                    } else if (trimEnd >= 1 && trimStart < 1) {
                        trimStart += 0.04f
                    } else if (trimEnd >= 1 && trimStart >= 1) {
                        trimEnd = 0f
                        trimStart = 0f
                        countLightning = 0
                    }
                    lightningModel.trimPathEnd = trimEnd
                    lightningModel.trimPathStart = trimStart
                    runOnUiThread { lightningView.update() }
                }
            }
        }, 500, (1000 / 60).toLong())
    }

    internal fun animateHourglass() {
        val frame = hourglassView.getGroupModelByName("hourglass_frame")
        val fillOutlines = hourglassView.getGroupModelByName("fill_outlines")
        val fillOutlinesPivot = hourglassView.getGroupModelByName("fill_outlines_pivot")
        val group_fill_path = hourglassView.getGroupModelByName("group_fill_path")

        state = 0
        translationY = -24f

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (state == 0) {
                    translationY += 0.3f
                    fillOutlinesPivot.translateY = translationY
                    group_fill_path.translateY = -1 * translationY
                    if (translationY >= -12) {
                        state = 1
                    }
                } else if (state == 1) {
                    rotation += 3f
                    frame.rotation = rotation
                    fillOutlines.rotation = rotation
                    if (rotation == 180f) {
                        state = 2
                    }
                } else if (state == 2) {
                    translationY -= 0.3f
                    fillOutlinesPivot.translateY = translationY
                    group_fill_path.translateY = -1 * translationY
                    if (translationY <= -24) {
                        state = 3
                    }
                } else if (state == 3) {
                    rotation += 3f
                    frame.rotation = rotation
                    fillOutlines.rotation = rotation
                    if (rotation == 360f) {
                        rotation = 0f
                        state = 0
                    }
                }
                runOnUiThread { hourglassView.update() }
            }
        }, 500, (1000 / 60).toLong())

    }

    internal fun animateSearchBack() {
        val searchCircle = searchBackView.getPathModelByName("search_circle")
        val stem = searchBackView.getPathModelByName("stem")
        val arrowUp = searchBackView.getPathModelByName("arrow_head_top")
        val arrowDown = searchBackView.getPathModelByName("arrow_head_bottom")

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                count++
                if (count >= 50) {
                    if (searchBackState == 0) {
                        circleTrimEnd -= 1.0f / 20
                        stemTrimStart += 0.75f / 20
                        stemTrimEnd += (1 - 0.185f) / 20
                        arrowHeadBottomTrimEnd += 1.0f / 20
                        arrowHeadTopTrimEnd += 1.0f / 20
                        if (circleTrimEnd <= 0) {
                            searchBackState = 1
                            count = 0
                        }
                    } else if (searchBackState == 1) {
                        arrowHeadBottomTrimEnd -= 1.0f / 20
                        arrowHeadTopTrimEnd -= 1.0f / 20
                        stemTrimStart -= 0.75f / 20
                        stemTrimEnd -= (1 - 0.185f) / 20
                        circleTrimEnd += 1.0f / 20
                        if (circleTrimEnd >= 1) {
                            searchBackState = 0
                            count = 0
                        }
                    }

                    searchCircle.trimPathEnd = circleTrimEnd
                    stem.trimPathEnd = stemTrimEnd
                    stem.trimPathStart = stemTrimStart
                    arrowUp.trimPathEnd = arrowHeadTopTrimEnd
                    arrowDown.trimPathEnd = arrowHeadBottomTrimEnd

                    runOnUiThread { searchBackView.update() }
                }

            }
        }, 1000, (1000 / 60).toLong())
    }

    internal fun animateRain() {
        val rainLeft = rainView.getPathModelByName("rain_left")
        val rainCenter = rainView.getPathModelByName("rain_center")
        val rainRight = rainView.getPathModelByName("rain_right")

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                rainCount_C++
                if (rainCount_C >= 60) {
                    if (rainTrimEnd_C < 1) {
                        rainTrimEnd_C += 0.04f
                    } else if (rainTrimEnd_C >= 1 && rainTrimStart_C < 1) {
                        rainTrimStart_C += 0.04f
                    } else if (rainTrimEnd_C >= 1 && rainTrimStart_C >= 1) {
                        rainTrimEnd_C = 0f
                        rainTrimStart_C = 0f
                        rainCount_C = 0
                    }
                }
                rainCount_L++
                if (rainCount_L >= 60) {
                    if (rainTrimEnd_L < 1) {
                        rainTrimEnd_L += 0.04f
                    } else if (rainTrimEnd_L >= 1 && rainTrimStart_L < 1) {
                        rainTrimStart_L += 0.04f
                    } else if (rainTrimEnd_L >= 1 && rainTrimStart_L >= 1) {
                        rainTrimEnd_L = 0f
                        rainTrimStart_L = 0f
                        rainCount_L = 0
                    }
                }
                rainCount_R++
                if (rainCount_R >= 60) {
                    if (rainTrimEnd_R < 1) {
                        rainTrimEnd_R += 0.04f
                    } else if (rainTrimEnd_R >= 1 && rainTrimStart_R < 1) {
                        rainTrimStart_R += 0.04f
                    } else if (rainTrimEnd_R >= 1 && rainTrimStart_R >= 1) {
                        rainTrimEnd_R = 0f
                        rainTrimStart_R = 0f
                        rainCount_R = 0
                    }
                }

                rainCenter.trimPathEnd = rainTrimEnd_C
                rainCenter.trimPathStart = rainTrimStart_C

                rainLeft.trimPathEnd = rainTrimEnd_L
                rainLeft.trimPathStart = rainTrimStart_L

                rainRight.trimPathEnd = rainTrimEnd_R
                rainRight.trimPathStart = rainTrimStart_R

                runOnUiThread { rainView.update() }

            }
        }, 500, (1000 / 60).toLong())

    }

}
