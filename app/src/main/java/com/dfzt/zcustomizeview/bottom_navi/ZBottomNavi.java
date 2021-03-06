package com.dfzt.zcustomizeview.bottom_navi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import android.view.View;

import android.widget.LinearLayout;

import com.dfzt.zcustomizeview.R;


public class ZBottomNavi extends LinearLayout {

    private Context context;

    private Path path;

    private Paint paint;

    private RectF rectF;

    private int fabId;

    private float centerRadius;

    private float cornerRadius;
    private float shadowsize;

    public ZBottomNavi(Context context) {
        this(context, null);
    }

    public ZBottomNavi(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZBottomNavi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZBottomNavi);
        fabId = typedArray.getResourceId(R.styleable.ZBottomNavi_anchor_fab, 0);
        centerRadius = typedArray.getFloat(R.styleable.ZBottomNavi_center_radius,50);
        cornerRadius = typedArray.getFloat(R.styleable.ZBottomNavi_corner_radius,5);
        shadowsize = typedArray.getFloat(R.styleable.ZBottomNavi_shadow_length,5);
        typedArray.recycle();
        init();
    }


    public void setFabId(int id) {
        fabId = id;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (fabId != 0) {
            View fab = getRootView().findViewById(fabId);
//            centerRadius = fab.getWidth() / 2 + cornerRadius;
        }
//
//        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int count = getChildCount();
//        int currentHeight = 0;
//        for (int i = 0 ; i < count ; i++){
//            View view = getChildAt(i);
//            int height = view.getMeasuredHeight();
//            int width  = view.getMeasuredWidth();
//            view.layout(left, currentHeight, left + width, currentHeight + height);
//            currentHeight += height;
//        }
//    }


    private void init() {
        setBackgroundColor(Color.TRANSPARENT);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        path = new Path();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);


        path.moveTo(0, shadowsize);
        path.lineTo(getWidth() / 2 - centerRadius - cornerRadius, shadowsize);

        path.quadTo(
                getWidth() / 2 - centerRadius,
                shadowsize,
                getWidth() / 2 - centerRadius,
                cornerRadius + shadowsize
        );

        //中间凹陷的半圆
        RectF rectCenter = new RectF(
                getWidth() / 2 - centerRadius,
                shadowsize + cornerRadius - centerRadius,
                getWidth() / 2 + centerRadius,
                shadowsize + centerRadius + cornerRadius
        );

        path.arcTo(rectCenter, 180, -180, false);

        //右边转角处
        path.quadTo(
                getWidth() / 2 + centerRadius,
                shadowsize,
                getWidth() / 2 + centerRadius + cornerRadius,
                shadowsize
        );
        path.lineTo(getWidth(), shadowsize);
        path.lineTo(getWidth(), getHeight());


        path.lineTo(0, getHeight());
        path.lineTo(0, shadowsize);
        path.close();
        canvas.drawPath(path, paint);
    }


}
