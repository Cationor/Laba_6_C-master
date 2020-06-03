package Laba_6.Kashuba_9gr;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {


    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    private JMenuItem KirpMenuItem;
    private JMenuItem DLMenuItem;


    private Field field = new Field();


    public MainFrame() {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);


        // Создать меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled()  && !resumeMenuItem.isEnabled()) {

                    pauseMenuItem.setEnabled(true);
                }
            }
        };

        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        Action delball = new AbstractAction("Удалить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.delball( );
            }
        };

        menuBar.add(ballMenu);
        ballMenu.add(delball);

        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);


        Action pauseAction = new AbstractAction("Приостановить движение мячей"){
            public void actionPerformed(ActionEvent event) {
                field.pause1();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };

        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };

        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
        // Добавить в центр граничной компоновки поле Field
        getContentPane().add(field, BorderLayout.CENTER);


        JMenu KirpMenu = new JMenu("Кирпич");
        menuBar.add(KirpMenu);
        Action addKirpAction = new AbstractAction("Добавить кирпич") {
            public void actionPerformed(ActionEvent event) {
                field.addKirp();
                KirpMenuItem.setEnabled(false);
                DLMenuItem.setEnabled(true);

            }
        };
        KirpMenuItem = KirpMenu.add(addKirpAction);
        pauseMenuItem.setEnabled(false);

        Action delKirp = new AbstractAction("Удалить Кирпичей") {
            public void actionPerformed(ActionEvent event) {
                field.delKirp( );
                KirpMenuItem.setEnabled(true);
                DLMenuItem.setEnabled(false);
            }
        };


        DLMenuItem= KirpMenu.add(delKirp);
        DLMenuItem.setEnabled(true);
    }

    // Главный метод приложения
    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
