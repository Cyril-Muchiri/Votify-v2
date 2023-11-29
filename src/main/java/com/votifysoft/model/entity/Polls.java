package com.votifysoft.model.entity;

import java.util.Date;

import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;

@DbTable(name = "polls")
public class Polls extends BaseEntity {

    // @DbTableColumn(name = "poll_id", definition = "int")
    // private int poll_id;

    @DbTableColumn(name = "topic",definition = "VARCHAR(200)")
    private String topicName;

    @DbTableColumn(name = "created_at")
    private Date createdAt;

    @DbTableColumn(name = "deadline")
    private Date deadline;

    public Polls(){};

    public Polls(String topicName,Date createdAt,Date deadline){
        // this.poll_id=poll_id;
        this.topicName=topicName;
        this.createdAt=createdAt;
        this.deadline=deadline;
    }

    // public int getPoll_id() {
    //     return poll_id;
    // }

    // public void setPoll_id(int poll_id) {
    //     this.poll_id = poll_id;
    // }

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

}
