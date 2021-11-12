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
    private Stick stick;
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

    public void initSticks() {
        sumbranch = 7;
        distance = 200 * Constants.SCREEN_HEIGHT / 1920;
        arrSicks = new ArrayList<>();

        this.arrSicks.add(new Stick(-47, 0, 1050, 300));
        arrSicks.get(0).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewo));
        for (int i = 0; i < sumbranch; i++) {

            Random liczba = new Random();
            los = liczba.nextInt(3);

            if (los == 0) {
                this.arrSicks.add(new Stick(-47, arrSicks.get(arrSicks.size() - 1).getY() + 300, 1050, 300));
                arrSicks.get(i+1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewo));
            } else if (los == 1) {
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() + 300,1050,300));
                arrSicks.get(i+1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.prawo));
            }
            else{
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() + 300,526,300));
                arrSicks.get(i+1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
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
        if(start)
        {
            woodcutter.draw(canvas);
            for(int i = 0;i<arrSicks.size()-1; i++){
            arrSicks.get(i).draw(canvas);
           }
        }
//         else{ //todo tu bedzie else do zrestartowania gry
//             if(){
//
//             }
//             woodcutter.draw(canvas);
//        }
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

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
