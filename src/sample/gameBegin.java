package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private addNew add;
    public KeyCode direction = KeyCode.DOWN;
    private moveImage move;
    private ArrayList <Pair<Color, Pair<Integer, Integer>>> lista;


    @FXML
    void initialize() throws InterruptedException {
        points.setZeroPoints();

        playgroundC.setOnKeyPressed(event -> {
            moveImage move = new moveImage(lista, playgroundC, add);
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode()
                    == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                /*add =  auto.getAdd();
                lista = auto.getLista();
                playgroundC = auto.getPlaygroundC();
                direction = event.getCode();
                auto.setKey(direction);*/
                add = move.move(event.getCode());

            }
        });
        gameStart();
    }

    public gameBegin(Stage firstStage)
    {
        gameBegin.firstStage = firstStage;
    }

    public gameBegin()
    {

    }

    public void gameStart() throws InterruptedException {

        add = new addNew();

        lista = new ArrayList<>();
        playgroundC.setStyle("-fx-background-color: black");
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 400, 400);
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, 20, 20);
        gc.fillRect(0, 20, 20, 20);
        gc.fillRect(0, 40, 20, 20);

        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 60, 20, 20);

        gc.setFill(Color.RED);
        randomPoint.random();
        while(lista.contains(new Pair<>(Color.RED, new Pair<>(randomPoint.getX(), randomPoint.getY()))))
        {
            randomPoint.random();
        }
        gc.fillRect(randomPoint.x, randomPoint.y, 20, 20);
        lista.add(new Pair<>(Color.YELLOW, new Pair<>(0, 60)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 40)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 20)));
        lista.add(new Pair<>(Color.RED, new Pair<>(0, 0)));
        playgroundC.setFocusTraversable(true);
        playgroundC.requestFocus();

       /* Runnable auto = new Runnable() {
            @Override
            public void run() {
                moveImage move = new moveImage(lista, playgroundC, add);
                move.move(direction);
            }
        };

        Thread thread = new Thread(auto);
        thread.start();*/
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
