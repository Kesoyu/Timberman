package com.example.timberman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ShopActivity extends AppCompatActivity {
    public ImageButton Avatar1;
    public ImageView Avatar2,Avatar3,Avatar4;
    public Button btn_shop_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Avatar1 = findViewById(R.id.Avatar1);

        if(Constants.bestScore>10){
            Avatar1.setImageResource(R.drawable.shop);
        }
        btn_shop_back=findViewById(R.id.btn_shop_back);
        btn_shop_back.setOnClickListener(view -> {
            Constants.Restart=false;
            Intent intent= new Intent(ShopActivity.this,MainActivity.class);
            startActivity(intent);
        });
        Avatar1.setOnClickListener(v -> {
            if(Constants.bestScore>10){
               Constants.Avatar=1;
            }
        });
    }
}