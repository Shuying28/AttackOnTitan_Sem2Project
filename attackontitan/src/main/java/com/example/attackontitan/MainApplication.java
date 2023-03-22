package com.example.attackontitan;

import com.example.attackontitan.Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/** Main Application */
public class MainApplication extends Application {
    Group root = new Group();
    Scene scene = new Scene(root,950,550);
    public Stage stage;

    public static void main(String[] args) {
        launch();
    }

    String path = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\music (1).mp3";
    Media media = new Media (new File(path).toURI().toString());
    MediaPlayer mediaPlayer1 = new MediaPlayer(media);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Attack On Titan 進撃の巨人");
        mediaPlayer1.play();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        this.stage = primaryStage;
        goToStartPage();
        stage.show();
    }

    //Start Page
    public void goToStartPage() throws Exception {
        StartPageController startPageController = (StartPageController) loadScene("StartPageMenu.fxml");
        startPageController.setApp(this);
    }
    //Menu Page
    public void goToLoginMenu() throws Exception{
        LoginMenuController loginMenuController = (LoginMenuController)loadScene("LoginMenu.fxml");
        loginMenuController.setApp(this);
    }

    //Loading page
    public void goToLoadingPage() throws Exception{
        BasicLoadingController basicLoadingController=(BasicLoadingController) loadScene("BasicLoading.fxml");
        basicLoadingController.setApp(this);
    }

    //Loading page 2
    public void goToLoadingPage2() throws Exception{
        BasicLoading2Controller basicLoading2Controller=(BasicLoading2Controller) loadScene("BasicLoading2.fxml");
        basicLoading2Controller.setApp(this);
    }

    //About Titan page
    public void goToAboutTitanPage() throws Exception{
        AboutTitanController aboutTitanController = (AboutTitanController) loadScene("AboutTitan.fxml");
        aboutTitanController.setApp(this);
    }

    //2.1 Adding new Allies
    public void goToCheckAlliesMenu() throws Exception{
        CheckAllAlliesMenuController checkAllAlliesMenuController = (CheckAllAlliesMenuController) loadScene("CheckAlliesMenu.fxml");
        checkAllAlliesMenuController.setApp(this);
    }

    //2.1 Display all information of Allies in a Table
    public void goToDisplayAllAlliesMenu() throws Exception{
        DisplayAllAlliesMenuController displayAllAlliesMenuController = (DisplayAllAlliesMenuController) loadScene("DisplayAllAlliesMenu.fxml");
        displayAllAlliesMenuController.setApp(this);
    }

    //2.2 Sorting
    public void goToArrangeSoldierAndGroupingMenu() throws Exception{
        ArrangeSoldierAndGroupingMenuController arrangeSoldierAndGroupingMenuController = (ArrangeSoldierAndGroupingMenuController) loadScene("ArrangeSoldierAndGroupingMenu.fxml");
        arrangeSoldierAndGroupingMenuController.setApp(this);
    }

    //2.2 Finding Ability
    public void goToFindingAbilityMenu() throws Exception{
        FindingAbilityController findingAbilityController = (FindingAbilityController) loadScene("FindingAbilityMenu.fxml");
        findingAbilityController.setApp(this);
    }

    //2.3 Upper Part (Killing Priority)
    public void goToEvaluateTitanMenu() throws Exception{
        EvaluateTitanMenuController evaluateTitanMenuController = (EvaluateTitanMenuController) loadScene("EvaluateTitanMenu.fxml");
        evaluateTitanMenuController.setApp(this);
    }
    //2.3 Upper Part + Extra feature
    public void goToSelectSoldier() throws Exception{
        SelectSoldierController selectSoldierController = (SelectSoldierController) loadScene("SelectSoldier.fxml");
        selectSoldierController.setApp(this);
    }

    //2.3 Lower Part (HamiltonianCycle)
    public void goToCarryOutMissionMenu() throws Exception{
        CarryOutMissionMenuController carryOutMissionMenuController = (CarryOutMissionMenuController) loadScene("CarryOutMissionMenu.fxml");
        carryOutMissionMenuController.setApp(this);
    }

    //2.4 Extra feature (BFS)
    public void goToDetermineBestPathMenu() throws Exception{
        DetermineBestPathMenuController determineBestPathMenuController = (DetermineBestPathMenuController) loadScene("DetermineBestPathMenu.fxml");
        determineBestPathMenuController.setApp(this);
    }

    //2.5 + Extra feature
    public void goToConvertMarleyWordMenu() throws Exception{
        ConvertMarleyWordMenuController convertMarleyWordMenuController = (ConvertMarleyWordMenuController) loadScene("ConvertMarleyWordMenu.fxml");
        convertMarleyWordMenuController.setApp(this);
    }

    //2.6 Maria Wall
    public void goToProtectWallOfMariaMenu() throws Exception{
        ProtectWallOfMariaMenuController protectWallOfMariaMenuController = (ProtectWallOfMariaMenuController) loadScene("ProtectWallOfMariaMenu.fxml");
        protectWallOfMariaMenuController.setApp(this);
    }

    public Initializable loadScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        scene.setRoot(loader.load(MainApplication.class.getResourceAsStream(fxml)));
        return loader.getController();
    }




}