/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.pojo;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private int mahoadon;
    private String tensanpham;
    private int soluong;
    private float gianhap;
    private String tennhanvien;
    private Date ngaynhap;
    private int makhachhang;

    public HoaDon() {
    }

    public HoaDon(int mahoadon, String tensanpham, int soluong, float gianhap, String tennhanvien, Date ngaynhap, int makhachhang) {
        this.mahoadon = mahoadon;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.gianhap = gianhap;
        this.tennhanvien = tennhanvien;
        this.ngaynhap = ngaynhap;
        this.makhachhang = makhachhang;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getGianhap() {
        return gianhap;
    }

    public void setGianhap(float gianhap) {
        this.gianhap = gianhap;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public Date getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(Date ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public int getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }
    
   
    

    
    
}
