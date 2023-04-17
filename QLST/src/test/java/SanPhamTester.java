/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.*;
import com.nat.services.SanPhamServices;
import com.nat.pojo.SanPham;
import com.nat.Utils.jdbcUtils;
import com.nat.pojo.NhanVien;
import com.nat.services.NhanVienServices;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Admin
 */

public class SanPhamTester {
    private static SanPhamServices spS;
//    @Test
//    public void testAddNhanVien() throws Exception {
//        SanPham sp = new SanPham("Bánh orion", 19, 3200, Date.valueOf("2023-02-21"), 1);
//        spS = new SanPhamServices();
//        spS.addSanPham(sp);
//        try(Connection conn = jdbcUtils.getConn()) {
//            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM nhanvien where chinhanhID = ?");
//            stm.setInt(1, 1);
//            ResultSet rs = stm.executeQuery();
//            rs.next();
//            int rowCount = rs.getInt(1);
//            Assertions.assertEquals(11, rowCount); 
//        }
//    }
    @Test
    public void testAddSanPham() throws Exception {
            final int expectedChiNhanhID = 1;
            final String newTenSanPham = "Test Ten San Pham";
            final int newSoLuong = 10;
            final float newGiaTien = 100000;
            final Date newNgaySanXuat = new Date(System.currentTimeMillis());
            
            SanPham sanPham = new SanPham(newTenSanPham, newSoLuong, newGiaTien, newNgaySanXuat, expectedChiNhanhID);
            spS = new SanPhamServices();
            spS.addSanPham(sanPham);
            try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM sanpham where chinhanhID = ?");
            stm.setInt(1, 1);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            Assertions.assertEquals(7, rowCount); 
            spS.delSanPham(sanPham.getMasanpham());
        }
    }
    @Test
    public void testDelSanPham() throws Exception {
        // Insert data before deleting
        try(Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO sanpham(masanpham, tensanpham, soluong, giatien, ngaysanxuat, chinhanhid)" 
                + " VALUES(" + 100 + ", 'sp de xoa', 10, 100000, '2023-04-17', 1)";
            stm.executeUpdate(sql);
        }
        spS = new SanPhamServices();
        spS.delSanPham(100);
        // Check if the chi nhanh has been deleted
        try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM nhanvien WHERE manhanvien = ?");
            stm.setInt(1, 100);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            Assertions.assertEquals(0, rowCount);
        }
    }
}
