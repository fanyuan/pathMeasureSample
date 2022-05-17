package com.pathmeasure.sample

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PathView1 : View {
    constructor(context: Context?):super(context){
        initPaint()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs) {
        initPaint()
    }


    // 高阶函数
    var callBack:((Int) -> Boolean)? = null

    // 高阶函数
//    var callBack2:((Int,String) -> Boolean)? = null

    // 高阶函数
    var callBack3:((Int) -> Unit)? = null

    //        callBack3 = {
//            true;
//        }
//        callBack2 = {
//            i,v ->
//            true
//        }

    private lateinit var paint:Paint;
    private var pathMeasure:PathMeasure = PathMeasure()
    lateinit var valueAnimator : ValueAnimator;
    private val path = Path()
    private val mCurrentPosition = FloatArray(2)

    private fun initPaint() {
        paint = Paint()
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.parseColor("#FF0000"));
    }
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        makePath()

        pathMeasure.setPath(path,false);

        val length =pathMeasure.length
        Logger.d("12pathMeasure.length = ${pathMeasure.length}")
        valueAnimator = ValueAnimator.ofFloat(0f,length)
        valueAnimator.duration = 2000

        valueAnimator.addUpdateListener {
            //Logger.d("update01 animatedValue = ${it.animatedValue}")
            val value = it.animatedValue as Float

            pathMeasure.getPosTan(value,mCurrentPosition,null)
            postInvalidate()

        }
        Logger.d("pathView1 onAttachedToWindow")

        startAnimation()
    }

    private fun makePath() {
        path.lineTo(200f,200f)
        path.lineTo(400f,0f)

        path.moveTo(100f,400f);
        path.quadTo(300f, 100f, 400f, 400f);

        path.moveTo(200f, 400f);
        path.cubicTo(200f, 400f, 300f, 200f, 400f, 600f);

        path.lineTo(200f, 200f);
        val rectF = RectF(100f, 100f, 400f, 400f);
        path.arcTo(rectF, 0f, 270f, true);
        // path.addArc(rectF,0,270);和上面一句等价
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator.cancel()
        //valueAnimator = null
        Logger.d("pathView1 onDetachedFromWindow")
    }

    private fun startAnimation() {
        valueAnimator.start()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(path, paint);

        canvas?.drawCircle(mCurrentPosition[0],mCurrentPosition[1],12F,paint)

    }
}