package com.example.attackontitan.Controller;

import com.example.attackontitan.MainApplication;
import com.example.attackontitan.MyQueue;
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
import java.util.*;

/** 2.4 Extra Feature (Breadth First Search) */
public class DetermineBestPathMenuController implements Initializable {
    @FXML
    private Text enterLocationOfTitan;
    @FXML
    private Text shortSentence;
    @FXML
    private Text shortSentence1;
    @FXML
    private TextField locationOfTitanEnteredByUser;
    @FXML
    private Button btDetermine;
    @FXML
    private Button btBackToPrevious;
    @FXML
    private Label resultLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private ImageView backgroundView;
    private StringBuilder stringBuilder;
    private int dest;
    //Two dimensional ArrayList to store multiple shortest paths
    private ArrayList<ArrayList<Integer>> shortestPaths = new ArrayList<>();
    //ArrayList to store one shortest path
    private ArrayList<Integer> shortestPath = new ArrayList<>();
    //Store parent of node
    private ArrayList<ArrayList<Integer>> parent = new ArrayList<>();
    //Graph map
    private ArrayList<MyQueue> map = new ArrayList<>();
    private MainApplication main;

    String path9 = "C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media9 = new Media (new File(path9).toURI().toString());
    MediaPlayer mediaPlayer9 = new MediaPlayer(media9);

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    //Start finding the best bath to kill Titans
    public void determine(ActionEvent event) {
        mediaPlayer9.play();
        clear();
        shortSentence.setVisible(true);
        shortSentence1.setVisible(true);
        stringBuilder = new StringBuilder();
        startKillingTitan();
        resultLabel.setText(stringBuilder.toString());

    }

    //Construct a new node to be added into map
    public void newNode(int currentValue, int[] adjacentValues) {
        MyQueue<Integer> nodeQueue = new MyQueue<>();
        //Add current value into nodeQueue, eg: 0
        nodeQueue.enqueue(currentValue);
        //Add all adjacent values into nodeQueue, eg: 1, 5, 7
        for (int i = 0; i < adjacentValues.length; i++) {
            nodeQueue.enqueue(adjacentValues[i]);
        }
        //Add [0,1,5,7] into map
        map.add(nodeQueue);
    }

    public void startKillingTitan() {
            try {
                dest = Integer.parseInt(locationOfTitanEnteredByUser.getText());
                //Ensure the entered destination is only from 0 to 15
                if (dest < 0 || dest >= map.size()) {
                    errorLabel.setText("Out of the range of 0 to " + (map.size() - 1));
                    shortSentence.setVisible(false);
                    shortSentence1.setVisible(false);
                    return;
                }
            } catch (Exception e) {
                //Accept only integer
                errorLabel.setText("Please enter an Integer!");
                shortSentence.setVisible(false);
                shortSentence1.setVisible(false);
                return;
            }
            errorLabel.setText("");
            printPath();
    }

    //Function which finds all the shortest paths and stores it in shortestPaths array
    public void findShortestPaths(int dest) {
        //Base condition: if the destination vertex -1
        //and all nodes have been visited
        if (dest == -1) {
            shortestPaths.add(new ArrayList<>(shortestPath));
            return;
        }

        // Loop for all the parents of the given vertex
        for (int par : parent.get(dest)) {

            // Insert the current vertex in path
            shortestPath.add(dest);

            // Recursive call for its parent
            findShortestPaths(par);

            // Remove the current vertex
            shortestPath.remove(shortestPath.size()-1);
        }
    }

