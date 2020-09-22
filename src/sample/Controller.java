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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.*;
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
    private BorderPane pane;

    private int choice = -1;

    private Matrix game = new Matrix();

   private int numRows = 0;

   private int[][] loadMatrix;
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
        appStart();
    }

    public void handleRestartButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset Confirmation");
        alert.setHeaderText("Resetting the game will lose all your progress!");
        alert.setContentText("Are you sure you want to reset?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
               if(choice == 1){
                   startGame();
               }else {
                   if(choice == -1) {
                       startLoad();
                   }else{
                       appStart();
                   }
               }
        }else {
            alert.close();
        }

    }

    public void startGame(){

        choice = 1;
        int matrixSize = Integer.valueOf(box.getValue());

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

                pane.setCenter(gameMatrix);
            }
        }
    }

    public void handleLoadButton(ActionEvent actionEvent) throws IOException {
        choice = -1;
        FileChooser fileChooser = new FileChooser();
        Stage window= new Stage();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        File selectedFile = fileChooser.showOpenDialog(window);
        String data = "";


        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
            String line;
            while((line = reader.readLine()) != null){
                data += line;
                data+="\n";
                numRows++;
            }
        System.out.println(data);
        loadMatrix = new int[numRows][numRows];
        int rowIndex = 0;
        int colIndex ;
        for(String row: data.split("\n")){
            colIndex = 0;
            for(String elem: row.split(" ")){
                loadMatrix[rowIndex][colIndex] = Integer.parseInt(elem);
                colIndex++;
            }
            rowIndex++;
        }
        System.out.println(numRows);
        startLoad();

    }

    public void startLoad(){
        int matrixSize = numRows;

        gameMatrix = new GridPane();
        for(int i = 0; i < matrixSize; i++){
            for(int j = 0; j < matrixSize; j++){
                Rectangle rectangle = new Rectangle();
                int[][] matrix = loadMatrix;


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

                pane.setCenter(gameMatrix);
            }
        }
    }

    public void appStart(){
        choice = 0;
        gameMatrix = new GridPane();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                Rectangle rectangle = new Rectangle();
                int[][] matrix = {
                        {5,6,6,2,2,5,3},
                        {3,7,6,5,4,4,2},
                        {1,5,3,7,3,2,1},
                        {1,2,3,4,3,6,6},
                        {3,4,1,7,7,3,5},
                        {5,3,7,1,2,6,1},
                        {3,1,7,3,4,4,7}
                };
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

                pane.setCenter(gameMatrix);
            }
        }
    }

}
