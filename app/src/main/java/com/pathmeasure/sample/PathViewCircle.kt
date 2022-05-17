package com.pathmeasure.sample

import android.R.attr
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.R.attr.animation
import kotlin.random.Random


class PathViewCircle : View {
    constructor(context: Context?):super(context){
        initPaint()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs) {
        initPaint()
    }

    private lateinit var paint:Paint;
    private lateinit var paintSegment:Paint;
    private var pathMeasure:PathMeasure = PathMeasure()
    lateinit var valueAnimator : ValueAnimator;
    private val path = Path()
    private val mCurrentPosition = FloatArray(2)
    var radius:Float = 0f;
    private var segmentPath = Path()


    private fun initPaint() {
        paint = Paint();
        paint.isAntiAlias = true;
        paint.style = Paint.Style.STROKE;
        paint.strokeWidth = 10f;
        paint.color = Color.parseColor("#FF0000");

        paintSegment = Paint()
        paintSegment.isAntiAlias = true;
        paintSegment.style = Paint.Style.STROKE;
        paintSegment.strokeWidth = 20f
        paintSegment.color = Color.CYAN
        Logger.d("PathViewCircle initPaint")
    }


    val random = Random(10)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val diameter = w.coerceAtMost(h)
        radius = (diameter/2)*0.9f


//        path.lineTo(200f, 200f);
//        val rectF = RectF(100f, 100f, 400f, 400f);
//        path.arcTo(rectF, 0f, 310f, false);
        // path.addArc(rectF,0,270);和上面一句等价

        path.addCircle((width/2).toFloat(), (height/2).toFloat(),radius,Path.Direction.CW)

        pathMeasure.setPath(path,false);

        val length =pathMeasure.length
        Logger.d("12pathMeasure.length = ${pathMeasure.length}")
        valueAnimator = ValueAnimator.ofFloat(0f,length)
        valueAnimator.duration = 5000

        valueAnimator.addUpdateListener {
            //Logger.d("update01 animatedValue = ${it.animatedValue}")
            var value = it.animatedValue as Float

            pathMeasure.getPosTan(value,mCurrentPosition,null)


            segmentPath.reset()
            var start = random.nextInt(10)/10f*length
            Logger.d("start = $start")
            //pathMeasure.getSegment(value - length * 0.1f,value,segmentPath,true)
            pathMeasure.getSegment(start - length * 0.1f,start,segmentPath,true)

            postInvalidate()

        }
        Logger.d("PathViewCircle onAttachedToWindow $width  $height   measuredWidth = $measuredWidth  measuredHeight = $measuredHeight")

        startAnimation()
        Logger.d("PathViewCircle onSizeChanged $width  $height")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator.cancel()
        //valueAnimator = null
        Logger.d("PathViewCircle onDetachedFromWindow")
    }

    private fun startAnimation() {
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(path, paint);



        //segmentPath = resetSegmentPath()
        canvas?.drawPath(segmentPath,paintSegment)

        canvas?.drawCircle(mCurrentPosition[0],mCurrentPosition[1],10F,paint)

    }

}