package sample;

import javafx.beans.binding.DoubleExpression;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private javafx.scene.control.Button startButton;

    @FXML
    private GridPane gameMatrix;

    @FXML
    private ComboBox<String> box;

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
            int matrixSize = Integer.valueOf(box.getValue());
            for(int i = 0; i < matrixSize; i++){
                for(int j = 0; j < matrixSize; j++){
                    Rectangle rectangle = new Rectangle();
                    rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            rectangle.setFill(Color.BLACK);
                        }
                    });
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setHeight(50);
                    rectangle.setWidth(50);
                    rectangle.setFill(Color.RED);
                    gameMatrix.add(rectangle,j,i,1,1);
                }
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
