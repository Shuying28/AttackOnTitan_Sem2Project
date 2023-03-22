package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DetermineBestPathMenuController implements Initializable {
    @FXML
    private Text enterLocationOfTitan;
    @FXML
    private TextField locationOfTitanEnteredByUser;
    @FXML
    private Button btDetermine;
    @FXML
    private Button btBackToPrevious;
    private MainApplication main;

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
