/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.Utils.MessageBox;
import com.nat.pojo.ChiNhanh;
import com.nat.pojo.SanPham;
import com.nat.services.ChinhanhServices;
import com.nat.services.SanPhamServices;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Admin
 */
public class FXMLSanPhamController implements Initializable{
    @FXML private TableView<SanPham> tbvSanPham;
    @FXML private TextField txtTenSanPham;
    @FXML private TextField txtSoLuong;
    @FXML private TextField txtGiaTien;
    @FXML private DatePicker DateNgaySanXuat;
    @FXML private TableColumn<SanPham,String> colTenSP;
    @FXML private TableColumn<SanPham,Integer> colSoLuongSP;
    @FXML private TableColumn<SanPham,Float> colGiaTien;
    @FXML private TableColumn<SanPham,DatePicker> colNgaySanXuat;
    @FXML private TableColumn<SanPham,String> colDel;
    @FXML private ComboBox<ChiNhanh> cbChiNhanhs ;
    @FXML private Integer index;
    public void setData() throws SQLException{
            SanPhamServices spS = new SanPhamServices();
            this.tbvSanPham.setItems(FXCollections.observableList(spS.getSanPham()));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
            this.loadTableView();
            ChinhanhServices cnS = new ChinhanhServices();
            this.cbChiNhanhs.setItems(FXCollections.observableList(cnS.getChiNhanh()));
            SanPhamServices spS = new SanPhamServices();
            cbChiNhanhs.setOnAction(event -> {
                ChiNhanh selectedDCN = cbChiNhanhs.getSelectionModel().getSelectedItem();
                if (selectedDCN != null) {
                    
                    try {
                        this.tbvSanPham.setItems(FXCollections.observableList(spS.getSanPham(cbChiNhanhs.getSelectionModel().getSelectedItem().getMachinhanh())));
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            });
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSanPhamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadTableView(){
        colTenSP.setPrefWidth(180);
        colTenSP.setCellValueFactory(new PropertyValueFactory("tensanpham"));
        
        colSoLuongSP.setPrefWidth(180);
        colSoLuongSP.setCellValueFactory(new PropertyValueFactory("soluong"));
        
        colGiaTien.setPrefWidth(180);
        colGiaTien.setCellValueFactory(new PropertyValueFactory("giatien"));
        
        colNgaySanXuat.setPrefWidth(180);
        colNgaySanXuat.setCellValueFactory(new PropertyValueFactory("ngaysanxuat"));
        
        colDel.setPrefWidth(100);
        colDel.setCellFactory(r -> {
               Button btn = new Button("Delete");
        
            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Question", 
                        "Bạn chắc chắn muốn xóa sản phẩm này chứ?", 
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button)evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        SanPham sp = (SanPham) cell.getTableRow().getItem();
                        
                        SanPhamServices spS = new SanPhamServices();
                        
                        try {
                            spS.delSanPham(sp.getMasanpham());  
                            loadTableSanPham();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });
            
            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });
    }
    public void addSanPham(ActionEvent event) throws SQLException{
        if(cbChiNhanhs.getSelectionModel().getSelectedItem() == null ){
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa chọn chi nhánh để thêm sản phẩm", Alert.AlertType.ERROR).showAndWait();
        }
        else if(txtTenSanPham.getText().isEmpty() || txtSoLuong.getText().isEmpty() || txtGiaTien.getText().isEmpty() || DateNgaySanXuat.getValue() == null){
        MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa Nhập đầy đủ dữ liệu", Alert.AlertType.ERROR).showAndWait();

        }
        else{
            SanPham sp = new SanPham(txtTenSanPham.getText(),Integer.parseInt(txtSoLuong.getText()),Float.parseFloat(txtGiaTien.getText()),Date.valueOf(DateNgaySanXuat.getValue()) ,this.cbChiNhanhs.getSelectionModel().getSelectedItem().getMachinhanh());
            SanPhamServices spS = new SanPhamServices();
            spS.addSanPham(sp);
            loadTableSanPham();
        }
    }
    public void updateSanPham(ActionEvent event) throws SQLException{
        
        if(cbChiNhanhs.getSelectionModel().getSelectedItem() == null ){
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa chọn chi nhánh để thêm nhân viên", Alert.AlertType.ERROR).showAndWait();
        }
        else if(txtTenSanPham.getText().isEmpty() || txtSoLuong.getText().isEmpty() || txtGiaTien.getText().isEmpty() || DateNgaySanXuat.getValue() == null){
        MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa Nhập đầy đủ dữ liệu", Alert.AlertType.ERROR).showAndWait();

        }
        else{
        SanPham sp = new SanPham(txtTenSanPham.getText(),Float.parseFloat(txtGiaTien.getText()),Integer.parseInt(txtSoLuong.getText()),Date.valueOf(DateNgaySanXuat.getValue()),idSanPham());
        SanPhamServices spS = new SanPhamServices();
        spS.updateSanPham(sp);
        loadTableSanPham();
        }
    }
    public void loadTableSanPham() throws SQLException{
        SanPhamServices spS = new SanPhamServices();
        this.tbvSanPham.setItems(FXCollections.observableArrayList(spS.getSanPham(this.cbChiNhanhs.getSelectionModel().getSelectedItem().getMachinhanh())));
    }
    public void GetItem(MouseEvent event){
        index = tbvSanPham.getSelectionModel().getSelectedIndex();
        if(index < -1){
            return;
        }
        txtTenSanPham.setText(colTenSP.getCellData(index));
        txtSoLuong.setText(String.valueOf(colSoLuongSP.getCellData(index)));
        txtGiaTien.setText(String.valueOf(colGiaTien.getCellData(index)));
    }
    public int idSanPham(){
        SelectionModel<SanPham> selectionCollumn = tbvSanPham.getSelectionModel();

        SanPham selectedUser = selectionCollumn.getSelectedItem();

        int SanPhamID = selectedUser.getMasanpham();
        return  SanPhamID;
    }
}
