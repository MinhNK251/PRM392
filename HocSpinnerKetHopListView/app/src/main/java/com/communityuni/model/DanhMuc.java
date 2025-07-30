package com.communityuni.model;

import java.util.ArrayList;

/**
 * Created by cafe on 25/12/2016.
 */

public class DanhMuc {
    private String ma;
    private String ten;
    private ArrayList<SanPham>sanPhams=new ArrayList<>();

    public DanhMuc(String ma, String ten) {
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

    public ArrayList<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

    @Override
    public String toString() {
        return this.ma+"-"+this.ten;
    }
}
