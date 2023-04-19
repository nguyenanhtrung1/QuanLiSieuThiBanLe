/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.pojo;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
    private int mataikhoan;
    private String tendangnhap;
    private String matkhau;
    private String taikhoan_role;
    
    public TaiKhoan() {
    }
    public TaiKhoan( String tendangnhap, String matkhau, String taikhoan_role) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.taikhoan_role = taikhoan_role;
    }
    public TaiKhoan( String tendangnhap, String matkhau) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
    }
    public TaiKhoan(int mataikhoan, String tendangnhap, String matkhau, String taikhoan_role) {
        this.mataikhoan = mataikhoan;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.taikhoan_role = taikhoan_role;
    }
    public TaiKhoan(String tendangnhap, String matkhau, String taikhoan_role,int mataikhoan) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.taikhoan_role = taikhoan_role;
        this.mataikhoan = mataikhoan;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTaikhoan_role() {
        return taikhoan_role;
    }

    public void setTaikhoan_role(String taikhoan_role) {
        this.taikhoan_role = taikhoan_role;
    }

    
}
