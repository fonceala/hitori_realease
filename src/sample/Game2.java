//package sample;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.ComboBox;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.RowConstraints;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.stage.Stage;
//
//public class Game2 extends Application{
//    @Override
//    public void start(final Stage stage) throws Exception {
//        int rows = 5;
//        int columns = 5;
//
//        stage.setTitle("Hitori Game");
//
//        GridPane grid = new GridPane();
//        grid.getStyleClass().add("game-grid");
//
//        for(int i = 0; i < columns; i++) {
//            ColumnConstraints column = new ColumnConstraints(40);
//            grid.getColumnConstraints().add(column);
//        }
//
//        for(int i = 0; i < rows; i++) {
//            RowConstraints row = new RowConstraints(40);
//            grid.getRowConstraints().add(row);
//        }
//
//        for (int i = 0; i < columns; i++) {
//            for (int j = 0; j < rows; j++) {
//                Pane pane = new Pane();
//                pane.setOnMouseReleased(e -> {
//                    pane.getChildren().add(Anims.getAtoms(1));
//                });
//                pane.getStyleClass().add("game-grid-cell");
//                if (i == 0) {
//                    pane.getStyleClass().add("first-column");
//                }
//                if (j == 0) {
//                    pane.getStyleClass().add("first-row");
//                }
//                grid.add(pane, i, j);
//            }
//        }
//
//
//        Scene scene = new Scene(grid, (columns * 40) + 100, (rows * 40) + 100, Color.WHITE);
//        scene.getStylesheets().add("game.css");
//        stage.setScene(scene);
//        stage.show();
//
//
//        ComboBox<String> cb = new ComboBox<>();
//        cb.getItems().addAll("6x6","7x7","5x5");
//        cb.getStyleClass().add("combo");
//
//        Button module=new Button();
//        module.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                String choice = cb.getValue();
//                switch (choice){
//                    case "6x6":
//                        break;
//                }
//            }
//        });
//    }
//
//    public static class Anims {
//
//        public static Node getAtoms(final int number) {
//            Circle circle = new Circle(20, 20f, 7);
//            circle.setFill(Color.RED);
//            Group group = new Group();
//            group.getChildren().add(circle);
////            SubScene scene = new SubScene(group, 40, 40);
////            scene.setFill(Color.TRANSPARENT);
//            return group;
//        }
//    }
//
//    public static void main(final String[] arguments) {
//        Application.launch(arguments);
//    }
//}