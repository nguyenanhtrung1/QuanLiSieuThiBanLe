
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.nat.services.ChinhanhServices;
import com.nat.pojo.ChiNhanh;
import com.nat.Utils.jdbcUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class ChiNhanhTester {
    private static Connection conn;
    private ChinhanhServices cnS;
    @BeforeAll
    public static void beforeAll() throws SQLException {
       conn = jdbcUtils.getConn();
//        cnS = new ChinhanhServices();
    }
    
    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null)
                conn.close();

    }
    
    @Test
    public void testAddChiNhanh() throws Exception {
        ChiNhanh cn = new ChiNhanh("CN3", "3 Nguyen Van Troi, Q.Phu Nhuan");
        cnS = new ChinhanhServices();
        cnS.addChiNhanh(cn);
        try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM chinhanh");
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
            String sql = "INSERT INTO chinhanh(machinhanh, tenchinhanh, diachi) VALUES(100,'Test', 'Test Address')";
            stm.executeUpdate(sql);
        }
        cnS = new ChinhanhServices();
        cnS.delChiNhanh(100);
        // Check if the chi nhanh has been deleted
        try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM chinhanh WHERE machinhanh = ?");
            stm.setInt(1, 100);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            Assertions.assertEquals(0, rowCount);
        }
    }
//    @Test
//    public void testUpdateChiNhanh() throws Exception {
//        // Add new ChiNhanh
//        ChiNhanh cn = new ChiNhanh(10, "CNTest_update", "Test Update Address");
//        cnS = new ChinhanhServices();
//        cnS.addChiNhanh(cn);
//        cn.setTenchinhanh("Chi Nhanh 10");
//        cn.setDiachi("4 Pham van dong Go Vap");
//        cnS.updateChiNhanh(cn);
//        // Check if the ChiNhanh has been updated
//        try(Connection conn = jdbcUtils.getConn()) {
//            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM chinhanh WHERE machinhanh = ? AND tenchinhanh = ? AND diachi = ?");
//            stm.setInt(1, 103);
//            stm.setString(2, "Chi Nhanh 10");
//            stm.setString(3, "4 Pham van dong Go Vap");
//            ResultSet rs = stm.executeQuery();
//            rs.next();
//            int rowCount = rs.getInt(1);
//            Assertions.assertEquals(1, rowCount);
//        }
//    }
}
