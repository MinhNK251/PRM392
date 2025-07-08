package com.communityuni.model;

import java.io.Serializable;

/**
 * Created by cafe on 25/12/2016.
 */

public class SanPham implements Serializable{
    private String ten;
    private int gia;
    private int hinh;
    private int soLuong;

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

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham() {
    }

    public SanPham(String ten, int gia, int hinh, int soLuong) {
        this.ten = ten;
        this.gia = gia;
        this.hinh = hinh;
        this.soLuong = soLuong;
    }
}
