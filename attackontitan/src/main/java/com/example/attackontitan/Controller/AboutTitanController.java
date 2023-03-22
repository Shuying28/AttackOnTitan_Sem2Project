package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/** 2.1 About Titan Application */
public class AboutTitanController implements Initializable {
    private MainApplication main;
    @FXML
    private Button btNext;
    @FXML
    private Label typewriter;
    @FXML
    private ImageView backgroundView;


    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void goToLoginMenu() throws Exception {
        main.goToLoginMenu();
    }

    public void AnimateText(Label lbl, String text) {
        String str = text;
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(8000));
            }

            protected void interpolate(double frac) {
                final int length = text.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(text.substring(0, n));
            }
        };
        animation.play();
    }

    String path2 = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media2 = new Media (new File(path2).toURI().toString());
    MediaPlayer mediaPlayer2 = new MediaPlayer(media2);

    private void setBtNext(ActionEvent event) {
        mediaPlayer2.play();
        try {
            main.goToLoginMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btNext.setOnAction(this::setBtNext);
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\StartKilling2.jpg").toURI().toString()));
        AnimateText(typewriter, "Several  hundred  years ago,  humanity  was  driven to the brink of extinction by the \n" +
                "humanoid giants called  Titans, who apparently have no intelligence, and only attack \n" +
                "and eat humans on sight. A small portion  of  humans  retreated  and formed a country \n" +
                "behind extremely high walls,  known as Paradis.  Eren Yeager,  a young man,  suffered \n" +
                "his childhood without his mother's love because she was eaten by a titan in one titan \n" +
                "ambush  incident.  Since then,  he  decided to join the country’s military forces and \n" +
                "confront his fate to eliminate titans from the world.");
    }
}