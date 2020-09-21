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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    private Matrix game;
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
            game = new Matrix();
            for(int i = 0; i < matrixSize; i++){
                for(int j = 0; j < matrixSize; j++){
                    Rectangle rectangle = new Rectangle();
                    int[][] matrix={};

                    switch (matrixSize){
                        case 5:
                            matrix=game.getMatrix5();
                            break;
                        case 6:
                            matrix = game.getMatrix6();
                            break;
                        case 7:
                            matrix = game.getMatrix7();
                            break;
                    }
                    Text text = new Text(String.valueOf(matrix[i][j]));
                    text.setFill(Color.BLACK);
                    text.setFont(Font.font(24));
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setHeight(50);
                    rectangle.setWidth(50);
                    rectangle.setFill(Color.WHITE);

                    rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            rectangle.setFill(Color.BLACK);
                            text.setFill(Color.WHITE);
                        }
                    });

                    StackPane stack = new StackPane();
                    stack.getChildren().addAll(rectangle,text);
                    gameMatrix.add(stack,j,i,1,1);
                }
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
