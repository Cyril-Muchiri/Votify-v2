package com.votifysoft.app.beans;

import javax.ejb.Stateless;

import com.votifysoft.model.entity.Polls;

@Stateless
public class PollBean extends GenericBean<Polls> implements PollBeanI {

    @Override
    public boolean registerTopic(Polls pollTopic) {
        getDao().addOrUpdate(pollTopic);
       
        return false;
    }


    
}
