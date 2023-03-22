package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.example.attackontitan.Titan;

import java.net.URL;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

public class EvaluateTitanMenuController implements Initializable {

    @FXML
    private Label resultLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Text numberOfTitans;
    @FXML
    private TextField numberOfTitansTextField;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button enterButton;
    private MainApplication main;

    public void setApp(MainApplication main){
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    public void enter() {
        resultLabel.setText(randomGenerate());
        new Titan().setTitanNo(0);
    }

    public String randomGenerate() {
        int titanNumber;
        if(!isNumeric(numberOfTitansTextField.getText())){
            errorLabel.setText("Please input an INTEGER!");
        }else {
            titanNumber = Integer.parseInt(numberOfTitansTextField.getText());
            PriorityQueue titanQueue = new PriorityQueue();

            StringBuilder str = new StringBuilder("Generating " + titanNumber + " Titans ....\n");
            for (int i = 1; i <= titanNumber; i++) {
                Titan t = new Titan();
                titanQueue.offer(t);
                str.append(t.toString()).append("\n");
            }
            str.append("\n");
            str.append(titanQueue.toString()).append("\n");

            return str.toString();
        }
        return "";
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        resultLabel.setText("");
    }
}
