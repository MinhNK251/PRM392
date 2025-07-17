package com.communityuni.model;

/**
 * Created by cafe on 25/12/2016.
 */

public class SanPham {
    private int hinh;
    private String ten;

    public SanPham(int hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
