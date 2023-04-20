/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.*;
import com.nat.services.ChinhanhServices;
import com.nat.pojo.ChiNhanh;
import com.nat.Utils.jdbcUtils;
import com.nat.pojo.NhanVien;
import com.nat.services.NhanVienServices;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class NhanVienTester {
    private NhanVienServices nvS;
    
    @Test
    public void testAddNhanVien() throws Exception {
        NhanVien  nv = new NhanVien("Nguyễn Thái","Sơn",19, "3 Phan Đăng Lưu Phú Nhuận", 1);
        nvS = new NhanVienServices();
        nvS.addNhanVien(nv);
        try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM nhanvien where chinhanhID = ?");
            stm.setInt(1, 1);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            Assertions.assertEquals(11, rowCount); 
        }
    }
    @Test
    public void testDelChiNhanh() throws Exception {
        // Insert data before deleting
        try(Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO nhanvien(manhanvien, firstname, lastname, age, phonenumber) VALUES(100,'Đặng Văn','Dũng',23, '0898357214')";
            stm.executeUpdate(sql);
        }
        nvS = new NhanVienServices();
        nvS.delNhanVien(100);
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
//    @Test
//    public void testUpdateNhanVien() throws Exception {
//        try (Connection conn = jdbcUtils.getConn()) {
//            final int expectedId = 1;
//            final String expectedLastName = "Demo 1";
//            final String newLastName = "Demo 1 New";
//            final int expectedAge = 27;
//            final int newAge = 30;
//            final String expectedFirstName = "Test";
//            final String newFirstName = "Test New";
//            final String expectedPhoneNumber = "12345";
//            final String newPhoneNumber = "54321";
//            final byte expectedActive = 1;
//            final byte newActive = 0;
//
//            NhanVien nv = new NhanVien(newLastName, newAge, newFirstName, newPhoneNumber, newAge);
//           
//            nvS = new NhanVienServices();
//            nvS.updateNhanVien(nv);
//
//            String selectSql = "SELECT * FROM NhanVien WHERE manhanvien = ?";
//            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
//            preparedStatement.setInt(1, expectedId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                Assertions.assertEquals(expectedId, resultSet.getInt("manhanvien"));
//                Assertions.assertEquals(expectedLastName, resultSet.getString("lastname"));
//                Assertions.assertEquals(newAge, resultSet.getInt("age"));
//                Assertions.assertEquals(newFirstName, resultSet.getString("firstname"));
//                Assertions.assertEquals(newPhoneNumber, resultSet.getString("phonenumber"));
//                Assertions.assertEquals(newActive, resultSet.getByte("activenhanvien"));
//            } else {
//                Assertions.fail("Lỗi cập nhật cơ sở dữ liệu!");
//            }
//        }
//    }
    
}
