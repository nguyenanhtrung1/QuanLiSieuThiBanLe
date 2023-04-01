
import com.nat.Utils.jdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.nat.services.ChinhanhServices;
import com.nat.pojo.ChiNhanh;
import com.nat.pojo.ChiNhanh;
import com.nat.Utils.jdbcUtils;
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
//    private static ChinhanhServices cnS;
    @BeforeAll
    public static void beforeAll() throws SQLException {
       conn = jdbcUtils.getConn();
//        cnS = new ChinhanhServices();
    }
    
    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null)
                conn.close();
//        try {
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ChiNhanhTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    @Test 
    public void testDiaChi() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM chinhanh");
        List<String> kq = new ArrayList<>();
        while(rs.next()){
            String name = rs.getString("tenchinhanh");
            kq.add(name);
        }
        Set<String> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
        
    }
}
