/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.*;
import com.nat.Utils.jdbcUtils;
import com.nat.pojo.TaiKhoan;
import com.nat.services.TaiKhoanServices;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */

public class LoginTester {
    private static Connection conn;
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
    private static TaiKhoanServices tkS;

    @Test
    public void testCheckLogin() throws SQLException {
        tkS = new TaiKhoanServices();
        TaiKhoan tk = new TaiKhoan("admin", "123", "quantrivien");
        Assertions.assertTrue(tkS.checkLogin(tk));

        TaiKhoan tkWrong = new TaiKhoan("wronguser", "wrongpass", "nhanvien");
        Assertions.assertFalse(tkS.checkLogin(tkWrong));
    }
    @Test
    public void testAddTaiKhoan() throws Exception {
            tkS = new TaiKhoanServices();
            final int expectedTaiKhoanID = 1;
            final String newTaiKhoan = "newTKNV";
            final String newMatKhau = "newMKNV";
            final String role = "nhanvien";
            
            TaiKhoan tk = new TaiKhoan(expectedTaiKhoanID, newTaiKhoan, newMatKhau, role);
            tkS = new TaiKhoanServices();
            tkS.addTaiKhoan(tk);
            try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM taikhoan");
            ResultSet rs = stm.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            Assertions.assertEquals(9, rowCount); 
            tkS.delTaiKhoan(tk.getMataikhoan());
        }
    }
    @Test
    public void testDelTaiKhoan() throws Exception {
        
        try(Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO taikhoan(mataikhoan, tendangnhap, matkhau, taikhoan_role)" 
                + " VALUES(" + 100 + ", 'tkDel', 'mkDel', 'nhanvien')";
            stm.executeUpdate(sql);
        }
        tkS = new TaiKhoanServices();
        tkS.delTaiKhoan(100);
        try(Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT COUNT(*) FROM taikhoan WHERE mataikhoan = ?");
            stm.setInt(1, 100);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            Assertions.assertEquals(0, rowCount);
        }
    }
}
