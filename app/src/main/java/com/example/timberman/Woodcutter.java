package com.example.timberman;

import android.graphics.Bitmap;
import android.graphics.Canvas;

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

    public void onClick(){
        count++;
        if(this.count == 1){
            for(int i = 0; i < arrBms.size(); i++){
                if(i == arrBms.size()-1){
                    this.x -= 500;
                    this.idCurrentBitmap=0;
                    break;
                }
                else if(this.idCurrentBitmap == i){
                    this.x += 500;
                    idCurrentBitmap = i+1;
                    break;
                }
            }
            count=0;
        }
    }

    @Override
    public Bitmap getBm() {
        return this.getArrBms().get(idCurrentBitmap);
    }
}
