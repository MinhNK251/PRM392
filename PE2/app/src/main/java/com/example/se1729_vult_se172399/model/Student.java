package com.example.se1729_vult_se172399.model;

public class Student {
    private int id;
    private String name;
    private String date;
    private String gender;
    private String address;
    private int idNganh;
    private String phone;
    private String imagePath;

    public Student() {}

    public Student(int id, String name, String date, String gender, String address, int idNganh, String phone) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.idNganh = idNganh;
        this.phone = phone;
    }

    public Student(String name, String date, String gender, String address, int idNganh, String phone) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.idNganh = idNganh;
        this.phone = phone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getIdNganh() { return idNganh; }
    public void setIdNganh(int idNganh) { this.idNganh = idNganh; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    @Override
    public String toString() {
        return name; // For ListView display
    }
} 