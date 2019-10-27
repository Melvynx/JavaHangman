package pendu.Pages;

import pendu.Navigation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesPages extends JPanel {
    JLabel title = new JLabel();
    JLabel textCenter = new JLabel();



    public RulesPages(Navigation navigation) {
        createJLabel();

        JButton restartButton = new JButton("Commencez !");
        JButton homePage = new JButton("High score !");
        JPanel contentSouth = new JPanel(new GridLayout(1,2));
        JPanel buttonPanel = new JPanel();
        restartButton.setPreferredSize(new Dimension(200, 75));
        homePage.setPreferredSize(new Dimension(200, 75));
        buttonPanel.setPreferredSize(new Dimension(400, 100));
        buttonPanel.add(restartButton);
        buttonPanel.add(homePage);

        contentSouth.add(buttonPanel, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(textCenter, BorderLayout.CENTER);
        this.add(contentSouth, BorderLayout.SOUTH);


        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigation.setPage(2);
            }
        });
        homePage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigation.setPage(3);
            }
        });
    }
    public void createJLabel() {
        Font fontStyleText = new Font("Chalkboard", Font.LAYOUT_LEFT_TO_RIGHT, 20);
        Font fontStyleTitle = new Font("Chalkboard", Font.BOLD, 40);
        title.setText("Le jeu du PENDU : ");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(300, 100));
        title.setFont(fontStyleTitle);
        textCenter.setFont(fontStyleText);
        textCenter.setText("<html>" +
                "Vous avez 7 coups pour trouver le mot caché ! Et si vous réussisez : en recommence ! <br />" +
                "Plus vous avez trouvé de mots, plus votre score grandira !! Alors à vous de jouer !<br /><br />" +
                "COMPTE DES POINTS : <br /> <br />" +
                "       " + "Mot trouvé sans erreur : 100pts.<br />" +
                "       Mot trouvé avec 1 erreur : 50pts.<br />" +
                "       " + "Mot trouvé avec 2 erreur : 40pts.<br />" +
                "       " + "Mot trouvé avec 3 erreur : 25pts.<br />" +
                "       " + "Mot trouvé avec 4 erreur : 15pts.<br />" +
                "       " + "Mot trouvé avec 5 erreur : 10pts.<br />" +
                "       " + "Mot trouvé avec 6 erreur : 5pts.<br /><br />" +
                "Je vous souhaite bien du plaisir...<br/>" +
                "Et si vous pensez pouvoir trouver un mot en un eul coup, c'est que vous pensez que le dictionnaire st petit !<br />" +
                "Hors, pour votre Information, il comprend plus de 330 000 mots... Donc bonne chance !! :)" +
                "</html>");
        Border border = textCenter.getBorder();
        Border margin = new EmptyBorder(10,50,10,50);
        textCenter.setBorder(new CompoundBorder(border, margin));
    }
}
