package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class feedSnake {

    private ArrayList<Pair<Color, Pair<Integer, Integer>>> lista;

    public feedSnake (ArrayList<Pair<Color, Pair<Integer, Integer>>> lista)
    {
        this.lista = lista;
    }

    public void checkIfContains(int x, int y)
    {
        if(lista.contains(new Pair<>(Color.RED, new Pair<>(x, y))))
        {
            gameBegin quit = new gameBegin();
            try {
                quit.quit();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {

        }
    }

    public void checkIfInside(int x, int y, Canvas playgroundC)
    {
        WritableImage wi = new WritableImage(400,400);
        playgroundC.snapshot(null, wi);
        if(wi.getPixelReader().getColor(x, y).equals(Color.RED))
        {
            checkIfContains(x, y);
        }
    }

    public void go()
    {
        gameBegin newGame = new gameBegin();
        newGame.gameStart();
    }
}
