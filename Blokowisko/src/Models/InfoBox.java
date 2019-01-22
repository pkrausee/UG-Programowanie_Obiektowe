package Models;

import javax.swing.*;

public class InfoBox extends JFrame {
    private JLabel text1;
    private JLabel text2;

    public InfoBox(String boxName, String text2) {
        this.text1 = new JLabel(boxName, SwingConstants.CENTER);
        this.text2 = new JLabel(text2, SwingConstants.CENTER);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public JLabel getText1() {
        return text1;
    }

    public void setText1(JLabel name) {
        this.text1 = name;
    }

    public JLabel getText2() {
        return text2;
    }

    public void setText2(JLabel text2) {
        this.text2 = text2;
    }
}
