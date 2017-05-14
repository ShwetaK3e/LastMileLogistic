package com.shwetak3e.zentello.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.franchisee_activity.SeeTodayScheduleActivity;

public class DrawSignature extends View {

    private Paint mPaint;
    private Path mPath;
    private static final String TAG= DrawSignature.class.getSimpleName();
    Context context;

    public DrawSignature(Context context) {
        super(context);
        this.context=context;
        Log.i(TAG,"construct");
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPath = new Path();
        this.setWillNotDraw(false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG,"onDraw");
        canvas.drawPath(mPath, mPaint);
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                Log.i(TAG,"Action Down");
                mPath.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                Log.i(TAG,"action move");
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                Toast.makeText(context,"Your signature is being saved",Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, SeeTodayScheduleActivity.class));
                Log.i(TAG,"action up");
                break;
        }

        return true;
    }


}
