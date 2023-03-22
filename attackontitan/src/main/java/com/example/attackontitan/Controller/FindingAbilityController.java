package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.attackontitan.AOTCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/** 2.2 Basic Feature */
public class FindingAbilityController extends CheckAllAlliesMenuController implements Initializable {
    @FXML
    private TextField valueTextField;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Label valueErrorLabel;
    @FXML
    private Label resultLabel;

    private String ability;
    private int key;
    private MainApplication main;

    public void setApp(MainApplication main) {
        this.main = main;
    }

    String path12 = "C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media12 = new Media (new File(path12).toURI().toString());
    MediaPlayer mediaPlayer12 = new MediaPlayer(media12);

    public void backToPrevious() throws Exception {
        main.goToArrangeSoldierAndGroupingMenu();
    }

    @FXML
    private ImageView backgroundView;
    @FXML
    private ImageView frame;
    @FXML
    private ChoiceBox<String> abilityChoice;

    private String[] choices = {"Height", "Weight", "Strength", "Agility", "Intelligence", "Coordination", "Leadership"};

    // Find matching ability
    public void find(MouseEvent event) {
        ability = abilityChoice.getSelectionModel().getSelectedItem();
        boolean shouldReturn = false;

        try {
            key = Integer.parseInt(valueTextField.getText());
        } catch (Exception e) {
            // Display error
            // For example, int type is required but String is entered
            valueErrorLabel.setText("Invalid value input! ");
            shouldReturn = true;
        }

        // Create AOTCharacters array and abilityValues array
        AOTCharacter[] characters = new AOTCharacter[getCharactersList().size()];
        int[] abilityValues = new int[getCharactersList().size()];

        for (int i = 0; i < characters.length; i++) {
            // Store respective character from linked list to array
            characters[i] = getCharactersList().get(i);

            // Check which ability is chosen and store respective value
            switch (ability) {
                case "Height" -> abilityValues[i] = characters[i].getHeight();
                case "Weight" -> abilityValues[i] = characters[i].getWeight();
                case "Strength" -> abilityValues[i] = characters[i].getStrength();
                case "Agility" -> abilityValues[i] = characters[i].getAgility();
                case "Intelligence" -> abilityValues[i] = characters[i].getIntelligence();
                case "Coordination" -> abilityValues[i] = characters[i].getCoordination();
                case "Leadership" -> abilityValues[i] = characters[i].getLeadership();
                default -> {
                    resultLabel.setText("");
                    shouldReturn = true;
                }
            }

        }

        if(shouldReturn) {
            return;
        }

        // Sorting using bubble sort
        boolean needNextPass = true;
        for (int i = 1; i < abilityValues.length && needNextPass; i++) {
            // Array may be sorted and next pass not needed
            needNextPass = false;
            for (int j = 0; j < abilityValues.length - i; j++) {
                if (abilityValues[j] > abilityValues[j + 1]) {
                    // swap value
                    int temp = abilityValues[j];
                    abilityValues[j] = abilityValues[j + 1];
                    abilityValues[j + 1] = temp;

                    // swap AOT character
                    AOTCharacter tempCharacter = characters[j];
                    characters[j] = characters[j + 1];
                    characters[j + 1] = tempCharacter;

                    // Next pass still needed
                    needNextPass = true;
                }
            }
        }

        StringBuilder result = new StringBuilder("Soldier:\n\n");

        // Modified binary search to print all matching key
        int low = 0;
        int high = abilityValues.length - 1;
        boolean isFound = false;
        while (high >= low) {
            int mid = (low + high) / 2;
            if (key < abilityValues[mid]) {
                high = mid - 1;
            }
            else if (key == abilityValues[mid]) {
                result.append(characters[mid].getName());
                isFound = true;
                int leftIndex = mid - 1;
                int rightIndex = mid + 1;
                //To prevent out of bound
                if (leftIndex >=0 && rightIndex <= abilityValues.length-1) {

                    // Check leftist until no matching to return all matching value
                    while (leftIndex >=0 && rightIndex <= abilityValues.length-1) {
                        if (key == abilityValues[leftIndex]) {
                            // Append result
                            result.append(", ").append(characters[leftIndex].getName());
                        } else {
                            break;
                        }
                        leftIndex--;
                    }

                    // Check leftist until no matching to return all matching value
                    while (leftIndex >=0 && rightIndex <= abilityValues.length-1) {
                        if (key == abilityValues[rightIndex]) {
                            // Append result
                            result.append(", ").append(characters[rightIndex].getName());
                        } else {
                            break;
                        }
                        rightIndex++;
                    }
                }
                break;
            }
            else {
                low = mid + 1;
            }
        }

        // If not found display no matching
        if (!isFound) {
            resultLabel.setText("No matching!");
        }
        // Else display result
        else {
            valueErrorLabel.setText("");
            resultLabel.setText(result.toString());
        }
    }

    // Initialize, styling and read database
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readFromTextFile();
        valueErrorLabel.setText("");
        resultLabel.setText("");
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\ArrangeBackground.jpg").toURI().toString()));
        frame.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\convertFrame.png").toURI().toString()));
        abilityChoice.getItems().addAll(choices);
    }
}