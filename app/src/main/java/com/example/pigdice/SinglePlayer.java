package com.example.pigdice;


import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class SinglePlayer extends AppCompatActivity {

    private ArrayList<Integer> dieImages = new ArrayList<Integer>();
    private MediaPlayer rollSound;
    private MediaPlayer shakeSound;
    //private Animation shake;
    private Animation violentShake;
    private int turnScore;
    private TextView playerScoreText;
    private int playerScore;
    private TextView sumText;
    private int HoldScore;
    private Dice dice = new Dice(0,0);
    private MediaPlayer holdSound;
    private boolean gamewon =false;
    public TinyDB tinydb;

    public int diceSum;
    public int playerSum;
    /*public AlertDialog dialog;
    private Random random =new Random();

     */



    public Button rollDice;
    public Button holdDice;
    private TextView playerTextView;
    public ImageView dice1;
    private RotateAnimation rotateAnimation;






    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.singleplayer);
        this.rollSound = MediaPlayer.create(SinglePlayer.this, R.raw.rolldice2);
        this.shakeSound = MediaPlayer.create(SinglePlayer.this, R.raw.shake);
        this.holdSound = MediaPlayer.create(SinglePlayer.this, R.raw.hold);
        this.violentShake = AnimationUtils.loadAnimation(SinglePlayer.this, R.anim.violent_shake);
        this.rollDice = findViewById(R.id.button6);
        this.holdDice = findViewById(R.id.button7);
        this.playerScoreText = findViewById(R.id.textView);
        this.dice1 = findViewById(R.id.imageView);
        this.sumText = findViewById(R.id.textView4);
        this.playerScoreText.setText("0");

        this.rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.rotateAnimation.setDuration(300);
        initializeDie();
        //toggleSound();

        this.rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinglePlayer.this.dice1.startAnimation(SinglePlayer.this.rotateAnimation);
                SinglePlayer.this.rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                    @Override
                    public void onAnimationStart(Animation animation) {
                        SinglePlayer.this.rollDice.setEnabled(false);

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        SinglePlayer.this.rollDice.setEnabled(true);
                        SinglePlayer.this.holdDice.setEnabled(true);
                        SinglePlayer.this.holdDice.setAlpha(1.0f);
                        SinglePlayer.this.handlePlayerRoll();


                    }


                });


            }
        });

        this.holdDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinglePlayer.this.playsound(3);
                SinglePlayer.this.holdDice.setEnabled(false);
                SinglePlayer.this.holdDice.setAlpha(0.5f);
                SinglePlayer.this.rollDice.setEnabled(true);
                SinglePlayer.this.handleHold(SinglePlayer.this.playerSum);


            }
        });





    }

    public void handlePlayerRoll() {
        this.diceSum = dice.rollDie1();
        setDieImages(this.diceSum);
        if(this.diceSum == 1) {
            playsound(2);
            this.dice1.startAnimation(this.violentShake);
            this.holdDice.setEnabled(false);
            //this.playerScore = ;
            this.playerSum = 0;
            this.sumText.setText("0");
            //this.playerScoreText.setText("0");
        }
        playsound(1);
        this.playerSum += diceSum;
        TextView textview =this.sumText;
        String sb5 = "" +
                this.playerSum;
        textview.setText(sb5);
    }
    public void handleHold(int i) {
        this.playerSum = 0;
        this.playerScore += i;
        TextView textView =this.playerScoreText;
        String sb = "" +
                this.playerScore;
        textView.setText(sb);
        TextView tv2 = this.sumText;
        String sb2 = "" +
                this.playerSum;
        tv2.setText(sb2);

    }

    public void  onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SinglePlayer.this);

        View view  = LayoutInflater.from(SinglePlayer.this).inflate(R.layout.giveup_alert, null);
        TextView title =  view.findViewById(R.id.title);
        ImageButton imageButton =  view.findViewById(R.id.image);

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
    private void toggleSound() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (this.tinydb.getInt("soundToggle") == 1) {
            assert audioManager != null;
            audioManager.setStreamMute(3, false);
        } else {
            assert audioManager != null;
            audioManager.setStreamMute(3, true);
        }
    }


    private void initializeDie() {
        switch (2) {
            case 0:
                this.dice1.setImageResource(R.drawable.dice1);

                this.dieImages.add(R.drawable.dice1);
                this.dieImages.add(R.drawable.dice2);
                this.dieImages.add(R.drawable.dice3);
                this.dieImages.add(R.drawable.dice4);
                this.dieImages.add(R.drawable.dice5);
                this.dieImages.add(R.drawable.dice6);
                return;

            case 1:
                this.dice1.setImageResource(R.drawable.dice1_silver);

                this.dieImages.add(R.drawable.dice1_silver);
                this.dieImages.add(R.drawable.dice2_silver);
                this.dieImages.add(R.drawable.dice3_silver);
                this.dieImages.add(R.drawable.dice4_silver);
                this.dieImages.add(R.drawable.dice5_silver);
                this.dieImages.add(R.drawable.dice6_silver);
                return;
            case 2:
                this.dice1.setImageResource(R.drawable.dice1_gold);

                this.dieImages.add(R.drawable.dice1_gold);
                this.dieImages.add(R.drawable.dice2_gold);
                this.dieImages.add(R.drawable.dice3_gold);
                this.dieImages.add(R.drawable.dice4_gold);
                this.dieImages.add(R.drawable.dice5_gold);
                this.dieImages.add(R.drawable.dice6_gold);
                return;

            default:
                return;
        }
    }


    private void setDieImages(int i) {
        this.dice1.setImageResource(this.dieImages.get(i - 1));
    }


    public void playsound(int i) {
        switch (i) {
            case 1:
                if (this.rollSound.isPlaying()) {
                    this.rollSound.stop();
                    this.rollSound.reset();
                    this.rollSound = MediaPlayer.create(this, R.raw.rolldice2);

                }
                this.rollSound.start();
                return;
            case 2:
                if (this.shakeSound.isPlaying()) {
                    this.shakeSound.stop();
                    this.shakeSound.reset();
                    this.shakeSound = MediaPlayer.create(this, R.raw.shake);
                }
                this.shakeSound.start();
                return;
            case 3:
                if (this.holdSound.isPlaying()) {
                    this.holdSound.stop();
                    this.holdSound.reset();
                    this.holdSound = MediaPlayer.create(this, R.raw.hold);
                }
                this.holdSound.start();
                return;
            default:
                return;

        }
    }






}
