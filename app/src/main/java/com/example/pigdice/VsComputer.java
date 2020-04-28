package com.example.pigdice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class VsComputer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vscomputer);
    }



    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VsComputer.this);

        View view  = LayoutInflater.from(VsComputer.this).inflate(R.layout.giveup_alert, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

        title.setText("GivingUP?");

        imageButton.setImageResource(R.drawable.gold);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                VsComputer.super.onBackPressed();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setView(view);
        builder.show();



    }
}
