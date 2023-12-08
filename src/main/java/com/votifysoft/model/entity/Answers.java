package com.votifysoft.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class Answers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int answer_id;

    @Column
    private int pollId;

    @Column
    private String choice;

    @Column
    private int votes;

    public Answers() {
    }

    public Answers(int answer_id, int poll_Id, String choice, int votes) {
        this.answer_id = answer_id;
        this.pollId = poll_Id;
        this.choice = choice;
        this.votes = votes;
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

    public void setChoice(String topicChoice) {
        this.choice = topicChoice;
    }

    public int getVotes() {
        return votes;
    }
    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }


    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return choice;
    }
}
