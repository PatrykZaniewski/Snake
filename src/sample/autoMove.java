package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

public class autoMove extends Thread {

    public synchronized void setKey(KeyCode key) {
        this.key = key;
    }

    private KeyCode key;
    private addNew add;
    private ArrayList<Pair<Color, Pair<Integer, Integer>>> lista;
    private Canvas playgroundC;

    public autoMove(ArrayList<Pair<Color, Pair<Integer, Integer>>> lista, Canvas playgroundC, addNew add, KeyCode key) {
        this.add = add;
        this.key = key;
        this.lista = lista;
        this.playgroundC = playgroundC;
    }

    @Override
    public void run() {
        while (true) {
            try {
                moveImage move = new moveImage(lista, playgroundC, add);
                move.move(key);
                wait(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized addNew getAdd() {
        return add;
    }

    public synchronized void setAdd(addNew add) {
        this.add = add;
    }

    public synchronized ArrayList<Pair<Color, Pair<Integer, Integer>>> getLista() {
        return lista;
    }

    public synchronized void setLista(ArrayList<Pair<Color, Pair<Integer, Integer>>> lista) {
        this.lista = lista;
    }

    public synchronized Canvas getPlaygroundC() {
        return playgroundC;
    }

    public synchronized void setPlaygroundC(Canvas playgroundC) {
        this.playgroundC = playgroundC;
    }
}
