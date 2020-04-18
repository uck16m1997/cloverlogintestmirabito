package com.example.cloverlogintestmirabito;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

public class offer {



    String name;
    String desc;
    String buttonText;
    String prompt;
    int type;//1,2,3
    int condition;//do this to get started
    int unit;
    int reward;                                                             //switch to small int
    double Mult; //limit or mult
    int Limit;

    public offer(String name,String desc,String buttonText,String prompt,int type,int condition,
                 int unit, int reward,double Mult,int Limit)
    {
        this.name=name;
        this.desc=desc;
        this.buttonText=buttonText;
        this.prompt=prompt;
        this.type=type;//1,2,3
        this.condition=condition;//do this to get started
        this.unit=unit;
        this.reward=reward;
        this.Mult=Mult; //limit or mult
        this.Limit=Limit;
    }
    public offer(){


    }
    public offer(offer of){
        this.name=of.name;
        this.desc=of.desc;
        this.buttonText=of.buttonText;
        this.prompt=of.prompt;
        this.type=of.type;//1,2,3
        this.condition=of.condition;//do this to get started
        this.unit=of.unit;
        this.reward=of.reward;
        this.Mult=of.Mult; //limit or mult
        this.Limit=of.Limit;
    }
    public JSONObject dummyjson(){
        offer of =new offer(this);
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jo = new JSONObject();
        try {
            String jsonstring = mapper.writeValueAsString(of);
            jo = new JSONObject(jsonstring);
        }catch(Exception e )
        {

        }
        return jo;//fornow

    }

    public offer Json2Offer(JSONObject jo){
        offer of = new offer();
        ObjectMapper mapper = new ObjectMapper();
        try {
            of = mapper.readValue(jo.toString(), offer.class);
        }catch(Exception e )
        {

        }
        return of;
    }
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getPrompt() {
        return prompt;
    }

    public int getType() {
        return type;
    }

    public int getCondition() {
        return condition;
    }

    public int getUnit() {
        return unit;
    }

    public int getReward() {
        return reward;
    }

    public double getMult() {
        return Mult;
    }

    public int getLimit() {
        return Limit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setMult(double mult) {
        Mult = mult;
    }

    public void setLimit(int limit) {
        Limit = limit;
    }


}



