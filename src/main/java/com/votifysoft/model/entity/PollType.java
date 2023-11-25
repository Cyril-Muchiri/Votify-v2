package com.votifysoft.model.entity;

public enum PollType {

    TOPIC("Topic"),
    PARTICIPANTS("Electives"),
    ENTERTAINMENT("Entertainment");


    private String name;


      PollType(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


  
    
}
