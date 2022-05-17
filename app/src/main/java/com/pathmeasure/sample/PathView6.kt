package com.pathmeasure.sample

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator


class PathView6 : View {
    companion object{
        const val ORIENTATION_LEFT:Int = 0;
        const val ORIENTATION_RIGHT:Int = 1;
    }
    constructor(context: Context?):super(context){
        initPaint()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context,attrs) {
        initPaint()
    }

    private lateinit var paint:Paint;
    private lateinit var paintSegment:Paint;
    private var pathMeasureTop:PathMeasure = PathMeasure()
    private var pathMeasureBottom:PathMeasure = PathMeasure()
    lateinit var valueAnimator : ValueAnimator;
    private val pathTop = Path()
    private var segmentPathTop = Path()
    private val pathBottom = Path()
    private var segmentPathBottom = Path()

    /**
     * 方向<br>
     *  @link PathView6#ORIENTATION_RIGHT PathView6#ORIENTATION_LEFT
     */
    var orientation:Int = ORIENTATION_LEFT//ORIENTATION_RIGHT//0
        get() = field
        set(value) {
            field = value
            setUp(width,height)
            Logger.d("orientation   set")
        }

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
        Logger.d("pathView6 initPaint")
    }




    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setUp(w,h)
    }

    @SuppressLint("WrongConstant")
    private fun setUp(width: Int, height: Int) {
        val effect = CornerPathEffect(70f);
        paint.pathEffect = effect;
        paint.strokeWidth = (width * 0.03).toFloat()

        paintSegment.pathEffect = CornerPathEffect(250f)
        paintSegment.strokeWidth = (paint.strokeWidth * 1.7).toFloat()

        setUpPathUP(width,height)

        setUpPathBottom(width,height)

        val length =pathMeasureTop.length
        Logger.d("12pathMeasure.length = ${pathMeasureTop.length}")
        valueAnimator = ValueAnimator.ofFloat(0f,length)
        valueAnimator.duration = 5000
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        //valueAnimator.repeatMode = ValueAnimator.RESTART

        valueAnimator.addUpdateListener {
            //Logger.d("update01 animatedValue = ${it.animatedValue}")
            val value = it.animatedValue as Float

            segmentPathTop.reset()
            pathMeasureTop.getSegment(value - length * 0.15f,value,segmentPathTop,true)

            segmentPathBottom.reset()
            pathMeasureBottom.getSegment(value - length * 0.15f,value,segmentPathBottom,true)

            postInvalidate()

        }

        startAnimation()
    }

    private fun setUpPathBottom(with: Int, height: Int) {
        pathBottom.reset()
        if(orientation == ORIENTATION_LEFT){
            pathBottom.moveTo((with/2).toFloat(), height.toFloat())
            pathBottom.lineTo((with/2).toFloat(), (height/2).toFloat());
            pathBottom.lineTo(0f,(height/2).toFloat())
        }else{
            pathBottom.moveTo((with/2).toFloat(), height.toFloat())
            pathBottom.lineTo((with/2).toFloat(), (height/2).toFloat());
            pathBottom.lineTo(with.toFloat(),(height/2).toFloat())
        }

        pathMeasureBottom.setPath(pathBottom,false);
    }

    private fun setUpPathUP(with: Int, height: Int) {
        pathTop.reset()
        if(orientation == ORIENTATION_LEFT){
            pathTop.moveTo((with/2).toFloat(),0f)
            pathTop.lineTo((with/2).toFloat(), (height/2).toFloat());
            pathTop.lineTo(0f,(height/2).toFloat())
        }else{
            pathTop.moveTo((with/2).toFloat(),0f)
            pathTop.lineTo((with/2).toFloat(), (height/2).toFloat());
            pathTop.lineTo(with.toFloat(),(height/2).toFloat())
        }
        pathMeasureTop.setPath(pathTop,false);
        Logger.d("pathMeasure.length = " + pathMeasureTop.length)
        // path.addArc(rectF,0,270);和上面一句等价
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator.cancel()
        //valueAnimator = null
        Logger.d("pathView5 onDetachedFromWindow")
    }
    public fun start(){
        setUp(width,height)
    }
    private fun startAnimation() {
        valueAnimator.start()
    }
    public fun stop() {
        valueAnimator.cancel()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(pathTop, paint);
        canvas?.drawPath(pathBottom,paint)

        canvas?.drawPath(segmentPathTop,paintSegment)
        canvas?.drawPath(segmentPathBottom,paintSegment)

    }
}