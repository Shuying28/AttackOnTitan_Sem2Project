package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/** 2.1 Loading page 1 */
public class BasicLoadingController implements Initializable {
    private MainApplication main;
    @FXML
    private Button btNext;
    @FXML
    private ImageView backgroundView ;


    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void goToAboutTitanPage() throws Exception {
        main.goToAboutTitanPage();
    }

    String path3 = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media3 = new Media (new File(path3).toURI().toString());
    MediaPlayer mediaPlayer3 = new MediaPlayer(media3);

    private void setBtNext(ActionEvent event) {
        mediaPlayer3.play();
        try {
            main.goToAboutTitanPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\gif1.gif").toURI().toString()));
        btNext.setOnAction(this::setBtNext);
    }
}
