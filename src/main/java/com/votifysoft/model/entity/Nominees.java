package com.votifysoft.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nominee")
public class Nominees {

     @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int nominee_id;

    @ManyToOne
    @JoinColumn(name = "elective_id", referencedColumnName = "elective_id")
    private Electives elective;

    @Column
    private String noineeName;

    @Column
    private String nomineePhoto;

    @Column
    private int votes;

    public Nominees(){
        
    }

    public Nominees(int nominee_id, Electives elective, String noineeName, String nomineePhoto, int votes) {
        this.nominee_id = nominee_id;
        this.elective = elective;
        this.noineeName = noineeName;
        this.nomineePhoto = nomineePhoto;
        this.votes = votes;
    }

    public int getNominee_id() {
        return nominee_id;
    }

    public void setNominee_id(int nominee_id) {
        this.nominee_id = nominee_id;
    }

    public Electives getElective() {
        return elective;
    }

    public void setElective(Electives elective) {
        this.elective = elective;
    }

    public String getNoineeName() {
        return noineeName;
    }

    public void setNoineeName(String noineeName) {
        this.noineeName = noineeName;
    }

    public String getNomineePhoto() {
        return nomineePhoto;
    }

    public void setNomineePhoto(String nomineePhoto) {
        this.nomineePhoto = nomineePhoto;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Nominees [elective=" + elective + ", noineeName=" + noineeName + ", votes=" + votes + "]";
    }

    
}
