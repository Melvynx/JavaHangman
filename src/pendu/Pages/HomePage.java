package pendu.Pages;



import pendu.Navigation;
import pendu.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomePage extends JPanel {
    private final Navigation navigation;
    private JLabel title = new JLabel("Bienvenue dans le jeu du PENDU");
    private JLabel textSouth = new JLabel("");
    private JPanel contentSouth = new JPanel();
    private Image image;
    private ImagePanel imagePendu = new ImagePanel();
    private TextPanel panText = new TextPanel();
    private JButton buttonToStart = new JButton("Commencez !");



    public HomePage(Navigation navigation) {
        try {
            image = ImageIO.read(new File("images/initial.jpg"));
        } catch (IOException e) {
            image = null;
        }

        createJLabel();
        contentSouth.setLayout(new BorderLayout());
        contentSouth.add(textSouth, BorderLayout.CENTER);
        contentSouth.add(buttonToStart, BorderLayout.SOUTH);
        imagePendu.setIndexSet(0);
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(imagePendu, BorderLayout.CENTER);
        this.add(contentSouth, BorderLayout.SOUTH);

        this.navigation = navigation;
    }
    private void createJLabel() {
        //Settings
        Font fontStyleHomePage = new Font("Chalkboard", Font.LAYOUT_LEFT_TO_RIGHT, 25);
        Border borderStyleHomePage = BorderFactory.createEtchedBorder(Color.lightGray, Color.ORANGE);
        //Title
        title.setVerticalAlignment(JLabel.CENTER);
        title.setAlignmentX(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(fontStyleHomePage);
        title.setPreferredSize(new Dimension(500, 100));
        title.setBorder(borderStyleHomePage);
        //Label south
        textSouth.setAlignmentX(JLabel.CENTER);
        textSouth.setHorizontalAlignment(JLabel.CENTER);
        textSouth.setPreferredSize(new Dimension(700, 300));
        textSouth.setFont(fontStyleHomePage);
        textSouth.setText("<html>Vous avez 7 coups pour trouver le mot caché! et si vous réussisez... et bien on recommence !<br /> Plus vous trouvé de mot, plus votre score grandira !! Alors à vous de jouer.<br /> Proverbe : \t\"Pas vue, pas pris! <br />\t Pris! PENDU !!!</html>");
        Border border = textSouth.getBorder();
        Border margin = new EmptyBorder(10,50,10,50);
        textSouth.setBorder(new CompoundBorder(border, margin));
        //Button start
        buttonToStart.setPreferredSize(new Dimension(50, 30));
        buttonToStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigation.setPage(2);
            }
        });
    }
}
