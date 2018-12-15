import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Komorka {
    private int wartosc = 0;

    void setWartosc(int w) {
        wartosc = w;
    }

    int getWartosc() {
        return wartosc;
    }
}

class TrzyKomorki {
    private Komorka[] tk = new Komorka[3];

    TrzyKomorki() {
        int i;
        for (i = 0; i < 3; i++)
            tk[i] = new Komorka();
    }

    void losuj() {
        for (int i = 0; i < 3; i++)
            tk[i].setWartosc((int) (Math.random() * 10));
    }

    int getValKom1() {
        return tk[0].getWartosc();
    }

    int getValKom2() {
        return tk[1].getWartosc();
    }

    int getValKom3() {
        return tk[2].getWartosc();
    }

    Komorka getKom1() {
        return tk[0];
    }

    Komorka getKom2() {
        return tk[1];
    }

    Komorka getKom3() {
        return tk[2];
    }

    void setKom1(Komorka k) {
        tk[0] = k;
    }

    void setKom2(Komorka k) {
        tk[1] = k;
    }

    void setKom3(Komorka k) {
        tk[2] = k;
    }
}

public class zad3 extends JFrame {
    JTextField komorka1 = new JTextField(50);
    JTextField komorka2 = new JTextField(50);
    JTextField komorka3 = new JTextField(50);

    TrzyKomorki tk = new TrzyKomorki();

    JButton button1 = new JButton("Pokaz wartosci");
    JButton button2 = new JButton("Losuj wartosci");
    JButton button3 = new JButton("Zamien wartosci");

    zad3() {
        setTitle("Sprawdzian zad 3");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(5, 2, 50, 50));

        cp.add(new JLabel("Komorka 1"));
        cp.add(komorka1);
        cp.add(new JLabel("Komorka 2"));
        cp.add(komorka2);
        cp.add(new JLabel("Komorka 3"));
        cp.add(komorka3);
        komorka1.setEnabled(false);
        komorka2.setEnabled(false);
        komorka3.setEnabled(false);

        cp.add(button1);
        cp.add(button2);
        cp.add(button3);
        button1.addActionListener(new Button1AL());
        button2.addActionListener(new Button2AL());
        button3.addActionListener(new Button3AL());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    class Button1AL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            komorka1.setText(String.valueOf(tk.getValKom1()));
            komorka2.setText(String.valueOf(tk.getValKom2()));
            komorka3.setText(String.valueOf(tk.getValKom3()));
        }
    }

    class Button2AL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tk.losuj();
        }
    }

    class Button3AL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Komorka temp = tk.getKom1();
            tk.setKom1(tk.getKom2());
            tk.setKom2(temp);
        }
    }

    public static void main(String[] arg) {
        JFrame gi = new zad3();
        gi.setSize(400, 600);
    }
}