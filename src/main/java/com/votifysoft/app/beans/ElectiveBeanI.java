package com.votifysoft.app.beans;

import com.votifysoft.model.entity.Electives;

public interface ElectiveBeanI extends GenericBeanI<Electives> {
    Integer registerElective(Electives elective);

    public Electives getLatestElective();
}
