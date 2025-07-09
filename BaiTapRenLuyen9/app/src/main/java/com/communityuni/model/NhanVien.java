package com.communityuni.model;

import java.io.Serializable;

/**
 * Created by cafe on 25/12/2016.
 */

public class NhanVien implements Serializable {
    private String ma;
    private String ten;
    private boolean nu;
    private boolean xoa;

    public NhanVien() {
    }

    public NhanVien(String ma, String ten, boolean nu, boolean xoa) {
        this.ma = ma;
        this.ten = ten;
        this.nu = nu;
        this.xoa = xoa;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isNu() {
        return nu;
    }

    public void setNu(boolean nu) {
        this.nu = nu;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
