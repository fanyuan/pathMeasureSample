package com.pathmeasure.sample

import android.R.attr
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.R.attr.animation
import android.provider.CalendarContract


class PathView5 : View {
    constructor(context: Context?):super(context){
        initPaint()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs) {
        initPaint()
    }

    private lateinit var paint:Paint;
    private var pathMeasure:PathMeasure = PathMeasure()
    //lateinit var valueAnimator : ValueAnimator;
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
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paint.setPathEffect(CornerPathEffect(50f));
        paint.strokeWidth = (w * 0.07).toFloat()
        path.moveTo((w/2).toFloat(),0f)
        path.lineTo((w/2).toFloat(), (h/2).toFloat());
        path.lineTo(0f,(h/2).toFloat())
        pathMeasure.setPath(path,false);
        Logger.d("pathMeasure.length = " + pathMeasure.length)
        // path.addArc(rectF,0,270);和上面一句等价

//        pathMeasure.setPath(path,false);
//
//        val length =pathMeasure.length
//        Logger.d("12pathMeasure.length = ${pathMeasure.length}")
//        valueAnimator = ValueAnimator.ofFloat(0f,length)
//        valueAnimator.duration = 5000
//
//        valueAnimator.addUpdateListener {
//            //Logger.d("update01 animatedValue = ${it.animatedValue}")
//            val value = it.animatedValue as Float
//
//            pathMeasure.getPosTan(value,mCurrentPosition,null)
//
//            postInvalidate()
//
//        }
//        Logger.d("pathView5 onAttachedToWindow")
//
//        startAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        //valueAnimator.cancel()
        //valueAnimator = null
        Logger.d("pathView5 onDetachedFromWindow")
    }

//    private fun startAnimation() {
//        valueAnimator.start()
//    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint);

        val length = pathMeasure.length
        Logger.d("pathMeasure.length = " + pathMeasure.length)
        val subLength = length * 0.01
        val segmentPath = Path()
        var index = 0
        var times = 0

        var drawLength = paint.strokeWidth * 0.5f
        while (index <= length * 0.4){

            Logger.d("index == $index")
            if(times % 2 == 0){
                Logger.d("times % 2 == 0")
                segmentPath.reset()

                paint.color = Color.YELLOW

                pathMeasure.getSegment(index - drawLength, index.toFloat(),segmentPath,true)

                canvas?.drawPath(segmentPath,paint)

            }else{
                paint.color = Color.BLUE
            }
            index += drawLength.toInt()
            times++
        }

    }
}