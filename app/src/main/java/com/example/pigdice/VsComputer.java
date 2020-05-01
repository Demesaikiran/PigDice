package com.example.pigdice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ComputableLiveData;

import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.PrintStream;
import java.util.ArrayList;

public class VsComputer extends AppCompatActivity {
    private static final String TAG = "ComputerTag";

    static int gameChangeCounter;
    private TinyDB tinydb;
    private TextView turnscore;
    private Dice dice = new Dice(0, 0);
    private Button rollDice;
    private ArrayList<Integer> dieImages = new ArrayList<Integer>();
    private MediaPlayer rollSound;
    private MediaPlayer shakeSound;
    private Animation violentShake;
    private Animation shake;
    private int turnScore;
    private TextView playerScoreText;
    private TextView computerScoreText;
    private int playerScore;
    private TextView sumText;
    private MediaPlayer holdSound;
    private boolean gamewon =false;
    public int computerSum,computerScore;


    public int diceSum1, diceSum2;
    public int playerSum;
    public Button holdDice;
    private TextView playerTextView;
    public ImageView dice1;
    public ImageView dice2;
    private RotateAnimation rotateAnimation;
    Handler handler = new Handler();
    private int holdScore;
    private int originalScore;
    private int levelScore;
    private int reRollPercent;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.vscomputer);
        this.rollSound = MediaPlayer.create(VsComputer.this, R.raw.rolldice2);
        this.shakeSound = MediaPlayer.create(VsComputer.this, R.raw.shake);
        this.holdSound = MediaPlayer.create(VsComputer.this, R.raw.hold);
        this.shake = AnimationUtils.loadAnimation(VsComputer.this, R.anim.shake);
        this.violentShake = AnimationUtils.loadAnimation(VsComputer.this, R.anim.violent_shake);
        Level level = (Level) getIntent().getSerializableExtra("levelObject");
        this.holdScore = level.getHoldValue();
        this.computerScore = level.getCompScore();
        this.originalScore = level.getCompScore();
        this.levelScore = level.getLevelScore();
        this.reRollPercent = level.getRerollPercent();
        this.rollDice = findViewById(R.id.button6);
        this.holdDice = findViewById(R.id.button7);
        this.holdDice.setEnabled(false);
        this.playerScoreText = findViewById(R.id.textView);
        this.computerScoreText = findViewById(R.id.textView3);
        this.dice1 = findViewById(R.id.imageView);
        this.dice2 = findViewById(R.id.imageView2);
        this.sumText = findViewById(R.id.textView4);
        this.playerScoreText.setText("0");
        this.tinydb = new TinyDB(this);

        this.rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        this.rotateAnimation.setDuration(300);
        initializeDie();
        toggleSound();
        TextView textview2 =this.computerScoreText;
        String sb2 = "" +
                this.computerScore;
        textview2.setText(sb2);


        this.rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VsComputer.this.dice1.startAnimation(VsComputer.this.rotateAnimation);
                VsComputer.this.dice2.startAnimation(VsComputer.this.rotateAnimation);
                VsComputer.this.rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        VsComputer.this.rollDice.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        VsComputer.this.rollDice.setEnabled(true);
                        VsComputer.this.holdDice.setEnabled(true);
                        VsComputer.this.holdDice.setAlpha(1.0f);
                        VsComputer.this.handlePlayerRoll();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        this.holdDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VsComputer.this.playsound(3);
                VsComputer.this.holdDice.setEnabled(false);
                VsComputer.this.holdDice.setAlpha(0.5f);
                VsComputer.this.rollDice.setEnabled(false);
                VsComputer.this.rollDice.setAlpha(0.5f);

                VsComputer.this.handleHold(VsComputer.this.playerSum);

            }
        });



    }

    public void handlePlayerRoll() {
        this.diceSum1 = dice.rollDie1();
        this.diceSum2 = dice.rollDie2();

        setDieImages(this.diceSum1, this.diceSum2);

        if(this.diceSum1 == 1 && this.diceSum2 == 1) {
            playsound(2);
            this.dice1.startAnimation(this.violentShake);
            this.dice2.startAnimation(this.violentShake);
            this.playerScore = 0;
            this.playerSum = 0;
            this.sumText.setText("0");
            this.playerScoreText.setText("0");
            this.holdDice.setEnabled(false);
            this.holdDice.setAlpha(0.5f);
            this.rollDice.setEnabled(false);
            this.rollDice.setAlpha(0.5f);
            this.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    VsComputer.this.computersRoll();
                }
            }, 2000);

            return;

        }

        if (this.diceSum1 == 1 || this.diceSum2 == 1) {
            playsound(2);
            this.dice2.startAnimation(this.shake);
            this.dice1.startAnimation(this.shake);
            this.holdDice.setEnabled(false);
            this.holdDice.setAlpha(0.5f);
            this.rollDice.setEnabled(false);
            this.rollDice.setAlpha(0.5f);
            this.playerSum = 0;
            this.sumText.setText("0");
            this.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    VsComputer.this.computersRoll();
                }
            }, 1500);
            return;


        }

        playsound(1);
        this.playerSum += this.dice.sumNumbers(this.diceSum1, this.diceSum2);
        TextView textView = this.sumText;
        String sb = "" +
                this.playerSum;
        textView.setText(sb);

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


        if(!this.gamewon) {
            this.handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    VsComputer.this.computersRoll();
                }
            }, 2000);
        }

    }

    public void computersRoll(){
        PrintStream printStream = System.out;
        String sb = "";
        // Need to check once to change game

        this.diceSum1 = this.dice.rollDie1();
        this.diceSum2 = this.dice.rollDie2();
        this.dice1.startAnimation(this.rotateAnimation);
        this.dice2.startAnimation(this.rotateAnimation);
        this.rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                VsComputer.this.handleComputerAnimation(VsComputer.this.diceSum1, VsComputer.this.diceSum2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void handleComputerAnimation(int i, int j){
        if(i ==1 && j == 1) {
            playsound(2);
            this.dice1.startAnimation(this.violentShake);
            this.dice2.startAnimation(this.violentShake);
            setDieImages(i, j);
            this.holdDice.setEnabled(false);
            this.rollDice.setEnabled(true);
            this.rollDice.setAlpha(1.0f);
            gameChangeCounter = 0;
            this.computerSum = 0;
            this.sumText.setText("0");
            this.computerScore = 0;
            TextView textView = this.computerScoreText;
            String sb = "" +
                    this.computerScore;
            textView.setText(sb);
            return;
        }

        if (i == 1 || j == 1) {
            playsound(2);
            this.dice2.startAnimation(this.shake);
            this.dice1.startAnimation(this.shake);
            setDieImages(i, j);

            this.holdDice.setEnabled(false);
            this.holdDice.setAlpha(0.5f);
            this.rollDice.setEnabled(true);
            this.rollDice.setAlpha(1.0f);
            this.computerSum = 0;
            this.sumText.setText("0");
            gameChangeCounter = 0;
            return;

        }

        playsound(1);
        setDieImages(i, j);
        int sumNumbers = this.dice.sumNumbers(i, j);
        this.computerSum += sumNumbers;
        gameChangeCounter += sumNumbers;
        TextView textView2 = this.sumText;
        String sb3 = "" +
                this.computerSum;

        textView2.setText(sb3);
        if (gameChangeCounter >= this.holdScore || this.computerSum + this.computerScore >= this.levelScore) {
            gameChangeCounter = 0;
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    VsComputer.this.handleComputerHold(VsComputer.this.computerSum);
                }
            }, 1500);
        } else {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    VsComputer.this.roll();
                }
            }, 1500);
        }



    }

    public void handleComputerHold(int i){
        playsound(3);
        this.computerScore += i;
        this.computerSum = 0;
        TextView textView = this.sumText;
        String sb = ""+
                this.computerSum;
        textView.setText(sb);

        TextView textView1 = this.computerScoreText;
        String sb2 = "" +
                this.computerScore;
        textView1.setText(sb2);
        this.holdDice.setEnabled(false);
        this.rollDice.setEnabled(true);
        this.rollDice.setAlpha(1.0f);
        //checkWinner();

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

    private void updateUserWins() {
        int i =this.tinydb.getInt("UserWins");
        this.tinydb.putInt("UserWins", i+1);
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
    public void roll(){ computersRoll();}

    private void initializeDie() {
        switch (this.tinydb.getInt("SkinInt")) {
            case 0:
                this.dice1.setImageResource(R.drawable.dice1);
                this.dice2.setImageResource(R.drawable.dice1);

                this.dieImages.add(R.drawable.dice1);
                this.dieImages.add(R.drawable.dice2);
                this.dieImages.add(R.drawable.dice3);
                this.dieImages.add(R.drawable.dice4);
                this.dieImages.add(R.drawable.dice5);
                this.dieImages.add(R.drawable.dice6);
                return;

            case 1:
                this.dice1.setImageResource(R.drawable.dice1_silver);
                this.dice2.setImageResource(R.drawable.dice1_silver);

                this.dieImages.add(R.drawable.dice1_silver);
                this.dieImages.add(R.drawable.dice2_silver);
                this.dieImages.add(R.drawable.dice3_silver);
                this.dieImages.add(R.drawable.dice4_silver);
                this.dieImages.add(R.drawable.dice5_silver);
                this.dieImages.add(R.drawable.dice6_silver);
                return;
            case 2:
                this.dice1.setImageResource(R.drawable.dice1_gold);
                this.dice2.setImageResource(R.drawable.dice1_gold);

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

    private void setDieImages(int i, int j) {
        this.dice1.setImageResource(this.dieImages.get(i - 1));
        this.dice2.setImageResource(this.dieImages.get(j-1));
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
