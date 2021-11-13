package com.example.timberman;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Stick extends BaseObject{
    private  int idCurrentBitmap;

    public Stick(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(this.getBm(),this.x,this.y,null);
    }

    @Override
    public void setBm(Bitmap Bm) {
      this.bm =  Bitmap.createScaledBitmap(Bm,this.width,this.height, true);
    }
}


