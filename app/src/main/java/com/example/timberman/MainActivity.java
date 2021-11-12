package com.example.timberman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    public static RelativeLayout rl_game_over;
    private  GameView gv;
    public static ImageButton btn_start,btn_shop,btn_info;


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
        btn_start=findViewById(R.id.btn_start);
        btn_info=findViewById(R.id.btn_info);
        btn_shop=findViewById(R.id.btn_shop);
        gv=findViewById(R.id.gv);

    }
}