package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;
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
    @FXML
    private ImageView backgroundView;
    @FXML
    private Button btBackToStartPage;

    private MainApplication main;

    String path13 = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media13 = new Media (new File(path13).toURI().toString());
    MediaPlayer mediaPlayer13 = new MediaPlayer(media13);

    public void setApp(MainApplication main){
        this.main = main;
    }

    //Start Page
    public void goToStartPage(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToStartPage();
    }

    //2.1
    public void goToCheckAlliesMenu(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToCheckAlliesMenu();
    }

    //2.2
    public void goToArrangeSoldierAndGroupingMenu(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToArrangeSoldierAndGroupingMenu();
    }

    //2.3 Upper Part
    public void goToSelectSoldier(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToSelectSoldier();
    }

    //2.3 Lower Part
    public void goToCarryOutMissionMenu(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToCarryOutMissionMenu();
    }

    //2.4
    public void goToDetermineBestPathMenu(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToDetermineBestPathMenu();
    }

    //2.5
    public void goToConvertMarleyWordMenu(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToConvertMarleyWordMenu();
    }

    //2.6
    public void goToProtectWallOfMariaMenu(ActionEvent event) throws Exception {
        mediaPlayer13.play();
        main.goToProtectWallOfMariaMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Menubackground.png").toURI().toString()));
    }

}
