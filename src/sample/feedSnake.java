package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

public class feedSnake {

    private ArrayList<Pair<Color, Pair<Integer, Integer>>> lista;
    private addNew add;


    public feedSnake (ArrayList<Pair<Color, Pair<Integer, Integer>>> lista, addNew add)
    {
        this.lista = lista;
        this.add= add;
    }

    public synchronized addNew checkIfInside(int x, int y, Canvas playgroundC)
    {
        if(lista.contains(new Pair<>(Color.RED, new Pair<>(x, y))) || x == 420 || y == 420)
        {
            gameBegin.endGame = true;
        }
        else if (x == randomPoint.getX() && y == randomPoint.getY())
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
        return add;
    }
}
