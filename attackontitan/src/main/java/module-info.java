module com.example.attackontitan {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.attackontitan to javafx.fxml;
    exports com.example.attackontitan;
    exports com.example.attackontitan.Controller;
    opens com.example.attackontitan.Controller to javafx.fxml;
}