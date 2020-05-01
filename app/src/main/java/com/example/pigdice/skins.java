package com.example.pigdice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class skins extends AppCompatActivity {

    public Button whiteButton;
    public Button goldButton;
    public Button silverButton;
    public TinyDB tinydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.tinydb = new TinyDB(this);
        setContentView(R.layout.activity_skins);
        this.whiteButton = findViewById(R.id.whiteDice);
        this.goldButton =  findViewById(R.id.goldDice);
        this.silverButton = findViewById(R.id.silverDice);


        this.whiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skins.this.tinydb.putInt("SkinInt", 0);
                Toast.makeText(skins.this, "White Dice Selected", Toast.LENGTH_SHORT).show();

            }
        });

        this.silverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skins.this.tinydb.putInt("SkinInt",1);
                Toast.makeText(skins.this, "Silver Dice Selected", Toast.LENGTH_SHORT).show();
            }
        });

        this.goldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skins.this.tinydb.putInt("SkinInt",2);
                Toast.makeText(skins.this, "Gold Dice Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
