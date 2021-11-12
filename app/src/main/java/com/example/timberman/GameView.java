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
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.LogRecord;

public class GameView extends View {
    private Woodcutter woodcutter;
    private android.os.Handler handler;
    private Runnable r;
    private float xValue, yValue,leftPersentage;
    private ArrayList<Stick>arrSicks;
    private int distance, sumbranch, los;
    private boolean start;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        start=false;
        initWoodCutter();
        leftPersentage = (Constants.SCREEN_WIDTH)*50/100;
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    public void initSticks(){
        sumbranch=4;
        distance=200*Constants.SCREEN_HEIGHT/1920;
        arrSicks = new ArrayList<>();
        for(int i=0;i<sumbranch;i++){
            Random liczba = new Random();
            los = liczba.nextInt(1);
            if(los==0){
                //TODO wyswietlanie po lewej
            }
            else if (los==1){
                //TODO wyswietlanie galazki po prawej
            }
        }
    }

    public void initWoodCutter(){
        woodcutter = new Woodcutter();
        //ustawianie wielkości drwala
        woodcutter.setWidth(300*Constants.SCREEN_WIDTH/1080);
        woodcutter.setHeight(300*Constants.SCREEN_HEIGHT/1920);
        //połeżenie drwala
        woodcutter.setX(170*Constants.SCREEN_WIDTH/1080);
        woodcutter.setY(1300*Constants.SCREEN_HEIGHT/1920);
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.cutleft));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.cutright));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.left));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.right));
        woodcutter.setArrBms(arrBms);
    }

    public void draw(Canvas canvas){
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
                woodcutter.onClick(1);
            }
            else{
                woodcutter.onClick(2);
            }
        }
        return true;
    }
}
