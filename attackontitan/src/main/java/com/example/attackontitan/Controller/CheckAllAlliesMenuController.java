package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.attackontitan.AOTCharacter;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CheckAllAlliesMenuController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField characteristicsTextField;
    @FXML
    private Button addCharacterButton;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button displayAllAlliesButton;
    @FXML
    private Label resultLabel;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label characteristicsErrorLabel;

    private MainApplication main;
    private String name;
    private String characteristics;
    LinkedList<AOTCharacter> charactersList = new LinkedList<>();

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void displayAllAllies() throws Exception {
        main.goToDisplayAllAlliesMenu();
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    public void addCharacter() throws Exception {
        try {
            name = nameTextField.getText();
            characteristics = characteristicsTextField.getText();
            boolean shouldReturn = false;

            if (!isMatchingNameInputFormat(name)) {
                nameErrorLabel.setText("Incorrect format for name!");
                shouldReturn = true;
            } else {
                nameErrorLabel.setText("");
            }

            if (!isMatchingCharacteristicsStringInputFormat(characteristics)) {
                characteristicsErrorLabel.setText("Incorrect format for characteristics!");
                shouldReturn = true;
            } else {
                characteristicsErrorLabel.setText("");
            }

            if (shouldReturn)
                return;

            if (!isRepeated(name)) {
                String[] values = characteristics.split(" ");
                int height = Integer.parseInt(values[0]);
                int weight = Integer.parseInt(values[1]);
                int strength = Integer.parseInt(values[2]);
                int agility = Integer.parseInt(values[3]);
                int intelligence = Integer.parseInt(values[4]);
                int coordination = Integer.parseInt(values[5]);
                int leadership = Integer.parseInt(values[6]);
                if (height < 100 || height > 200 ||
                        weight < 40 || weight > 80 ||
                        strength < 1 || strength > 12 ||
                        agility < 1 || agility > 12 ||
                        intelligence < 1 || intelligence > 12 ||
                        coordination < 1 || coordination > 12 ||
                        leadership < 1 || leadership > 12) {
                    characteristicsErrorLabel.setText("Invalid value(s)!");
                    return;
                }
                storeToTextFile(name, characteristics);
                AOTCharacter AOTCharacter = new AOTCharacter(name, height, weight, strength, agility, intelligence, coordination, leadership);
                charactersList.add(AOTCharacter);
                String result = "Name: " + name
                        + "\nHeight: " + height + "cm"
                        + "\nWeight: " + weight + "kg"
                        + "\nStrength: " + strength
                        + "\nAgility: " + agility
                        + "\nIntelligence: " + intelligence
                        + "\nCoordination: " + coordination
                        + "\nLeadership: " + leadership;
                resultLabel.setText(result);
            }
            else {
                resultLabel.setText("Character name is already inside the database!");
            }
        } catch (Exception e) {
            resultLabel.setText(String.valueOf(e));
        }
    }

    private boolean isMatchingNameInputFormat(String name) {
        return name.matches("([A-Z]\\w+\\s*) ([A-Z]\\w+\\s*)");
    }

    private  boolean isMatchingCharacteristicsStringInputFormat(String characteristicsString) {
        return characteristicsString.matches("\\d+ \\d+ \\d+ \\d+ \\d+ \\d+ \\d+");
    }

    private  boolean isRepeated(String name) throws FileNotFoundException {
        try {
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\Asus\\IdeaProjects\\attackontitan\\src\\characters.txt"));
            while (input.hasNextLine()) {
                String checkedName = input.nextLine();
                input.nextLine();
                if (checkedName.equals(name)) {
                    input.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return false;
    }

    private boolean storeToTextFile(String name, String characteristics) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("C:\\Users\\Asus\\IdeaProjects\\attackontitan\\src\\characters.txt", true));
            writer.println(name);
            writer.println(characteristics);
            writer.close();
            return true;
        } catch (IOException e) {
            throw e;
        }
    }

    protected void readFromTextFile() {
        try {
            Scanner read = new Scanner(new FileInputStream("C:\\Users\\Asus\\IdeaProjects\\attackontitan\\src\\characters.txt"));
            while (read.hasNextLine()) {
                String name = read.nextLine();
                String[] attributes = read.nextLine().split(" ");
                int height = Integer.parseInt(attributes[0]);
                int weight = Integer.parseInt(attributes[1]);
                int strength = Integer.parseInt(attributes[2]);
                int agility = Integer.parseInt(attributes[3]);
                int intelligence = Integer.parseInt(attributes[4]);
                int coordination = Integer.parseInt(attributes[5]);
                int leadership = Integer.parseInt(attributes[6]);
                AOTCharacter AOTCharacter = new AOTCharacter(name, height, weight, strength, agility, intelligence, coordination, leadership);
                charactersList.add(AOTCharacter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        readFromTextFile();
        nameErrorLabel.setText("");
        characteristicsErrorLabel.setText("");
        resultLabel.setText("");
    }
}
