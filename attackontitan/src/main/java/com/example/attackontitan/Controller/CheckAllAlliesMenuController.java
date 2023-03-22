package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.attackontitan.AOTCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

/** 2.1 Basic Feature */
public class CheckAllAlliesMenuController implements Initializable {

    @FXML
    private ImageView CharView;

    @FXML
    private Button ClickMe;

    @FXML
    private Button addCharacterButton;

    @FXML
    private Button backToPreviousButton;

    @FXML
    private Label characteristicsErrorLabel;
    @FXML
    private Rectangle rectangle;
    @FXML
    private TextField characteristicsTextField;

    @FXML
    private Button displayAllAlliesButton;
    @FXML
    private ImageView backgroundView;
    @FXML
    private Button displayAllAlliesButton11;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label resultLabel;
    private MainApplication main;
    private String name;
    private String characteristics;
    private LinkedList<AOTCharacter> charactersList = new LinkedList<>();

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public LinkedList<AOTCharacter> getCharactersList() {
        return charactersList;
    }

    public void displayAllAllies() throws Exception {
        main.goToDisplayAllAlliesMenu();
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    // Add new character to database
    public void addCharacter() throws Exception {
        try {
            name = nameTextField.getText();
            characteristics = characteristicsTextField.getText();
            boolean shouldReturn = false;

            // Check name input format
            if (!isMatchingNameInputFormat(name)) {
                nameErrorLabel.setText("Incorrect format for name!");
                shouldReturn = true;
            } else {
                nameErrorLabel.setText("");
            }

            // Check characteristics input format
            if (!isMatchingCharacteristicsStringInputFormat(characteristics)) {
                characteristicsErrorLabel.setText("Incorrect format for characteristics!");
                shouldReturn = true;
            } else {
                characteristicsErrorLabel.setText("");
            }

            // Return if either one format is violated
            if (shouldReturn)
                return;

            // Check if the name is repeated in database
            // Ignore characteristics
            if (!isRepeated(name)) {
                String[] values = characteristics.split(" ");
                int height = Integer.parseInt(values[0]);
                int weight = Integer.parseInt(values[1]);
                int strength = Integer.parseInt(values[2]);
                int agility = Integer.parseInt(values[3]);
                int intelligence = Integer.parseInt(values[4]);
                int coordination = Integer.parseInt(values[5]);
                int leadership = Integer.parseInt(values[6]);

                // Check characteristics range value format
                if (height < 100 || height > 200 ||
                        weight < 40 || weight > 100 ||
                        strength < 1 || strength > 12 ||
                        agility < 1 || agility > 12 ||
                        intelligence < 1 || intelligence > 12 ||
                        coordination < 1 || coordination > 12 ||
                        leadership < 1 || leadership > 12) {
                    characteristicsErrorLabel.setText("Invalid value(s)!");
                    return;
                }

                // Store character to text file
                storeToTextFile(name, characteristics);
                AOTCharacter AOTCharacter = new AOTCharacter(name, height, weight, strength, agility, intelligence, coordination, leadership);

                // Add to character linked list
                charactersList.add(AOTCharacter);
                String result = "Name\t\t: " + name
                        + "\nHeight\t\t: " + height + "cm"
                        + "\nWeight\t\t: " + weight + "kg"
                        + "\nStrength\t\t: " + strength
                        + "\nAgility\t\t: " + agility
                        + "\nIntelligence\t: " + intelligence
                        + "\nCoordination\t: " + coordination
                        + "\nLeadership\t\t: " + leadership;
                rectangle.setVisible(true);
                resultLabel.setText(result);
            }
            else {
                // Display repeated character name
                resultLabel.setText("Character name is already inside the database!");
            }
        } catch (Exception e) {
            // Display error when user enter wrong format
            // Suppose String should be entered but somehow int entered and cause exception
            rectangle.setVisible(true);
            resultLabel.setText(String.valueOf(e));
        }
    }

    // Check name input format
    private boolean isMatchingNameInputFormat(String name) {
        return name.matches("([A-Z]\\w+\\s*) ([A-Z]\\w+\\s*)");
    }

    // Check characteristics input format
    private  boolean isMatchingCharacteristicsStringInputFormat(String characteristicsString) {
        return characteristicsString.matches("\\d+ \\d+ \\d+ \\d+ \\d+ \\d+ \\d+");
    }

    // Check if name is repeated
    private  boolean isRepeated(String name) throws FileNotFoundException {
        try {
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\characters.txt"));
            while (input.hasNextLine()) {
                String checkedName = input.nextLine();
                input.nextLine(); // Skip characteristics line
                if (checkedName.equals(name)) {
                    input.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return false; // return false after checking all lines
    }

    // Store to txt file
    private boolean storeToTextFile(String name, String characteristics) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\characters.txt", true));
            writer.println(name); // store name
            writer.println(characteristics); // store characteristics
            writer.close();
            return true;
        } catch (IOException e) {
            throw e;
        }
    }

    // Read from txt file
    protected void readFromTextFile() {
        try {
            Scanner read = new Scanner(new FileInputStream("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\characters.txt"));
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

    String path8 = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media8 = new Media (new File(path8).toURI().toString());
    MediaPlayer mediaPlayer8 = new MediaPlayer(media8);

    private void setBackToPreviousButton(ActionEvent event) {
        mediaPlayer8.play();
        try {
            main.goToLoginMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDisplayAllAlliesButton11(ActionEvent event) {
        mediaPlayer8.play();
        try {
            main.goToDisplayAllAlliesMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Initialize styling
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\CheckAlliessss.png").toURI().toString()));
        rectangle.setVisible(false);
        readFromTextFile();
        nameErrorLabel.setText("");
        characteristicsErrorLabel.setText("");
        resultLabel.setText("");

        backToPreviousButton.setOnAction(this::setBackToPreviousButton);
        displayAllAlliesButton11.setOnAction(this::setDisplayAllAlliesButton11);
    }
}
