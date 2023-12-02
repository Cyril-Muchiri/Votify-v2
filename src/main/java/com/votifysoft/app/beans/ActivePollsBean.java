package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.votifysoft.database.MySqlDb;
import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

@Stateless
@Remote
public class ActivePollsBean extends GenericBean<Polls> implements ActivePollsBeanI {

    @EJB
    MySqlDb db;

    public List<Polls> fetchAllPolls() throws SQLException {
        System.out.println("method being for topics executed!!");
        return db.select(new Polls());
    }

       public List<Answers> fetchAllPollChoices() throws SQLException {
        System.out.println("method being for answers executed!!");
        return db.select(new Answers());
    }

}
