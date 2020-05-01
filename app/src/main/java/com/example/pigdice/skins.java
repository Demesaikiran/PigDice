package com.example.pigdice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class skins extends AppCompatActivity {

    public Button whiteButton;
    public Button goldButton;
    public Button silverButton;
    public TinyDB tinydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skins);
        this.whiteButton = findViewById(R.id.whiteDice);
        this.goldButton =  findViewById(R.id.goldDice);
        this.silverButton = findViewById(R.id.silverDice);


        this.whiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skins.this.tinydb.putInt("SkinInt", 0);

            }
        });

        this.silverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skins.this.tinydb.putInt("SkinInt",1);
            }
        });

        this.goldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skins.this.tinydb.putInt("SkinInt",2);
            }
        });

    }
}
