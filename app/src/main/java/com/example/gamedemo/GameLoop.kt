package com.example.gamedemo

import android.graphics.Canvas
import android.view.SurfaceHolder
import java.lang.Exception

class GameLoop(private val game: Game, private val surfaceHolder: SurfaceHolder) : Thread() {
    private var isRunning: Boolean = false
    private var averageUps: Double = 0.0
    private var averageFps: Double = 0.0

    fun getAverageUps(): Double {
        return averageUps
    }

    fun getAverageFps(): Double {
        return averageFps
    }

    fun startLoop() {
        isRunning = true
        start()
    }

    override fun run() {
        super.run()
        var updateCount : Int = 0
        var frameCount : Int = 0

        var elapsedTime: Long
        var sleepTime: Long

        var canvas: Canvas
        val startTime: Long = System.currentTimeMillis()
        while (true) {
            try {
                canvas = surfaceHolder.lockCanvas()
                game.update()
                game.draw(canvas)
                surfaceHolder.unlockCanvasAndPost(canvas)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            updateCount++
            frameCount++



        }
    }
}