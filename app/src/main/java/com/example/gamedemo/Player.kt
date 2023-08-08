package com.example.gamedemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class Player(
    private val context: Context,
    private var positionX: Double,
    private var positionY: Double,
    private val radius: Float
) {
    private val MAX_SPEED: Double = 600.0 / 30.0
    private var paint: Paint = Paint()
    private var velocityX: Double = 0.0
    private var velocityY: Double = 0.0

    init {
        paint.color = ContextCompat.getColor(context, R.color.lightBlue)
    }


    fun draw(canvas: Canvas?) {
        canvas?.drawCircle(positionX.toFloat(), positionY.toFloat(), radius, paint)
    }

    fun update(controlPoint: ControlPoint) {
        velocityX = controlPoint.getActuatorX() * MAX_SPEED
        velocityY = controlPoint.getActuatorY() * MAX_SPEED

        positionX += velocityX
        positionY += velocityY
    }

    fun setPosition(newPositionX: Double, newPositionY: Double) {
        positionX = newPositionX
        positionY = newPositionY
    }
}