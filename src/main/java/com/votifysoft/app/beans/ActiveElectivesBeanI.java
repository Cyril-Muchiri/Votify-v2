package com.votifysoft.app.beans;

import java.sql.SQLException;
import java.util.List;

import com.votifysoft.model.entity.Electives;
import com.votifysoft.model.entity.Nominees;

public interface ActiveElectivesBeanI {

     List<Electives> fetchAllElectives() throws SQLException;

    public List<Nominees> fetchAllNominees() throws SQLException;
    
}
