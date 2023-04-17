/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.services;

import com.nat.Utils.jdbcUtils;
import com.nat.pojo.KhachHang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangServices {
    public List<KhachHang> getKhachHang() throws SQLException {
        List<KhachHang> ResultKH = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String query = "SELECT * FROM khachhang ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
                 while(rs.next()){
                 KhachHang kh = new KhachHang(rs.getInt("makhachhang"),rs.getString("hokhachhang"),rs.getString("tenkhachhang"),rs.getDate("ngaysinh"),rs.getString("sodienthoai"));
                 ResultKH.add(kh);
              }
        }
        
        return ResultKH;
    }
}
