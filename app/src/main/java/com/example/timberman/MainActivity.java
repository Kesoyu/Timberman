package com.example.timberman;

import static com.example.timberman.R.id.btn_shop_back;
import static com.example.timberman.R.id.start;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static RelativeLayout rl_game_over;
    private  GameView gv;
    public static ImageView imageView_logo,imageView_timber_man;
    public static ImageButton btn_start,btn_shop,btn_info,btn_pause,btn_select;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);// ustawia full screen
        DisplayMetrics dm = new DisplayMetrics();// wyświetlacz w telefonie
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //wysokosc i szerokosc ekranu
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

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gv.setStart(true);
                //w tymi miejscu trzeba dopisać schowanie punkotw
                btn_start.setVisibility(view.INVISIBLE);
                btn_info.setVisibility(view.INVISIBLE);
                btn_shop.setVisibility(view.INVISIBLE);
                btn_pause.setVisibility(view.VISIBLE);
                imageView_logo.setVisibility(view.INVISIBLE);

            }
        });
        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);

            }
        });
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        R.string.info_string, Toast.LENGTH_LONG).show();
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        R.string.pause_string, Toast.LENGTH_LONG).show();

            }
        });
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setVisibility(view.VISIBLE);
                btn_info.setVisibility(view.VISIBLE);
                btn_shop.setVisibility(view.VISIBLE);
                btn_select.setVisibility(view.INVISIBLE);
            }
        });


    }
}