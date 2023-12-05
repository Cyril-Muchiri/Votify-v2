package com.votifysoft.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;

@Entity
@DbTable(name = "polls")
public class Polls extends BaseEntity implements Serializable{

    @DbTableColumn(name = "poll_id", definition = " INT PRIMARY KEY AUTO_INCREMENT")
    private int poll_id;

    @DbTableColumn(name = "topic", definition = "VARCHAR(200)")
    private String topicName;

    @DbTableColumn(name ="creator_id",definition = " INT ")
    private int creator_id;

    @DbTableColumn(name = "created_at", definition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    // @DbTableColumn(name = "deadline", definition = "DATETIME")
    private Date deadline;

    public Polls() {
    };

    public Polls(String topicName,int creator_id, Date createdAt) {
       this.creator_id=creator_id;
        this.topicName = topicName;
        this.createdAt = createdAt;
        // this.deadline = deadline;
    }

    public int getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(int poll_id) {
        this.poll_id = poll_id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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

     public int getcreator_id() {
        return creator_id;
    }

    public void setcreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

}
