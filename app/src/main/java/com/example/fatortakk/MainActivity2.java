package com.example.fatortakk;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    ImageButton AllReceipts, Insights, MyAccount, PersonalQR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton AllReceipts, Insights, MyAccount;
        ImageButton PersonalQR;

        PersonalQR = (ImageButton) findViewById(R.id.button2);
        MyAccount = (ImageButton) findViewById(R.id.button4);
        AllReceipts = (ImageButton) findViewById(R.id.button3);
        Insights = (ImageButton) findViewById(R.id.button1);
        PersonalQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        MyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity2.this, MyAccount.class);
                startActivity(intent);
            }
        });

        AllReceipts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AllReceipts.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
