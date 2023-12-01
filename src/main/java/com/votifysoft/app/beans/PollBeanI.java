package com.votifysoft.app.beans;

import com.votifysoft.model.entity.Polls;

public interface PollBeanI extends GenericBeanI<Polls> {

    boolean registerTopic(Polls pollTopic);

    

}
