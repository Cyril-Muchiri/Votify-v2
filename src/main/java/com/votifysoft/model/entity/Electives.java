package com.votifysoft.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "electives")
public class Electives implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int elective_id;

    @Column
    private String electiveTitle;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    private User creator;

    @OneToMany(mappedBy = "elective", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Nominees> nominees;

    @Column(name = "participants")
    private String participants="0,";

   @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private Date createdAt;

    @Transient
    private Date deadline;

    public Electives() {
    };

    public Electives(String electiveTitle, List<Nominees> nominees, Date createdAt) {
        this.electiveTitle = electiveTitle;
        this.nominees = nominees;
        this.createdAt = createdAt;
    }
  

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getElective_id() {
        return elective_id;
    }

    public void setElective_id(int elective_id) {
        this.elective_id = elective_id;
    }

    public String getElectiveTitle() {
        return electiveTitle;
    }

    public void setElectiveTitle(String elective_title) {
        this.electiveTitle = elective_title;
    }

    public List<Nominees> getNominees() {
        return nominees;
    }

    public void setNominees(List<Nominees> nominees) {
        this.nominees = nominees;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}

