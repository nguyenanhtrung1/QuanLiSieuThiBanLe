/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.services;

import com.nat.Utils.jdbcUtils;
import com.nat.pojo.HoaDon;
import com.nat.pojo.KhachHang;
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
public class HoaDonServices {
    public List<HoaDon> getHoaDon() throws SQLException {
        List<HoaDon> ResultHD = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String query = "SELECT * FROM hoadon ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
                 while(rs.next()){
                 HoaDon hd = new HoaDon(rs.getInt("mahoadon"), rs.getString("nhanviennhap"), rs.getDouble("giatien"), rs.getDate("ngayban"));
                 ResultHD.add(hd);
              }
        }
        
        return ResultHD;
    }
    
    public void addHoaDon(HoaDon hd) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hoadon(nhanviennhap,giatien,ngayban) VALUES(?,?,?)");
            stm.setString(1, hd.getNhanviennhap());
            stm.setDouble(2, hd.getGiatien());
            stm.setDate(3, hd.getNgayban());
            stm.executeUpdate();
       }
    } 
    public List<KhachHang> getKhachHang(int kw) throws SQLException {
        List<KhachHang> ResultKH = new ArrayList<>();
        
        try ( Connection conn = jdbcUtils.getConn()) {

            String query = "SELECT * FROM khachhang WHERE makhachhang = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(kw));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getInt("makhachhang"), rs.getString("hokhachhang"), rs.getString("tenkhachhang"), rs.getDate("ngaysinh"), rs.getString("sodienthoai"));
                 ResultKH.add(kh);
            }
        }
        
        return ResultKH;
    }
    
}
