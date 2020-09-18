package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.awt.*;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private javafx.scene.control.Button startButton;

    @FXML
    private Canvas gameMatrix;

    @FXML
    private ComboBox box;

    @FXML

        public void handle(ActionEvent event) {


                Parent root;
                try {
                    //root= loader.setLocation(FXMLDocumentController.class.getResource("rules.fxml"));

                    root = FXMLLoader.load(getClass().getResource("/resources/rules.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Hitori Rules");
                    stage.setScene(new Scene(root, 600, 600));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    // ((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }

    @FXML
    public void handleStartButton(ActionEvent event){
            int matrixSize = Integer.valueOf(box.getId().charAt(0));
            for(int i = 0; i < matrixSize; i++){
                for(int j = 0; j < matrixSize; j++){

                }
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
