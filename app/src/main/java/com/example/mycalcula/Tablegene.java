package com.example.mycalcula;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Tablegene extends AppCompatActivity {
    EditText edit1;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean flag=false;
        super.onCreate(savedInstanceState);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("Table Generator");
        }
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            flag = extras.getBoolean("night_mode");
        }
        System.out.println(flag);
        if(flag){
            setContentView(R.layout.activity_tablegene_dark);
        }
        else {
            System.out.println("light theme now");
            setContentView(R.layout.activity_tablegene);
        }
        edit1=findViewById(R.id.editTextTextPersonName);
        LinearLayout lay1=(LinearLayout)findViewById(R.id.linlay1);
        button3=findViewById(R.id.button);
        button3.setOnClickListener(v -> {
            try{
            lay1.removeAllViews();
            int a=Integer.parseInt(edit1.getText()+"");
            for (int i=0; i<50 ; i++){
                TextView text=new TextView(this);
                text.setTextSize(25);
                text.setText(a+"  x  "+(i+1)+"  =  "+a*(i+1));
                lay1.addView(text);
            }}
            catch (Exception e){
                TextView text=new TextView(this);
                text.setTextSize(25);
                text.setText("Enter something");
            }
        });
    }
}