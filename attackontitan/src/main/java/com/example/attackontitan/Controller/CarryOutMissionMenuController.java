package com.example.attackontitan.Controller;


import com.example.attackontitan.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.example.attackontitan.MyQueue;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

public class CarryOutMissionMenuController implements Initializable {
    @FXML
    private Text startingPoint;
    @FXML
    private TextField startingPointTextField;
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button checkButton;
    @FXML
    private Label resultLabel;
    @FXML
    private Label errorLabel;
    private StringBuilder stringBuilder;
    //Graph map
    private ArrayList<MyQueue> map = new ArrayList<>();
    //start (& end) vertex index
    private int startPoint;
    //list mapping of vertices to mark vertex visited
    private boolean[] visited;
    //stack used as list to store the path of the cycle
    private Stack<Integer> hamPath = new Stack<>();
    //number of vertices in the graph
    private int N;
    private boolean hasCycle = false;
    private MainApplication main;

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    public void check() {
        clear();
        stringBuilder = new StringBuilder();
        startScouting();
        resultLabel.setText(stringBuilder.toString());
    }

    //Construct a new node to be added into map
    private void newNode(int currentValue, int[] adjacentValues) {
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

    // getter method for mapNode
    private MyQueue getMapNode(int index) {
        return map.get(index);
    }

    //Enter starting point
    private void startScouting() {
        try {
            startPoint = Integer.parseInt(startingPointTextField.getText());
            if (startPoint < 0 || startPoint >= map.size()) {
                errorLabel.setText("Out of the range from 0 to " + (map.size() - 1));
                return;
            }
        } catch (Exception e) {
            errorLabel.setText("Please enter an Integer!");
            return;
        }
        errorLabel.setText("");
        findHamiltonianCycle(startPoint);
    }

    //method to inititate the search of the Hamiltonian cycle
    private void findHamiltonianCycle(int startPoint){
        this.startPoint=startPoint;
        //Add starting node to the list
        hamPath.add(startPoint);
        visited= new boolean[map.size()];

        //start searching the path
        getHamiltonianCycle(startPoint);
        if (!hasCycle){
            resultLabel.setText("No path found\n");
        }
    }

    //Visiting through all nodes without repeating
    private void getHamiltonianCycle(int node){

        //Base condition: if the vertex is the start vertex
        //and all nodes have been visited (start vertex twice)
        if (node == startPoint && hamPath.size() == map.size()+1) {
            hasCycle = true;
            stringBuilder.append("\nPath found!\n");

            //print the Hamiltonian path
            for (int i = 0; i < hamPath.size() - 1; i++) {
                stringBuilder.append(hamPath.get(i)).append("-->");
            }

            stringBuilder.append(hamPath.get(0));

            //return to explore more hamiltonian cycles
            return;
        }

        //Loop through all the adjacent nodes of current node
        for (int i = 1; i < getMapNode(node).getSize(); i++) {
            int neighbourNode = (int) getMapNode(node).getElement(i);

            // process only unvisited vertices as the Hamiltonian
            // path visit each vertex exactly once
            if (!visited[neighbourNode]) {
                visited[neighbourNode] = true;
                hamPath.add(neighbourNode);

                getHamiltonianCycle(neighbourNode);

                // Backtrack
                visited[neighbourNode] = false;
                hamPath.pop();
            }
        }
    }

    private void clear() {
        hamPath.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
