package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.css.CssMetaData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {
    private MainApplication main;
    @FXML
    private ImageView backgroundView;
    @FXML
    private Button startButton;

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void goToLoadingPage() throws Exception {
        main.goToLoadingPage();
    }



    String path = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media = new Media (new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    @FXML
    private void setStartButton(ActionEvent event) {
        mediaPlayer.play();
        try {
            main.goToLoadingPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Startbackground.png").toURI().toString()));
        startButton.setOnAction(this::setStartButton);

    }
}
