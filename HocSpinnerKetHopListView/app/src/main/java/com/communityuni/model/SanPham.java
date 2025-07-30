package com.communityuni.model;

/**
 * Created by cafe on 25/12/2016.
 */

public class SanPham {
    private String ma;
    private String ten;
    private DanhMuc danhMuc;

    public SanPham(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
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

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    @Override
    public String toString() {
        return this.ma+"-"+this.ten;
    }
}
