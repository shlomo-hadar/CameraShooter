package com.example.camerashooter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class DrawScope extends View {

    private ScopeList scopeList;

    public DrawScope(Context context) {
        super(context);
    }

    public DrawScope(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawScope(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawScope(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

          scopeList = new ScopeList((int)getWidth()/2, (int)getHeight()/2, 50);

//          scopeList.defaultScope(canvas);
//          scopeList.rectangleScope(canvas);
//          scopeList.largeCircleScopeWithLines(canvas);
//          scopeList.circleInsideCircle(canvas);
          scopeList.defaultScope(canvas);


    }
}
