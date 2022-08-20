package com.ibrahimethem.todoremember.customline

import android.content.Context
import android.graphics.Canvas

import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.ibrahimethem.todoremember.R

class LinesView @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : View(ctx, attributeSet, defStyleAttr) {

    private fun checkedLine(canvas: Canvas?) {

        canvas?.drawLine(
            width* 0.07f,
            height / 2F,
            width.toFloat(),
            height / 2f,
            secondLinePaint
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        checkedLine(canvas)
        //deneme(canvas)
    }

    private val secondLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.check_box_color)
        strokeWidth = 5f
    }

}