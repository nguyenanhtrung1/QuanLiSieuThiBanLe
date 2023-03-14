/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.pojo;

/**
 *
 * @author Joe
 */
public class NhanVien {
    private int manhanvien;
    private String lastname;
    private String firstname;
    private int age;
    private String phonenumber;
    private int id_ChiNhanh;

    public NhanVien() {
    }
    public NhanVien(String firstname, int age, String phonenumber,int id_ChiNhanh) {
        
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.id_ChiNhanh = id_ChiNhanh;
    }
    public NhanVien(int manhanvien, String lastname, String firstname, int age, String phonenumber) {
        this.manhanvien = manhanvien;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
    }
    public NhanVien(int manhanvien, String lastname, String firstname, int age, String phonenumber, int id_ChiNhanh) {
        this.manhanvien = manhanvien;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.id_ChiNhanh = id_ChiNhanh;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getId_ChiNhanh() {
        return id_ChiNhanh;
    }

    public void setId_ChiNhanh(int id_ChiNhanh) {
        this.id_ChiNhanh = id_ChiNhanh;
    }

    
    
}
