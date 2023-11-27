package com.votifysoft.app.beans;

import com.votifysoft.model.entity.Topics;

public interface TopicBeanI extends GenericBeanI<Topics> {

    boolean registerTopic(Topics topic);
    

}
