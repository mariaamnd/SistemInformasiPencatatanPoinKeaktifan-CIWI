module com.mycompany.sistemcatatpoinkeaktifan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
   //requires mysql.connector.java;

    opens com.mycompany.sistemcatatpoinkeaktifan to javafx.fxml;
    exports com.mycompany.sistemcatatpoinkeaktifan;
}
