/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.services;

import com.nat.Utils.jdbcUtils;
import com.nat.pojo.TaiKhoan;
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
public class TaiKhoanServices {
    public List<TaiKhoan> getTaiKhoanAdmin() throws SQLException {
        List<TaiKhoan> ResultCN = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM taikhoan");
            
                 while(rs.next()){
                 TaiKhoan tk = new TaiKhoan(rs.getInt("mataikhoan"),rs.getString("tendangnhap"), rs.getString("matkhau"),rs.getString("taikhoan_role"));
                 ResultCN.add(tk);
              }
        }
        
        return ResultCN;
    }
    public void addTaiKhoan(TaiKhoan tk) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO taikhoan ( tendangnhap, matkhau,taikhoan_role) VALUES ( ?, ?,?)");
            stm.setString(1, tk.getTendangnhap());
            stm.setString(2, tk.getMatkhau());
            stm.setString(3, tk.getTaikhoan_role());
            stm.executeUpdate();
       }
    }
    public boolean checkLogin(TaiKhoan tk) throws SQLException {
        int count;
        try(Connection conn = jdbcUtils.getConn()){
            String sql = "SELECT COUNT(*) FROM taikhoan WHERE tendangnhap = ? AND matkhau = ?  AND taikhoan_role = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, tk.getTendangnhap());
            stm.setString(2, tk.getMatkhau());
            stm.setString(3, tk.getTaikhoan_role());
            ResultSet rs = stm.executeQuery();
            rs.next();
            count = rs.getInt(1);
            
       }
        return count > 0;
    }
    public void delTaiKhoan(int MaTaiKhoan) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
          Statement stm = conn.createStatement();       
          String sql = "DELETE FROM taikhoan WHERE mataikhoan =" + MaTaiKhoan;
          stm.executeUpdate(sql);
       }
   }
}
