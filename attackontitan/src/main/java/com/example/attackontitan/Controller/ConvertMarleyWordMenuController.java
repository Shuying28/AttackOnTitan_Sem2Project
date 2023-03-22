package com.example.attackontitan.Controller;


import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.attackontitan.MyHashMap;
import com.example.attackontitan.MyMap;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

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
    private String sentence;
    private MyMap<Character, Character> map = new MyHashMap<>();
    private MainApplication main;

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    public void translate() {
        sentence = marleySentenceTextField.getText();
        if (sentence.isEmpty()) {
            errorLabel.setText("Please enter character!");
            return;
        }
        boolean isNextCapital = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            char key = sentence.charAt(i);
            if (map.containsKey(key)) {
                if (isNextCapital) {
                    stringBuilder.append(Character.toUpperCase(map.get(key)));
                    isNextCapital = false;
                }
                else {
                    stringBuilder.append(map.get(key));
                }
            } else {
                switch (key) {
                    case '^' -> isNextCapital = true;
                    case '$' -> stringBuilder.append(" ");
                    case ',' -> stringBuilder.append(",");
                    case '(' -> stringBuilder.append("(");
                    case ')' -> stringBuilder.append(")");
                    default -> {
                        errorLabel.setText("Sentence contains invalid character!");
                        resultLabel.setText("");
                        return;
                    }
                }
            }
        }
        errorLabel.setText("");
        resultLabel.setText(reverseParentheses(stringBuilder.toString()));
    }

    private String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> opened = new Stack<>();
        int[] pair = new int[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(')
                opened.push(i);
            if (s.charAt(i) == ')') {
                int j = opened.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        // i is index of current position

        for (int i = 0, direction = 1; i < n; i += direction) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];
                 direction = -direction;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        resultLabel.setText("");
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
