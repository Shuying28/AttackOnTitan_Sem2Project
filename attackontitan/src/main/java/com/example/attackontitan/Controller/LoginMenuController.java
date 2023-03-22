package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginMenuController implements Initializable {
    @FXML
    private Button btCheckAllies;
    @FXML
    private Button btArrangeSoldiers;
    @FXML
    private Button btEvaluateTitanAndKillingPriority;
    @FXML
    private Button btCarryOutMissionInsideTheWall;
    @FXML
    private Button btDetermineTheBestPathToKillTitan;
    @FXML
    private Button btConvertMarleyWord;
    @FXML
    private Button btProtectWallOfMaria;
    private MainApplication main;

    public void setApp(MainApplication main){
        this.main = main;
    }

    public void goToCheckAlliesMenu() throws Exception {
        main.goToCheckAlliesMenu();
    }

    public void goToArrangeSoldierAndGroupingMenu() throws Exception {
        main.goToArrangeSoldierAndGroupingMenu();
    }

    public void goToEvaluateTitanMenu() throws Exception {
        main.goToEvaluateTitanMenu();
    }

    public void goToCarryOutMissionMenu() throws Exception {
        main.goToCarryOutMissionMenu();
    }

    public void goToDetermineBestPathMenu() throws Exception {
        main.goToDetermineBestPathMenu();
    }

    public void goToConvertMarleyWordMenu() throws Exception {
        main.goToConvertMarleyWordMenu();
    }

    public void goToProtectWallOfMariaMenu() throws Exception {
        main.goToProtectWallOfMariaMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
