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
import java.util.ResourceBundle;

/** 2.3 Upper Part (Killing Priority) */
public class SelectSoldierController extends EvaluateTitanMenuController implements Initializable {
    @FXML
    private Button backToPreviousButton;
    @FXML
    private Button btConfirm;
    @FXML
    private Button button1;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private ImageView charView1;
    @FXML
    private ImageView charView10;
    @FXML
    private ImageView charView11;
    @FXML
    private ImageView charView12;
    @FXML
    private ImageView charView13;
    @FXML
    private ImageView charView2;
    @FXML
    private ImageView charView3;
    @FXML
    private ImageView charView4;
    @FXML
    private ImageView charView5;
    @FXML
    private ImageView charView6;
    @FXML
    private ImageView charView7;
    @FXML
    private ImageView charView8;
    @FXML
    private ImageView charView9;
    @FXML
    private ImageView backgroundView;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel1;
    @FXML
    private Text numberOfTitans;
    @FXML
    private TextField numberOfTitansTextField;
    @FXML
    private TextField soldierTextField;
    private static String selectedSoldier;
    private static int titanNumber;
    private MainApplication main;

    String path16 = "C:\\Users\\user\\JavaIdeaProjects\\attackontitan23JuneLATEST\\attackontitan\\src\\main\\resources\\Images\\button.mp3";
    Media media16 = new Media (new File(path16).toURI().toString());
    MediaPlayer mediaPlayer16 = new MediaPlayer(media16);

    public void setApp(MainApplication main) {
        this.main = main;
    }

    public void backToPrevious() throws Exception {
        mediaPlayer16.play();
        main.goToLoginMenu();
    }

    //Get the soldier selected by user
    public static String getSelectedSoldier() {
        return selectedSoldier;
    }

    //Get the titan number entered by user
    public static int getTitanNumber() {
        return titanNumber;
    }

    public void setButton1(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Reiner Braun");
    }
    public void setButton2(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Armin Arlert");
    }
    public void setButton3(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Annie Leonhart");
    }
    public void setButton4(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Bertholdt Hoover");
    }
    public void setButton5(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Jean Kristein");
    }
    public void setButton6(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Sasha Blouse");
    }
    public void setButton7(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Connie Springer");
    }
    public void setButton8(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Mikasa Ackerman");
    }
    public void setButton9(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Eren Yeager");
    }
    public void setButton10(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Historia Reiss");
    }
    public void setButton11(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Levi Ackerman");
    }
    public void setButton12(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Erwin Smith");
    }
    public void setButton13(ActionEvent event){
        mediaPlayer16.play();
        soldierTextField.setText("Hange Zoë");
    }
    public void confirm(ActionEvent event) throws Exception{
        mediaPlayer16.play();
        this.selectedSoldier = soldierTextField.getText();

        //Ensure the user have selected a character before go to next scene
        if (selectedSoldier.equals("")){
            errorLabel1.setText("Please select a character!");
        }

        try {
            this.titanNumber = Integer.parseInt(numberOfTitansTextField.getText());

            //Only except the titan number from 1 to 15 (index 0-15)
            if (titanNumber < 0 || titanNumber>=16 ) {
                errorLabel.setText("Out of range of 1 to 15!");
            }
        } catch (Exception e) {
            //Ensure the user enter an integer only
            errorLabel.setText("Please enter an Integer!");
            return;
        }
        //go to next scene if all conditions fulfil
        if(titanNumber>=1 && titanNumber<=15 && !selectedSoldier.equals("")){
            main.goToLoadingPage2();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");
        errorLabel1.setText("");
        soldierTextField.setText("");
        selectedSoldier = "";

        try {
            //EvaluateTitanMenuController can access any variable in this controller (getTitanNumber and getSelectedSoldier)
            EvaluateTitanMenuController evaluateTitanMenuController = (EvaluateTitanMenuController) main.loadScene("EvaluateTitanMenu.fxml");
            evaluateTitanMenuController.setSelectSoldier(this);

            //BasicLoading2Controller can access any variable in this controller (getTitanNumber)
            BasicLoading2Controller basicLoading2Controller = (BasicLoading2Controller) main.loadScene("BasicLoading2.fxml");
            basicLoading2Controller.setSelectSoldier(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        charView1.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Reiner Braun.png").toURI().toString()));
        charView2.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Armin Arlert.png").toURI().toString()));
        charView3.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Annie Leonhart##.png").toURI().toString()));
        charView4.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Bertholdt Hoover.png").toURI().toString()));
        charView5.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Jean Kristein.png").toURI().toString()));
        charView6.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Sasha Blouse.png").toURI().toString()));
        charView7.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Connie Springer.png").toURI().toString()));
        charView8.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Mikasa Ackerman 2.png").toURI().toString()));
        charView9.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Eren Yeager.png").toURI().toString()));
        charView10.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\HIstoria Reiss.png").toURI().toString()));
        charView11.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Levi Ackerman.png").toURI().toString()));
        charView12.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Erwin Smith.png").toURI().toString()));
        charView13.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Character\\Hange Zoe.png").toURI().toString()));
        backgroundView.setImage(new Image(new File("C:\\Users\\Asus\\IdeaProjects\\attackontitan23JuneOMG\\attackontitan\\src\\main\\resources\\Images\\Convert1.jpg").toURI().toString()));
    }
}
