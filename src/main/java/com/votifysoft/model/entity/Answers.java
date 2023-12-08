package com.votifysoft.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class Answers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int answer_id;

    @ManyToOne
    @JoinColumn(name = "poll_id", referencedColumnName = "poll_id")
    private Polls poll;

    @Column
    private String choice;

    @Column
    private int votes;

    public Answers() {
    }

    public Answers(int answer_id, Polls poll, String choice, int votes) {
        this.answer_id = answer_id;
        this.poll = poll;
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

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return choice;
    }

    public Polls getPoll() {
        return poll;
    }

    public void setPoll(Polls poll) {
        this.poll = poll;
    }
}
