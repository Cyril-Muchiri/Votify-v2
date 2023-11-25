package com.votifysoft.model.entity;

import java.math.BigDecimal;

import com.votifysoft.app.view.helper.HtmlTable;
import com.votifysoft.app.view.helper.HtmlTableColHeader;
import com.votifysoft.database.helper.DbTable;
import com.votifysoft.database.helper.DbTableColumn;

@DbTable(name = "topics")
@HtmlTable(addUrl = "./customers?action=add")
// @HtmlForm(label = "Customer", url = "./customers")
public class Topics extends BaseEntity {

    @DbTableColumn(name = "name")
    @HtmlTableColHeader(header = "Topic Name")
    @HtmlFormField(label = "Topic Name", required = true)
    private String name;

    @DbTableColumn(name = "address")
    @HtmlTableColHeader(header = "Customer Address")
    @HtmlFormField(label = "Customer Address", required = true)
    private String address;

    @HtmlTableColHeader(header = "Account Balance")
    private BigDecimal accountBalance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
    
}
