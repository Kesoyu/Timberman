package com.example.timberman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.SystemClock;

import java.util.ArrayList;

//drwal
public class Woodcutter extends BaseObject {
    private ArrayList<Bitmap> arrBms = new ArrayList<>();
    private int count, vHit, idCurrentBitmap;
    public Woodcutter(){
    this.count = 0;
    this.vHit = 5;
    this.idCurrentBitmap = 0;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.getBm(),this.x, this.y, null);
    }

    public ArrayList<Bitmap> getArrBms() {
        return arrBms;
    }

    public void setArrBms(ArrayList<Bitmap> arrBms) {
        this.arrBms = arrBms;
        for (int i = 0; i<arrBms.size(); i++){
            this.arrBms.set(i,Bitmap.createScaledBitmap(this.arrBms.get(i),this.width,this.height, true));
        }
    }

    public void onClick(int strona){
        if(strona==1){
            this.x = 170*Constants.SCREEN_WIDTH/1080;
            idCurrentBitmap = 0;
        }
        else if(strona==2){
            this.x = 700*Constants.SCREEN_WIDTH/1080;
            idCurrentBitmap = 1;
        }
    }

    @Override
    public Bitmap getBm() {
        return this.getArrBms().get(idCurrentBitmap);
    }
}
