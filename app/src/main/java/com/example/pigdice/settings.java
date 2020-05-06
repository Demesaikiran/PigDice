package com.example.pigdice;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class settings extends AppCompatActivity {
    public AlertDialog dialog;
    public TinyDB tinydb;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        Switch soundToggle = findViewById(R.id.switch2);
        this.tinydb = new TinyDB(this);
        if(this.tinydb.getInt("soundToggle")==1) {
            soundToggle.setChecked(true);
        }
        else {
            soundToggle.setChecked(false);
        }
        soundToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    settings.this.tinydb.putInt("soundToggle", 1);
                }
                else {
                    settings.this.tinydb.putInt("soundToggle", 0);
                }

            }
        });

        Button button2 = findViewById(R.id.help);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, help.class);
                startActivity(intent);
            }
        });
    }


}
