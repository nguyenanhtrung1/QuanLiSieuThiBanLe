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
    private String nhanviennhap;
    private Double giatien;
    private Date ngayban;

    public HoaDon() {
    }

    public HoaDon(String nhanviennhap, Double giatien, Date ngayban) {
        this.nhanviennhap = nhanviennhap;
        this.giatien = giatien;
        this.ngayban = ngayban;
    }
    
    public HoaDon(int mahoadon, String nhanviennhap, Double giatien, Date ngayban) {
        this.mahoadon = mahoadon;
        this.nhanviennhap = nhanviennhap;
        this.giatien = giatien;
        this.ngayban = ngayban;
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getNhanviennhap() {
        return nhanviennhap;
    }

    public void setNhanviennhap(String nhanviennhap) {
        this.nhanviennhap = nhanviennhap;
    }

    public Double getGiatien() {
        return giatien;
    }

    public void setGiatien(Double giatien) {
        this.giatien = giatien;
    }

    public Date getNgayban() {
        return ngayban;
    }

    public void setNgayban(Date ngayban) {
        this.ngayban = ngayban;
    }

    
    
    
    
    
}
