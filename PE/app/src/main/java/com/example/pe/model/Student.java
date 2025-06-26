package com.example.pe.model;

public class Student {
    private int id;
    private String name;
    private String date;
    private String gender;
    private String address;
    private int majorId;
    private String phone;

    public Student(int id, String name, String date, String gender, String address, int majorId, String phone) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.majorId = majorId;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
