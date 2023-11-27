package com.votifysoft.app.beans;

import javax.ejb.Stateless;

import com.votifysoft.model.entity.Topics;

@Stateless
public class TopicBean extends GenericBean<Topics> implements TopicBeanI {

    @Override
    public boolean registerTopic(Topics topic) {

        getDao().addOrUpdate(topic);
       
        return false;
    }
    
}
