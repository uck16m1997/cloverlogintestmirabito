package com.example.cloverlogintestmirabito;

import android.app.AlertDialog;
import android.content.Intent;

public class MockApi {

    public boolean card_check(String barcode){
        if(barcode.compareTo("62993748264949534")==0)
        {
            return true;

        }
        else if(barcode.compareTo("62993748264949535")==0)
        {
           return true;
        }else{
           return false;

        }
    }
    public offers getoffers(String barcode){
        offers ofs =new offers();
        offer of=new offer( "Egemen", "test pizza","Egemen","Test Prompt",1,5,5, 1,0.25,20);
        offer of2=new offer( "Pizza", "test pizza","1 Pizza for 1 Point","Test Prompt",2,2,1, 5,0.25,4);
        offer of3=new offer( "Burger", "test Burger","1 Burger for 1 Point","Test Prompt",2,2,1, 5,0.25,4);
        offer of4=new offer( "Cola", "test Cola","1 Cola for 1 Point","Test Prompt",2,2,1, 5,0.25,4);
        ofs.insertoffer(of);
        ofs.insertoffer(of2);
        ofs.insertoffer(of3);
        ofs.insertoffer(of4);
        return ofs;


    }

    public card getcard(String barcode)
    {
        card c=new card();
        c.card_no=barcode;

        if(barcode.compareTo("62993748264949534")==0)
        {
          c.name="Egemen";
          c.point=10;
        }
        else if(barcode.compareTo("62993748264949535")==0) {
            c.name = "Umut";
            c.point = 150;
        }
        return c;
    }
}

