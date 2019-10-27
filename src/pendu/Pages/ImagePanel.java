package pendu.Pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    Image[] image;
    Integer indexSet = 0;

    public ImagePanel() {
        try {
            image = new Image[]{ImageIO.read(new File("images/initial.jpg")), ImageIO.read(new File("images/try1.jpg")), ImageIO.read(new File("images/try2.jpg")), ImageIO.read(new File("images/try3.jpg")), ImageIO.read(new File("images/try4.jpg")), ImageIO.read(new File("images/try5.jpg")), ImageIO.read(new File("images/try6.jpg")), ImageIO.read(new File("images/try7.jpg")), ImageIO.read(new File("images/try8.jpg")),ImageIO.read(new File("images/lose.png")),ImageIO.read(new File("images/looser.jpeg")),ImageIO.read(new File("images/gagner.png")),};
        } catch (IOException e) {

        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = (this.getWidth() - image[indexSet].getWidth(null)) / 2;
        int y = (this.getHeight() - image[indexSet].getHeight(null)) / 2;
        g2d.drawImage(image[indexSet], x, y, null);
    }

    public void setIndexSet(Integer indexSet) {
        this.indexSet = indexSet;
    }
}
