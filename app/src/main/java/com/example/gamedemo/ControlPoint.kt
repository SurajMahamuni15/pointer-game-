package com.example.gamedemo

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.pow
import kotlin.math.sqrt

class ControlPoint(
    private val centerPointX: Float,
    private val centerPointY: Float,
    private val outerCirculeRadius: Float,
    private val innerCirculeRadius: Float
) {
    private var outerCenterPointX: Int = centerPointX.toInt()
    private var outerCenterPointY: Int = centerPointY.toInt()
    private var innerCenterPointX: Int = centerPointX.toInt()
    private var innerCenterPointY: Int = centerPointY.toInt()
    private var outerCirculePaint = Paint()
    private var innerCirculePaint = Paint()
    private var controlPointCenterToTouchPosition: Double = 0.0
    private var isPressed = false
    private var actuatorX: Double = 0.0
    private var actuatorY: Double = 0.0

    init {

        outerCirculePaint.color = Color.LTGRAY
        outerCirculePaint.style = Paint.Style.FILL_AND_STROKE

        innerCirculePaint.color = Color.BLUE
        innerCirculePaint.style = Paint.Style.FILL_AND_STROKE
    }

    fun draw(canvas: Canvas?) {
        canvas?.drawCircle(
            centerPointX,
            centerPointY, outerCirculeRadius, outerCirculePaint
        )
        canvas?.drawCircle(
            centerPointX,
            centerPointY, innerCirculeRadius, innerCirculePaint
        )
    }

    fun update() {
        updateInnerCirculePosition()
    }

    private fun updateInnerCirculePosition() {
        innerCenterPointX = (outerCenterPointX + actuatorX * outerCirculeRadius).toInt()
        innerCenterPointY = (outerCenterPointY + actuatorY * outerCirculeRadius).toInt()
    }

    fun isPressed(touchPositionX: Double, touchPositionY: Double): Boolean {
        controlPointCenterToTouchPosition = sqrt(
            (centerPointX - touchPositionX).pow(2.0) +
                    (centerPointY - touchPositionY).pow(2.0)
        )
        return controlPointCenterToTouchPosition < outerCirculeRadius
    }

    fun setIsPressed(pressedFlag: Boolean) {
        isPressed = pressedFlag
    }

    fun getIsPressed(): Boolean {
        return isPressed
    }

    fun setActuator(touchPositionX: Double, touchPositionY: Double) {
        val deltaX = touchPositionX - outerCenterPointX
        val deltaY = touchPositionY - outerCenterPointY
        val deltaDistance = Math.sqrt(Math.pow(deltaX, 2.0) + Math.pow(deltaY, 2.0))

        if (deltaDistance < outerCirculeRadius) {
            actuatorX = deltaX / outerCirculeRadius
            actuatorY = deltaY / outerCirculeRadius
        } else {
            actuatorX = deltaX / deltaDistance
            actuatorY = deltaY / deltaDistance
        }
    }

    fun resetActuator() {
        actuatorX = 0.0
        actuatorY = 0.0
    }

    fun getActuatorX(): Double {
        return actuatorX
    }

    fun getActuatorY(): Double {
        return actuatorY
    }
}