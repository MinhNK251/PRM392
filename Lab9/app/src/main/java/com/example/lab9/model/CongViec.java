package com.example.lab9.model;

public class CongViec {
    private int IdCv;
    private String TenCV;

    public CongViec(int idCv, String tenCV) {
        IdCv = idCv;
        TenCV = tenCV;
    }

    public int getIdCv() {
        return IdCv;
    }

    public void setIdCv(int idCv) {
        IdCv = idCv;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }
}
