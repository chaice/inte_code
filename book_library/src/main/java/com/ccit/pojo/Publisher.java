package com.ccit.pojo;


import java.io.Serializable;

public class Publisher implements Serializable {
    private static final long serialVersionUID = 776754142247076071L;
    private Integer id;
    private String pubname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname;
    }
}
