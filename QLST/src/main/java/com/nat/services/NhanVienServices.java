/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.services;

import com.nat.Utils.jdbcUtils;
import com.nat.pojo.ChiNhanh;
import com.nat.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienServices {
    public List<NhanVien> getNhanVien() throws SQLException {
        List<NhanVien> ResultNV = new ArrayList<>();

        try ( Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
           
            ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
                 while(rs.next()){
                 
                 NhanVien nv = new NhanVien(rs.getInt("manhanvien"),rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"),rs.getString("phonenumber"));
                 ResultNV.add(nv);
              }
        }
        
        return ResultNV;
    }
    public void addNhanVien(NhanVien nv) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO chinhanh(manhanvien,lastname,firstname,age,phone,id_ChiNhanh) VALUES(?,?,?,?,?,?)");
            stm.setInt(1, nv.getManhanvien());
            stm.setString(2, nv.getLastname());
            stm.setString(3,nv.getLastname());
            stm.setInt(4, nv.getAge());
            stm.setString(5, nv.getPhonenumber());
            stm.setInt(6, nv.getId_ChiNhanh());
            stm.executeUpdate();
        }
    }
}
