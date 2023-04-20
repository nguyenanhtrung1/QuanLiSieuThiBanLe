/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.pojo.HoaDon;
import com.nat.services.HoaDonServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class FXMLBanHangController {
    
    @FXML private TableView<HoaDon> tbvHoaDon;
    @FXML
    private TableColumn<HoaDon, String> colNhanVienNhap;
    @FXML
    private TableColumn<HoaDon, Double> colGiaTien;
    @FXML
    private TableColumn<HoaDon, DatePicker> colNgayBan;
    
    public void LoadData() throws SQLException  {
        HoaDonServices hdS = new HoaDonServices();
        this.tbvHoaDon.setItems(FXCollections.observableList(hdS.getHoaDon()));
        this.loadTableView();
    }
    
    public void initialize(URL url, ResourceBundle rb) throws SQLException {
        this.loadTableHoaDon();
        this.loadTableView();
        HoaDonServices hdS = new HoaDonServices();
        this.tbvHoaDon.setItems(FXCollections.observableList(hdS.getHoaDon()));
    }   
    public void loadTableView() {
        colNhanVienNhap.setPrefWidth(200);
        colNhanVienNhap.setCellValueFactory(new PropertyValueFactory("nhanviennhap"));

        colGiaTien.setPrefWidth(350);
        colGiaTien.setCellValueFactory(new PropertyValueFactory("giatien"));

        colNgayBan.setPrefWidth(200);
        colNgayBan.setCellValueFactory(new PropertyValueFactory("ngayban"));
        
    }
    public void loadTableHoaDon() throws SQLException{
        HoaDonServices hdS = new HoaDonServices();
        this.tbvHoaDon.setItems(FXCollections.observableArrayList(hdS.getHoaDon()));
    }
}
