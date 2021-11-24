package com.example.timberman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private final android.os.Handler handler;
    private final Runnable runnable;
    private Woodcutter woodcutter;



    private ArrayList<Stick>arrSicks=new ArrayList<>();


    private int sumbranch=8, los;
    private float xValue, leftPersentage;
    private boolean changeSide=false;


    private ProgressBar pb;
    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }
    private int progressCounter=100;


    private boolean start;
    public  int score;
    public boolean is_he_dead;
    public static TextView txt_score;

//    private Stick stick;
//    private View view;
//    private ImageButton btn_shop;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        btn_shop=findViewById(R.id.btn_shop);
//        view=this.view;
       //txt_score=findViewById(R.id.txt_score);
        //score=0;
        start = false;
        is_he_dead = false;
        score=0;

        initWoodCutter();
        initSticks();

        leftPersentage = (Constants.SCREEN_WIDTH)*50/100;

        handler = new Handler();
        runnable = new Runnable() {
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

        //pierwszy element
        this.arrSicks.add(new Stick(478,1620,526,300));
        arrSicks.get(0).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
        arrSicks.get(0).setKolor(Stick.Kolor.SRODEK);


        for (int i = 1; i < sumbranch; i++){
                Random liczba = new Random();
                los = liczba.nextInt(2);

            if (los == 0){
                if (!changeSide) {
                    this.arrSicks.add(new Stick(-47, arrSicks.get(arrSicks.size() - 1).getY() - 300, 1050, 300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewo));
                    arrSicks.get(i).setKolor(Stick.Kolor.LEWO);
                }
                else{
                    this.arrSicks.add(new Stick(478, arrSicks.get(arrSicks.size() - 1).getY() - 300, 526, 300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                    arrSicks.get(i).setKolor(Stick.Kolor.SRODEK);
                    changeSide=false;
                }
            }
            else if (los == 1){
                if (changeSide) {
                    this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() - 300,1050,300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.prawo));
                    arrSicks.get(i).setKolor(Stick.Kolor.PRAWO);
                }
                else{
                    this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() - 300,526,300));
                    arrSicks.get(i).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                    arrSicks.get(i).setKolor(Stick.Kolor.SRODEK);
                    changeSide=true;
                }
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
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.nagrobek));
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
        if(is_he_dead){ //todo tu bedzie else do zrestartowania gry
            woodcutter.smierc();
            for(int i = 0;i<arrSicks.size(); i++){
                arrSicks.get(i).draw(canvas);
            }
            woodcutter.draw(canvas);
        }
        handler.postDelayed(runnable, 10);
    }

    private void EdoTensei(){


     //  MainActivity.txt_score.setText(""+score);//todo ta linijka wypieradala cały program a jest potrzebna do pokazania nowych pkt ;)

        //przesuwanie drzewa w dol
        for(int i = 0;i<arrSicks.size(); i++) { arrSicks.get(i).setY(arrSicks.get(i).getY() + 300); }

        //usuwanie dolngo elementu
        arrSicks.remove(0);

        //dodawanie elementu na gore
        Random liczba = new Random();
        los = liczba.nextInt(2);

        if (los == 0) {
            if (!changeSide) {
                this.arrSicks.add(new Stick(-47, arrSicks.get(arrSicks.size()-1).getY() - 300, 1050, 300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.lewo));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.LEWO);
            }
            else{
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size()-1).getY() - 300,526,300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.SRODEK);
                changeSide=false;
            }
        }
        else if (los == 1) {
            if (changeSide) {
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size() - 1).getY() - 300,1050,300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.prawo));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.PRAWO);
            }
            else{
                this.arrSicks.add(new Stick(478,arrSicks.get(arrSicks.size()-1).getY() - 300,526,300));
                arrSicks.get(arrSicks.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.srodek));
                arrSicks.get(arrSicks.size()-1).setKolor(Stick.Kolor.SRODEK);
                changeSide=true;
            }
        }

    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if (start) {

                xValue = event.getX();

                if (xValue <= leftPersentage) {
                    Constants.click=1;
                    Constants.clickL++;
                    woodcutter.onClick(1);
                    if (woodcutter.getY() + 1 == arrSicks.get(0).getY() && arrSicks.get(0).getKolor() == Stick.Kolor.LEWO) {
                        Log.d("OnTouchEventDead-Left", "Gameover - Leftside");
                        //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek)
                        is_he_dead = true;//TODO smierc dziala tylko wypierdala cale drzewo XD
                        start = false;

                    }
                    else if (woodcutter.getY() + 1 == arrSicks.get(1).getY() + 300 && arrSicks.get(1).getKolor() == Stick.Kolor.LEWO) {
                        EdoTensei();
                        Log.d("OnTouchEventDead-Left", "Gameover - Leftside");
                        //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek)
                        is_he_dead = true;//TODO smierc dziala tylko wypierdala cale drzewo XD
                        start = false;


                    }
                    else {
                        EdoTensei();
                    }
                }
                else {
                    Constants.click=2;
                    Constants.clickR++;
                    woodcutter.onClick(2);
                    if (woodcutter.getY() + 1 == arrSicks.get(0).getY() && arrSicks.get(0).getKolor() == Stick.Kolor.PRAWO) {
                        Log.d("OnTouchEventDead-Right", "Gameover - Rightside");
                        //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek)
                        is_he_dead=true;//TODO smierc dziala tylko wypierdala cale drzewo XD
                        start = false;
                    }
                    else if (woodcutter.getY() + 1 == arrSicks.get(1).getY() + 300 && arrSicks.get(1).getKolor() == Stick.Kolor.PRAWO) {
                        EdoTensei();
                        Log.d("OnTouchEventDead-Right", "Gameover - Rightside");
                        //TODO stawianie nagrobka - probowalem ale nie orietuje sie w tym jak jest jakas tablica do przekazania no kurwa nie dziala(podmienienie woodcuter-drawble na nagrobek) tu na dole jest to co probwale ja :D
                        woodcutter.setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.nagrobek));
                        is_he_dead = true;
                        start = false;
                    }
                    else {
                        score++;
                       Log.d("pisjhd","wiadomosc"+getScore());
                        EdoTensei();

                    }
                }
            }
        }
        return true;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStart() {
        return start;
    };

    public void setStart(boolean start) {
        this.start = start;
    };

    public boolean isIs_he_dead() {
        return is_he_dead;
    }

    public void setIs_he_dead(boolean is_he_dead) {
        this.is_he_dead = is_he_dead;
    }
}
