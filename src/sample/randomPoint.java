package sample;

import java.util.concurrent.ThreadLocalRandom;

public class randomPoint {

    public static int x = 0;
    public static int y = 0;

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    private static void setX() {
        x = ThreadLocalRandom.current().nextInt(0, 19) * 20;
    }

    private static void setY() {
        y = ThreadLocalRandom.current().nextInt(0, 19) * 20;
    }

    public static void random()
    {
        setX();
        setY();
    }
}
