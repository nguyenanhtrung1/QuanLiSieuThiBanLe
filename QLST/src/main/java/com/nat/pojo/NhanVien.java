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
    private byte activenhanvien;
    private int chinhanhID;
    
    public NhanVien() {
    }

    public NhanVien(String firstname, int age, String phonenumber,int chinhanhID) {
        
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.chinhanhID = chinhanhID;
    }

    public NhanVien(String firstname, String lastname, int age, String phonenumber, int chinhanhID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.chinhanhID = chinhanhID;
    }
    public NhanVien(String lastname,int age , String firstname, String phonenumber,byte activenhanvien , int manhanvien) {
        this.lastname = lastname;
        this.age = age;
        this.firstname = firstname;
        this.phonenumber = phonenumber;
        this.activenhanvien = activenhanvien;
        this.manhanvien = manhanvien;
    }
    public NhanVien(int manhanvien, String lastname, String firstname, int age, String phonenumber) {
        this.manhanvien = manhanvien;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
    }
    public NhanVien(int manhanvien, String lastname, String firstname, int age, String phonenumber, int chinhanhID) {
        this.manhanvien = manhanvien;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.chinhanhID = chinhanhID;
    }

    public NhanVien(String lastname, String firstname, int age, String phonenumber, byte activenhanvien, int chinhanhID) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.activenhanvien = activenhanvien;
        this.chinhanhID = chinhanhID;
    }

    public NhanVien(int manhanvien, String lastname, String firstname, int age, String phonenumber, byte activenhanvien, int chinhanhID) {
        this.manhanvien = manhanvien;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.phonenumber = phonenumber;
        this.activenhanvien = activenhanvien;
        this.chinhanhID = chinhanhID;
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

    public int getChinhanhID() {
        return chinhanhID;
    }

    public void setChinhanhID(int chinhanhID) {
        this.chinhanhID = chinhanhID;
    }

    public byte getActivenhanvien() {
        return activenhanvien;
    }

    public void setActivenhanvien(byte activenhanvien) {
        this.activenhanvien = activenhanvien;
    }
    
    @Override
    public String toString() {
        return this.firstname + " " +this.lastname;
    }

}
