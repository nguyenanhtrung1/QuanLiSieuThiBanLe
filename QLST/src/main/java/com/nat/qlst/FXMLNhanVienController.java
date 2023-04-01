/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.Utils.MessageBox;
import com.nat.pojo.ChiNhanh;
import com.nat.pojo.NhanVien;
import com.nat.services.ChinhanhServices;
import com.nat.services.NhanVienServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.IntegerExpression;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
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
public class FXMLNhanVienController implements Initializable{
    @FXML private TableView<NhanVien> tbvNhanVien;
    @FXML private TextField txtTenNV;
    @FXML private TextField txtTuoi;
    @FXML private TextField txtPhoneNumber;
    @FXML private TextField txtHo;
    @FXML private ComboBox<ChiNhanh> cbChiNhanhs ;
    @FXML private ComboBox<Integer> cbActive ;
    @FXML private TableColumn<NhanVien,String> colTenNV;
    @FXML private TableColumn<NhanVien,Integer> colTuoiNV;
    @FXML private TableColumn<NhanVien,String> colPhoneNumber;
    @FXML private TableColumn<NhanVien,String> colHo;
    @FXML private TableColumn<NhanVien,Byte> colActive;
    @FXML private TableColumn<NhanVien,String> colDel;
    @FXML private Integer index;
    public void setData(int data) throws SQLException {
        NhanVienServices nvS = new NhanVienServices();
        this.tbvNhanVien.setItems(FXCollections.observableList(nvS.getNhanVien(data)));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        NhanVienServices nvS = new NhanVienServices();
        try {
            List<Integer> listCBActive = new ArrayList<>();
            listCBActive.add(0);
            listCBActive.add(1);
            this.cbActive.setItems(FXCollections.observableList(listCBActive));
            this.loadTableView();
            ChinhanhServices cnS = new ChinhanhServices();
            this.cbChiNhanhs.setItems(FXCollections.observableList(cnS.getChiNhanh()));
//            cbChiNhanhs.setOnAction(new EventHandler<ActionEvent>(){
//                public void handle(ActionEvent event) {
//                    try {
//                    ChiNhanh cn = cbChiNhanhs.getSelectionModel().getSelectedItem();
//                    tbvNhanVien.setItems(FXCollections.observableArrayList());
//                        loadTableNhanVien();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(FXMLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            });

            
                
        } catch (SQLException ex) {
            Logger.getLogger(FXMLNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void loadTableView(){         

        colTenNV.setPrefWidth(150);
        colTenNV.setCellValueFactory(new PropertyValueFactory("firstname"));
        
        colHo.setPrefWidth(150);
        colHo.setCellValueFactory(new PropertyValueFactory("lastname"));
        
        colTuoiNV.setPrefWidth(150);
        colTuoiNV.setCellValueFactory(new PropertyValueFactory("age"));
        
        colPhoneNumber.setPrefWidth(150);
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory("phonenumber"));
        
        colActive.setPrefWidth(150);
        colActive.setCellValueFactory(new PropertyValueFactory("activenhanvien"));
//        colCNLamViec.setPrefWidth(200);
//        colCNLamViec.setCellValueFactory(new PropertyValueFactory("chinhanhID"));
        colDel.setPrefWidth(100);
        colDel.setCellFactory(r -> {
               Button btn = new Button("Delete");
        
            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Question", 
                        "Bạn chắc chắn muốn xóa nhân viên này chứ?", 
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button)evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        NhanVien nv = (NhanVien) cell.getTableRow().getItem();
                        NhanVienServices nvS = new NhanVienServices();
                        
                        try {
                            nvS.delNhanVien(nv.getManhanvien());  
                            loadTableNhanVien();
                            
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
    public void addNhanVien(ActionEvent event) throws SQLException{
        
        if(cbChiNhanhs.getSelectionModel().getSelectedItem() == null ){
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa chọn chi nhánh để thêm nhân viên", Alert.AlertType.ERROR).showAndWait();
        }
        else if(txtHo.getText().isEmpty() || txtTenNV.getText().isEmpty() || txtTuoi.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()){
        MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa Nhập đầy đủ dữ liệu", Alert.AlertType.ERROR).showAndWait();

        }
        else{
            NhanVien nv = new NhanVien(txtHo.getText(),txtTenNV.getText(),Integer.parseInt(txtTuoi.getText()),txtPhoneNumber.getText(),this.cbChiNhanhs.getSelectionModel().getSelectedItem().getMachinhanh());
            NhanVienServices nvS = new NhanVienServices();
            nvS.addNhanVien(nv);
            loadTableNhanVien();
        }
    }
    public void updateNhanVien(ActionEvent event) throws SQLException{
        
        if(cbChiNhanhs.getSelectionModel().getSelectedItem() == null ){
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa chọn chi nhánh để cập nhật nhân viên", Alert.AlertType.ERROR).showAndWait();
        }
        else if(txtHo.getText().isEmpty() || txtTenNV.getText().isEmpty() || txtTuoi.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()){
        MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa Nhập đầy đủ dữ liệu", Alert.AlertType.ERROR).showAndWait();

        }
        else{
        NhanVien nv = new NhanVien(txtHo.getText(),Integer.parseInt(txtTuoi.getText()),txtTenNV.getText(),txtPhoneNumber.getText(),idNhanVien());
        NhanVienServices nvS = new NhanVienServices();
        nvS.updateNhanVien(nv);
        loadTableNhanVien();
        }
           
    }
    public void loadTableNhanVien() throws SQLException{
        NhanVienServices nvS = new NhanVienServices();
        this.tbvNhanVien.setItems(FXCollections.observableArrayList(nvS.getNhanVien(this.cbChiNhanhs.getSelectionModel().getSelectedItem().getMachinhanh())));
    }
    public int idNhanVien(){
        SelectionModel<NhanVien> selectionCollumn = tbvNhanVien.getSelectionModel();

        NhanVien selectedUser = selectionCollumn.getSelectedItem();

        int NhanVienID = selectedUser.getManhanvien();
        return  NhanVienID;
    }
    public void GetItem(MouseEvent event){
        index = tbvNhanVien.getSelectionModel().getSelectedIndex();
        if(index < -1){
            return;
        }
        txtHo.setText(colHo.getCellData(index));
        txtTenNV.setText(colTenNV.getCellData(index));
        txtTuoi.setText(String.valueOf(colTuoiNV.getCellData(index)));
        txtPhoneNumber.setText(colPhoneNumber.getCellData(index));
    }
//    private void loadTableNhanVien(int kw) throws SQLException {
//        NhanVienServices nvS = new NhanVienServices();
//        List<NhanVien> nv = nvS.getNhanVien(kw);
//        
//        this.tbvNhanVien.getItems().clear();
//        this.tbvNhanVien.setItems(FXCollections.observableList(nv));
//    }
    
}
