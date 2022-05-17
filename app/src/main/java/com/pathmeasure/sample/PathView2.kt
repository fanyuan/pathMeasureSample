package com.pathmeasure.sample

import android.R.attr
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.R.attr.animation




class PathView2 : View {
    constructor(context: Context?):super(context){
        initPaint()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs) {
        initPaint()
    }

    private lateinit var paint:Paint;
    private var pathMeasure:PathMeasure = PathMeasure()
    lateinit var valueAnimator : ValueAnimator;
    private val path = Path()
    private val mCurrentPosition = FloatArray(2)

    private fun initPaint() {
        paint = Paint();
        paint.isAntiAlias = true;
        paint.style = Paint.Style.STROKE;
        paint.strokeWidth = 10f;
        paint.color = Color.parseColor("#FF0000");
        Logger.d("pathView2 initPaint")
    }




    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        path.lineTo(200f, 200f);
        val rectF = RectF(100f, 100f, 400f, 400f);
        path.arcTo(rectF, 0f, 310f, false);
        // path.addArc(rectF,0,270);和上面一句等价

        pathMeasure.setPath(path,false);

        val length =pathMeasure.length
        Logger.d("12pathMeasure.length = ${pathMeasure.length}")
        valueAnimator = ValueAnimator.ofFloat(0f,length)
        valueAnimator.duration = 5000

        valueAnimator.addUpdateListener {
            //Logger.d("update01 animatedValue = ${it.animatedValue}")
            val value = it.animatedValue as Float

            pathMeasure.getPosTan(value,mCurrentPosition,null)

            postInvalidate()

        }
        Logger.d("pathView2 onAttachedToWindow")

        startAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator.cancel()
        //valueAnimator = null
        Logger.d("pathView2 onDetachedFromWindow")
    }

    private fun startAnimation() {
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(path, paint);

        canvas?.drawCircle(mCurrentPosition[0],mCurrentPosition[1],10F,paint)

    }
}