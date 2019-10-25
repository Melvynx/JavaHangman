package pendu.Pages;

import javax.swing.*;
import java.awt.*;

class TextPanel extends JPanel {
    private String text = "Test";
    private JTextArea jta;
    TextPanel() {
        this.jta = new JTextArea(text);
        this.setBackground(Color.white);
        this.add(jta);
    }

    public void setText(String text) {
        this.text = text;
    }
}
