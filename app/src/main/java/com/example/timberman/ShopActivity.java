package com.example.timberman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ShopActivity extends AppCompatActivity {
    public ImageButton Avatar1, btn_shop_back;
    public ImageView Avatar2,Avatar3,Avatar4,woodercutter1,popcat2,omniman3;
    public Button btn_next,btn_previous;
    public int avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


       // Avatar1 = findViewById(R.id.Avatar1);

        if(Constants.bestScore>10){
          //  Avatar1.setImageResource(R.drawable.shop);
        }
        btn_shop_back=findViewById(R.id.btn_shop_back);
        btn_next=findViewById(R.id.btn_next);
        btn_previous=findViewById(R.id.btn_previous);
        woodercutter1=findViewById(R.id.woodercutter1);
        popcat2=findViewById(R.id.popcat2);
        omniman3=findViewById(R.id.omniman3);
        avatar=Constants.Avatar;
        WhoChose(avatar);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatar++;
                if(avatar>2)
                    avatar=0;
                WhoChose(avatar);
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatar--;
                if(avatar==-1)
                    avatar=2;
                WhoChose(avatar);
            }
        });



        btn_shop_back.setOnClickListener(view -> {
            Constants.Restart=false;
            Intent intent= new Intent(ShopActivity.this,MainActivity.class);
            Constants.Avatar=avatar;
            startActivity(intent);
        });
//        Avatar1.setOnClickListener(v -> {
//            if(Constants.bestScore>10){
//               Constants.Avatar=1;
//            }
//        });

    }
    void WhoChose(int avatar)
    {
        if(avatar==0) {
            omniman3.setVisibility(View.INVISIBLE);
            woodercutter1.setVisibility(View.VISIBLE);
            popcat2.setVisibility(View.INVISIBLE);

        }

        else if (avatar==1) {
            woodercutter1.setVisibility(View.INVISIBLE);
            popcat2.setVisibility(View.VISIBLE);

            omniman3.setVisibility(View.INVISIBLE);
        }
        else if (avatar==2) {
           popcat2.setVisibility(View.INVISIBLE);
           woodercutter1.setVisibility(View.INVISIBLE);
          omniman3.setVisibility(View.VISIBLE);
        }
    }
}
