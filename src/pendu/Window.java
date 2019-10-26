package pendu;

import pendu.Pages.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame implements Navigation {

    JMenuBar menuBar = new JMenuBar();
    JMenu fillMenu = new JMenu("Fichier"), aboutMenu = new JMenu("A propos");
    JMenuItem newMenu = new JMenuItem("Nouveau"), scoreMenu = new JMenuItem("Score"), rulesMenu = new JMenuItem("Règle"), aboutItemMenu = new JMenuItem("A propos");

    int pointPlayer = 10;

    HomePage homePage = new HomePage(this);
    RulesPages rulesPages = new RulesPages(this);
    GamePage gamePage = new GamePage(this);
    ScorePage scorePage = new ScorePage(this);

    Window() {

        this.setTitle("PENDU");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);



        initBar();
        this.setContentPane(gamePage);
        this.setVisible(true);
        /*this.getContentPane().removeAll();
        this.setContentPane(rulesPages);
        this.getContentPane().revalidate();*/
    }
    public void initBar() {
        fillMenu.add(newMenu);
        fillMenu.add(scoreMenu);
        fillMenu.add(rulesMenu);

        aboutMenu.add(aboutItemMenu);

        menuBar.add(fillMenu);
        menuBar.add(aboutMenu);

        aboutItemMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setPage(0);
            }
        });
        newMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setPage(2);
            }
        });
        rulesMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setPage(1);
            }
        });
        scoreMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setPage(3);
            }
        });

        this.setJMenuBar(menuBar);
    }
    public void setPage(int pages) {
        this.getContentPane().removeAll();
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

