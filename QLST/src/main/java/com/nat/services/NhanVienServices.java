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
    public List<NhanVien> getNhanVien(int kw) throws SQLException {
        List<NhanVien> ResultNV = new ArrayList<>();
        
        try ( Connection conn = jdbcUtils.getConn()) {
//            Statement stm = conn.createStatement();
//           
//            ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
//                 while(rs.next()){
//                 
//                 NhanVien nv = new NhanVien(rs.getInt("manhanvien"),rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"),rs.getString("phonenumber"),rs.getInt("chinhanhID"));
//                 ResultNV.add(nv);
//              }
            String query = "SELECT * FROM nhanvien WHERE nhanvien.chinhanhID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(kw));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getInt("manhanvien"),rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"),rs.getString("phonenumber"),rs.getInt("chinhanhID"));
                 ResultNV.add(nv);
            }
        }
        
        return ResultNV;
    }
    public void addNhanVien(NhanVien nv) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO nhanvien(lastname,firstname,age,phonenumber,chinhanhID) VALUES(?,?,?,?,?)");
            
            stm.setString(1, nv.getLastname()); 
            stm.setString(2,nv.getFirstname());
            stm.setInt(3, nv.getAge());
            stm.setString(4, nv.getPhonenumber());
            stm.setInt(5, nv.getChinhanhID());
            stm.executeUpdate();
            
//            String sql = "UPDATE chinhanh SET ? = (SELECT COUNT(?) FROM nhanvien WHERE ? = ?)";
//            PreparedStatement stm2 = conn.prepareStatement(sql);
//            stm2.setInt(1, cn.getSoluongnhanvien());
//            stm2.setInt(2,nv.getManhanvien());
//            stm2.setInt(3, nv.getManhanvien());
//            stm2.setInt(4, cn.getMachinhanh());
//            stm2.executeUpdate();
        }
    }
    public void delNhanVien(int MaNhanVien) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
          Statement stm = conn.createStatement();       
          String sql = "DELETE FROM nhanvien WHERE manhanvien =" + MaNhanVien;
          stm.executeUpdate(sql);
       }
   }
    public void updateNhanVien(NhanVien nv) throws SQLException{
       try(Connection conn = jdbcUtils.getConn()){
          String sql = "UPDATE nhanvien SET lastname = ?,age = ? ,firstname = ? , phonenumber = ? WHERE manhanvien = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getLastname());
            stm.setInt(2, nv.getAge());
            stm.setString(3,nv.getFirstname());
            stm.setString(4,nv.getPhonenumber());
            stm.setInt(5, nv.getManhanvien());
            stm.executeUpdate();
            
       }
   }
}
