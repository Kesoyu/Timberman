package com.example.timberman;

import static android.view.View.INVISIBLE;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageView imageView_logo,imageView_game_over;
    MediaPlayer player;
    public ImageButton btn_start,btn_shop,btn_info,btn_pause,btn_select,btn_retry,btn_musicon,btn_musicoff;
    private GameView gv;

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

        btn_musicoff=findViewById(R.id.btn_musicoff);
        btn_musicon=findViewById(R.id.btn_musicon);
        imageView_logo=findViewById(R.id.imageView_logo);
        btn_pause=findViewById(R.id.btn_pause);
        btn_start=findViewById(R.id.btn_start);
        btn_info=findViewById(R.id.btn_info);
        btn_shop=findViewById(R.id.btn_shop);

        btn_retry=findViewById(R.id.btn_retry);
        btn_select=findViewById(R.id.btn_select);
        gv=findViewById(R.id.gv);
        gv.setPb(findViewById(R.id.idpbbar));
        if(player==null) {
            player = MediaPlayer.create(this, R.raw.dx);
            player.setLooping(true);
            player.seekTo(0);
            player.setVolume(0.5f, 0.5f);

        }
        player.start();







        if(!Constants.Restart) {
            btn_start.setOnClickListener(view -> {
                gv.setStart(true);
                TextView txt_best_score = findViewById(R.id.txt_best_score); txt_best_score.setText(String.valueOf(Constants.bestScore)); // linia do sprawdzania czy wynik dobrze sie zapisuje

                //w tymi miejscu trzeba dopisać schowanie punkotw

                btn_start.setVisibility(INVISIBLE);
                btn_retry.setVisibility(INVISIBLE);
                btn_info.setVisibility(INVISIBLE);
                btn_shop.setVisibility(INVISIBLE);
                btn_pause.setVisibility(View.VISIBLE);
                imageView_logo.setVisibility(INVISIBLE);
            });
        }
        else{
            gv.setStart(true);
            TextView txt_best_score = findViewById(R.id.txt_best_score); txt_best_score.setText(String.valueOf(Constants.bestScore)); // linia do sprawdzania czy wynik dobrze sie zapisuje

            //w tymi miejscu trzeba dopisać schowanie punkotw

            btn_start.setVisibility(INVISIBLE);
            btn_info.setVisibility(INVISIBLE);
            btn_shop.setVisibility(INVISIBLE);
            btn_retry.setVisibility(INVISIBLE);
            btn_pause.setVisibility(View.VISIBLE);
            imageView_logo.setVisibility(INVISIBLE);
        }

        // Animacja
        gv.setOnTouchListener((view, motionEvent) -> {

            TextView txt_score = findViewById(R.id.txt_score);
            txt_score.setText(String.valueOf(Constants.score));
            TextView txt_best_score = findViewById(R.id.txt_best_score); txt_best_score.setText(String.valueOf(Constants.bestScore));
            if(Constants.score>Constants.bestScore){ saveData(); }
            if(Constants.IsDead){
                btn_retry.setVisibility(View.VISIBLE);
                btn_shop.setVisibility(View.VISIBLE);
            }

            if (Constants.click == 2 && Constants.clickR!=0) {
                switch (Constants.clickR){
                    case 1:
                        ImageView AnimatedTree = findViewById(R.id.animation);
                        AnimatedTree.setImageResource(R.drawable.animation);
                        AnimationDrawable runningTree = (AnimationDrawable) AnimatedTree.getDrawable();
                        runningTree.start();
                        break;
                    case 2:
                        ImageView AnimatedTree2 = findViewById(R.id.animation2);
                        AnimatedTree2.setImageResource(R.drawable.animation2);
                        AnimationDrawable runningTree2 = (AnimationDrawable) AnimatedTree2.getDrawable();
                        runningTree2.start();
                        break;
                    case 3:
                        ImageView AnimatedTree3 = findViewById(R.id.animation3);
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
                        ImageView AnimatedTree4 = findViewById(R.id.animation4);
                        AnimatedTree4.setImageResource(R.drawable.animation4);
                        AnimationDrawable runningTree4 = (AnimationDrawable) AnimatedTree4.getDrawable();
                        runningTree4.start();
                        break;
                    case 2:
                        ImageView AnimatedTree5 = findViewById(R.id.animation5);
                        AnimatedTree5.setImageResource(R.drawable.animation5);
                        AnimationDrawable runningTree5 = (AnimationDrawable) AnimatedTree5.getDrawable();
                        runningTree5.start();
                        break;
                    case 3:
                        ImageView AnimatedTree6 = findViewById(R.id.animation6);
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
        });

        btn_shop.setOnClickListener(view -> {
            if(Constants.score>Constants.bestScore){ saveData(); }
             Intent intent = new Intent(MainActivity.this,ShopActivity.class);
            startActivity(intent);
        });
        btn_retry.setOnClickListener(view -> {

            Constants.Restart=true;
            if(Constants.score>Constants.bestScore){ saveData(); }
            Constants.score=0;
            recreate();

            //w tymi miejscu trzeba dopisać schowanie punkotw
        });
        btn_info.setOnClickListener(view -> Toast.makeText(MainActivity.this, R.string.info_string, Toast.LENGTH_LONG).show());
        btn_pause.setOnClickListener(view -> {
            if(!Constants.IsDead){
                if(gv.getStart()) {
                    gv.setStart(false);
                    btn_shop.setVisibility(View.VISIBLE);
                    btn_retry.setVisibility(View.VISIBLE);
                    btn_musicon.setVisibility(View.VISIBLE);
                    btn_musicoff.setVisibility(View.VISIBLE);
                }
                else {
                    gv.setStart(true);
                    btn_shop.setVisibility(INVISIBLE);
                    btn_retry.setVisibility(INVISIBLE);
                    btn_musicon.setVisibility(INVISIBLE);
                    btn_musicoff.setVisibility(INVISIBLE);
                }
            }
        });
        btn_select.setOnClickListener(view -> {
            btn_start.setVisibility(View.VISIBLE);
            btn_info.setVisibility(View.VISIBLE);
            btn_shop.setVisibility(View.VISIBLE);
            btn_select.setVisibility(INVISIBLE);
        });
        btn_musicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.start();
            }
        });
        btn_musicoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.pause();
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
    //muzyka
    public void onClick(View view) {
        if(view.getId()==R.id.stop){
            if(player.isPlaying()){
                //gra
                player.pause();
                //stop_play.setBackgroundResource(R.drawable.ic_play);
                //we ogarnijcie grafiki pod przycisk od pauzy i play
            }
            else{
                player.start();
                //stop_play.setBackgroundResource(R.drawable.ic_pause);
                //to samo co wyżej ale to kurwa zrób
            }
        }
    }
}