package com.example.cloverlogintestmirabito;

public class card {


    String card_no;
    String name;
    float point;



    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }


    public card(  String card_no,String name, float point){
        this.card_no=card_no;
        this.name=name;
        this.point=point;
    }
    public card(){}

}
