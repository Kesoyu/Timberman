package com.example.timberman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.logging.LogRecord;

public class GameView extends View {
    private Woodcutter woodcutter;
    private android.os.Handler handler;
    private Runnable r;
    private float xValue, yValue,leftPersentage;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        woodcutter = new Woodcutter();
        //ustawianie wielkości drwala
        woodcutter.setWidth(300*Constants.SCREEN_WIDTH/1080);
        woodcutter.setHeight(300*Constants.SCREEN_HEIGHT/1920);
        //połeżenie drwala
        woodcutter.setX(200*Constants.SCREEN_WIDTH/1080);
        woodcutter.setY(1300*Constants.SCREEN_HEIGHT/1920);
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.woodcutter));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.woodcutter2));
        woodcutter.setArrBms(arrBms);

        leftPersentage = (Constants.SCREEN_WIDTH)*50/100;
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

    }
    public  void draw(Canvas canvas){
        super.draw(canvas);
        woodcutter.draw(canvas);
        handler.postDelayed(r, 10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            xValue = event.getX();
            yValue = event.getY();
            if(xValue <= leftPersentage){
                woodcutter.onClick();
            }
        }
        return true;
    }
}
