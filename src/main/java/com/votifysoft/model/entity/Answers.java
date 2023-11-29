package com.votifysoft.model.entity;

import java.io.Serializable;

import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;

@DbTable(name = "answers")
public class Answers implements Serializable{

 @DbTableColumn(name = "answer_id",definition = "INT PRIMARY KEY AUTO_INCREMENT")
    private int answer_id;

    @DbTableColumn(name = "poll_id" )
    private int poll_id;

    @DbTableColumn(name = "choice" )
    private String choice;

    @DbTableColumn(name ="votes",definition = "INT DEFAULT 0")
    private int votes;


    public Answers(){};

    public Answers(int answer_id,int poll_id,String choice,int votes){
        this.answer_id=answer_id;
        this.poll_id=poll_id;
        this.choice=choice;
        this.votes=votes;    
        
    }

    public int getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(int poll_id) {
        this.poll_id = poll_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

}