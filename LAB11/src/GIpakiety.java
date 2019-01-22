import java.awt.*;
import java.awt.event.*;
import GIdoObl.*;


class Uzytkowa {
    public static void main(String[] arg) {
        Frame f = new Frame("Obliczenia");
        f.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        f.add(new GIdoObl());
        f.setSize(200, 200);
        f.setVisible(true);

    }
}