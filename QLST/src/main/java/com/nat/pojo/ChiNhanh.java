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
    

    public ChiNhanh() {

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

}
