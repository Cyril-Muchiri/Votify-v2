package com.votifysoft.app.view.helper;

import java.io.Serializable;

public class MenuLink implements Serializable {

    private String url;

    private String label;

    private MenuLinkStatus status;

    public MenuLink(String url, String label, MenuLinkStatus status){
        this.url = url;
        this.label = label;
        this.status = status;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MenuLinkStatus getStatus() {
        return status;
    }

    public void setStatus(MenuLinkStatus status) {
        this.status = status;
    }
}
