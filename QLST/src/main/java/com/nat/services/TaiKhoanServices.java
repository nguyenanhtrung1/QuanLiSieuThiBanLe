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

/**
 *
 * @author Admin
 */
public class TaiKhoanServices {
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
}
