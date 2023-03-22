package com.example.attackontitan.Controller;


import com.example.attackontitan.AESCipher;
import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.attackontitan.MyHashMap;
import com.example.attackontitan.MyMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

/** 2.5 Basic Feature */
public class ConvertMarleyWordMenuController implements Initializable {

    @FXML
    private TextField marleySentenceTextField;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button translateButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private ImageView backgroundView;
    @FXML
    private ImageView convertFrame;
    @FXML
    private ImageView decryptByAllies;
    @FXML
    private ImageView encryptByAllies;
    @FXML
    private ImageView alert;
    @FXML
    private Text decrypt;
    @FXML
    private Text encrypt;
    @FXML
    private Text warning1;
    @FXML
    private Text warning2;
    private String sentence;
    private String message;
    private MyHashMap<Character, Character> map = new MyHashMap<>(512);
    private MainApplication main;
    private int shiftKey = 3;
    private int shiftKey2 = 11;

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    // Translate the sentence entered by user
    public void translate(ActionEvent event) {
        mediaPlayer5.play();

        // Get the sentence
        sentence = marleySentenceTextField.getText();

        // If no words and characters is entered
        // Display error
        if (sentence.isEmpty()) {
            translateButton.setVisible(true);
            encryptByAllies.setVisible(false);
            decryptByAllies.setVisible(false);
            convertFrame.setVisible(false);
            alert.setVisible(false);
            warning1.setVisible(false);
            warning2.setVisible(false);
            encrypt.setVisible(false);
            decrypt.setVisible(false);
            errorLabel.setText("Please enter character!");
            return;
        }
        boolean isNextCapital = false;
        StringBuilder stringBuilder = new StringBuilder();

        // Convert marley word into respective readable characters
        for (int i = 0; i < sentence.length(); i++) {

            // Store the key
            char key = sentence.charAt(i);

            // Alphabet characters are stored in hashmap
            if (map.containsKey(key)) {

                // Check if the character should be in capital letter
                if (isNextCapital) {
                    stringBuilder.append(Character.toUpperCase(map.get(key)));
                    isNextCapital = false;
                    translateButton.setVisible(true);
                    encryptByAllies.setVisible(false);
                    decryptByAllies.setVisible(false);
                    encrypt.setVisible(false);
                    decrypt.setVisible(false);
                    convertFrame.setVisible(false);
                    alert.setVisible(false);
                    warning1.setVisible(false);
                    warning2.setVisible(false);
                } else {
                    stringBuilder.append(map.get(key));
                    translateButton.setVisible(true);
                    encryptByAllies.setVisible(false);
                    decryptByAllies.setVisible(false);
                    encrypt.setVisible(false);
                    decrypt.setVisible(false);
                    convertFrame.setVisible(true);
                    alert.setVisible(true);
                    warning1.setVisible(true);
                    warning2.setVisible(true);
                }
            }
            // Special characters which are not stored in hashmap
            else {
                switch (key) {
                    case '^' -> isNextCapital = true;           // ^ next letter should be capital
                    case '$' -> stringBuilder.append(" ");      // white space
                    case ',' -> stringBuilder.append(",");      // a comma
                    case '(' -> stringBuilder.append("(");      // append opening parentheses first (words inside parentheses should be reversed)
                    case ')' -> stringBuilder.append(")");      // append closing parentheses first (words inside parentheses should be reversed)
                    default -> {                                // display error when invalid characters are entered
                        translateButton.setVisible(true);
                        encryptByAllies.setVisible(false);
                        decryptByAllies.setVisible(false);
                        encrypt.setVisible(false);
                        decrypt.setVisible(false);
                        convertFrame.setVisible(false);
                        alert.setVisible(false);
                        warning1.setVisible(false);
                        warning2.setVisible(false);
                        errorLabel.setText("Sentence contains invalid character!");
                        resultLabel.setText("");
                        return;
                    }
                }
            }
        }

        // Display the result
        errorLabel.setText("");
        resultLabel.setText("Decrypted data : \n" + reverseParentheses(stringBuilder.toString()));
    }


