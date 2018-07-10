package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class feedSnake {

    private ArrayList<Pair<Color, Pair<Integer, Integer>>> lista;
    private addNew add;

    public feedSnake (ArrayList<Pair<Color, Pair<Integer, Integer>>> lista, addNew add)
    {
        this.lista = lista;
        this.add= add;
    }

    private void checkIfContains(int x, int y)
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
            add.addCord(Color.RED, x, y);
            points.increasePoints();
        }
    }

    public addNew checkIfInside(int x, int y, Canvas playgroundC)
    {
        WritableImage wi = new WritableImage(400,400);
        playgroundC.snapshot(null, wi);
        if(wi.getPixelReader().getColor(x, y).equals(Color.RED))
        {
            checkIfContains(x, y);
        }
        return add;
    }
}
