package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;

import com.votifysoft.model.entity.Polls;
import com.votifysoft.model.entity.User;

public interface ActivePollsBeanI {

    List<Polls> fetchAllPolls() throws SQLException;
}
