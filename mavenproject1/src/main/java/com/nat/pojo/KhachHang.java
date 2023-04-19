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
public class KhachHang {
    private int makhachhang;
    private String hokhachhang;
    private String tenkhachhang;
    private Date ngaysinh;
    private String sodienthoai;

    public KhachHang() {
    }

    public KhachHang(int makhachhang, String hokhachhang, String tenkhachhang, Date ngaysinh, String sodienthoai) {
        this.makhachhang = makhachhang;
        this.hokhachhang = hokhachhang;
        this.tenkhachhang = tenkhachhang;
        this.ngaysinh = ngaysinh;
        this.sodienthoai = sodienthoai;
    }

    public int getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(int makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getHokhachhang() {
        return hokhachhang;
    }

    public void setHokhachhang(String hokhachhang) {
        this.hokhachhang = hokhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }
    
    
    
}
