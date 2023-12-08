package com.votifysoft.app.beans;

import com.votifysoft.model.entity.Polls;

public interface PollBeanI extends GenericBeanI<Polls> {

    Integer registerTopic(Polls pollTopic);
    public Polls getLatestPoll();

    

}
