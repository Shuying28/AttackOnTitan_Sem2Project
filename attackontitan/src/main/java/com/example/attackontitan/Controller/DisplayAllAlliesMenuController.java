package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.example.attackontitan.AOTCharacter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/** 2.1 Basic Feature */
public class DisplayAllAlliesMenuController extends CheckAllAlliesMenuController implements Initializable  {
    @FXML
    private Stage stage;
    @FXML
    private Button btBackToPrevious;
    @FXML
    private Label allAlliesLabel = new Label();
    @FXML
    private TableView<AOTCharacter> tableView;
    @FXML
    private TableColumn<Character, Integer> agility;
    @FXML
    private TableColumn<Character, Integer> coordination;
    @FXML
    private TableColumn<Character, Integer> height;
    @FXML
    private TableColumn<Character, Integer> intelligence;
    @FXML
    private TableColumn<Character, Integer> leadership;
    @FXML
    private TableColumn<Character, String> name;
    @FXML
    private TableColumn<Character, Integer> strength;
    @FXML
    private TableColumn<Character, Integer> weight;
    @FXML
    private ImageView backgroundView;
    private MainApplication main;
    private ObservableList<AOTCharacter> observableList = FXCollections.observableList(getCharactersList());

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToCheckAlliesMenu();
    }

    String path10 = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media10 = new Media (new File(path10).toURI().toString());
    MediaPlayer mediaPlayer10 = new MediaPlayer(media10);

    private void setBtBackToPrevious(ActionEvent event) {
        mediaPlayer10.play();
        try {
            main.goToCheckAlliesMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btBackToPrevious.setOnAction(this::setBtBackToPrevious);

        // Styling
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\CheckAlliessss.png").toURI().toString()));
        btBackToPrevious.setBackground(new Background(new BackgroundFill(Color.GOLD,null,null)));

        // Read from database
        readFromTextFile();

        // Set each table cell to display respective value
        name.setCellValueFactory(new PropertyValueFactory<Character, String>("name"));
        height.setCellValueFactory(new PropertyValueFactory<Character, Integer>("height"));
        weight.setCellValueFactory(new PropertyValueFactory<Character, Integer>("weight"));
        strength.setCellValueFactory(new PropertyValueFactory<Character, Integer>("strength"));
        agility.setCellValueFactory(new PropertyValueFactory<Character, Integer>("agility"));
        intelligence.setCellValueFactory(new PropertyValueFactory<Character, Integer>("intelligence"));
        coordination.setCellValueFactory(new PropertyValueFactory<Character, Integer>("coordination"));
        leadership.setCellValueFactory(new PropertyValueFactory<Character, Integer>("leadership"));
        tableView.setItems(observableList);
    }

}
