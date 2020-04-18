package com.example.cloverlogintestmirabito;

import java.util.ArrayList;

public class offers {

    ArrayList<offer> Offers = new ArrayList<offer>();

    public offers(){}

    public offers dummy_offers(){
        offer of=new offer( "Egemen", "test pizza","Egemen","Test Prompt",1,5,5, 1,0.25,20);
        offer of2=new offer( "Pizza", "test pizza","1 Pizza for 1 Point","Test Prompt",2,2,1, 5,0.25,4);
        offer of3=new offer( "Burger", "test Burger","1 Burger for 1 Point","Test Prompt",2,2,1, 5,0.25,4);
        offer of4=new offer( "Cola", "test Cola","1 Cola for 1 Point","Test Prompt",2,2,1, 5,0.25,4);
        this.insertoffer(of);
        this.insertoffer(of2);
        this.insertoffer(of3);
        this.insertoffer(of4);

        return this;
    }
    public offer getoffer(int index){

        return Offers.get(index);
    }
    public void insertoffer(offer Offer){
        Offers.add(Offer);
    }
    public void removeoffer(int index){
        Offers.remove(index);
    }


}
