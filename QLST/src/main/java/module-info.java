module com.nat.qlst {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    opens com.nat.qlst to javafx.fxml;
    exports com.nat.qlst;
    exports com.nat.pojo;
}
