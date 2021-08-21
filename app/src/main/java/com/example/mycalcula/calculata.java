package com.example.mycalcula;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class calculata extends AppCompatActivity {
    TextView edit1,edit2;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bc,bd,bad,bsu,bmu,bdi,beq,brac,bpow,bdel;
    boolean equal=false,error=false;
    short bracket=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean flag=false;
        super.onCreate(savedInstanceState);
        ActionBar ac=getSupportActionBar();
        if(ac!=null){
            ac.setTitle("Simple Calculator");
        }
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            flag = extras.getBoolean("night_mode");
        }
        System.out.println(flag);
        if(flag){
            System.out.println("dark theme now");
            setContentView(R.layout.activity_calculata_dark);
        }
        else {
            System.out.println("light theme now");
            setContentView(R.layout.activity_calculata);
        }
    }
    public void button0(View view){
        numbersetter("0");
    }
    public void button1(View view){
        numbersetter("1");
    }
    public void button2(View view){
        numbersetter("2");
    }
    public void button3(View view){
        numbersetter("3");
    }
    public  void button4(View view){
        numbersetter("4");
    }
    public void button5(View view){
        numbersetter("5");
    }
    public void button6(View view){
        numbersetter("6");
    }
    public void button7(View view){
        numbersetter("7");
    }
    public void button8(View view){
        numbersetter("8");
    }
    public void button9(View view){
        numbersetter("9");
    }
    public void buttond(View view){
        numbersetter("0.");
    }
    public void buttonadd(View view){
        symbolsetter("+");
    }
    public void buttonsub(View view){
        symbolsetter("-");
    }
    public void buttonmul(View view){
        symbolsetter("*");
    }
    public void buttondiv(View view){
        symbolsetter("/");
    }
    public void buttonbrac(View view){
        edit1=findViewById(R.id.edit1);
        String s=edit1.getText()+"";
        if(s.length()>0) {
            char c = s.charAt(s.length() - 1);
            if (bracket > 0) {
                if (Character.isDigit(c) || c==')') {
                    edit1.setText(edit1.getText() + ")");
                    bracket--;
                } else {
                    edit1.setText(edit1.getText() + "(");
                    bracket++;
                }
            } else {
                edit1.setText(edit1.getText() + "(");
                bracket++;
            }
        }
        else{
            edit1.setText(edit1.getText()+"(");
            bracket++;
        }
    }
    public void buttonpow(View view){
        symbolsetter("^");
    }
    public void buttondel(View view){
        edit1=findViewById(R.id.edit1);
        if(equal){
            equal=false;
        }
        String s=(String)edit1.getText();
        if(s!=null) {
            s = s.substring(0, s.length() - 1);
            edit1.setText(s);
        }
    }
    public void buttonc(View view){
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        edit1.setText(null);
        edit2.setText(null);
        equal=false;
        error=false;
        bracket=0;
    }
    public void buttoneq(View view){
        int k=0,index=0;
        String str=new String("");
        double[] a=new double[52];
        if(edit1.getText()!=null){
            str=edit1.getText()+"=";
            String st1=new String("");
            try {
                int l=str.length();
                for (int i = 0; i < l; i++) {
                    if (!Character.isDigit(str.charAt(i))  && str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '.' && !(i==0 && (str.charAt(i)=='-' || str.charAt(i)=='+'))) {
                        String s = str.substring(k, i).replace(")","").replace("(","");
                        a[index] = Double.parseDouble(s);
                        index++;
                        k = i + 1;
                        st1 += (char) (index + 96);
                        if (str.charAt(i - 1) != ')') {
                            st1 += str.charAt(i);
                        }
                    }
                    if (str.charAt(i) == '(') {
                        st1 += str.charAt(i);
                    }
                    if (i > 0 && str.charAt(i - 1) == ')' && str.charAt(i) != ')') {
                        int o = i - 1;
                        while (str.charAt(o) == ')' && o > 0) {
                            st1 += str.charAt(o);
                            o--;
                        }
                        st1 += str.charAt(i);
                    }
                }
                st1=st1.replace("=" ,"");
                String str1 = Evaluator(st1);
                double ans = valueFinder(str1, a);
                edit2.setText(""+ans);
                equal=true;
            }
            catch (Exception e){
                System.out.println(e);
                edit2.setText("Math Error");
                error =true;
            }
        }
    }
    private void numbersetter(String s){
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        if(error || equal){
            edit1.setText(null);
            edit2.setText(null);
            if(equal){equal=false;}
            if(error){error=false;}
        }
        edit1.setText(edit1.getText()+s);
    }
    private void symbolsetter(String s){
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        if(equal || error){
            edit1.setText(edit2.getText());
            edit2.setText(null);
            if(equal){equal=false;}
            if(error){error=false;}
        }
        edit1.setText(edit1.getText()+s);
    }
    private static byte Precedence(char c){
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
    private static String Evaluator(String s){
        String str = "";
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c))
                str += c;
            else if (c == '(')
                stk.push(c);
            else if (c == ')') {
                while (!stk.isEmpty() && stk.peek() != '(')
                    str += stk.pop();

                stk.pop();
            } else {
                while (!stk.isEmpty() && Precedence(c) <= Precedence(stk.peek())) {

                    str += stk.pop();
                }
                stk.push(c);
            }

        }
        while (!stk.isEmpty()) {
            str += stk.pop();
        }
        return str;
    }
    private static double valueFinder(String s,double[] arr){
        Stack<Double> stk = new Stack<Double>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stk.push(arr[(int)(ch-97)]);
            } else {
                double a, b, val = 0;
                a = stk.pop();
                b=stk.pop();
                switch (ch) {
                    case '^':
                        val=Math.pow(b,a);
                        break;
                    case '*':
                        val = a * b;
                        break;
                    case '/':
                        val = b / a;
                        break;
                    case '+':
                        val = a + b;
                        break;
                    case '-':
                        val = b - a;
                        break;
                }
                stk.push(val);
            }
        }
        return stk.pop();
    }
}