package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.attackontitan.AOTCharacter;

import java.net.URL;
import java.util.ResourceBundle;

public class ArrangeSoldierAndGroupingMenuController extends CheckAllAlliesMenuController implements Initializable {
    @FXML
    private TextField attributeTextField;
    @FXML
    private Button sortingButton;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Label errorLabel;
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

    public void sort() throws Exception {
        attribute = attributeTextField.getText();
        AOTCharacter[] characters = new AOTCharacter[charactersList.size()];
        for (int i = 0; i < characters.length; i++) {
            characters[i] = charactersList.get(i);
        }

        for (int i = 0; i < characters.length - 1; i++) {
            AOTCharacter currentCharacter = characters[i];

            int currentMax = 0;
            switch (attribute) {
                case "Height" -> currentMax = currentCharacter.getHeight();
                case "Weight" -> currentMax = currentCharacter.getWeight();
                case "Strength" -> currentMax = currentCharacter.getStrength();
                case "Agility" -> currentMax = currentCharacter.getAgility();
                case "Intelligence" -> currentMax = currentCharacter.getIntelligence();
                case "Coordination" -> currentMax = currentCharacter.getCoordination();
                case "Leadership" -> currentMax = currentCharacter.getLeadership();
                default -> {
                    errorLabel.setText("Invalid attribute!");
                    return;
                }
            }
            errorLabel.setText("");
            int currentMaxIndex = i;
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
                if (currentMax < temp) {
                    currentCharacter = characters[j];
                    currentMax = temp;
                    currentMaxIndex = j;
                }
            }
            if (currentMaxIndex != i) {
                characters[currentMaxIndex] = characters[i];
                characters[i] = currentCharacter;
            }
        }
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
                case "Height" -> stringBuilder.append(c.getName()).append(" ").append(c.getHeight()).append("\n");
                case "Weight" -> stringBuilder.append(c.getName()).append(" ").append(c.getWeight()).append("\n");
                case "Strength" -> stringBuilder.append(c.getName()).append(" ").append(c.getStrength()).append("\n");
                case "Agility" -> stringBuilder.append(c.getName()).append(" ").append(c.getAgility()).append("\n");
                case "Intelligence" -> stringBuilder.append(c.getName()).append(" ").append(c.getIntelligence()).append("\n");
                case "Coordination" -> stringBuilder.append(c.getName()).append(" ").append(c.getCoordination()).append("\n");
                case "Leadership" -> stringBuilder.append(c.getName()).append(" ").append(c.getLeadership()).append("\n");
            }
        }
        resultLabel.setText(String.valueOf(stringBuilder));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readFromTextFile();
        errorLabel.setText("");
        resultLabel.setText("");
    }

}
