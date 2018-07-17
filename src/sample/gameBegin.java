package sample;

import javafx.application.Platform;
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
    @FXML
    public static Stage firstStage;
    private addNew add;
    public KeyCode direction = KeyCode.DOWN;
    private moveImage move;
    private ArrayList <Pair<Color, Pair<Integer, Integer>>> lista;
    public static boolean endGame = false;


    @FXML
    void initialize() {
        points.setZeroPoints();
        playgroundC.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode()
                    == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                if(event.getCode() == KeyCode.UP && direction == KeyCode.DOWN);
                else if(event.getCode() == KeyCode.DOWN && direction == KeyCode.UP);
                else if(event.getCode() == KeyCode.LEFT && direction == KeyCode.RIGHT);
                else if(event.getCode() == KeyCode.RIGHT && direction == KeyCode.LEFT);
                else direction = event.getCode();
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

    public void gameStart() {
        endGame = false;
        add = new addNew();
        lista = new ArrayList<>();
        playgroundC.setStyle("-fx-background-color: black");
        moveImage move = new moveImage(lista, playgroundC, add);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 400, 400);
        gc.setFill(Color.RED);
        gc.fillRect(180, 180, 20, 20);

        gc.setFill(Color.YELLOW);
        gc.fillRect(180, 160, 20, 20);

        gc.setFill(Color.RED);
        randomPoint.random();
        while(lista.contains(new Pair<>(Color.RED, new Pair<>(randomPoint.getX(), randomPoint.getY()))))
        {
            randomPoint.random();
        }

        gc.fillRect(randomPoint.x, randomPoint.y, 20, 20);
        lista.add(new Pair<>(Color.YELLOW, new Pair<>(180, 180)));
        lista.add(new Pair<>(Color.RED, new Pair<>(180, 160)));
        playgroundC.setFocusTraversable(true);
        playgroundC.requestFocus();

        Thread thread = new Thread() {
            @Override
            public synchronized void run() {
                while(!endGame) {
                    add = move.move(direction);
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Platform.runLater(()->{
                    try {
                        quit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public synchronized void quit() throws IOException {


        Parent DesignerSceneParent = FXMLLoader.load(getClass().getResource("endGame.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(DesignerSceneParent, 100, 125));
        stage.setResizable(false);
        stage.show();
        firstStage.hide();
    }
}
