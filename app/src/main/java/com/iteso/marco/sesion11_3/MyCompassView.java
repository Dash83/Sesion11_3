package com.iteso.marco.sesion11_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by marco on 4/15/15.
 */
public class MyCompassView extends View
{
    private Paint paint;
    private float position = 0;

    public MyCompassView(Context context)
    {
        super(context);
        init();
    }

    public void init()
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(25);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
    }

    public void updateData(float position)
    {
        this.position = position;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int xPoint = getMeasuredWidth() / 2;
        int yPoint = getMeasuredHeight() / 2;
        float radius = (float)(Math.max(xPoint, yPoint) * 0.6);

        canvas.drawCircle(xPoint, yPoint, radius, paint);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
        canvas.drawLine(xPoint, yPoint,
                        (float)(xPoint + radius * Math.sin((double) (-position) / 180 * 3.143)),
                        (float)(yPoint - radius * Math.cos((double) (-position) / 180 * 3.143)), paint);
        canvas.drawText(String.valueOf(position), xPoint, yPoint, paint);

    }
}
