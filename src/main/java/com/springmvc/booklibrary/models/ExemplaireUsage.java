package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.annotations.Mapping;

@Mapping(table_name = "v_usage", id_preffix = "", sequence_name = "")
public class ExemplaireUsage extends Exemplaire {
    private Long usage;

    public Long getUsage() {
        return usage;
    }

    public void setUsage(Long usage) {
        this.usage = usage;
    }
}
