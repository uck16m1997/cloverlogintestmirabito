package com.example.cloverlogintestmirabito;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class jasonactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jasonactivity);
        Button button2=(Button) findViewById(R.id.button2);
        TextView textView=(TextView) findViewById(R.id.textView);


        button2.setOnClickListener(new View.OnClickListener() { //barcode
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            try {
                                URL url = new URL("https://jsonplaceholder.typicode.com/todos");
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                String s = readStream(in);
                                //JSONObject jObject = new JSONObject(s);
                                JSONArray jArray = new JSONArray(s);
                                textView.setText(textView.getText()+"\n");
                                //listarray<> designilazer
                                textView.setMovementMethod(new ScrollingMovementMethod());
                                //textView.setVerticalScrollBarEnabled(true);
                                //classs

                                for (int i=0; i < jArray.length(); i++)
                                {
                                    try {
                                        JSONObject oneObject = jArray.getJSONObject(i);
                                        textView.setText(textView.getText()+"\n\n"+oneObject.toString());

                                        // Pulling items from the array
                                        //String oneObjectsItem = oneObject.getString("STRINGNAMEinTHEarray");
                                        //String oneObjectsItem2 = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
                                    } catch (JSONException e) {
                                        // Oops
                                    }
                                }
                                urlConnection.disconnect();
                            }
                            catch (Exception e ) {
                                e.printStackTrace();
                            }finally {
                                //jumptomain2();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();


            }
            private String readStream(InputStream is) {
                try {
                    ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    int i = is.read();
                    while(i != -1) {
                        bo.write(i);
                        i = is.read();
                    }
                    return bo.toString();
                } catch (IOException e) {
                    return "";
                }
            }
        });
    }

}



