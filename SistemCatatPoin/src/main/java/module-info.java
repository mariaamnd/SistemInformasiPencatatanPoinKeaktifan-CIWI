module com.mycompany.sistemcatatpoin {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.sistemcatatpoin to javafx.fxml;
    exports com.mycompany.sistemcatatpoin;
}
