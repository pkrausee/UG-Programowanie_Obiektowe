package GIdoObl;

import Obliczenia.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GIdoObl extends Panel {
    TextField
            dane = new TextField(20),
            wynik = new TextField(20);
    IObliczenia ob = new Obliczenia();
    Button
            obl1 = new Button("obliczenie 1"),
            obl2 = new Button("obliczenie 2");

    public GIdoObl() {
        setLayout(new GridLayout(4, 2, 10, 10));
        add(new Label("Argument:"));
        add(dane);
        obl1.addActionListener(new Obl1L());
        obl2.addActionListener(new Obl2L());
        add(obl1);
        add(obl2);
        add(new Label("")); // odstep
        add(new Label(""));
        add(new Label("Wynik:"));
        add(wynik);
    }

    int dajLiczbe(TextField tf) {
        try {
            return Integer.parseInt(tf.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    class Obl1L implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            wynik.setText(Integer.toString(
                    ob.oblicz1(dajLiczbe(dane))));
        }
    }

    class Obl2L implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            wynik.setText(Integer.toString(
                    ob.oblicz2(dajLiczbe(dane))));
        }
    }
}