    // Performs Breadth First Search from vertex 0
    public void breadthFirstSearch() {

        // Distance will contain shortest distance from start to every other vertex
        int[] distance = new int[map.size()];

        //All elements in distance array become 16
        Arrays.fill(distance,16);

        MyQueue<Integer> queue = new MyQueue<>();

        //0 as root node and its parent -1 and distance 0
        queue.enqueue(0);
        distance[0] = 0;

        //All elements in parent array become -1
        parent.get(0).clear();
        parent.get(0).add(-1);

        // Until Queue is empty
        while (!queue.isEmpty()) {
            int currentVertex = queue.dequeue();

            //Loop all adjacent values (neighbours) of the current Vertex
            for (int i=1 ; i< map.get(currentVertex).getSize(); i++){
                int adjacentVertex = (int) map.get(currentVertex).getElement(i);

                // The shortest distance is the distance to current vertex + 1
                if (distance[adjacentVertex] > distance[currentVertex] + 1) {
                    // If a shorter distance is found, erase all the previous parents
                    // and insert new parent currentVertex in parent[adjacentVertex]
                    distance[adjacentVertex] = distance[currentVertex] + 1;
                    queue.enqueue(adjacentVertex);
                    // erase all previous parents
                    parent.get(adjacentVertex).clear();
                    //get child get parent
                    parent.get(adjacentVertex).add(currentVertex);
                }
                // If the distance is the same to distance to current vertex + 1
                else if (distance[adjacentVertex] == distance[currentVertex] + 1) {
                    // Another candidate parent for the same shortest path found
                    parent.get(adjacentVertex).add(currentVertex);
                }
            }
        }
    }

    // Prints all the shortest paths from start to dest
    public void printPath(){

        for(int i = 0; i < map.size(); i++){
            parent.add(new ArrayList<>());
        }

        // Function call to breadthFirstSearch
        // Always start from 0
        breadthFirstSearch();

        // Function call to findShortestPaths
        findShortestPaths(dest);

        for (ArrayList<Integer> path : shortestPaths) {

            //Reverse the path
            for (int i = path.size() - 1; i >= 0; i--) {
                if (i == 0) {
                    stringBuilder.append(path.get(i)).append("\n");
                } else {
                    stringBuilder.append(path.get(i) + " --> ");
                }
            }

        }
    }

    private void clear() {
        shortestPaths.clear();
    }

    private void setBtBackToPrevious(ActionEvent event) {
        mediaPlayer9.play();
        try {
            main.goToLoginMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btDetermine.setOnAction(this::determine);
        btBackToPrevious.setOnAction(this::setBtBackToPrevious);
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Evaluation.jpg").toURI().toString()));
        shortSentence.setVisible(false);
        shortSentence1.setVisible(false);

        //Adding all the current nodes and their adjacent nodes into graph map
        int[] adjacentValue0 = {1,5,7};
        newNode(0,adjacentValue0);
        int[] adjacentValue1 = {0,2,4,6};
        newNode(1,adjacentValue1);
        int[] adjacentValue2 = {1,3,11,13};
        newNode(2,adjacentValue2);
        int[] adjacentValue3 = {2,10};
        newNode(3,adjacentValue3);
        int[] adjacentValue4 = {1,6,10};
        newNode(4,adjacentValue4);
        int[] adjacentValue5 = {0,6,7,12};
        newNode(5,adjacentValue5);
        int[] adjacentValue6 = {1,4,5,8,15};
        newNode(6,adjacentValue6);
        int[] adjacentValue7 = {0,5,9};
        newNode(7,adjacentValue7);
        int[] adjacentValue8 = {6,10};
        newNode(8,adjacentValue8);
        int[] adjacentValue9 = {7,12,15};
        newNode(9,adjacentValue9);
        int[] adjacentValue10 = {3,4,8,14};
        newNode(10,adjacentValue10);
        int[] adjacentValue11 = {2,13};
        newNode(11,adjacentValue11);
        int[] adjacentValue12 = {5,9};
        newNode(12,adjacentValue12);
        int[] adjacentValue13 = {2,11,14};
        newNode(13,adjacentValue13);
        int[] adjacentValue14 = {10,13,15};
        newNode(14,adjacentValue14);
        int[] adjacentValue15 = {6,9,14};
        newNode(15,adjacentValue15);

        resultLabel.setText("");
        errorLabel.setText("");
    }
}
