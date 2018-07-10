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
    private addNew add;
    private int orientation;
    private Canvas playgroundC;
    private int x, y;
    private Color c;

    public int getOrientation() {
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
                    if(y - 20 < 0)y = 20;
                    pair = moveUp(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x, y - 20)));
                    break;
                case DOWN:
                    if(y + 20 > 380) y = 360;
                    pair = moveDown(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x, y + 20)));
                    break;
                case LEFT:
                    if(x - 20 < 0) x = 20;
                    pair = moveLeft(x, y, c);
                    lista.set(currentPos, new Pair<>(c, new Pair<>(x - 20, y)));
                    break;
                case RIGHT:
                    if(x + 20 > 380) x = 360;
                    pair = moveRight(x, y, c);
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
        if(pair != null)
        {
            lista.add(new Pair<> (pair.getKey(), new Pair <> (pair.getValue().getKey(), pair.getValue().getValue())));
        }
        return add;
    }

    private Pair<Color, Pair<Integer, Integer>> moveRight(int x, int y, Color c)
    {
        feedSnake feed = new feedSnake(lista, add);
        System.out.println(add.toString());
        add = feed.checkIfInside(x + 20, y, playgroundC);
        GraphicsContext gc = playgroundC.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillRect(x + 20, y, 20, 20);
        if(add.getAmount() != 0)
        {
            System.out.println("zapisane");
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();
                //System.out.print(x);
                //System.out.println(lista.get(lista.size()-1).getValue().getKey());
                //if(lista.get(lista.size()-1).getValue().getKey().equals(x))System.out.print("Lewa");
                  //  if( lista.get(lista.size()-1).getValue().getValue().equals(y))System.out.print("prawa");
                /*{
                    add.removeOne();
                    gc.setFill(c);
                    gc.fillRect(x, y, 20, 20);
                    return pair;
                }*/
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
        if(add != null)
        {
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();

                if(!lista.contains(new Pair<>(Color.RED, new Pair<>(x, y))))
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
        if(add != null)
        {
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();

                if(!lista.contains(new Pair<>(Color.RED, new Pair<>(x, y))))
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
        if(add != null)
        {
            try {
                Pair<Color, Pair<Integer, Integer>> pair = add.peekOne();
                c = pair.getKey();
                x = pair.getValue().getKey();
                y = pair.getValue().getValue();

                if(!lista.contains(new Pair<>(Color.RED, new Pair<>(x, y))))
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
