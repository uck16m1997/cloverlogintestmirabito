package com.example.cloverlogintestmirabito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private Button button22;
    private TextView textView6;
    private TextView textView7;
    offers Offerlist=new offers().dummy_offers();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        String barcode=intent.getStringExtra("barcode");
        textView6=(TextView) findViewById(R.id.textView6);
        textView6.setText(barcode);

        String points=intent.getStringExtra("points");
        textView7=(TextView) findViewById(R.id.textView7);
        textView7.setText(points);

        Integer x=270;
        Integer y=380;


        for(int i=0;i<Offerlist.Offers.size();i++) {

            Button myButton = new Button(this);
            myButton.setText(Offerlist.getoffer(i).buttonText);
            myButton.setTranslationX(x);
            myButton.setTranslationY(y);
            y+=85;

            AbsoluteLayout al = (AbsoluteLayout) findViewById(R.id.abs);
            //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            al.addView(myButton);
        }
        Button myButton = new Button(this);
        myButton.setText("ENTER");
        myButton.setTranslationX(x);
        myButton.setTranslationY(y);
        y+=85;

        AbsoluteLayout al = (AbsoluteLayout) findViewById(R.id.abs);
        //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        al.addView(myButton);
        myButton = new Button(this);
        myButton.setText("EXIT");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openActivity1();
            }
        });
        myButton.setTranslationX(x);
        myButton.setTranslationY(y);
        y+=85;

        //AbsoluteLayout al = (AbsoluteLayout) findViewById(R.id.abs);
        //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        al.addView(myButton);
        /*
        Button buttonen = (Button) findViewById(R.id.button2);
        buttonen.setTranslationY(y);
        y+=85;
        al.addView(buttonen);

        Button ex = (Button) findViewById(R.id.button7);
        ex.setTranslationY(y);
        al.addView(ex);*/


    }
    public void openActivity1(){
        Intent intent1 =new Intent(this, MainActivity.class);
        startActivity(intent1);


    }
}
