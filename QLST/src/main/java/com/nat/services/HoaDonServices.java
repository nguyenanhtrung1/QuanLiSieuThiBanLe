/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.services;

import com.nat.Utils.jdbcUtils;
import com.nat.pojo.HoaDon;
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
public class HoaDonServices {
    public List<HoaDon> getHoaDon() throws SQLException {
        List<HoaDon> ResultHD = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String query = "SELECT * FROM hoadon ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
                 while(rs.next()){
                 HoaDon hd = new HoaDon(rs.getInt("mahoadon"),rs.getString("tensanpham"),rs.getInt("soluong"),rs.getFloat("gianhap"),rs.getString("tennhanvien"),rs.getDate("ngaynhap"),rs.getInt("makhachhang"));
                 ResultHD.add(hd);
              }
        }
        
        return ResultHD;
    }
}
