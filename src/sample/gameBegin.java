package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class gameBegin {

    @FXML
    public Pane backgroundP;
    @FXML
    public Canvas playgroundC;
    public static Stage firstStage;

    private ArrayList <Pair<Color, Pair<Integer, Integer>>> lista;

    @FXML
    void initialize() {

        playgroundC.setOnKeyPressed(event -> {
            moveImage move = new moveImage(lista, playgroundC);

            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode()
                    == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                move.move(event.getCode());
            }

        });

        lista = new ArrayList<>();
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 400, 400);
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, 20, 20);
        gc.fillRect(0, 20, 20, 20);
        gc.fillRect(0, 40, 20, 20);
        gc.fillRect(0, 60, 20, 20);
        gc.strokeRect(0, 0, 20, 20);

        lista.add(new Pair<>(Color.RED, new Pair<>(0, 60)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 40)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 20)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 0)));
        playgroundC.setFocusTraversable(true);
        playgroundC.requestFocus();
    }

    public gameBegin(Stage firstStage)
    {
        this.firstStage = firstStage;
    }
    
    public gameBegin()
    {

    }

    public void gameStart()
    {
        playgroundC.setOnKeyPressed(event -> {
            moveImage move = new moveImage(lista, playgroundC);

            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode()
                    == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                move.move(event.getCode());
            }

        });
        lista = new ArrayList<>();
        playgroundC.setStyle("-fx-background-color: black");
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 400, 400);
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, 20, 20);
        gc.fillRect(0, 20, 20, 20);
        gc.fillRect(0, 40, 20, 20);
        gc.fillRect(0, 60, 20, 20);
        gc.strokeRect(0, 0, 20, 20);

        lista.add(new Pair<>(Color.RED, new Pair<>(0, 60)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 40)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 20)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 0)));
        playgroundC.setFocusTraversable(true);
        playgroundC.requestFocus();
    }

    public void quit() throws IOException {
        Parent DesignerSceneParent = FXMLLoader.load(getClass().getResource("endGame.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(DesignerSceneParent, 100, 125));
        stage.setResizable(false);
        stage.show();
        firstStage.hide();
    }

}
