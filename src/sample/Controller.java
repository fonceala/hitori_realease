package sample;

import javafx.beans.binding.DoubleExpression;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Optional;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML
    private javafx.scene.control.Button startButton;

    @FXML
    private GridPane gameMatrix;

    @FXML
    private ComboBox<String> box;

    @FXML
    private BorderPane root;

    private Matrix game;

    private ArrayList<Rectangle> rectList;

    private ArrayList<Text> textList;
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
            startGame();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleRestartButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset Confirmation");
        alert.setHeaderText("Resetting the game will lose all your progress!");
        alert.setContentText("Are you sure you want to reset?");


        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
                startGame();
        }else {
            alert.close();
        }

    }

    public void startGame(){
        int matrixSize = Integer.valueOf(box.getValue());
        game = new Matrix();
        gameMatrix = new GridPane();
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
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            rectangle.setFill(Color.BLACK);
                            text.setFill(Color.WHITE);
                        }else{
                            rectangle.setFill(Color.WHITE);
                            text.setFill(Color.BLACK);
                        }
                    }
                });

                StackPane stack = new StackPane();
                stack.getChildren().addAll(rectangle,text);
                gameMatrix.add(stack,j,i,1,1);
                gameMatrix.setAlignment(Pos.BOTTOM_CENTER);

                root.setCenter(gameMatrix);
            }
        }
    }

}
