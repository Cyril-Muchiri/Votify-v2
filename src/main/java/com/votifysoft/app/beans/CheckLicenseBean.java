package com.votifysoft.app.beans;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.Serializable;

@Singleton
public class CheckLicenseBean implements Serializable {

    @PostConstruct
    public void init(){
        System.out.println("Executed 3rd ... You must have a license");
    }
}
