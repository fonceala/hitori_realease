package sample;

import checker.Check;
import checker.CorrectMatrixChecker;
import checker.DuplicateChecker;
import checker.SingleComponentChecker;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Main extends Application {

    private int[][] resultMatrix;

    private int choice = -1;

    private Matrix game = new Matrix();

    private int numRows = 0;

    private int[][] loadMatrix;

    private Check matrixTest;

    private ComboBox<String> box;

    private GridPane gameMatrix;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane root = new BorderPane();

        //TOP================================================================
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        Label titlu = new Label("Hitori Puzzle Game");
        titlu.setFont(Font.font(24));
        titleBox.getChildren().add(titlu);
        //===================================================================

        //Bottom
        box = new ComboBox<>();
        box.getItems().addAll("5", "6", "7");
        box.setPromptText("Mode");
        box.prefWidth(90);
        Button restart = new Button("Restart");
        restart.prefWidth(90);
        Button rules = new Button("Rules");
        rules.prefWidth(90);
        Button start = new Button("Start");
        start.prefWidth(90);
        Button load = new Button("Load");
        load.prefWidth(90);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleStartButton(actionEvent,root);
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleRestartButton(actionEvent,root);
            }
        });

        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    handleLoadButton(actionEvent,root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    handleRulesButton(actionEvent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Button check = new Button("Check");
        check.prefWidth(90);
        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleCheckButton(actionEvent,root);
            }
        });

        VBox bottomBox = new VBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(20);

        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(20);
        buttonsBox.getChildren().addAll(box, restart, rules, start, load);

        bottomBox.getChildren().addAll(check, buttonsBox);

        root.setTop(titleBox);
        root.setBottom(bottomBox);
        appStart(root);

        primaryStage.setTitle("Hitori Game");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


//
    public void handleRulesButton(ActionEvent event) throws FileNotFoundException {



        VBox root = new VBox();
        TextArea ta = new TextArea();
        ta.setWrapText(true);
        ta.setText("Hitori is played with a grid filled with numbers.&#10;&#10;Eliminate numbers by tapping them to make the following rules true:&#10;1) No row or column can have more than one occurrence of any given number.&#10;2) Removed cells cannot be touching horizontally or vertically.&#10;3) Remaining cells must form a connected continuous area.&#10;&#10;Here it is an example:");

       // ImageView view1 = new ImageView(new Image(new FileInputStream("/resources/800px-Hitori.svg.png")));
        //ImageView view2 = new ImageView(new Image(new FileInputStream("/resources/800px-Hitori_completed.svg.png")));

       // HBox imageBox = new HBox();
        //imageBox.setAlignment(Pos.CENTER);
        //imageBox.setSpacing(100);
        //imageBox.getChildren().addAll(view1,view2);


        root.setAlignment(Pos.CENTER);
        root.setSpacing(40);

        root.getChildren().addAll(ta);
        Stage stage = new Stage();
        stage.setTitle("Hitori Rules");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();

    }
//
//
    public void handleStartButton(ActionEvent event,BorderPane pane){
        startGame(pane);
    }
//
//
    public void handleRestartButton(ActionEvent actionEvent,BorderPane pane) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset Confirmation");
        alert.setHeaderText("Resetting the game will lose all your progress!");
        alert.setContentText("Are you sure you want to reset?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            if(choice == 1){
                startGame(pane);
            }else {
                if(choice == -1) {
                    startLoad(pane);
                }else{
                    appStart(pane);
                }
            }
        }else {
            alert.close();
        }

    }
//
    public void startGame(BorderPane pane){

        choice = 1;
        int matrixSize = Integer.valueOf(box.getValue());
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
        resultMatrix = new int[matrixSize][matrixSize];
        for(int m = 0; m < resultMatrix.length; m++){
            for(int n = 0; n < resultMatrix.length; n++){
                resultMatrix[m][n] = matrix[m][n];
            }
        }
        gameMatrix = new GridPane();
        for(int i = 0; i < matrixSize; i++){
            for(int j = 0; j < matrixSize; j++){
                Rectangle rectangle = new Rectangle();

                Text text = new Text(String.valueOf(matrix[i][j]));
                text.setFill(Color.BLACK);
                text.setFont(Font.font(24));
                rectangle.setStroke(Color.BLACK);
                rectangle.setHeight(50);
                rectangle.setWidth(50);
                rectangle.setFill(Color.WHITE);
                final int row = i;
                final int col = j;
                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        handleRectAndText(mouseEvent,rectangle,text,row,col);
                    }
                });

                text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        handleRectAndText(mouseEvent,rectangle,text,row,col);
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
//
    public void handleLoadButton(ActionEvent actionEvent,BorderPane pane) throws IOException {
        choice = -1;
        FileChooser fileChooser = new FileChooser();
        Stage window= new Stage();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        File selectedFile = fileChooser.showOpenDialog(window);
        String data = new String("");

        numRows = 0;
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
        startLoad(pane);

    }
