package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/** 2.6 Wall Of Maria */
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
    @FXML
    private ImageView backgroundView;
    private StringBuilder str= new StringBuilder("");
    private MainApplication main;
    private int noOfLayer, maxValueOfEdge;
    private int numberOfClickCount = 0;
    private List<Integer> edgesList = new ArrayList<>();
    private List<List<Integer>> mariaWallList = new ArrayList<>();

    public void setApp(MainApplication main) {
        this.main = main;
    }

    String path15 = "C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media15 = new Media (new File(path15).toURI().toString());
    MediaPlayer mediaPlayer15 = new MediaPlayer(media15);

    public void backToPrevious(ActionEvent event) throws Exception {
        mediaPlayer15.play();
        main.goToLoginMenu();
    }

    // Method to find (enter the number of layer in JavaFx)
    public void find(ActionEvent event) {
        mediaPlayer15.play();
        try {
            noOfLayer = Integer.parseInt(numberOfLayersTextField.getText());
            if (noOfLayer <= 0) {
                noOfLayer = 0;
                numberOfLayerErrorLabel.setText("Negative value or 0 is invalid!");
                return;
            }
        } catch (Exception e) {
            // Display error when int is required but other format is entered
            numberOfLayerErrorLabel.setText("Please enter an Integer!");
            return;
        }
        numberOfLayerErrorLabel.setText("");
        // Prompt message to let user enter
        brickEdgesText.setText("Enter brick edges of layer 1: ");
        maxValueOfEdge = -1;
        findButton.setDisable(true);
        brickEdgesLayerTextField.setVisible(true);
        numberOfLayersTextField.setVisible(false);
        numberOfLayersText.setVisible(false);
        enterButton.setDisable(false);
    }

    public void enter(ActionEvent event) {
        mediaPlayer15.play();
        try {
            if (!brickEdges())
                return;
            numberOfClickCount++; // Increase number of click count
            brickEdgesLayerErrorLabel.setText("");
            brickEdgesLayerTextField.clear();
            // Prompt message
            brickEdgesText.setText("Enter brick edges of layer " + (numberOfClickCount + 1)+": ");
            if (numberOfClickCount == noOfLayer) {
                // When the number of layer reaches number of click count, display the output
                getMostWeakest();
                brickEdgesText.setText("");
                enterButton.setDisable(true);
                brickEdgesLayerTextField.setVisible(false);
            }
        } catch (NumberFormatException e) {
            // Display error when int is required but other format is entered
            brickEdgesLayerErrorLabel.setText("Please enter integer only!");
        }
    }

    // Method to obtain edges input for that particular layer
    private boolean brickEdges() {
        String inputEdges = brickEdgesLayerTextField.getText();
        String[] strEdges = inputEdges.split(" ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String strEdge : strEdges) {
            int intEdge = Integer.parseInt(strEdge);

            // Accept only positive integers
            // Display error when negative integers are entered
            if (intEdge < 0) {
                edgesList.clear();
                brickEdgesLayerErrorLabel.setText("Please enter only positive values!");
                return false;
            }

            // To make sure in the same layer no duplicated intEdge is entered
            // For example, 3 3 5 is invalid because edge 3 is duplicated
            if (arrayList.contains(intEdge)) {
                edgesList.clear();
                brickEdgesLayerErrorLabel.setText("Please do not enter duplicated value in the input!");
                return false;
            }
            str.append((intEdge)).append(" ");
            edgesList.add(intEdge);
            arrayList.add(intEdge);

            // Assign intEdge too maxValueOfEdge if intEdge is larger or equal to maxValueOfEdge
            if (intEdge >= maxValueOfEdge) {
                maxValueOfEdge = intEdge;
            }
        }

        str.append("\n");
        // Add to edges list
        mariaWallList.add(edgesList);
        return true;
    }


    // Method to display the weakest part of the wall
    private void getMostWeakest() {

        int[] edgeTimes = new int[maxValueOfEdge + 1];
        List<Integer> wall = mariaWallList.get(0);

        // Increment respective element
        for (Integer integer : wall) {
            edgeTimes[integer]++;
        }

        // Get the highest duplicate value inside edgeTimes array
        int temp = -1;
        for (int x = 0; x < edgeTimes.length; x++) {
            if (edgeTimes[x] >= temp) {
                temp = edgeTimes[x];

            }
        }

        //Get the weakest part of wall
        str.append("Weakest part of the wall is at position ");
        //ArrayList<Integer> maxList = new ArrayList<>();
        for (int x = 0; x < edgeTimes.length; x++) {
            if (edgeTimes[x] == temp) {
                str.append(x+" ");

            }
        }
        // Display result
        resultLabel.setText(str.toString());
    }

    // Initialize styling
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        brickEdgesLayerTextField.setVisible(false);
        numberOfLayerErrorLabel.setText("");
        brickEdgesLayerErrorLabel.setText("");
        brickEdgesText.setText("");
        resultLabel.setText("");
        enterButton.setDisable(true);
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\pic8.jfif").toURI().toString()));
    }
}

