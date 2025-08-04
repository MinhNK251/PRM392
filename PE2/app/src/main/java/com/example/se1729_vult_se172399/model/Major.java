package com.example.se1729_vult_se172399.model;

public class Major {
    private int idNganh;
    private String nameNganh;

    public Major() {}

    public Major(int idNganh, String nameNganh) {
        this.idNganh = idNganh;
        this.nameNganh = nameNganh;
    }

    public Major(String nameNganh) {
        this.nameNganh = nameNganh;
    }

    public int getIdNganh() { return idNganh; }
    public void setIdNganh(int idNganh) { this.idNganh = idNganh; }

    public String getNameNganh() { return nameNganh; }
    public void setNameNganh(String nameNganh) { this.nameNganh = nameNganh; }

    @Override
    public String toString() {
        return nameNganh; // For Spinner display
    }
} 