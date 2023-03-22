package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.attackontitan.AOTCharacter;

import java.net.URL;
import java.util.ResourceBundle;

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
    private MainApplication main;
    private ObservableList<AOTCharacter> observableList = FXCollections.observableList(charactersList);

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToCheckAlliesMenu();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readFromTextFile();
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
