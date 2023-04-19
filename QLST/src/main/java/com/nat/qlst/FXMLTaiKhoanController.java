/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.Utils.MessageBox;
import com.nat.pojo.TaiKhoan;
import com.nat.services.TaiKhoanServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class FXMLTaiKhoanController implements Initializable {
    
    @FXML private TextField txtTaiKhoan;
    @FXML private TextField txtMatKhau;
    @FXML private ComboBox<String> cbTaiKhoan;
    @FXML private TableView<TaiKhoan> tbvTaiKhoan;
    @FXML
    private TableColumn<TaiKhoan, String> colTaiKhoan;
    @FXML
    private TableColumn<TaiKhoan, String> colMatKhau;
    @FXML
    private TableColumn<TaiKhoan, String> colVaiTro;
    @FXML
    private TableColumn<TaiKhoan, String> colDel;
    @FXML
    private Integer index;
    /**
     * Initializes the controller class.
     */
    TaiKhoanServices tkS = new TaiKhoanServices();
    public void LoadData() throws SQLException {
        this.tbvTaiKhoan.setItems(FXCollections.observableList(tkS.getTaiKhoanAdmin()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableView();
        List<String> listRole = new ArrayList<>();
        String s1 = "quantrivien";
        String s2 = "nhanvien";
        listRole.add(s1);
        listRole.add(s2);
        this.cbTaiKhoan.setItems(FXCollections.observableList(listRole));
    }    
    public void loadTableView() {
        colTaiKhoan.setPrefWidth(200);
        colTaiKhoan.setCellValueFactory(new PropertyValueFactory("tendangnhap"));

        colMatKhau.setPrefWidth(350);
        colMatKhau.setCellValueFactory(new PropertyValueFactory("matkhau"));

        colVaiTro.setPrefWidth(200);
        colVaiTro.setCellValueFactory(new PropertyValueFactory("taikhoan_role"));
        
        colDel.setCellFactory(r -> {
            Button btn = new Button("Delete");

            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Question",
                        "Bạn chắc chắn muốn xóa chi nhánh này chứ?",
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        TaiKhoan tk = (TaiKhoan) cell.getTableRow().getItem();
                        
                        try {
                            tkS.delTaiKhoan(tk.getMataikhoan());
                            loadTableTaiKhoan();
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
    public void addTaiKhoan(ActionEvent event) throws SQLException {

        if (cbTaiKhoan.getSelectionModel().getSelectedItem() == null) {
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa chọn chức vụ để thêm tài khoản", Alert.AlertType.ERROR).showAndWait();
        } else if (txtTaiKhoan.getText().isEmpty() || txtMatKhau.getText().isEmpty() ) {
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa Nhập đầy đủ dữ liệu", Alert.AlertType.ERROR).showAndWait();

        } else {
            TaiKhoan tk = new TaiKhoan(txtTaiKhoan.getText(), txtMatKhau.getText(),  this.cbTaiKhoan.getValue());
            
            tkS.addTaiKhoan(tk);
            loadTableTaiKhoan();
        }
    }
    public void updateTaiKhoan(ActionEvent event) throws SQLException {

        if (cbTaiKhoan.getSelectionModel().getSelectedItem() == null) {
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa chọn chi nhánh để cập nhật nhân viên", Alert.AlertType.ERROR).showAndWait();
        } else if (txtTaiKhoan.getText().isEmpty() || txtMatKhau.getText().isEmpty()) {
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Chưa Nhập đầy đủ dữ liệu", Alert.AlertType.ERROR).showAndWait();

        } else {
           TaiKhoan tk = new TaiKhoan(txtTaiKhoan.getText(), txtMatKhau.getText(),  this.cbTaiKhoan.getValue(),idTaiKhoan());
            tkS.updateTaiKhoan(tk);
            loadTableTaiKhoan();
        }
    }
    public void GetItem(MouseEvent event) {
        index = tbvTaiKhoan.getSelectionModel().getSelectedIndex();
        if (index < -1) {
            return;
        }
        txtTaiKhoan.setText(colTaiKhoan.getCellData(index));
        txtMatKhau.setText(colMatKhau.getCellData(index));
    }
    public void loadTableTaiKhoan() throws SQLException{
        this.tbvTaiKhoan.setItems(FXCollections.observableArrayList(tkS.getTaiKhoanAdmin()));
    }
    public int idTaiKhoan() {
        SelectionModel<TaiKhoan> selectionCollumn = tbvTaiKhoan.getSelectionModel();

        TaiKhoan selectedUser = selectionCollumn.getSelectedItem();

        int TaiKhoanID = selectedUser.getMataikhoan();
        return TaiKhoanID;
    }
}
