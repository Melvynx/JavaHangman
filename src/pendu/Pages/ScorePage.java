package pendu.Pages;

import pendu.Navigation;

import javax.swing.*;
import java.awt.*;

public class ScorePage extends JPanel {
    ImagePanel penduImage = new ImagePanel();
    JLabel[] topScore = new JLabel[10];
    JPanel contentTopScore = new JPanel(new GridLayout(10,1));
    JPanel content = new JPanel(new GridLayout(1,2));

    public ScorePage(Navigation navigation) {
        tryScore();
        penduImage.setIndexSet(0);






    }
    void tryScore() {

    }
}
