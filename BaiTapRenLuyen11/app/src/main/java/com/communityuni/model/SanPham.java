package com.communityuni.model;

import java.io.Serializable;

/**
 * Created by cafe on 28/12/2016.
 */

public class SanPham implements Serializable {
    private String ma;
    private String ten;
    private int gia;

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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public SanPham() {
    }

    public SanPham(String ma, String ten, int gia) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
    }

    @Override
    public String toString() {
        return this.ten;
    }
}
