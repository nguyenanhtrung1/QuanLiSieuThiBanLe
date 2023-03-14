/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.pojo.NhanVien;
import com.nat.services.NhanVienServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class FXMLNhanVienController implements Initializable{
    @FXML private TableView<NhanVien> tbvNhanVien;
    @FXML private TextField txtTenNV;
    @FXML private TextField txtTuoi;
    @FXML private TextField txtPhoneNumber;
    @FXML private TextField txtTenCN;
    @FXML private TableColumn<NhanVien,String> colTenNV;
    @FXML private TableColumn<NhanVien,String> colTuoiNV;
    @FXML private TableColumn<NhanVien,String> colPhoneNumber;
    @FXML private TableColumn<NhanVien,String> colCNLamViec;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            this.loadTableView();
             NhanVienServices nvS = new NhanVienServices();
            this.tbvNhanVien.setItems(FXCollections.observableList(nvS.getNhanVien()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadTableView(){         
        
        colTenNV.setPrefWidth(200);
        colTenNV.setCellValueFactory(new PropertyValueFactory("firstname"));
        
        colTuoiNV.setPrefWidth(200);
        colTuoiNV.setCellValueFactory(new PropertyValueFactory("age"));
        
        colPhoneNumber.setPrefWidth(200);
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory("phonenumber"));
        
        colCNLamViec.setPrefWidth(200);
        colCNLamViec.setCellValueFactory(new PropertyValueFactory("id_ChiNhanh"));

    }
    public void addNhanVien(ActionEvent event) throws SQLException{
        
        NhanVien nv = new NhanVien(txtTenNV.getText(),Integer.parseInt(txtTuoi.getText()),txtPhoneNumber.getText(),Integer.parseInt(txtTenCN.getText()));
        NhanVienServices nvS = new NhanVienServices();
        if(txtTenNV.getText().equals("") || txtTuoi.getText().equals("")||txtPhoneNumber.getText().equals("")||txtTenCN.getText().equals("")){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Không thể thêm dữ liệu bỏ trống");
            errorAlert.setContentText("Vui lòng hãy điền đầy đủ thông tin");
            errorAlert.showAndWait();
        }
        else{
            nvS.addNhanVien(nv);
            tbvNhanVien.setItems(FXCollections.observableList(nvS.getNhanVien()));
            txtTenNV.clear();
            txtTuoi.clear(); 
        }
    }
    
}
