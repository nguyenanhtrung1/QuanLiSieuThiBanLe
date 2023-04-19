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
public class SanPham {
    private int masanpham;
    private String tensanpham;
    private int soluong;
    private float giatien;
    private Date ngaysanxuat;
    private int chinhanhID;

    public SanPham() {
    }
    
    public SanPham(String tensanpham, int soluong, float giatien, Date ngaysanxuat, int chinhanhID) {
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ngaysanxuat = ngaysanxuat;
        this.chinhanhID = chinhanhID;
    }
    public SanPham(String tensanpham,  float giatien, int soluong, Date ngaysanxuat, int chinhanhID) {
        this.tensanpham = tensanpham;
        this.giatien = giatien;
        this.soluong = soluong;
        this.ngaysanxuat = ngaysanxuat;
        this.chinhanhID = chinhanhID;
    }
    public SanPham(int masanpham, String tensanpham, int soluong, float giatien, Date ngaysanxuat, int chinhanhID) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ngaysanxuat = ngaysanxuat;
        this.chinhanhID = chinhanhID;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
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

    public float getGiatien() {
        return giatien;
    }

    public void setGiatien(float giatien) {
        this.giatien = giatien;
    }

    public Date getNgaysanxuat() {
        return ngaysanxuat;
    }

    public void setNgaysanxuat(Date ngaysanxuat) {
        this.ngaysanxuat = ngaysanxuat;
    }

    public int getChinhanhID() {
        return chinhanhID;
    }

    public void setChinhanhID(int chinhanhID) {
        this.chinhanhID = chinhanhID;
    }
    
    
    
}
