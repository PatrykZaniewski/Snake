package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")

public class moveImage {

    private ArrayList <Pair<Color, Pair<Integer, Integer>>> lista;
    private int orientation;
    private Canvas playgroundC;
    private int x, y;

    public int getOrientation() {
        return orientation;
    }

    public moveImage(ArrayList <Pair<Color, Pair<Integer, Integer>>> lista, Canvas playgroundC)
    {
        this.lista = lista;
        this.playgroundC = playgroundC;
    }

    public void move(KeyCode key)
    {
        int currentPos = 0;
        int lastY = 0;
        int lastX = 0;
        GraphicsContext gc = playgroundC.getGraphicsContext2D();

        for (Pair<Color, Pair<Integer, Integer>> iterLista : lista) {
            x = iterLista.getValue().getKey();
            y = iterLista.getValue().getValue();
            Color c = iterLista.getKey();
            if(currentPos==0)
            switch (key)
            {
                case UP:
                    if(y - 20 < 0)y = 20;
                    moveUp(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x, y - 20)));
                    break;
                case DOWN:
                    if(y + 20 > 380) y = 360;
                    moveDown(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x, y + 20)));
                    break;
                case LEFT:
                    if(x - 20 < 0) x = 20;
                    moveLeft(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x - 20, y)));
                    break;
                case RIGHT:
                    if(x + 20 > 380) x = 360;
                    moveRight(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x + 20, y)));
                    break;
            }
            else
            {
                gc.setFill(Color.RED);
                gc.fillRect(lastX, lastY, 20, 20);
                lista.set(currentPos, new Pair<>(c, new Pair<>(lastX, lastY)));
            }
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, 20, 20);
            lastX = x;
            lastY = y;
            currentPos ++;
        }
    }

    private void moveRight(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista);
        feed.checkIfInside(x + 20, y, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x + 20, y, 20, 20);
    }

    private void moveLeft(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista);
        feed.checkIfInside(x - 20, y, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x - 20, y, 20, 20);
    }

    private void moveUp(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista);
        feed.checkIfInside(x, y - 20, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x, y - 20, 20, 20);
    }

    private void moveDown(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista);
        feed.checkIfInside(x, y + 20, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x, y + 20, 20, 20);
    }

}
