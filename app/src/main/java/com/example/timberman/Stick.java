package com.example.timberman;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Stick extends BaseObject{
    public static int speed;

    public Stick(float x, float y, int width, int height) {
        super(x, y, width, height);
        speed=10*Constants.SCREEN_HEIGHT/1920;
    }
    public void draw(Canvas canvas){
        this.y-=speed;
        canvas.drawBitmap(this.bm,this.x,this.y,null);
    }

    @Override
    public void setBm(Bitmap bm) {
        this.bm = Bitmap.createScaledBitmap(bm,width,height,true);
    }
}


