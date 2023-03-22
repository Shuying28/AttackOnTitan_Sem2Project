package com.example.attackontitan.Controller;

import com.example.attackontitan.*;
import com.example.attackontitan.PriorityQueue;
import com.example.attackontitan.Queue;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.*;

/** 2.3 Upper Part Basic Feature + Extra Feature */
public class EvaluateTitanMenuController extends CheckAllAlliesMenuController implements Initializable {
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button btNo;
    @FXML
    private Button btYes;
    @FXML
    private Button btNo1;
    @FXML
    private Button btYes1;
    @FXML
    private Button btContinue;
    @FXML
    private ImageView titanView;
    @FXML
    private Label distance;
    @FXML
    private Label randomTitanLabel;
    @FXML
    private Label readyToKill;
    @FXML
    private Label highabilityLabel;
    @FXML
    private Label lowabilityLabel;
    @FXML
    private Text titan;
    @FXML
    private Text titandialog;
    @FXML
    private ImageView backgroundView;
    @FXML
    private Label sequenceLabel;
    private StringBuilder str = new StringBuilder("");
    private final static int TOTALNUMOFTITAN = 15;
    private static String selectSoldier;
    private int titanNumber;
    private AOTCharacter currentS;
    private MainApplication main;
    protected static AOTCharacter soldier;
    private Queue halfWayqueue = new Queue();
    private static SelectSoldierController selectSoldierController;
    private boolean canKill = false;
    private Titan currentTitan;
    private Timer timer;
    private static final int DELAY = 2000; // Delay for 2 seconds

    String path11 = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media11 = new Media (new File(path11).toURI().toString());
    MediaPlayer mediaPlayer11 = new MediaPlayer(media11);

    //To access the SelectSoldierController
    public static void setSelectSoldier(SelectSoldierController select){
        selectSoldierController = select;
    }

    public void setApp(MainApplication main){
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        main.goToLoginMenu();
    }

    //there are some new dangerous titans were added to the queue at the
    //halfway point, so users can choose if they want to continue Killing
    @FXML
    private void continueKilling(ActionEvent event) throws Exception {
        mediaPlayer11.play();
        btContinue.setVisible(false);
        randomGenerateothers();
    }


    public void yes (ActionEvent event) throws Exception{
        mediaPlayer11.play();
        readyToKill.setVisible(false);
        btYes.setVisible(false);
        btNo.setVisible(false);
        titanView.setVisible(true);
        titandialog.setVisible(true);

        //If selected soldier able to kill Titans
        if (canKill){
            //Show Soldier's distance moved
            distance.setText(String.valueOf(halfWayqueue.distanceMoved()));

            //If reach the maximum number of Titan (15)
            if(halfWayqueue.getSize()==15){
                //Kill Titans again
                highabilityLabel.setText("Bravo, your soldier, " + currentS.getName() + " with Strength & Agility : " + currentS.sumOfStrengthAgility() +
                        "  able to kill ALL " + titanNumber + " Titans.\n" +
                        "Do you want to kill Titans again?");
            }else {
                //Continue killing Titans
                highabilityLabel.setText("Bravo, your soldier, " + currentS.getName() + " with Strength & Agility : " + currentS.sumOfStrengthAgility() +
                        "  able to kill " + titanNumber + " Titans.\n" +
                        "Do you want to continue killing Titans?");
            }

        }//If selected soldier able to kill Titans
        else{
            //Set distance to 0
            distance.setText("0");
            //Select soldier to kill Titans again
            lowabilityLabel.setText("Oops, your soldier, "+currentS.getName()+" with Strength & Agility : "+currentS.sumOfStrengthAgility()
                    +"\ndon't have ability to kill Titans, he/she ran away.\n"+
                    "Do you want to select your soldier again?");
        }

        btYes1.setVisible(true);
        btNo1.setVisible(true);

    }

    public void yes1 (ActionEvent event) throws Exception{
        mediaPlayer11.play();
        //If selected soldier unable to kill Titans
        if (!canKill){
            //return to Select soldier page
            main.goToSelectSoldier();

        }//If selected soldier able to kill Titans
        else {
            //If reach the maximum number of Titan (15)
            if(halfWayqueue.getSize()>=15) {
                //return to Select soldier page
                main.goToSelectSoldier();
                //Stop timer
                timer.cancel();
            }else{
                btContinue.setVisible(true);
            }
        }
    }

