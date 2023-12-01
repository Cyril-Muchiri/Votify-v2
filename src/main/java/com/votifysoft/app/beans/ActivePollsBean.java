package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.votifysoft.database.MySqlDb;
import com.votifysoft.model.entity.ActivePoll;
import com.votifysoft.model.entity.Polls;
import com.votifysoft.model.entity.User;

@Stateless
@Remote
public class ActivePollsBean  extends GenericBean<Polls> implements ActivePollsBeanI{


    @EJB
    MySqlDb db;
    // public Polls fetchPolls(Polls polls) throws SQLException {

    //     // List<Polls> pollz = db.select(polls);

    //     // for (Polls poll : pollz) {
    //     //     return db.select(new Polls());
    //     // }
    //     // return userResult;

    // }

    public List<Polls> fetchAllPolls() throws SQLException {
        System.out.println("method being executed!!");
        return db.select(new Polls());
    }
   
}
