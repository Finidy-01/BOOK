package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.dao.ModelDao;

public class Collection extends ModelDao {
    private String id;
    private String designation;

    public Collection() { super("collection", "COL", "collection_seq"); }

    public Collection(String designation) {
        super("collection", "COL", "collection_seq");
        this.setDesignation(designation);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
