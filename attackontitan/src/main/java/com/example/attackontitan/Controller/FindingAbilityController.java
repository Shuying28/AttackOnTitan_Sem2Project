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

public class FindingAbilityController extends CheckAllAlliesMenuController implements Initializable {
    @FXML
    private TextField abilityTextField;
    @FXML
    private TextField valueTextField;
    @FXML
    private Button findingButton;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Label abilityErrorLabel;
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

    public void backToPrevious() throws Exception {
        main.goToArrangeSoldierAndGroupingMenu();
    }

    public void find() {
        ability = abilityTextField.getText();
        boolean shouldReturn = false;
        try {
            key = Integer.parseInt(valueTextField.getText());
        } catch (Exception e) {
            valueErrorLabel.setText("Invalid value input! ");
            shouldReturn = true;
        }
        AOTCharacter[] characters = new AOTCharacter[charactersList.size()];
        int[] abilityValues = new int[charactersList.size()];

        for (int i = 0; i < characters.length; i++) {
            characters[i] = charactersList.get(i);
            switch (ability) {
                case "Height" -> abilityValues[i] = characters[i].getHeight();
                case "Weight" -> abilityValues[i] = characters[i].getWeight();
                case "Strength" -> abilityValues[i] = characters[i].getStrength();
                case "Agility" -> abilityValues[i] = characters[i].getAgility();
                case "Intelligence" -> abilityValues[i] = characters[i].getIntelligence();
                case "Coordination" -> abilityValues[i] = characters[i].getCoordination();
                case "Leadership" -> abilityValues[i] = characters[i].getLeadership();
                default -> {
                    abilityErrorLabel.setText("Invalid ability!");
                    resultLabel.setText("");
                    shouldReturn = true;
                }
            }
        }

        if(shouldReturn) {
            return;
        }

        boolean needNextPass = true;
        for (int i = 1; i < abilityValues.length && needNextPass; i++) {
            needNextPass = false;
            for (int j = 0; j < abilityValues.length - i; j++) {
                if (abilityValues[j] > abilityValues[j + 1]) {
                    int temp = abilityValues[j];
                    abilityValues[j] = abilityValues[j + 1];
                    abilityValues[j + 1] = temp;
                    AOTCharacter tempCharacter = characters[j];
                    characters[j] = characters[j + 1];
                    characters[j + 1] = tempCharacter;
                    needNextPass = true;
                }
            }
        }

        StringBuilder result = new StringBuilder("Soldier: ");
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
                while(true) {
                    if (key == abilityValues[leftIndex]) {
                        result.append(", ").append(characters[leftIndex].getName());
                    }
                    else {
                        break;
                    }
                    leftIndex--;
                }
                while (true) {
                    if (key == abilityValues[rightIndex]) {
                        result.append(", ").append(characters[rightIndex].getName());
                    }
                    else {
                        break;
                    }
                    rightIndex++;
                }
                break;
            }
            else {
                low = mid + 1;
            }
        }
        if (!isFound) {
            resultLabel.setText("No matching!");
        }
        else {
            abilityErrorLabel.setText("");
            valueErrorLabel.setText("");
            resultLabel.setText(result.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readFromTextFile();
        abilityErrorLabel.setText("");
        valueErrorLabel.setText("");
        resultLabel.setText("");
    }

}
