package com.example.attackontitan;

import com.example.attackontitan.Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    Group root = new Group();
    Scene scene = new Scene(root);
    Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Attack On Titan 進撃の巨人");
        primaryStage.setScene(scene);
        this.stage = primaryStage;
        goToLoginMenu();
        stage.show();
    }

    public void goToLoginMenu() throws Exception{
        LoginMenuController loginMenuController = (LoginMenuController)loadScene("LoginMenu.fxml");
        loginMenuController.setApp(this);
    }

    public void goToCheckAlliesMenu() throws Exception{
        CheckAllAlliesMenuController checkAllAlliesMenuController = (CheckAllAlliesMenuController) loadScene("CheckAlliesMenu.fxml");
        checkAllAlliesMenuController.setApp(this);
    }

    public void goToArrangeSoldierAndGroupingMenu() throws Exception{
        ArrangeSoldierAndGroupingMenuController arrangeSoldierAndGroupingMenuController = (ArrangeSoldierAndGroupingMenuController) loadScene("ArrangeSoldierAndGroupingMenu.fxml");
        arrangeSoldierAndGroupingMenuController.setApp(this);
    }

    public void goToEvaluateTitanMenu() throws Exception{
        EvaluateTitanMenuController evaluateTitanMenuController = (EvaluateTitanMenuController) loadScene("EvaluateTitanMenu.fxml");
        evaluateTitanMenuController.setApp(this);
    }

    public void goToCarryOutMissionMenu() throws Exception{
        CarryOutMissionMenuController carryOutMissionMenuController = (CarryOutMissionMenuController) loadScene("CarryOutMissionMenu.fxml");
        carryOutMissionMenuController.setApp(this);
    }

    public void goToDetermineBestPathMenu() throws Exception{
        DetermineBestPathMenuController determineBestPathMenuController = (DetermineBestPathMenuController) loadScene("DetermineBestPathMenu.fxml");
        determineBestPathMenuController.setApp(this);
    }

    public void goToConvertMarleyWordMenu() throws Exception{
        ConvertMarleyWordMenuController convertMarleyWordMenuController = (ConvertMarleyWordMenuController) loadScene("ConvertMarleyWordMenu.fxml");
        convertMarleyWordMenuController.setApp(this);
    }

    public void goToProtectWallOfMariaMenu() throws Exception{
        ProtectWallOfMariaMenuController protectWallOfMariaMenuController = (ProtectWallOfMariaMenuController) loadScene("ProtectWallOfMariaMenu.fxml");
        protectWallOfMariaMenuController.setApp(this);
    }

    public void goToDisplayAllAlliesMenu() throws Exception{
        DisplayAllAlliesMenuController displayAllAlliesMenuController = (DisplayAllAlliesMenuController) loadScene("DisplayAllAlliesMenu.fxml");
        displayAllAlliesMenuController.setApp(this);
    }

    public void goToFindingAbilityMenu() throws Exception{
        FindingAbilityController findingAbilityController = (FindingAbilityController) loadScene("FindingAbilityMenu.fxml");
        findingAbilityController.setApp(this);
    }

    private Initializable loadScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        scene.setRoot(loader.load(MainApplication.class.getResourceAsStream(fxml)));
        return loader.getController();
    }
}