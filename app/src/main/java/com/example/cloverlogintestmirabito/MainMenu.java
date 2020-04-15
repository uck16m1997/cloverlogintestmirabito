package com.example.cloverlogintestmirabito;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent=getIntent();
        String log=intent.getStringExtra("log");
        TextView textView9=(TextView) findViewById(R.id.textView9);
        textView9.setText(log);


        final Button button8 = (Button) findViewById(R.id.button8);
        final Button button9 = (Button) findViewById(R.id.button9);
        final Button button11 = (Button) findViewById(R.id.button11);

        button8.setOnClickListener(new View.OnClickListener() { //barcode
            public void onClick(View v) {
                jumptobarcode();


            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
            }
        });

        button11.setOnClickListener(new View.OnClickListener() { //next
            public void onClick(View v) {
                //TextView t=(TextView) findViewById(R.id.editText4);
                String barcode =  "100";
                Intent intent3 =new Intent(getApplicationContext(), Main2Activity.class);
                intent3.putExtra("barcode",barcode);
                intent3.putExtra("points","10");
                startActivity(intent3);
                //jumptomain2();
            }
        });
    }

    public void jumptobarcode(){
        Intent intent =new Intent(this, BarcodeScannerTestActivity.class);
        startActivity(intent);

    }
    public void jumptomain2(){
        TextView t=(TextView) findViewById(R.id.editText4);
        String barcode =  t.getText().toString();

        if(barcode.compareTo("62993748264949534")==0)
        {
            Intent intent3 =new Intent(getApplicationContext(), Main2Activity.class);
            intent3.putExtra("barcode",barcode);
            intent3.putExtra("points","10");
            startActivity(intent3);
        }
        else if(barcode.compareTo("62993748264949535")==0)
        {
            Intent intent3 =new Intent(getApplicationContext(), Main2Activity.class);
            intent3.putExtra("barcode",barcode);
            intent3.putExtra("points","5");
            startActivity(intent3);
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Invalid Card");
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });



            AlertDialog alert11 = builder1.create();
            alert11.show();

     }
    }
}
