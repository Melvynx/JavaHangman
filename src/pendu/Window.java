package pendu;

import pendu.Pages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Navigation {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu fillMenu = new JMenu("Fichier"), aboutMenu = new JMenu("A propos");
    private JMenuItem newGame = new JMenuItem("Nouveau"), scoreMenu = new JMenuItem("Score"), rulesMenu = new JMenuItem("Règle"), aboutItemMenu = new JMenuItem("A propos");

    private int pointPlayer = 0;

    private HomePage homePage = new HomePage(this);

    Window() {

        this.setTitle("PENDU");
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);



        initBar();
        this.setContentPane(homePage);
        this.setVisible(true);
    }
    private void initBar() {
        fillMenu.add(newGame);
        fillMenu.add(scoreMenu);
        fillMenu.add(rulesMenu);

        aboutMenu.add(aboutItemMenu);

        menuBar.add(fillMenu);
        menuBar.add(aboutMenu);

        aboutItemMenu.addActionListener(e -> setPage(0));
        aboutItemMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        newGame.addActionListener(e -> setPage(2));
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        rulesMenu.addActionListener(e -> setPage(1));
        rulesMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        scoreMenu.addActionListener(e -> setPage(3));
        rulesMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        this.setJMenuBar(menuBar);
    }
    public void setPage(int pages) {
        this.getContentPane().removeAll();

        RulesPages rulesPages;
        GamePage gamePage;
        ScorePage scorePage;

        switch (pages) {
            case 0:
                homePage = new HomePage(this);
                this.setContentPane(homePage);
                break;
            case 1:
                rulesPages = new RulesPages(this);
                this.setContentPane(rulesPages);
                break;
            case 2:
                gamePage = new GamePage(this);
                this.setContentPane(gamePage);
                break;
            case 3:
                scorePage = new ScorePage(this);
                this.setContentPane(scorePage);
                break;
        }
        this.getContentPane().revalidate();
        if (pages > 3) {
            System.out.println("Erreur: ["+pages+"] n'est pas définit.(Définit entre 0 et 3)");
        }
    }

    public int getPointPlayer() {
        return pointPlayer;
    }
    public void addPointPlayer(int point){
        pointPlayer+=point;
    }
    public void resetPointPlay(){
        pointPlayer = 0;
    }
}