//
    public void startLoad(BorderPane pane){
        int matrixSize = numRows;
        int[][] matrix = loadMatrix;
        resultMatrix = new int[matrix.length][matrix.length];
        for(int m = 0; m < resultMatrix.length; m++){
            for(int n = 0; n < resultMatrix.length; n++){
                resultMatrix[m][n] = matrix[m][n];
            }
        }
        gameMatrix = new GridPane();
        for(int i = 0; i < matrixSize; i++){
            for(int j = 0; j < matrixSize; j++){
                Rectangle rectangle = new Rectangle();
                Text text = new Text(String.valueOf(matrix[i][j]));
                text.setFill(Color.BLACK);
                text.setFont(Font.font(24));
                rectangle.setStroke(Color.BLACK);
                rectangle.setHeight(50);
                rectangle.setWidth(50);
                rectangle.setFill(Color.WHITE);
                final int row = i;
                final int col = j;
                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        handleRectAndText(mouseEvent,rectangle,text,row,col);
                    }
                });
                text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        handleRectAndText(mouseEvent,rectangle,text,row,col);
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
//
    public void appStart(BorderPane pane){
        choice = 0;
        int[][] matrix = {
                {5,6,6,2,2,5,3},
                {3,7,6,5,4,4,2},
                {1,5,3,7,3,2,1},
                {1,2,3,4,3,6,6},
                {3,4,1,7,7,3,5},
                {5,3,7,1,2,6,1},
                {3,1,7,3,4,4,7}
        };
        resultMatrix = new int[matrix.length][matrix.length];
        for(int m = 0; m < resultMatrix.length; m++){
            for(int n = 0; n < resultMatrix.length; n++){
                resultMatrix[m][n] = matrix[m][n];
            }
        }
        gameMatrix = new GridPane();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                Rectangle rectangle = new Rectangle();

                Text text = new Text(String.valueOf(matrix[i][j]));
                text.setFill(Color.BLACK);
                text.setFont(Font.font(24));
                rectangle.setStroke(Color.BLACK);
                rectangle.setHeight(50);
                rectangle.setWidth(50);
                rectangle.setFill(Color.WHITE);

                final int row = i;
                final int col = j;
                rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        handleRectAndText(mouseEvent,rectangle,text,row,col);
                    }
                });

                text.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                       handleRectAndText(mouseEvent,rectangle,text,row,col);
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
//
    public void handleCheckButton(ActionEvent actionEvent, BorderPane pane) {
        matrixTest = new DuplicateChecker(resultMatrix);
        if(!matrixTest.check()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You did not solve correctly");
            alert.setContentText("There should not be two adjacent cells deleted!");
            alert.showAndWait();
        }else {
            matrixTest = new CorrectMatrixChecker(resultMatrix);
            if (!matrixTest.check()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("You did not solve correctly");
                alert.setContentText("The numbers must be unique on its corresponding row and column");
                alert.showAndWait();
            } else {
                matrixTest = new SingleComponentChecker(resultMatrix);
                if(!matrixTest.check()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("You did not solve correctly");
                    alert.setContentText("The result must form a single component");
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Correct");
                    alert.setHeaderText("Congratulations, YOU WON!");
                    alert.setContentText("Do you want to start a new game or exit the application?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        appStart(pane);
                    } else {
                        alert.close();
                    }
                }
            }
        }
    }

    public void handleRectAndText(MouseEvent mouseEvent,Rectangle rectangle, Text text, int row, int col){
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            rectangle.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
            resultMatrix[row][col] = 0;
        }else{
            rectangle.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
            resultMatrix[row][col] = Integer.parseInt(text.getText());
        }
    }
}