    // Method to reverse parentheses
    private String reverseParentheses(String message) {
        int length = message.length();

        // Use a stack to find all paired parentheses
        Stack<Integer> opened = new Stack<>();
        int[] pair = new int[length];
        for (int i = 0; i < length; ++i) {
            if (message.charAt(i) == '(')
                opened.push(i);                 // push the index of opening parentheses
            if (message.charAt(i) == ')') {
                int j = opened.pop();           // assign j with the last opening parentheses
                pair[i] = j;                    // i is the index of closing parentheses, j is the index of opening parentheses
                pair[j] = i;                    // assign pair with respective value
                // pair[i] stores index for respective opening parentheses
                // pair[j] stores index for respective closing parentheses
            }
        }
        StringBuilder sb = new StringBuilder();

        // Imagine all parentheses are wormholes and the paired parentheses are connected to each other
        // -------->(<--    <---------)-->
        // It first follows left long arrow -------->,
        // go into left wormhole and get out from right wormhole to the right long arrow <---------
        // Then iterates whole content between parentheses <--
        // Finally follows right wormhole and finishes -->

        // i is index of current position
        // direction is the direction of traversing
        for (int i = 0, direction = 1; i < length; i += direction) {
            if (message.charAt(i) == '(' || message.charAt(i) == ')') {
                i = pair[i];
                direction = -direction;
            } else {
                sb.append(message.charAt(i));
            }
        }
        return sb.toString();
    }

    // Caesar encryption by imposter
    @FXML
    void caesarCipherEncryptionByImposter(MouseEvent event) {
        translateButton.setVisible(true);
        encryptByAllies.setVisible(true);
        decryptByAllies.setVisible(true);
        convertFrame.setVisible(true);
        encrypt.setVisible(true);
        decrypt.setVisible(true);
        alert.setVisible(false);
        warning1.setVisible(false);
        warning2.setVisible(false);

        // Get message from user
        String message = marleySentenceTextField.getText();

        // Assign reference strings
        String lowerCaseAlphabets = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);

