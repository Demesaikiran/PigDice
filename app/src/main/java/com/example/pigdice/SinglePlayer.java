package com.example.pigdice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SinglePlayer extends AppCompatActivity {

    final Handler handler =new Handler();
    private ArrayList dieImages = new ArrayList();
    private MediaPlayer rollSound;
    private MediaPlayer shakeSound;
    private Animation shake;
    private Animation violentShake;
    private TextView turnScore;
    private TextView playerScoreText;
    private int playerScore;
    private int HoldScore;


    public Button rollDice;
    public Button holdDice;
    public ImageView diceImage;
    public int diceSum;
    public int playerSum;
    public AlertDialog dialog;
    private Random random =new Random();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.singleplayer);

    }

    public void handlePlayerRoll() {
        //this.diceSum =this.dice.rollDie();
    }

    public void  onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SinglePlayer.this);

        View view  = LayoutInflater.from(SinglePlayer.this).inflate(R.layout.giveup_alert, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

        title.setText("GivingUP?");

        imageButton.setImageResource(R.drawable.gold);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePlayer.super.onBackPressed();
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
