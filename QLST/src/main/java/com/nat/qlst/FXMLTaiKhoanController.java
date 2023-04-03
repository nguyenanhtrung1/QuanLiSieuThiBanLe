/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.Utils.MessageBox;
import com.nat.pojo.ChiNhanh;
import com.nat.pojo.TaiKhoan;
import com.nat.services.TaiKhoanServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLTaiKhoanController implements Initializable {

    @FXML
    private TableView<TaiKhoan> tbvNhanVien;
    @FXML
    private TableColumn<TaiKhoan, String> colTaiKhoan;
    @FXML
    private TableColumn<TaiKhoan, String> colMatKhau;
    @FXML
    private TableColumn<TaiKhoan, String> colVaiTro;
    @FXML
    private TableColumn<TaiKhoan, String> colDel;
    /**
     * Initializes the controller class.
     */
    TaiKhoanServices tkS = new TaiKhoanServices();
    public void LoadData() throws SQLException {
        this.tbvNhanVien.setItems(FXCollections.observableList(tkS.getTaiKhoanAdmin()));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableView();
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
    public void loadTableTaiKhoan() throws SQLException{
        this.tbvNhanVien.setItems(FXCollections.observableArrayList(tkS.getTaiKhoanAdmin()));
    }
}
