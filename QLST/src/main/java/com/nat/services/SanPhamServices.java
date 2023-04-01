/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.services;

import com.nat.Utils.jdbcUtils;
import com.nat.pojo.SanPham;
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
public class SanPhamServices {
    public List<SanPham> getSanPham(int kw) throws SQLException {
        List<SanPham> ResultSP = new ArrayList<>();
        
        try ( Connection conn = jdbcUtils.getConn()) {

            String query = "SELECT * FROM sanpham WHERE sanpham.chinhanhID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(kw));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt("masanpham"),rs.getString("tensanpham"), rs.getInt("soluong"), rs.getFloat("giatien"),rs.getDate("ngaysanxuat"),rs.getInt("chinhanhID"));
                 ResultSP.add(sp);
            }
        }
        
        return ResultSP;
    }
    public List<SanPham> getSanPham() throws SQLException {
        List<SanPham> ResultSP = new ArrayList<>();
        
        try ( Connection conn = jdbcUtils.getConn()) {
            String query = "SELECT * FROM sanpham ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                SanPham sp = new SanPham(rs.getInt("masanpham"),rs.getString("tensanpham"), rs.getInt("soluong"), rs.getFloat("giatien"),rs.getDate("ngaysanxuat"),rs.getInt("chinhanhID"));
                ResultSP.add(sp);
            }
        }
        
        return ResultSP;
    }
    public void addSanPham(SanPham sp) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO sanpham(tensanpham,soluong,giatien,ngaysanxuat,chinhanhID) VALUES(?,?,?,?,?)");
            
            stm.setString(1, sp.getTensanpham()); 
            stm.setInt(2,sp.getSoluong());
            stm.setFloat(3, sp.getGiatien());
            stm.setDate(4, sp.getNgaysanxuat());
            stm.setInt(5, sp.getChinhanhID());
            stm.executeUpdate();
        }
    }
    public void delSanPham(int MaSanPham) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
          Statement stm = conn.createStatement();       
          String sql = "DELETE FROM sanpham WHERE masanpham =" + MaSanPham;
          stm.executeUpdate(sql);
       }
   }
    public void updateSanPham(SanPham sp) throws SQLException{
       try(Connection conn = jdbcUtils.getConn()){
          String sql = "UPDATE sanpham SET tensanpham = ? ,giatien = ?,soluong = ? , ngaysanxuat = ? WHERE masanpham = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, sp.getTensanpham());
            stm.setFloat(2,sp.getGiatien());
            stm.setInt(3, sp.getSoluong()); 
            stm.setDate(4,sp.getNgaysanxuat());
            stm.setInt(5, sp.getChinhanhID());
            stm.executeUpdate();
       }
   }
    
}
