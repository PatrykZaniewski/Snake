package sample;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

public class addNew {

    private Queue<Pair<Color, Pair<Integer, Integer>>> lista;
    private int amount = 0;
    public int test = 0;

    @Override
    public String toString()
    {
        return String.valueOf(lista.size());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addCord(Color c, int x, int y)
    {
        lista.add(new Pair<>(c, new Pair<>(x, y)));
        amount ++;
    }

    public addNew ()
    {
        lista = new ArrayDeque<>();
    }

    public Pair<Color, Pair<Integer, Integer>> get ()
    {
        return lista.remove();
    }

    public Pair<Color, Pair<Integer, Integer>> peekOne()
    {
        test ++;
        return lista.peek();
    }

    public Pair<Color, Pair<Integer, Integer>> removeOne()
    {
        setAmount(getAmount()-1);
        return lista.remove();
    }


}