    //pop out Alert
    public void no1 (ActionEvent event) throws Exception{
        mediaPlayer11.play();
        //If selected soldier unable to kill Titans
        if (!canKill){
            //Pop out Alert window
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setContentText("You will LOSE if you quit!");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isEmpty()){
                System.out.println("LOSE");
            } else if (result.get() == ButtonType.OK) {
                main.goToLoginMenu();

            } else if (result.get() == ButtonType.CANCEL) {
            }

        }else {
            main.goToLoginMenu();
        }

    }

    //Return to menu page
    public void no (ActionEvent event) throws Exception{
        mediaPlayer11.play();
        main.goToLoginMenu();
    }

    //Random generate the number of Titan which entered by user
    public String randomGenerate() throws Exception{
        PriorityQueue titanQueue = new PriorityQueue();

        //For loop to generate Titan randomly
        for (int i = 1; i <= titanNumber; i++) {
            Titan t = new Titan();
            titanQueue.offer(t);
            str.append(t.display()).append("\n");
        }

        //Check if the soldier able to kill titan
        canKillTitan(titanQueue);

        //Display the sequence
        sequenceLabel.setText(titanQueue.toString());

        //The random generate titans will be store in halfway queue
        while (!titanQueue.isEmpty()){
            halfWayqueue.enqueue(titanQueue.poll());
        }

        return str.toString();
    }

    //Check if the soldier able to kill Titan
    public Boolean canKillTitan(PriorityQueue titanQueue) throws Exception{
        canKill = false;
        currentS = getSoldier();

        if (currentS.sumOfStrengthAgility() >= titanQueue.getElement(0).getRisk()) {
            canKill = true;
        }else {
            canKill = false;
        }
        return canKill;
    }

    //Continue Killing until Titan 15 (Extra feature)
    public void randomGenerateothers() throws Exception{

        str.append(".............................................................................................................\n");
        str.append("Continue generating another " + (TOTALNUMOFTITAN - titanNumber) + " Titans ....\n");

        //Pop out Titan one by one
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Not run on the JavaFX Application Thread!
                try {
                    halfwayTitan();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("test 1");

                // Using Platform.runLater(Runnable) to ensure halfwayTitan()
                // is run on the JavaFX Application Thread
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        try{
                            updateTitan();

                            System.out.println("test 2");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }, 0, DELAY);  //Delay for 2 seconds each


    }

    //Random generate Titan one by one on the halfway point
    public void halfwayTitan() throws Exception{
        //If titan number less than 15
        if (titanNumber < 15) {
            //Generate new Titan
            this.currentTitan = new Titan();
            halfWayqueue.enqueue(currentTitan);
            str.append("New Titan Generated, Come on fight fight --> " + currentTitan.display()).append("\n");

            titanNumber++;

        } //If number of generated Titan == 15
        else {
            //Stop timer
            timer.cancel();

        }

    }

    //Update new generated Titan on JavaFX Application Thread (Scenebuilder)
    public void updateTitan(){
        randomTitanLabel.setText(str.toString());
        sequenceLabel.setText(halfWayqueue.toString());
        distance.setText(String.valueOf(halfWayqueue.distanceMoved()));

        if (currentS.sumOfStrengthAgility() >= currentTitan.getRisk()) {
            canKill = true;
            lowabilityLabel.setText("");
            highabilityLabel.setText("Let's continue Killing.");
        } else {
            canKill = false;
        }

        if(canKill && halfWayqueue.getSize()==15){
            titandialog.setText("");
            distance.setText(String.valueOf(halfWayqueue.distanceMoved()));
            lowabilityLabel.setText("");
            highabilityLabel.setText("Bravo, your soldier, " + currentS.getName() + " with Strength & Agility : " + currentS.sumOfStrengthAgility() +
                    "  able to kill ALL " + halfWayqueue.getSize() + " Titans.\n" +
                    "Do you want to kill Titan again?");

        }else if(!canKill){
            distance.setText("0");
            highabilityLabel.setText("");
            lowabilityLabel.setText("Oops, your soldier, "+currentS.getName()+" with Strength & Agility : "+currentS.sumOfStrengthAgility()
                    +"\ndon't have ability to kill Titans, he/she ran away.\n"+
                    "Do you want to select your soldier again?");
            timer.cancel();
        }

    }

    //Get soldier selected by User (Access from LinkedList<AOTCharacter> charactersList)
    public AOTCharacter getSoldier(){
        for (int i = 0; i < getCharactersList().size(); i++) {
            if (selectSoldier.equals(getCharactersList().get(i).getName())){
                soldier = getCharactersList().get(i);
            }
        }
        return soldier;
    }



    private void setBtBackToPrevious(ActionEvent event) {
        mediaPlayer11.play();
        try {
            main.goToLoginMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btContinue.setOnAction(event -> {
            try {
                continueKilling(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Convert1.jpg").toURI().toString()));
        titanView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\titan.png").toURI().toString()));
        btYes1.setVisible(false);
        btNo1.setVisible(false);
        titanView.setVisible(false);
        titandialog.setVisible(false);
        btContinue.setVisible(false);

        readFromTextFile();
        new Titan().setTitanInt(0);


        this.selectSoldier = selectSoldierController.getSelectedSoldier();
        this.titanNumber = selectSoldierController.getTitanNumber();

        try {
            randomTitanLabel.setText(randomGenerate());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
