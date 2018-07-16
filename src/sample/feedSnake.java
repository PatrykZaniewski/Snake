package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class feedSnake {

    private ArrayList<Pair<Color, Pair<Integer, Integer>>> lista;
    private addNew add;
    private int feedX;
    private int feedY;

    public feedSnake (ArrayList<Pair<Color, Pair<Integer, Integer>>> lista, addNew add)
    {
        this.lista = lista;
        this.add= add;
    }

    private void checkIfContains(int x, int y, Canvas playgroundC)
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
            GraphicsContext gc = playgroundC.getGraphicsContext2D();
            randomPoint.random();
            int posX = randomPoint.x;
            int posY = randomPoint.y;
            while(lista.contains(new Pair<>(Color.RED, new Pair<>(posX, posY))))
            {
                randomPoint.random();
                posX = randomPoint.x;
                posY = randomPoint.y;
            }
            gc.setFill(Color.RED);
            gc.fillRect(posX, posY, 20, 20);
            points.increasePoints();
        }
    }



    public synchronized addNew checkIfInside(int x, int y, Canvas playgroundC)
    {
        if(lista.contains(new Pair<>(Color.RED, new Pair<>(x, y))))
        {
            try {
                gameBegin quit = new gameBegin();
                quit.quit();
            }
            catch (IOException e)
            {}
        }

        if(x == randomPoint.getX() && y == randomPoint.getY())
        {
            checkIfContains(x, y, playgroundC);
        }

        return add;
    }
}
