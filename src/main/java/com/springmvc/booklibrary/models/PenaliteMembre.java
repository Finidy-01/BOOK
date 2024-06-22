package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.annotations.Mapping;

@Mapping(table_name = "v_penalite", id_preffix = "", sequence_name = "")
public class PenaliteMembre extends Penalite{
    private Boolean estPenalise;

    public PenaliteMembre() {
        super();
    }

    public Boolean getEstPenalise() {
        return estPenalise;
    }

    public void setEstPenalise(Boolean estPenalise) {
        this.estPenalise = estPenalise;
    }
}
