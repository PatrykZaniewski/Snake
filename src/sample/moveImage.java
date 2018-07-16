package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")

public class moveImage {

    private ArrayList <Pair<Color, Pair<Integer, Integer>>> lista;
    private addNew add;
    private KeyCode orientation;
    private Canvas playgroundC;
    private int x, y;
    private Color c;
    private gameBegin quit = new gameBegin();

    public KeyCode getOrientation() {
        return orientation;
    }

    public moveImage(ArrayList <Pair<Color, Pair<Integer, Integer>>> lista, Canvas playgroundC, addNew add)
    {
        this.add = add;
        this.lista = lista;
        this.playgroundC = playgroundC;
    }

    public addNew move(KeyCode key)
    {
        System.out.println("A");
        x = lista.get(0).getValue().getKey();
        y = lista.get(0).getValue().getValue();
        System.out.println("B");
        if(lista.get(1).getValue().getKey().equals(x+20) && lista.get(1).getValue().getValue().equals(y)) orientation = KeyCode.RIGHT;
        if(lista.get(1).getValue().getKey().equals(x-20) && lista.get(1).getValue().getValue().equals(y)) orientation = KeyCode.LEFT;
        if(lista.get(1).getValue().getKey().equals(x) && lista.get(1).getValue().getValue().equals(y+20)) orientation = KeyCode.DOWN;
        if(lista.get(1).getValue().getKey().equals(x) && lista.get(1).getValue().getValue().equals(y-20)) orientation = KeyCode.UP;

        if(orientation == key)return add;

        Pair<Color, Pair<Integer, Integer>> pair = null;
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
                    if(y - 20 < 0)
                    {
                        try {
                            quit.quit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    pair = moveUp(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x, y - 20)));
                    orientation = KeyCode.UP;
                    break;
                case DOWN:
                    if(y + 20 > 380){
                        try {
                            quit.quit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    pair = moveDown(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x, y + 20)));
                    orientation = KeyCode.DOWN;
                    break;
                case LEFT:
                    if(x - 20 < 0){
                        try {
                            quit.quit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    pair = moveLeft(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x - 20, y)));
                    orientation = KeyCode.LEFT;
                    break;
                case RIGHT:
                    if(x + 20 > 380){
                        try {
                            quit.quit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    pair = moveRight(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x + 20, y)));
                    orientation = KeyCode.RIGHT;
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
        if(pair != null)
        {
            lista.add(new Pair<> (pair.getKey(), new Pair <> (pair.getValue().getKey(), pair.getValue().getValue())));
            gc.setFill(Color.RED);
            gc.fillRect(pair.getValue().getKey(), pair.getValue().getValue(), 20, 20);
        }
        return add;
    }

    private Pair<Color, Pair<Integer, Integer>> moveRight(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista, add);
        add = feed.checkIfInside(x + 20, y, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x + 20, y, 20, 20);
        if(add.getAmount() != 0)
        {
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();

                if(lista.get(lista.size()-1).getValue().getKey().equals(x) && lista.get(lista.size()-1).getValue().getValue().equals(y))
                {
                    add.removeOne();
                    gc.setFill(c);
                    gc.fillRect(x, y, 20, 20);
                    return pair;
                }
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Pair<Color, Pair<Integer, Integer>> moveLeft(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista, add);
        add = feed.checkIfInside(x - 20, y, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x - 20, y, 20, 20);
        if(add.getAmount() != 0)
        {
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();
                if(lista.get(lista.size()-1).getValue().getKey().equals(x) && lista.get(lista.size()-1).getValue().getValue().equals(y))
                {
                    add.removeOne();
                    gc.setFill(c);
                    gc.fillRect(x, y, 20, 20);
                    return pair;
                }
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Pair<Color, Pair<Integer, Integer>> moveUp(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista, add);
        add = feed.checkIfInside(x, y - 20, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x, y - 20, 20, 20);
        if(add.getAmount() != 0)
        {
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();

                if(lista.get(lista.size()-1).getValue().getKey().equals(x) && lista.get(lista.size()-1).getValue().getValue().equals(y))
                {
                    add.removeOne();
                    gc.setFill(c);
                    gc.fillRect(x, y, 20, 20);
                    return pair;
                }
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Pair<Color, Pair<Integer, Integer>> moveDown(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista, add);
        add = feed.checkIfInside(x, y + 20, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x, y + 20, 20, 20);
        if(add.getAmount() != 0)
        {
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();

                if(lista.get(lista.size()-1).getValue().getKey().equals(x) && lista.get(lista.size()-1).getValue().getValue().equals(y))
                {
                    add.removeOne();
                    gc.setFill(c);
                    gc.fillRect(x, y, 20, 20);
                    return pair;
                }
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

}
