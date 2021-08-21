package com.example.mycalcula;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout lay;
    ImageButton im1,im2;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("Calculator");
        }
        lay=(ConstraintLayout)findViewById(R.id.lay);
        im1=findViewById(R.id.imageButton);
        im2=findViewById(R.id.imageButton2);
        t1=findViewById(R.id.active1);
        t2=findViewById(R.id.active2);
        }

    public void calculata(View view){
        Toast.makeText(this, "Welcome to simple calculator", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,calculata.class);
        i.putExtra("night_mode",isNightModeActive(this));
        startActivity(i);
    }
    public void tablegen(View view){
        Toast.makeText(this, "Welcome to Table Generator", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,Tablegene.class);
        i.putExtra("night_mode",isNightModeActive(this));
        startActivity(i);
    }
    public void nightmode(View view){
        ImageButton flag=findViewById(R.id.imageButton3);
        if(!isNightModeActive(this)){
            flag.setImageResource(R.drawable.day);
            flag.setBackgroundColor(Color.parseColor("#000000"));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            lay.setBackgroundColor(Color.parseColor("#000000"));
            im1.setBackgroundColor(Color.parseColor("#000000"));
            im2.setBackgroundColor(Color.parseColor("#000000"));
            t1.setTextColor(Color.parseColor("#000000"));
            t2.setTextColor(Color.parseColor("#000000"));
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            flag.setImageResource(R.drawable.night);
            flag.setBackgroundColor(Color.parseColor("#FFFFFF"));
            lay.setBackgroundColor(Color.parseColor("#FFFFFF"));
            im1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            im2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            t1.setTextColor(Color.parseColor("#FFFFFF"));
            t2.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
    public static boolean isNightModeActive(Context context) {
        int defaultNightMode = AppCompatDelegate.getDefaultNightMode();
        if (defaultNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            return true;
        }
        if (defaultNightMode == AppCompatDelegate.MODE_NIGHT_NO) {
            return false;
        }

        int currentNightMode = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                return false;
            case Configuration.UI_MODE_NIGHT_YES:
                return true;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                return false;
        }
        return false;
    }
}
