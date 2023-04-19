module com.nat.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    opens com.nat.qlst to javafx.fxml;
    exports com.nat.qlst;
    exports com.nat.pojo;
    exports com.nat.services;
    exports com.nat.Utils;
}
