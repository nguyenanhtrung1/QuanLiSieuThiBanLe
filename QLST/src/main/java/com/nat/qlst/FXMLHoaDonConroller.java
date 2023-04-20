/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.Utils.MessageBox;
import com.nat.pojo.HoaDon;
import com.nat.pojo.KhachHang;
import com.nat.pojo.NhanVien;
import com.nat.pojo.SanPham;
import com.nat.services.HoaDonServices;
import com.nat.services.KhachHangServices;
import com.nat.services.NhanVienServices;
import java.net.URL;
import com.nat.services.SanPhamServices;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class FXMLHoaDonConroller implements Initializable {

    @FXML
    private TextField txtUuDai;
    @FXML
    private TextField txtTienThua;
    @FXML
    private TextField txtTienKhachDua;
    @FXML
    private TextField txtTimKiemTV;
    @FXML
    private TextField txtTongTien;
    @FXML
    private ComboBox<NhanVien> cbHoaDon;
    @FXML
    private DatePicker dateNhap;
    @FXML
    private TableView<KhachHang> tbvKhachHang;
    @FXML
    private TableColumn<KhachHang, Integer> colMaKhachHang;
    @FXML
    private TableColumn<KhachHang, String> colHoKhachHang;
    @FXML
    private TableColumn<KhachHang, String> colTenKhachHang;
    @FXML
    private TableColumn<KhachHang, DatePicker> colNgaySinh;
    @FXML
    private TableColumn<KhachHang, String> colSoDienThoai;
    @FXML
    private TableView<SanPham> tbvSanPham;
    @FXML
    private TableView<SanPham> tbvSanPham1;
    @FXML
    private TableColumn<SanPham, String> colTenSP;
    @FXML
    private TableColumn<SanPham, Integer> colSoLuongSP;
    @FXML
    private TableColumn<SanPham, Double> colGiaTienSP;
    @FXML
    private TableColumn<SanPham, DatePicker> colNgaySanXuat;
    @FXML
    private TableColumn<SanPham, String> colTenSP1;
    @FXML
    private TableColumn<SanPham, Integer> colSoLuongSP1;
    @FXML
    private TableColumn<SanPham, Double> colGiaTienSP1;
    @FXML
    private TableColumn<SanPham, DatePicker> colNgaySanXuat1;
    private ObservableList<SanPham> productList;
    @FXML
    private Button btndeleteSP;
    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    Pattern pattern = Pattern.compile("[0-9]*");

    public void LoadData() throws SQLException {
        KhachHangServices khS = new KhachHangServices();
        this.tbvKhachHang.setItems(FXCollections.observableList(khS.getKhachHang()));
        SanPhamServices spS = new SanPhamServices();
        this.tbvSanPham.setItems(FXCollections.observableList(spS.getSanPham()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadTableViewKH();
            this.loadTableViewSanPham();
            txtTongTien.setEditable(false);
            txtUuDai.setEditable(false);
            txtTienThua.setEditable(false);
            NhanVienServices nvs = new NhanVienServices();
            this.cbHoaDon.setItems(FXCollections.observableArrayList(nvs.getNhanVien()));

            KhachHangServices khS = new KhachHangServices();
            this.tbvKhachHang.setItems(FXCollections.observableList(khS.getKhachHang()));

            SanPhamServices spS = new SanPhamServices();
            this.tbvSanPham.setItems(FXCollections.observableList(spS.getSanPham()));
            TextFormatter<TextFormatter.Change> formatter = new TextFormatter<>(change -> {
                String newText = change.getControlNewText();
                if (pattern.matcher(newText).matches()) {
                    return change;
                } else {
                    return null;
                }
            });
            txtTienKhachDua.setTextFormatter(formatter);
            TinhTienKH();
            loadSearchKH();
            TinhTienThua();
            ApDungUuDai();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLChiNhanhController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TinhTienThua() {
        txtTienKhachDua.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateChange();
        });
        txtTongTien.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateChange();
        });
    }

    private void calculateChange() {
        if (!txtTienKhachDua.getText().isBlank() && !txtTongTien.getText().isBlank()) {
            
            double received = Double.parseDouble(txtTienKhachDua.getText());
            double total = Double.parseDouble(txtTongTien.getText());

            if (received > total) {
                double change = received - total;
                decimalFormat.setGroupingSize(3);
                decimalFormat.setGroupingUsed(true);
                txtTienThua.setText(decimalFormat.format(change));
            }
        } else {

            txtTienThua.setText(String.valueOf(0));
        }
    }

    public void ApDungUuDai() {
        tbvKhachHang.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtUuDai.setText("10%");
                double total = Double.parseDouble(txtTongTien.getText());
                double newValue = total * 0.9;
                txtTongTien.setText(String.format("%.1f", newValue));
            }
        });
    }

    public void TinhTienKH() {
        tbvSanPham.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tbvSanPham1.getItems().add(newSelection);
                loadTableViewSanPham1();
            }
        });
        productList = FXCollections.observableArrayList(tbvSanPham1.getItems());
        tbvSanPham1.setItems(productList);
        productList.addListener((ListChangeListener<SanPham>) change -> {
            double sum = 0.0;
            for (SanPham product : tbvSanPham1.getItems()) {
                sum += product.getGiatien();
            }

            txtTongTien.setText(String.format("%.1f", sum));
            if (productList.isEmpty()) {
                txtTongTien.setText("0");
            }
        });
        btndeleteSP.setOnAction(event -> {
            tbvSanPham1.getItems().clear();
            txtTongTien.setText("0");
            txtUuDai.setText("");
            calculateChange();
        });
    }

    public void loadSearchKH() {
        this.txtTimKiemTV.textProperty().addListener((evt) -> {
            try {
                if (!txtTimKiemTV.getText().isEmpty()) {
                    this.loadKH(Integer.parseInt(this.txtTimKiemTV.getText()));
                } else {
                    loadTableData();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLHoaDonConroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void loadTableViewKH() {
        colMaKhachHang.setPrefWidth(100);
        colMaKhachHang.setCellValueFactory(new PropertyValueFactory("makhachhang"));

        colHoKhachHang.setPrefWidth(100);
        colHoKhachHang.setCellValueFactory(new PropertyValueFactory("hokhachhang"));

        colTenKhachHang.setPrefWidth(100);
        colTenKhachHang.setCellValueFactory(new PropertyValueFactory("tenkhachhang"));

        colNgaySinh.setPrefWidth(100);
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaysinh"));

        colSoDienThoai.setPrefWidth(100);
        colSoDienThoai.setCellValueFactory(new PropertyValueFactory("sodienthoai"));

    }

    public void loadTableViewSanPham() {
        colTenSP.setPrefWidth(180);
        colTenSP.setCellValueFactory(new PropertyValueFactory("tensanpham"));

        colSoLuongSP.setPrefWidth(180);
        colSoLuongSP.setCellValueFactory(new PropertyValueFactory("soluong"));

        colGiaTienSP.setPrefWidth(180);
        colGiaTienSP.setCellValueFactory(new PropertyValueFactory("giatien"));

        colNgaySanXuat.setPrefWidth(180);
        colNgaySanXuat.setCellValueFactory(new PropertyValueFactory("ngaysanxuat"));
    }

    public void loadTableViewSanPham1() {
        colTenSP1.setPrefWidth(180);
        colTenSP1.setCellValueFactory(new PropertyValueFactory("tensanpham"));

        colSoLuongSP1.setPrefWidth(180);
        colSoLuongSP1.setCellValueFactory(new PropertyValueFactory("soluong"));

        colGiaTienSP1.setPrefWidth(180);
        colGiaTienSP1.setCellValueFactory(new PropertyValueFactory("giatien"));

        colNgaySanXuat1.setPrefWidth(180);
        colNgaySanXuat1.setCellValueFactory(new PropertyValueFactory("ngaysanxuat"));
    }

    public void loadTableData() throws SQLException {
//        HoaDonServices hdS = new HoaDonServices();
//        List<HoaDon> hd = hdS.getHoaDon();
//        this.tbvHoaDon.getItems().clear();
//        this.tbvHoaDon.setItems(FXCollections.observableList(hd));
        KhachHangServices khS = new KhachHangServices();
        List<KhachHang> kh = khS.getKhachHang();
        this.tbvKhachHang.getItems().clear();
        this.tbvKhachHang.setItems(FXCollections.observableList(kh));
    }

    public void loadKH(int kw) throws SQLException {
        HoaDonServices hdS = new HoaDonServices();
        this.tbvKhachHang.setItems(FXCollections.observableList(hdS.getKhachHang(kw)));
    }
    public void luuHoaDon(ActionEvent event) throws SQLException {
        
        HoaDonServices hdS = new HoaDonServices();
        HoaDon hd = new HoaDon(this.cbHoaDon.getSelectionModel().getSelectedItem().getLastname(), Double.parseDouble(txtTongTien.getText()),Date.valueOf(dateNhap.getValue()));
        hdS.addHoaDon(hd);
        MessageBox.getBox("Success", "Đã Lưu hóa đơn", Alert.AlertType.CONFIRMATION).showAndWait();
    }

}
