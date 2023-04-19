package com.nat.qlst;

import com.nat.Utils.MessageBox;
import com.nat.pojo.ChiNhanh;
import com.nat.pojo.TaiKhoan;
import com.nat.services.ChinhanhServices;
import com.nat.services.TaiKhoanServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {
    @FXML private TextField txtTK_DangNhap;
    @FXML private TextField txtMK_DangNhap;
    @FXML private TextField txtTK_DangKi;
    @FXML private TextField txtMK_DangKi;
    @FXML private ComboBox<String> cbTK_RoleDN ;
    @FXML private ComboBox<String> cbTK_RoleDK ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> ListENUM = new ArrayList<>();
            String s1 = "nhanvien";
            String s2 = "quantrivien";
            ListENUM.add(s1);
            ListENUM.add(s2);
            this.cbTK_RoleDK.setItems(FXCollections.observableList(ListENUM));
            this.cbTK_RoleDN.setItems(FXCollections.observableList(ListENUM));
    }
    public void addTaiKhoan(ActionEvent event) throws SQLException {
        if(cbTK_RoleDK.getSelectionModel().getSelectedItem() == null ){
            MessageBox.getBox("ERROR", "Chưa chọn chức vụ để thêm tài khoản", Alert.AlertType.ERROR).showAndWait();
        }
        else if(txtTK_DangKi.getText().isEmpty() || txtMK_DangKi.getText().isEmpty() ){
        MessageBox.getBox("ERROR", "Chưa Nhập đầy đủ dữ liệu", Alert.AlertType.ERROR).showAndWait();
        }
        else{
            TaiKhoan tk = new TaiKhoan(txtTK_DangKi.getText(),txtMK_DangKi.getText(),cbTK_RoleDK.getValue());
            TaiKhoanServices tkS = new TaiKhoanServices();
            tkS.addTaiKhoan(tk);
            MessageBox.getBox("Success", "Đăng Kí Thành Công", Alert.AlertType.ERROR).showAndWait();
        }
    }
    public void DangNhap(ActionEvent event) throws SQLException, IOException{
        TaiKhoan tk = new TaiKhoan(txtTK_DangNhap.getText(), txtMK_DangNhap.getText(), cbTK_RoleDN.getValue());
        try {
            TaiKhoanServices tks = new TaiKhoanServices();
            if (tks.checkLogin(tk) && cbTK_RoleDN.getValue().equals("quantrivien") == true) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSieuThiController.fxml"));
            Parent root = loader.load();
            FXMLChiNhanhController controller = loader.getController();
            controller.LoadData();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1000, 700);
            stage.setScene(scene);
            stage.show();

            } else if(tks.checkLogin(tk) && cbTK_RoleDN.getValue().equals("nhanvien") == true){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HoaDonDetails.fxml"));
            Parent root = loader.load();
            FXMLHoaDonConroller controller = loader.getController();
            controller.LoadData();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.show();
            }
            
            else {
                MessageBox.getBox("ERROR", "Đăng Nhập Thất Bại, Hãy kiểm tra lại thông tin", Alert.AlertType.ERROR).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
    }
    
}
