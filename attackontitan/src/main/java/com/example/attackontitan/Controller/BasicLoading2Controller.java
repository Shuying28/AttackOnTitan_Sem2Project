package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/** 2.1 Loading page 2 */
public class BasicLoading2Controller implements Initializable {
    private MainApplication main;
    @FXML
    private Button btNext;
    @FXML
    private ImageView backgroundView ;
    @FXML
    private Label TitanNumLabel;
    private static SelectSoldierController selectSoldierController;


    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void goToEvaluateTitanMenu() throws Exception {
        main.goToEvaluateTitanMenu();
    }
    public void setSelectSoldier(SelectSoldierController select){
        selectSoldierController = select;
    }

    String path6 = "C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media6 = new Media (new File(path6).toURI().toString());
    MediaPlayer mediaPlayer6 = new MediaPlayer(media6);

    private void setBtNext(ActionEvent event) {
        mediaPlayer6.play();
        try {
            main.goToEvaluateTitanMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TitanNumLabel.setText(String.valueOf(selectSoldierController.getTitanNumber()));
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\gif2.gif").toURI().toString()));
        btNext.setOnAction(this::setBtNext);

    }
}
