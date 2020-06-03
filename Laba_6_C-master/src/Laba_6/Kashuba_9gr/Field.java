package Laba_6.Kashuba_9gr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Field extends JPanel {

    private double dragOffsetX;
    private double dragOffsetY;


    private boolean paused;
    private boolean paused1;


    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
    private ArrayList<Kirpizh> rect = new ArrayList<Kirpizh>(3);


    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {

            repaint();
        }
    });

    public Field() {

        setBackground(Color.WHITE);

        repaintTimer.start();
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;

        for (BouncingBall ball: balls) {
            ball.paint(canvas);
        }
        Graphics2D path = (Graphics2D) g;
        for (Kirpizh kirp: rect) {
            kirp.paint(path);;
        }

    }


    public void addBall() {

        addMouseListener(new MouseHandler());
        balls.add(new BouncingBall(this));

    }
    public void delball()
    {

        balls.remove(0);

    }

    public void addKirp() {

        rect.add(new Kirpizh());
        addMouseMotionListener(new MouseMotionHandler());


    }

    public void delKirp()
    {

        rect.remove(0);

    }



    public  void pause1() {

        paused = true;

    }


    public synchronized void resume() {

        paused = false;
        paused1 = false;

        notifyAll();
    }



    public synchronized void canMove(BouncingBall ball) throws
            InterruptedException {
        if (paused) {
                wait();
        }
        if (paused1)
                if (ball.getRadius() < 10)
                    wait();
 ;
    }


    public class MouseHandler extends MouseAdapter{

        public void mousePressed(MouseEvent e) {
            if ((e.getModifiers() & MouseEvent.BUTTON2_MASK) == 0)
                if(Kirpizh.contains(e.getX(), e.getY())){

                    dragOffsetX = e.getX()- Kirpizh.getX();
                    dragOffsetY = e.getY()- Kirpizh.getY();
                }
            repaint();
            if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0) {
                paused1 = true;
            }
            if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) == 0) {
                resume();
            }
        }

    }

    public class MouseMotionHandler implements MouseMotionListener{

        public void mouseDragged(MouseEvent e){

            Kirpizh.setPos(e.getX() - dragOffsetX, e.getY() - dragOffsetY);



        }

        public void mouseMoved(MouseEvent e){
        }

    }
}