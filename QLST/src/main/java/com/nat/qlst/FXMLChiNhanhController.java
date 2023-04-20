package com.nat.qlst;

import com.nat.Utils.MessageBox;
import com.nat.pojo.ChiNhanh;
import com.nat.services.ChinhanhServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLChiNhanhController implements Initializable {

    @FXML
    private TableView<ChiNhanh> tbvChiNhanh;
    @FXML
    private TextField txtTenChiNhanh;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private Integer index;
    @FXML
    private TableColumn<ChiNhanh, String> colTenCN;
    @FXML
    private TableColumn<ChiNhanh, String> colDiaChi;
    ChinhanhServices cnS = new ChinhanhServices();

    public void LoadData() throws SQLException {
        this.tbvChiNhanh.setItems(FXCollections.observableList(cnS.getChiNhanh()));
    }
//    ObservableList<ChiNhanh> CNList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            this.loadTableView();
            this.tbvChiNhanh.setItems(FXCollections.observableList(cnS.getChiNhanh()));

        } catch (SQLException ex) {
            Logger.getLogger(FXMLChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addChiNhanh(ActionEvent event) throws SQLException {

        ChiNhanh cn = new ChiNhanh(txtTenChiNhanh.getText(), txtDiaChi.getText());
        if (txtTenChiNhanh.getText().equals("") || txtDiaChi.getText().equals("")) {
            MessageBox.getBox("Không thể thêm dữ liệu bỏ trống", "Vui lòng hãy điền đầy đủ thông tin", AlertType.ERROR).showAndWait();
        } else {
            cnS.addChiNhanh(cn);
            loadTableData();

        }
    }

    public void loadTableView() {
        colTenCN.setPrefWidth(200);
        colTenCN.setCellValueFactory(new PropertyValueFactory("tenchinhanh"));

        colDiaChi.setPrefWidth(350);
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diachi"));

//        colSLNhanVien.setPrefWidth(200);
//        colSLNhanVien.setCellValueFactory(new PropertyValueFactory("soluongnhanvien"));
//        this.tbvChiNhanh.getColumns().addAll(colTenCN,colDiaChi);
        TableColumn colDel = new TableColumn("Xóa");
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
                        ChiNhanh cn = (ChiNhanh) cell.getTableRow().getItem();

                        try {
                            cnS.delChiNhanh(cn.getMachinhanh());
                            loadTableData();

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

        this.tbvChiNhanh.getColumns().addAll(colDel);
    }

    

    public void updateChiNhanh(ActionEvent event) throws SQLException {

        if (txtTenChiNhanh.getText().equals("") || txtDiaChi.getText().equals("")) {
            MessageBox.getBox("Không thể cập nhật dữ liệu bỏ trống", "Vui lòng hãy điền đầy đủ thông tin", AlertType.ERROR).showAndWait();
        } else {
            ChiNhanh cn = new ChiNhanh(txtTenChiNhanh.getText(), txtDiaChi.getText(), idChiNhanh());
            cnS.updateChiNhanh(cn);
            loadTableData();

        }

    }

    public void GetItem(MouseEvent event) {
        index = tbvChiNhanh.getSelectionModel().getSelectedIndex();
        if (index < -1) {
            return;
        }
        txtTenChiNhanh.setText(colTenCN.getCellData(index));
        txtDiaChi.setText(colDiaChi.getCellData(index));
    }

//    public void changeScenceChiNhanhDetail(ActionEvent event) throws IOException {
//        if (tbvChiNhanh.getSelectionModel().getSelectedItem() == null) {
//            MessageBox.getBox("Chưa chọn dữ liệu", "Bạn chưa chọn chi nhánh để sử dụng chức năng", Alert.AlertType.ERROR).showAndWait();
//        } else {
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("ChiNhanhDetails.fxml"));
//
//            Parent ChiNhanhViewParent = loader.load();
//            Scene scene = new Scene(ChiNhanhViewParent, 1000, 700);
//            FXMLChiNhanhDetail controller = loader.getController();
//            ChiNhanh selected = tbvChiNhanh.getSelectionModel().getSelectedItem();
//            controller.setChiNhanh(selected);
//            stage.setScene(scene);
//        }
//
//    }

    public void loadNhanVienDetail(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("NhanVienDetails.fxml"));
        Parent root = loader.load();
        FXMLNhanVienController controller = loader.getController();
        controller.setData();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);  
        stage.show();
    }

    public void loadSanPhamDetail(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SanPhamDetails.fxml"));
        Parent root = loader.load();
        FXMLSanPhamController controller = loader.getController();
        controller.setData();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void loadTaiKhoanDetails(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TaiKhoanConTroller.fxml"));
        Parent root = loader.load();
        FXMLTaiKhoanController controller = loader.getController();
        controller.LoadData();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void loadBanHangDetails(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BanHangDetails.fxml"));
        Parent root = loader.load();
        FXMLBanHangController controller = loader.getController();
        controller.LoadData();
        Stage stage = new Stage();
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void loadTableData() throws SQLException {
//        List<ChiNhanh> cn = cnS.getChiNhanh();
//        txtDiaChi.clear();
//        txtTenChiNhanh.clear();
//        this.tbvChiNhanh.getItems().clear();
//        this.tbvChiNhanh.setItems(FXCollections.observableList(cn));
        this.tbvChiNhanh.setItems(FXCollections.observableList(cnS.getChiNhanh()));
    }

    public int idChiNhanh() {
        SelectionModel<ChiNhanh> selectionCollumn = tbvChiNhanh.getSelectionModel();

        ChiNhanh selectedUser = selectionCollumn.getSelectedItem();

        int ChiNhanhID = selectedUser.getMachinhanh();
        return ChiNhanhID;
    }
}
