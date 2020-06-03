package Laba_6.Kashuba_9gr;

import java.awt.Color;
import java.awt.Graphics2D;

public class Kirpizh {

    private static Color color;
    private static  int x =200;
    private static int y =200;
    private static int width = x;
    private static int height = y;

    public static  int getX() {
        return x;
    }

    public static void setX(int X) {
        x = X;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int Y) {
        y = Y;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static boolean contains(double mx, double my){
        boolean mark = false;
        if(mx >= x && my >= y)
            if(my <= height+y && mx <= width+x)
                mark = true;
        return mark;

    }

    public static void setPos(double Mx, double My){
        setX((int) Mx);
        setY((int) My);

    }

    public Kirpizh() {



        color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());

    }

    public static void paint(Graphics2D canvas) {


        canvas.setColor(color);
        canvas.setPaint(color);
        canvas.fillRect(x, y, width, height);
    }

}
