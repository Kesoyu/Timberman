package com.example.timberman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

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
    private ArrayList<Stick>arrSicks=new ArrayList<>();
    public ArrayList<Integer> wylosowane = new ArrayList<Integer>();
    private int sumbranch=8, los;
    private ProgressBar pb;

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

    private int progressCounter=100;
    private boolean start;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        start=false;
        initWoodCutter();
        initSticks();
        leftPersentage = (Constants.SCREEN_WIDTH)*50/100;
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
                if(pb!=null){
                    pb.setProgress(progressCounter);//TODO Tutuaj co sie pierdoli z wskaznikeim finda od progressbara jutro to naprawie/dzis
                    progressCounter-=1;
                    if (progressCounter==0){
                        progressCounter=100;
                    }
                }
            }
        };
    }

    public void initSticks() {
        for (int i = 0; i < sumbranch; i++){
            if(i==0){
                los=2;
                wylosowane.add(2);
            }
            else{
                Random liczba = new Random();
                los = liczba.nextInt(2);
                wylosowane.add(los);
            }
            if (los == 0){
                if(wylosowane.get(i-1) != 0){
                    this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() - 300,526,300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                    arrSicks.get(i).setKolor(Stick.Kolor.SRODEK);
                }
                else{
                    this.arrSicks.add(new Stick(-47, arrSicks.get(arrSicks.size() - 1).getY() - 300, 1050, 300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewo));
                    arrSicks.get(i).setKolor(Stick.Kolor.LEWO);
                }
            }
            else if (los == 1){
                if(wylosowane.get(i-1) != 1){
                    this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() - 300,526,300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                    arrSicks.get(i).setKolor(Stick.Kolor.SRODEK);
                }
                else{
                    this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() - 300,1050,300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.prawo));
                    arrSicks.get(i).setKolor(Stick.Kolor.PRAWO);
                }
            }
            else{
                Log.d("MainActivity","dzaila"+i);
                this.arrSicks.add(new Stick(478,1620,526,300));
                arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                arrSicks.get(i).setKolor(Stick.Kolor.SRODEK);
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
        Log.d("MainActivity","WoodcuterY"+woodcutter.getY());
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.left1));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.left2));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.left3));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.right1));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.right2));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.right3));
        woodcutter.setArrBms(arrBms);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        if(start)
        {

            for(int i = 0;i<arrSicks.size(); i++){
                arrSicks.get(i).draw(canvas);
           }
            woodcutter.draw(canvas);
        }
//         else{ //todo tu bedzie else do zrestartowania gry
//             if(){
//
//             }
//             woodcutter.draw(canvas);
//        }
        handler.postDelayed(r, 10);
    }

    private void EdoTensei(){//Witam nie miale pomyslu an nazwe do tego jak chcecie to zmienice otuszuwu owy element zawiera rysywanie drzewka musialem tak przezkonwertowac zeby mi sie ladnie Umieranie postaci robilo :D
        //przesuwanie drzewa w dol
        for(int i = 0;i<arrSicks.size(); i++) { arrSicks.get(i).setY(arrSicks.get(i).getY() + 300); }
        //usuwanie dolngo elementu
        arrSicks.remove(0);
        //dodawanie elementu na gore
        Random liczba = new Random();
        los = liczba.nextInt(2);
        wylosowane.add(los);
        if (los == 0) {
            if(wylosowane.get(wylosowane.size()-2) != 0){
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size()-1).getY() - 300,526,300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.SRODEK);
            }
            else{
                this.arrSicks.add(new Stick(-47, arrSicks.get(arrSicks.size()-1).getY() - 300, 1050, 300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewo));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.LEWO);
            }
        }
        else if (los == 1) {
            if(wylosowane.get(wylosowane.size()-2) != 1){
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size()-1).getY() - 300,526,300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.SRODEK);
            }
            else{
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() - 300,1050,300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.prawo));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.PRAWO);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            xValue = event.getX();
            yValue = event.getY();
            if(xValue <= leftPersentage){
                woodcutter.onClick(1);
                if(woodcutter.getY()+1==arrSicks.get(0).getY() && arrSicks.get(0).getKolor() == Stick.Kolor.LEWO){
                    Log.d("OnTouchEventDead-Left","Gameover - Leftside");
                    //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek)
                    start=false;//TODO smierc dziala tylko wypierdala cale drzewo XD
                }
                else if(woodcutter.getY()+1==arrSicks.get(1).getY()+300 && arrSicks.get(1).getKolor() == Stick.Kolor.LEWO){
                    EdoTensei();
                    Log.d("OnTouchEventDead-Left","Gameover - Leftside");
                    //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek)
                    start=false;//TODO smierc dziala tylko wypierdala cale drzewo XD
                }
                else{
                    EdoTensei();
                }
            }
            else{
                woodcutter.onClick(2);
                if(woodcutter.getY()+1==arrSicks.get(0).getY() && arrSicks.get(0).getKolor() == Stick.Kolor.PRAWO){
                    Log.d("OnTouchEventDead-Right","Gameover - Rightside");
                    //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek)
                    start=false;//TODO smierc dziala tylko wypierdala cale drzewo XD
                }
                else if(woodcutter.getY()+1==arrSicks.get(1).getY()+300 && arrSicks.get(1).getKolor() == Stick.Kolor.PRAWO){
                    EdoTensei();
                    Log.d("OnTouchEventDead-Right","Gameover - Rightside");
                    //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek) tu na dole jest to co probwale ja :D
                    woodcutter.setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.nagrobek));
                    start=false;//TODO smierc dziala tylko wypierdala cale drzewo XD
                }
                else{
                    EdoTensei();
                }
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
