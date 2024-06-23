package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.annotations.Mapping;

@Mapping(table_name = "v_livre_plus_emprunte", id_preffix = "", sequence_name = "")
public class LivrePlusEmprunte extends Livre{
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
