package com.example.timberman;

import static android.view.View.INVISIBLE;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    public static TextView txt_score;
    public ImageView imageView_logo;
    public ImageButton btn_start,btn_shop,btn_info,btn_pause,btn_select;
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
        //wysokosc i szerokosc ekranu
        txt_score=findViewById(R.id.txt_score);

        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(R.layout.activity_main);

        imageView_logo=findViewById(R.id.imageView_logo);
        btn_pause=findViewById(R.id.btn_pause);
        btn_start=findViewById(R.id.btn_start);
        btn_info=findViewById(R.id.btn_info);
        btn_shop=findViewById(R.id.btn_shop);
        btn_select=findViewById(R.id.btn_select);
        gv=findViewById(R.id.gv);
        gv.setPb(((ProgressBar) findViewById(R.id.idpbbar)));

        if(gv.isIs_he_dead()) {
            btn_shop.setVisibility(View.VISIBLE);
        }



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gv.setStart(true);

                //w tymi miejscu trzeba dopisać schowanie punkotw

                btn_start.setVisibility(INVISIBLE);
                btn_info.setVisibility(INVISIBLE);
                btn_shop.setVisibility(INVISIBLE);
                btn_pause.setVisibility(View.VISIBLE);
                imageView_logo.setVisibility(INVISIBLE);
            }});


        // Animacja
        gv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
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
}