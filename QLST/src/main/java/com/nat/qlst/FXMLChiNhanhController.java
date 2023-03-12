package com.nat.qlst;

import com.nat.pojo.ChiNhanh;
import com.nat.services.ChinhanhServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
public class FXMLChiNhanhController implements Initializable{
    @FXML private TableView<ChiNhanh> tbvChiNhanh;
    @FXML private TextField txtTenChiNhanh;
    @FXML private TextField txtDiaChi;
    @FXML private TextField txtTimKiem;
    @FXML private Integer index;
    @FXML private TableColumn<ChiNhanh, String> colTenCN;
    @FXML private TableColumn<ChiNhanh, String> colDiaChi;
    
//    ObservableList<ChiNhanh> CNList;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             this.loadTableView();
             ChinhanhServices cnS = new ChinhanhServices();
             this.tbvChiNhanh.setItems(FXCollections.observableList(cnS.getChiNhanh()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }  
         
    }
    public void addChiNhanh(ActionEvent event) throws SQLException{
        
        ChiNhanh cn = new ChiNhanh(txtTenChiNhanh.getText(),txtDiaChi.getText());
        ChinhanhServices cnS = new ChinhanhServices();
        if(txtTenChiNhanh.getText().equals("") || txtDiaChi.getText().equals("")){
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Không thể thêm dữ liệu bỏ trống");
            errorAlert.setContentText("Vui lòng hãy điền đầy đủ thông tin");
            errorAlert.showAndWait();
        }
        else{
            cnS.addChiNhanh(cn);
            tbvChiNhanh.setItems(FXCollections.observableList(cnS.getChiNhanh()));
            txtDiaChi.clear();
            txtTenChiNhanh.clear(); 
        }
        
           
    }
    public void deleteChiNhanh(ActionEvent event) throws SQLException {
        ChiNhanh cn = tbvChiNhanh.getSelectionModel().getSelectedItem();
        ChinhanhServices cnS = new ChinhanhServices();
        
        Alert ConfirmationAlert = new Alert(AlertType.CONFIRMATION);
            ConfirmationAlert.setTitle("Xác Nhận Xóa Dữ Liệu");
            ConfirmationAlert.setHeaderText("Bạn chắc chắn muốn xóa chi nhánh này?");
            ConfirmationAlert.setContentText("Vui lòng chọn lựa chọn");
        ButtonType btnTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType btnTypeCancle = new ButtonType("Cancle", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        ConfirmationAlert.getButtonTypes().setAll(btnTypeYes,btnTypeCancle);
        Optional<ButtonType> result = ConfirmationAlert.showAndWait();
        
        if(result.get() == btnTypeYes){
            cnS.delChiNhanh(cn.getMachinhanh());
            tbvChiNhanh.setItems(FXCollections.observableList(cnS.getChiNhanh()));
            txtDiaChi.clear();
            txtTenChiNhanh.clear(); 
        }
        else{
            ConfirmationAlert.close();
        }
        
    }
    public void loadTableView(){         
        colTenCN.setPrefWidth(200);
        colTenCN.setCellValueFactory(new PropertyValueFactory("tenchinhanh"));
        
        colDiaChi.setPrefWidth(200);
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diachi"));
        
//        this.tbvChiNhanh.getColumns().addAll(colTenCN,colDiaChi);

    }
    public void TimKiemChiNhanh(ActionEvent event) {
        
    }
    public void GetItem(MouseEvent event){
        index = tbvChiNhanh.getSelectionModel().getSelectedIndex();
        if(index < -1){
            return;
            
        }
        txtTenChiNhanh.setText(colTenCN.getCellData(index));
        txtDiaChi.setText(colDiaChi.getCellData(index));
    }
    
}