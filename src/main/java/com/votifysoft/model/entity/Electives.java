package com.votifysoft.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "electives")
public class Electives implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int elective_id;

    @Column
    private String elective_title;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    private User creator;

    @OneToMany(mappedBy = "elective", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nominees> nominees;

    @Column
    private Date createdAt;

    @Transient
    private Date deadline;

    public Electives() {
    };

    public Electives(String elective_title, List<Nominees> nominees, Date createdAt) {
        this.elective_title = elective_title;
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

    public List<Nominees> getAnswers() {
        return nominees;
    }

    public void setAnswers(List<Answers> answers) {
        this.nominees = nominees;
    }
}

