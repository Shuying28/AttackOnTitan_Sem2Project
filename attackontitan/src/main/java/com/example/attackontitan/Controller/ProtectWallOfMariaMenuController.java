package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProtectWallOfMariaMenuController implements Initializable {
    @FXML
    private Text brickEdgesText;
    @FXML
    private Text numberOfLayersText;
    @FXML
    private TextField numberOfLayersTextField;
    @FXML
    private TextField brickEdgesLayerTextField;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button findButton;
    @FXML
    private Button enterButton;
    @FXML
    private Label resultLabel;
    @FXML
    private Label numberOfLayerErrorLabel;
    @FXML
    private Label brickEdgesLayerErrorLabel;

    private MainApplication main;
    private int noOfLayer, maxValueOfEdge;
    private int numberOfClickCount = 0;
    private List<Integer> edgesList = new ArrayList<>();
    private List<List<Integer>> mariaWallList = new ArrayList<>();

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    public void find() {
        try {
            noOfLayer = Integer.parseInt(numberOfLayersTextField.getText());
        } catch (Exception e) {
            numberOfLayerErrorLabel.setText("Please enter an Integer!");
            return;
        }
        numberOfLayerErrorLabel.setText("");
        brickEdgesText.setText("Enter brick edges of layer 1");
        maxValueOfEdge = -1;
        findButton.setDisable(true);
        brickEdgesLayerTextField.setVisible(true);
        numberOfLayersTextField.setVisible(false);
        numberOfLayersText.setVisible(false);
        enterButton.setDisable(false);
    }

    public void enter() {
        try {
            brickEdges();
            numberOfClickCount++;
            brickEdgesLayerErrorLabel.setText("");
            brickEdgesLayerTextField.clear();
            brickEdgesText.setText("Enter brick edges of layer " + (numberOfClickCount + 1));
            if (numberOfClickCount == noOfLayer) {
                getMostWeakest();
                brickEdgesText.setText("");
                enterButton.setDisable(true);
                brickEdgesLayerTextField.setVisible(false);
            }
        } catch (NumberFormatException e) {
            brickEdgesLayerErrorLabel.setText("Please enter integer only!");
        }
    }

    private void brickEdges() {
        String inputEdges = brickEdgesLayerTextField.getText();
        String[] strEdges = inputEdges.split(" ");
        for (String strEdge : strEdges) {
            int intEdge = Integer.parseInt(strEdge);
            edgesList.add(intEdge);
            if (intEdge >= maxValueOfEdge) {
                maxValueOfEdge = intEdge;
            }
        }
        mariaWallList.add(edgesList);
    }

    private void getMostWeakest() {
        int[] edgeTimes = new int[maxValueOfEdge + 1];
        List<Integer> wall = mariaWallList.get(0);

        for (Integer integer : wall) {
            edgeTimes[integer]++;
        }

        int temp = -1, max = -1;
        for (int x = 0; x < edgeTimes.length; x++) {
            if (edgeTimes[x] >= temp) {
                temp = edgeTimes[x];
                max = x;
            }
        }
        resultLabel.setText("Weakest part of the wall is at position " + max);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brickEdgesLayerTextField.setVisible(false);
        numberOfLayerErrorLabel.setText("");
        brickEdgesLayerErrorLabel.setText("");
        brickEdgesText.setText("");
        resultLabel.setText("");
        enterButton.setDisable(true);
    }

}
