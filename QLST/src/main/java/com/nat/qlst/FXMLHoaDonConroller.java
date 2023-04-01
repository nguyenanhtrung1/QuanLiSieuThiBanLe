/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.pojo.HoaDon;
import com.nat.pojo.NhanVien;
import com.nat.services.HoaDonServices;
import com.nat.services.NhanVienServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class FXMLHoaDonConroller implements Initializable{
    @FXML private TextField txtMaHoaDon;
    @FXML private TextField txtMaSanPham;
    @FXML private TextField txtSoLuong;
    @FXML private TextField txtGiaNHap;
    @FXML private ComboBox<NhanVien> cbHoaDon;
    @FXML private DatePicker dateNhap;
    @FXML private TableColumn<HoaDon,Integer> colMaHD;
    @FXML private TableColumn<HoaDon,Integer> colMaSanPham;
    @FXML private TableColumn<HoaDon,Integer> colSoLuong;
    @FXML private TableColumn<HoaDon,Float> colGiaTien;
    @FXML private TableColumn<HoaDon,NhanVien> colNhanVien;
    @FXML private TableColumn<HoaDon,DatePicker> colDate;
    @FXML private TableColumn<HoaDon,String> colDel;
    @FXML private TableView<HoaDon> tbvHoaDon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             this.loadTableView();
             NhanVienServices nvs = new NhanVienServices();
             HoaDonServices hdS = new HoaDonServices();
             this.cbHoaDon.setItems(FXCollections.observableArrayList(nvs.getNhanVien()));
             this.tbvHoaDon.setItems(FXCollections.observableList(hdS.getHoaDon()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void loadTableView(){         
        colMaHD.setPrefWidth(100);
        colMaHD.setCellValueFactory(new PropertyValueFactory("mahoadon"));
        
        colMaSanPham.setPrefWidth(100);
        colMaSanPham.setCellValueFactory(new PropertyValueFactory("tensanpham"));
        
        colSoLuong.setPrefWidth(100);
        colSoLuong.setCellValueFactory(new PropertyValueFactory("soluong"));
        
        colGiaTien.setPrefWidth(100);
        colGiaTien.setCellValueFactory(new PropertyValueFactory("gianhap"));
        
        colNhanVien.setPrefWidth(100);
        colNhanVien.setCellValueFactory(new PropertyValueFactory("tennhanvien"));
        
        colDate.setPrefWidth(100);
        colDate.setCellValueFactory(new PropertyValueFactory("ngaynhap"));

    }
     public void loadTableData() throws SQLException {
        HoaDonServices hdS = new HoaDonServices();
        List<HoaDon> hd = hdS.getHoaDon();
        this.tbvHoaDon.getItems().clear();
        this.tbvHoaDon.setItems(FXCollections.observableList(hd));
    }
}
