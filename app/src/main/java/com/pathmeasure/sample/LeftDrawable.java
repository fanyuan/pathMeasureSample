package com.pathmeasure.sample;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.BounceInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class LeftDrawable extends Drawable {
    private Canvas mCanvas;
    private Paint paint;
    private Paint paintSegment;
    private PathMeasure pathMeasure = new PathMeasure();
    //private ValueAnimator valueAnimator;
    private Path path = new Path();
    private Path segmentPath = new Path();

    {
        initPaint();
    }


    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.parseColor("#FF0000"));

        paintSegment = new Paint();
        paintSegment.setAntiAlias(true);
        paintSegment.setStyle(Paint.Style.STROKE);
        paintSegment.setStrokeWidth(20f);
        paintSegment.setColor(Color.CYAN);
        Logger.d("LeftDrawable initPaint");
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        float w = bounds.width();
        float h = bounds.height();
        paint.setStrokeWidth(w * 0.03f);

        PathEffect effect = new CornerPathEffect(70f);
        paint.setPathEffect(effect);

        path.moveTo((w/2),0f);
        path.lineTo((w/2), (h/2));
        path.lineTo(0f,(h/2));
        Logger.d("LeftDrawable onBoundsChange");
        pathMeasure.setPath(path,false);
        Logger.d("pathMeasure.length = " + pathMeasure.getLength());
        // path.addArc(rectF,0,270);和上面一句等价

    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        if(canvas == null){
            return;
        }

        mCanvas = canvas;
        Logger.d("LeftDrawable  draw");
        canvas.drawPath(path, paint);
    }
    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    /**
     * @deprecated
     */
    @Override
    public int getOpacity() {
        //0;
        return PixelFormat.TRANSLUCENT;
    }


}
