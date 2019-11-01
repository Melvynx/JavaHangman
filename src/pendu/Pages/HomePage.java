package pendu.Pages;



import pendu.Navigation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePage extends JPanel {
    private final Navigation navigation;
    private JLabel title = new JLabel("Bienvenue dans le jeu du PENDU");
    private JLabel textSouth = new JLabel("");
    private JButton buttonToStart = new JButton("Commencez !");
    private JButton rulesPages = new JButton("Règles");



    public HomePage(Navigation navigation) {

        createJLabel();
        JPanel contentSouth = new JPanel();
        contentSouth.setLayout(new BorderLayout());
        contentSouth.add(textSouth, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonToStart.setPreferredSize(new Dimension(200, 75));
        rulesPages.setPreferredSize(new Dimension(200, 75));
        buttonPanel.setPreferredSize(new Dimension(300, 100));
        buttonPanel.add(buttonToStart);
        buttonPanel.add(rulesPages);

        contentSouth.add(buttonPanel, BorderLayout.SOUTH);

        ImagePanel imagePendu = new ImagePanel();
        imagePendu.setIndexSet(0);
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(imagePendu, BorderLayout.CENTER);
        this.add(contentSouth, BorderLayout.SOUTH);

        this.navigation = navigation;
    }
    private void createJLabel() {
        //Settings
        Font fontStyleHomePage = new Font("Phosphate", Font.PLAIN, 25);
        Font fontStyleTextPage = new Font("Chalkboard", Font.PLAIN, 25);
        Border borderStyleHomePage = BorderFactory.createEtchedBorder(Color.lightGray, Color.ORANGE);
        //Title
        title.setVerticalAlignment(JLabel.CENTER);
        title.setAlignmentX(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(fontStyleHomePage);
        title.setPreferredSize(new Dimension(300, 50));
        title.setBorder(borderStyleHomePage);
        //Label south
        textSouth.setAlignmentX(JLabel.CENTER);
        textSouth.setHorizontalAlignment(JLabel.CENTER);
        textSouth.setPreferredSize(new Dimension(700, 200));
        textSouth.setFont(fontStyleTextPage);
        textSouth.setText("<html>Vous avez 7 coups pour trouver le mot caché! et si vous réussisez... et bien on recommence !<br /> Plus vous trouvé de mot, plus votre score grandira !! Alors à vous de jouer.<br /> Proverbe : \t\"Pas vue, pas pris! <br />\t Pris! PENDU !!!</html>");
        Border border = textSouth.getBorder();
        Border margin = new EmptyBorder(-10,50,10,50);
        textSouth.setBorder(new CompoundBorder(border, margin));
        //Button start
        buttonToStart.setPreferredSize(new Dimension(50, 30));

        buttonToStart.addActionListener(e -> navigation.setPage(2));
        rulesPages.addActionListener(e -> navigation.setPage(1));
    }
}
