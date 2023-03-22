package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import com.example.attackontitan.AOTCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/** 2.2 Basic Feature */
public class ArrangeSoldierAndGroupingMenuController extends CheckAllAlliesMenuController implements Initializable {
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button findAbility;
    @FXML
    private Label resultLabel;

    private String attribute;
    private MainApplication main;

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }
    public void findingAbility() throws Exception {
        main.goToFindingAbilityMenu();
    }
    @FXML
    private ImageView backgroundView;
    @FXML
    private ImageView frame;
    @FXML
    private ChoiceBox<String> attributeChoice;

    private String[] choices = {"Height", "Weight", "Strength", "Agility", "Intelligence", "Coordination", "Leadership"};

    @FXML
    // Selection sort
    public void sort(MouseEvent event) {
        attribute = attributeChoice.getSelectionModel().getSelectedItem();

        //Create AOTCharacters array to store AOTCharacters
        AOTCharacter[] characters = new AOTCharacter[getCharactersList().size()];

        // Get characters from linked list and store inside array
        for (int i = 0; i < characters.length; i++) {
            characters[i] = getCharactersList().get(i);
        }

        // Loop through all characters
        for (int i = 0; i < characters.length - 1; i++) {
            // store currentCharacter
            AOTCharacter currentCharacter = characters[i];

            // store currentMax value according to input attribute
            int currentMax = 0;
            switch (attribute) {
                case "Height" -> currentMax = currentCharacter.getHeight();
                case "Weight" -> currentMax = currentCharacter.getWeight();
                case "Strength" -> currentMax = currentCharacter.getStrength();
                case "Agility" -> currentMax = currentCharacter.getAgility();
                case "Intelligence" -> currentMax = currentCharacter.getIntelligence();
                case "Coordination" -> currentMax = currentCharacter.getCoordination();
                case "Leadership" -> currentMax = currentCharacter.getLeadership();
            }

            // store currentMaxIndex
            int currentMaxIndex = i;

            // Check the remaining elements of array
            for (int j = i + 1; j < characters.length; j++) {
                int temp = 0;
                switch (attribute) {
                    case "Height" -> temp = characters[j].getHeight();
                    case "Weight" -> temp = characters[j].getWeight();
                    case "Strength" -> temp = characters[j].getStrength();
                    case "Agility" -> temp = characters[j].getAgility();
                    case "Intelligence" -> temp = characters[j].getIntelligence();
                    case "Coordination" -> temp = characters[j].getCoordination();
                    case "Leadership" -> temp = characters[j].getLeadership();
                }

                // if a larger is found, then reassign the variables
                if (currentMax < temp) {
                    currentCharacter = characters[j];
                    currentMax = temp;
                    currentMaxIndex = j;
                }
            }

            // if currentMaxIndex is changed, then swap the characters
            if (currentMaxIndex != i) {
                characters[currentMaxIndex] = characters[i];
                characters[i] = currentCharacter;
            }
        }

        // Append displaying result
        StringBuilder stringBuilder = new StringBuilder();
        switch (attribute) {
            case "Height" -> stringBuilder.append("Height").append("\n");
            case "Weight" -> stringBuilder.append("Weight").append("\n");
            case "Strength" -> stringBuilder.append("Strength").append("\n");
            case "Agility" -> stringBuilder.append("Agility").append("\n");
            case "Intelligence" -> stringBuilder.append("Intelligence").append("\n");
            case "Coordination" -> stringBuilder.append("Coordination").append("\n");
            case "Leadership" -> stringBuilder.append("Leadership").append("\n");
        }

        for (AOTCharacter c : characters) {
            switch (attribute) {
                case "Height" -> stringBuilder.append(c.getName()).append(" -- ").append(c.getHeight()).append("\n");
                case "Weight" -> stringBuilder.append(c.getName()).append(" -- ").append(c.getWeight()).append("\n");
                case "Strength" -> stringBuilder.append(c.getName()).append(" -- ").append(c.getStrength()).append("\n");
                case "Agility" -> stringBuilder.append(c.getName()).append(" -- ").append(c.getAgility()).append("\n");
                case "Intelligence" -> stringBuilder.append(c.getName()).append(" -- ").append(c.getIntelligence()).append("\n");
                case "Coordination" -> stringBuilder.append(c.getName()).append(" -- ").append(c.getCoordination()).append("\n");
                case "Leadership" -> stringBuilder.append(c.getName()).append(" -- ").append(c.getLeadership()).append("\n");
            }
        }

        // Display result
        resultLabel.setText(String.valueOf(stringBuilder));
    }

    String path4 = "C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media4 = new Media (new File(path4).toURI().toString());
    MediaPlayer mediaPlayer4 = new MediaPlayer(media4);

    private void setBackToPreviousButton(ActionEvent event) {
        mediaPlayer4.play();
        try {
            main.goToLoginMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFindAbility(ActionEvent event) {
        mediaPlayer4.play();
        try {
            main.goToFindingAbilityMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\ArrangeBackground.jpg").toURI().toString()));
        frame.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\convertFrame.png").toURI().toString()));
        attributeChoice.getItems().addAll(choices);
        readFromTextFile();
        resultLabel.setText("");
        findAbility.setOnAction(this::setFindAbility);
        backToPreviousButton.setOnAction(this::setBackToPreviousButton);
    }

}