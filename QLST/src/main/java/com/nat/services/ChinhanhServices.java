/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.services;

import com.nat.Utils.jdbcUtils;
import com.nat.pojo.ChiNhanh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChinhanhServices {
    
    public List<ChiNhanh> getChiNhanh() throws SQLException {
        List<ChiNhanh> ResultCN = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chinhanh");
            
                 while(rs.next()){
                 ChiNhanh cn = new ChiNhanh(rs.getInt("machinhanh"),rs.getString("tenchinhanh"), rs.getString("diachi"));
                 ResultCN.add(cn);
              }
        }
        
        return ResultCN;
    }
    public void addChiNhanh(ChiNhanh cn) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO chinhanh(tenchinhanh,diachi) VALUES(?,?)");
            stm.setString(1, cn.getTenchinhanh());
            stm.setString(2, cn.getDiachi());
            stm.executeUpdate();
        }
    }
    public void delChiNhanh(int MaChiNhanh) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
          Statement stm = conn.createStatement();       
          String sql = "DELETE FROM chinhanh WHERE machinhanh =" + MaChiNhanh;
          stm.executeUpdate(sql);
       }
   }
   public void updateChiNhanh(int MaChiNhanh){
       
   }
}
