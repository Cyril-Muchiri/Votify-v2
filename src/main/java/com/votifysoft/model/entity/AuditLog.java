package com.votifysoft.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "audit_logs")
public class AuditLog extends BaseEntity{

    @Column(name = "log_details", columnDefinition = "longtext")
    private String logDetails;

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }
}
