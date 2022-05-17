package com.pathmeasure.sample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class PathViewDrawable : View {
    val drawable = LeftDrawable()
    constructor(context: Context?):super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawable.bounds = Rect(left,top,right,bottom)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawable.draw(canvas!!)
    }
}