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
    private Switch soundToggle;
    public TinyDB tinydb;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        this.soundToggle =(Switch)findViewById(R.id.switch2);
        this.tinydb = new TinyDB(this);
        if(this.tinydb.getInt("soundToggle")==1) {
            this.soundToggle.setChecked(true);
        }
        else {
            this.soundToggle.setChecked(false);
        }
        this.soundToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        Button button1 = (Button) findViewById(R.id.view);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, terms.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.help);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, help.class);
                startActivity(intent);
            }
        });
    }


}
