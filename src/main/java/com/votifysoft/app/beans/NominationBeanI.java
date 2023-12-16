package com.votifysoft.app.beans;

import java.util.List;

import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

public interface NominationBeanI  extends GenericBeanI<Nominees>{

    public boolean registerNominee(Electives elective, List<Nominees> nomineeList);

     public void registerVote(String elective,int nomineeId);
    
}
