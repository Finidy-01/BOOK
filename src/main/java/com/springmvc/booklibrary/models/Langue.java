package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.dao.ModelDao;

public class Langue extends ModelDao {
    private String code;
    private String designation;

    public Langue() { super("langue", null, null); }

    public Langue(String code, String designation) {
        super("langue", null, null);
        this.setCode(code);
        this.setDesignation(designation);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
