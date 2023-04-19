/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.pojo;

/**
 *
 * @author Admin
 */
public class ChiNhanh {   

    private int machinhanh;
    private String tenchinhanh;
    private String diachi;
    private int soluongnhanvien;

    public ChiNhanh() {

    }

    public ChiNhanh(String tenchinhanh, String diachi, int machinhanh) {
        this.tenchinhanh = tenchinhanh;
        this.diachi = diachi;
        this.machinhanh = machinhanh;
    }

    public ChiNhanh(int machinhanh, int soluongnhanvien) {
        this.machinhanh = machinhanh;
        this.soluongnhanvien = soluongnhanvien;
    }
    
    
    public ChiNhanh(String tenchinhanh, String diachi) {
        this.tenchinhanh = tenchinhanh;
        this.diachi = diachi;
    }

    public ChiNhanh(int machinhanh, String tenchinhanh, String diachi) {
        this.machinhanh = machinhanh;
        this.tenchinhanh = tenchinhanh;
        this.diachi = diachi;
    }

    public ChiNhanh(int machinhanh, String tenchinhanh, String diachi, int soluongnhanvien) {
        this.machinhanh = machinhanh;
        this.tenchinhanh = tenchinhanh;
        this.diachi = diachi;
        this.soluongnhanvien = soluongnhanvien;
    }
    public ChiNhanh(String tenchinhanh, String diachi, int soluongnhanvien,int machinhanh) {
        this.tenchinhanh = tenchinhanh;
        this.diachi = diachi;
        this.soluongnhanvien = soluongnhanvien;
        this.machinhanh = machinhanh;
    }
    public int getMachinhanh() {
        return machinhanh;
    }

    public void setMachinhanh(int machinhanh) {
        this.machinhanh = machinhanh;
    }

    public String getTenchinhanh() {
        return tenchinhanh;
    }

    public void setTenchinhanh(String tenchinhanh) {
        this.tenchinhanh = tenchinhanh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSoluongnhanvien() {
        return soluongnhanvien;
    }

    public void setSoluongnhanvien(int soluongnhanvien) {
        this.soluongnhanvien = soluongnhanvien;
    }
    
    @Override
    public String toString() {
        return this.tenchinhanh;
    }
    
}