            // If character is alphabet
            if (java.lang.Character.isAlphabetic(character)) {

                // Case for uppercase letter
                if (java.lang.Character.isUpperCase(character)) {
                    // Get position from uppercase reference strings
                    int alphabetsPositionValue = upperCaseAlphabets.indexOf(character);

                    // Shift the value
                    int newAlphabetPositionValue = (shiftKey + alphabetsPositionValue) % 26;

                    // Generate encrypted character
                    char newCharacter = upperCaseAlphabets.charAt(newAlphabetPositionValue);
                    cipherText.append(newCharacter);
                }

                // Case for lowercase letter
                else {

                    // Get position from lowercase reference strings
                    int alphabetsPositionValue = lowerCaseAlphabets.indexOf(character);

                    // Shift the value
                    int newAlphabetPositionValue = (shiftKey + alphabetsPositionValue) % 26;

                    // Generate encrypted character
                    char newCharacter = lowerCaseAlphabets.charAt(newAlphabetPositionValue);
                    cipherText.append(newCharacter);
                }
            } else { // If the character is not alphabet, then simply just shift the key using ASCII value
                int newPositionValue = character + shiftKey;
                cipherText.append((char) newPositionValue);
            }
        }
        this.message = cipherText.toString();
        resultLabel.setText("Encrypted data by imposters : \n" + cipherText);
        errorLabel.setText("");
    }


    // Method for decryption
    @FXML
    void caesarCipherDecryption(MouseEvent event) {
        convertFrame.setVisible(true);

        // Assign reference strings
        String lowerCaseAlphabets = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);

            // If character is alphabet
            if (java.lang.Character.isAlphabetic(character)) {
                // Case for uppercase letter
                if (java.lang.Character.isUpperCase(character)) {
                    // Get position from uppercase reference strings
                    int alphabetsPositionValue = upperCaseAlphabets.indexOf(character);

                    // Shift back position
                    int newAlphabetPositionValue = (alphabetsPositionValue - shiftKey) % 26;
                    if (newAlphabetPositionValue < 0) {
                        newAlphabetPositionValue = upperCaseAlphabets.length() + newAlphabetPositionValue;
                    }

                    // Generate new character
                    char newCharacter = upperCaseAlphabets.charAt(newAlphabetPositionValue);
                    cipherText.append(newCharacter);

                }
                // Case for lowercase letter
                else {
                    // Get position from uppercase reference strings
                    int alphabetsPositionValue = lowerCaseAlphabets.indexOf(character);

                    // Shift back position
                    int newAlphabetPositionValue = (alphabetsPositionValue - shiftKey) % 26;
                    if (newAlphabetPositionValue < 0) {
                        newAlphabetPositionValue = lowerCaseAlphabets.length() + newAlphabetPositionValue;
                    }

                    // Generate new character
                    char newCharacter = lowerCaseAlphabets.charAt(newAlphabetPositionValue);
                    cipherText.append(newCharacter);
                }
            } else {
                // If the character is not alphabet, then simply just shift back the key using ASCII value
                int newPositionValue = character - shiftKey;
                cipherText.append((char) newPositionValue);
            }
        }
        resultLabel.setText("Decrypted data : \n" + cipherText);
        errorLabel.setText("");
    }

    @FXML
    void encryptionByAllies(MouseEvent event) throws Exception {
        String lowerCaseAlphabets = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char character = message.charAt(i);
            // If character is alphabet
            if (java.lang.Character.isAlphabetic(character)) {

                // Case for uppercase letter
                if (java.lang.Character.isUpperCase(character)) {
                    // Get position from uppercase reference strings
                    int alphabetsPositionValue = upperCaseAlphabets.indexOf(character);

                    // Shift the value
                    int newAlphabetPositionValue = (shiftKey2 + alphabetsPositionValue) % 26;

                    // Generate encrypted character
                    char newCharacter = upperCaseAlphabets.charAt(newAlphabetPositionValue);
                    cipherText.append(newCharacter);
                }

                // Case for lowercase letter
                else {

                    // Get position from lowercase reference strings
                    int alphabetsPositionValue = lowerCaseAlphabets.indexOf(character);

                    // Shift the value
                    int newAlphabetPositionValue = (shiftKey2 + alphabetsPositionValue) % 26;

                    // Generate encrypted character
                    char newCharacter = lowerCaseAlphabets.charAt(newAlphabetPositionValue);
                    cipherText.append(newCharacter);
                }
            } else { // If the character is not alphabet, then simply just shift the key using ASCII value
                int newPositionValue = character + shiftKey2;
                cipherText.append((char) newPositionValue);
            }
        }
        resultLabel.setText("Encrypted data by allies : \n" + cipherText);
        errorLabel.setText("");
    }

    String path5 = "C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media5 = new Media (new File(path5).toURI().toString());
    MediaPlayer mediaPlayer5 = new MediaPlayer(media5);

    private void setBackToPreviousButton(ActionEvent event) {
        mediaPlayer5.play();
        try {
            main.goToLoginMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Button
        backToPreviousButton.setOnAction(this::setBackToPreviousButton);
        translateButton.setOnAction(this::translate);


        // Initialize styling
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\table2.jpg").toURI().toString()));
        convertFrame.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\frame2.png").toURI().toString()));
        alert.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\alert.png").toURI().toString()));
        encryptByAllies.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Lock.png").toURI().toString()));
        decryptByAllies.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Unlock.png").toURI().toString()));
        translateButton.setVisible(true);
        encryptByAllies.setVisible(false);
        decryptByAllies.setVisible(false);
        convertFrame.setVisible(false);
        encrypt.setVisible(false);
        decrypt.setVisible(false);
        alert.setVisible(false);
        warning1.setVisible(false);
        warning2.setVisible(false);
        errorLabel.setText("");
        resultLabel.setText("");

        // Initialize hash map
        map.put('a', 'j');
        map.put('b', 'c');
        map.put('c', 't');
        map.put('d', 'a');
        map.put('e', 'k');
        map.put('f', 'z');
        map.put('g', 's');
        map.put('h', 'i');
        map.put('i', 'w');
        map.put('j', 'x');
        map.put('k', 'o');
        map.put('l', 'n');
        map.put('m', 'g');
        map.put('n', 'b');
        map.put('o', 'f');
        map.put('p', 'h');
        map.put('q', 'l');
        map.put('r', 'd');
        map.put('s', 'e');
        map.put('t', 'y');
        map.put('u', 'm');
        map.put('v', 'v');
        map.put('w', 'u');
        map.put('x', 'p');
        map.put('y', 'q');
        map.put('z', 'r');
    }
}