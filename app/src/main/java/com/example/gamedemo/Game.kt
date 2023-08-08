package com.example.gamedemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat

class Game(private val context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val gameLoop: GameLoop
    private var player: Player
    private var controlPoint: ControlPoint

    init {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        gameLoop = GameLoop(this, surfaceHolder)
        isFocusable = true
        player = Player(context, 500.0, 500.0, 30f)
        controlPoint = ControlPoint(270F, 750f, 100f, 50f)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (controlPoint.isPressed(event.x.toDouble(), event.y.toDouble())) {
                    controlPoint.setIsPressed(true)
                }
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                if (controlPoint.getIsPressed()) {
                    controlPoint.setActuator(event.x.toDouble(), event.y.toDouble())
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                controlPoint.setIsPressed(false)
                controlPoint.resetActuator()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
//        drawUps(canvas)
//        drawFps(canvas)
        controlPoint.draw(canvas)
        player.draw(canvas)
    }

    private fun drawUps(canvas: Canvas?) {
        val averageUps: String = gameLoop.getAverageUps().toString()
        val paint = Paint()
        paint.color = ContextCompat.getColor(context, R.color.white)
        paint.textSize = 50f
        canvas?.drawText("UPS : $averageUps", 100F, 100F, paint)
    }

    private fun drawFps(canvas: Canvas?) {
        val averageFps: String = gameLoop.getAverageFps().toString()
        val paint = Paint()
        paint.textSize = 50f
        paint.color = ContextCompat.getColor(context, R.color.white)
        canvas?.drawText("FPS : $averageFps", 100F, 200F, paint)
    }

    fun update() {
        player.update(controlPoint)
        controlPoint.update()
    }

}