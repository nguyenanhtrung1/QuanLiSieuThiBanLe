/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.*;
import com.nat.Utils.jdbcUtils;
import com.nat.pojo.HoaDon;
import com.nat.pojo.TaiKhoan;
import com.nat.services.HoaDonServices;
import com.nat.services.TaiKhoanServices;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Admin
 */
public class HoaDonTester {
    private static HoaDonServices hdS;
    @Test
    public void testLuuHoaDon() throws Exception {
            hdS = new HoaDonServices();
            final int expectedHoaDonID = 1;
            final String newNVNhap = "newTKNV";
            final Double newGiaTien = 100000.0;
            final Date dateNhap = new Date(System.currentTimeMillis());
            
            HoaDon hd = new HoaDon(expectedHoaDonID, newNVNhap, newGiaTien, dateNhap);
            hdS = new HoaDonServices();
            hdS.addHoaDon(hd);
            try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM hoadon");
            ResultSet rs = stm.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            Assertions.assertEquals(7, rowCount); 
        }
    }
}
