/*package com.example.pigdice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, Welcome.class));
            }
        },2000);

    }
}

 */
package com.example.pigdice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView a,slogan;
    Animation topAnimation,bottomAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        a=findViewById(R.id.a);
        slogan=findViewById(R.id.tagLine);

        a.setAnimation(topAnimation);
        slogan.setAnimation(bottomAnimation);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(MainActivity.this, Welcome.class));
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        View view  = LayoutInflater.from(MainActivity.this).inflate(R.layout.giveup_alert, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

        title.setText("Do you want to exit");

        imageButton.setImageResource(R.drawable.sure);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
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

