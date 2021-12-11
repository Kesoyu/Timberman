package com.example.timberman;

import static android.view.View.INVISIBLE;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
//    public static TextView txt_score;
    public ImageView imageView_logo,imageView_game_over;
    public ImageButton btn_start,btn_shop,btn_info,btn_pause,btn_select,btn_retry;
    private GameView gv;

//    public RelativeLayout rl_game_over;
//    public ImageView imageView_timber_man;
//    public int click;
//    public boolean dead;


    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// ustawia full screen
        DisplayMetrics dm = new DisplayMetrics();// wyświetlacz w telefonie
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);

        //wczytywanie danych z pliku
        loadData();
        //wysokosc i szerokosc ekranu
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;


        setContentView(R.layout.activity_main);


        TextView txt_score = (TextView) findViewById(R.id.txt_score); txt_score.setText(" "+Constants.bestScore); // linia do sprawdzania czy wynik dobrze sie zapisuje

        imageView_logo=findViewById(R.id.imageView_logo);
        btn_pause=findViewById(R.id.btn_pause);
        btn_start=findViewById(R.id.btn_start);
        btn_info=findViewById(R.id.btn_info);
        btn_shop=findViewById(R.id.btn_shop);

        btn_retry=findViewById(R.id.btn_retry);
        btn_select=findViewById(R.id.btn_select);
        gv=findViewById(R.id.gv);
        gv.setPb(((ProgressBar) findViewById(R.id.idpbbar)));


        if(Constants.Restart==false) {
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gv.setStart(true);

                    //w tymi miejscu trzeba dopisać schowanie punkotw

                    btn_start.setVisibility(INVISIBLE);
                    btn_retry.setVisibility(INVISIBLE);
                    btn_info.setVisibility(INVISIBLE);
                    btn_shop.setVisibility(INVISIBLE);
                    btn_pause.setVisibility(View.VISIBLE);
                    imageView_logo.setVisibility(INVISIBLE);
                }
            });
        }
        else{
            gv.setStart(true);

            //w tymi miejscu trzeba dopisać schowanie punkotw

            btn_start.setVisibility(INVISIBLE);
            btn_info.setVisibility(INVISIBLE);
            btn_shop.setVisibility(INVISIBLE);
            btn_retry.setVisibility(INVISIBLE);
            btn_pause.setVisibility(View.VISIBLE);
            imageView_logo.setVisibility(INVISIBLE);
        }

        // Animacja
        gv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                TextView txt_score = (TextView) findViewById(R.id.txt_score);
                txt_score.setText(" "+Constants.score);

                if(Constants.IsDead){
                    btn_retry.setVisibility(View.VISIBLE);
                    btn_shop.setVisibility(View.VISIBLE);
                }

                Log.d("log", "zobacz"+gv.getScore());
                int wypisz=gv.getScore();
               // Log.d("gv","gw"+wypisz);
              // txt_score.setText(""+gv.score);
                if (Constants.click == 2 && Constants.clickR!=0) {
                    switch (Constants.clickR){

                        case 1:
                            ImageView AnimatedTree = (ImageView) findViewById(R.id.animation);
                            AnimatedTree.setImageResource(R.drawable.animation);
                            AnimationDrawable runningTree = (AnimationDrawable) AnimatedTree.getDrawable();
                            runningTree.start();
                            break;
                        case 2:
                            ImageView AnimatedTree2 = (ImageView) findViewById(R.id.animation2);
                            AnimatedTree2.setImageResource(R.drawable.animation2);
                            AnimationDrawable runningTree2 = (AnimationDrawable) AnimatedTree2.getDrawable();
                            runningTree2.start();
                            break;
                        case 3:
                            ImageView AnimatedTree3 = (ImageView) findViewById(R.id.animation3);
                            AnimatedTree3.setImageResource(R.drawable.animation3);
                            AnimationDrawable runningTree3 = (AnimationDrawable) AnimatedTree3.getDrawable();
                            runningTree3.start();
                            Constants.clickR =0;
                            break;
                        default: break;
                    }
                    Constants.click = 0;
                }
                else if (Constants.click == 1 && Constants.clickL!=0) {
                    switch (Constants.clickL){
                        case 1:
                            ImageView AnimatedTree4 = (ImageView) findViewById(R.id.animation4);
                            AnimatedTree4.setImageResource(R.drawable.animation4);
                            AnimationDrawable runningTree4 = (AnimationDrawable) AnimatedTree4.getDrawable();
                            runningTree4.start();
                            break;
                        case 2:
                            ImageView AnimatedTree5 = (ImageView) findViewById(R.id.animation5);
                            AnimatedTree5.setImageResource(R.drawable.animation5);
                            AnimationDrawable runningTree5 = (AnimationDrawable) AnimatedTree5.getDrawable();
                            runningTree5.start();
                            break;
                        case 3:
                            ImageView AnimatedTree6 = (ImageView) findViewById(R.id.animation6);
                            AnimatedTree6.setImageResource(R.drawable.animation6);
                            AnimationDrawable runningTree6 = (AnimationDrawable) AnimatedTree6.getDrawable();
                            runningTree6.start();
                            Constants.clickL =0;
                            break;
                        default: break;
                    }
                    Constants.click = 0;
                }
                return false;
            }
        });

        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
            }
        });
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.Restart=true;
                Constants.score=0;
                recreate();
                //w tymi miejscu trzeba dopisać schowanie punkotw
            }
        });
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.info_string, Toast.LENGTH_LONG).show();
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,R.string.pause_string, Toast.LENGTH_LONG).show();
                btn_shop.setVisibility(View.VISIBLE);


            }
        });
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setVisibility(View.VISIBLE);
                btn_info.setVisibility(View.VISIBLE);
                btn_shop.setVisibility(View.VISIBLE);
                btn_select.setVisibility(INVISIBLE);
            }
        });

    }

    // zapis do pliku najlepszego wyniku gracza
    @Override
    protected void onPause() {
        super.onPause();
        if(Constants.score>Constants.bestScore){ saveData(); }
    }
    private  void saveData() {
        SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("best_score", Constants.score);
        editor.apply();
    }
    private  void loadData() {
        SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
        Constants.bestScore = prefs.getInt("best_score", 0);
    }

}