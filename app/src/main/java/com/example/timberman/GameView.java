package com.example.timberman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {
    private Woodcutter woodcutter;
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
    }
    public  void draw(Canvas canvas){
        super.draw(canvas);
        woodcutter.draw(canvas);
    }
}
