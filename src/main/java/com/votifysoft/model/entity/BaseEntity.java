package com.votifysoft.model.entity;

import java.io.Serializable;

import com.votifysoft.database.helper.DbTableColumn;
import com.votifysoft.database.helper.DbTableId;

public class BaseEntity implements Serializable {

    @DbTableId
    @DbTableColumn(name = "id", definition = "int")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
