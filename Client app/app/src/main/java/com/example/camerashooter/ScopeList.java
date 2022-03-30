package com.example.camerashooter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScopeList {

    private int centerX, CenterY, size;
    private Paint paint;

    public ScopeList(int centerX, int centerY, int size) {
        this.centerX = centerX;
        CenterY = centerY;
        this.size = size;

        setUpCanvas();
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return CenterY;
    }

    public int getSize() {
        return size;
    }

    public void setUpCanvas(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }

    //the scope list

    public void defaultScope(Canvas canvas){

        canvas.drawLine( getCenterX() - getSize(), getCenterY(), getCenterX() + getSize(), getCenterY(), paint);
        canvas.drawLine( getCenterX(),getCenterY() - getSize(),  getCenterX(),getCenterY() + getSize(), paint);

    }

    public void rectangleScope(Canvas canvas){

        canvas.drawPoint( getCenterX(), getCenterY(), paint);
        canvas.drawRect( getCenterX() + getSize(), getCenterY() - getSize(),  getCenterX() - getSize(),  getCenterY() + getSize(), paint);

    }

    public void circleScope(Canvas canvas){

        canvas.drawPoint( getCenterX(), getCenterY(), paint);
        canvas.drawCircle( getCenterX(), getCenterY(), 60, paint);
    }

    public void largeCircleScopeWithLines(Canvas canvas){

        canvas.drawLine( getCenterX() - 160, getCenterY(), getCenterX() + 160, getCenterY(), paint);
        canvas.drawLine( getCenterX(),getCenterY() - 160,  getCenterX(),getCenterY() + 160, paint);

        canvas.drawCircle( getCenterX(), getCenterY(), 160, paint);
    }

    public void sniperScope(Canvas canvas){

        canvas.drawLine( getCenterX() - 160, getCenterY(), getCenterX() + 160, getCenterY(), paint);
        canvas.drawLine( getCenterX(),getCenterY() - 160,  getCenterX(),getCenterY() + 160, paint);

        canvas.drawCircle( getCenterX(), getCenterY(), 60, paint);
        canvas.drawCircle( getCenterX(), getCenterY(), 160, paint);
    }



}
