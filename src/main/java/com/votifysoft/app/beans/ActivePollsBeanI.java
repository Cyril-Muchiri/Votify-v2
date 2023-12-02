package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;

import com.votifysoft.model.entity.Answers;
import com.votifysoft.model.entity.Polls;

public interface ActivePollsBeanI {

    List<Polls> fetchAllPolls() throws SQLException;

    public List<Answers> fetchAllPollChoices() throws SQLException;
}
