import java .awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class DebetException extends Exception
{
    String msg;
    DebetException(){ super(); }
    DebetException(String msg){ this.msg = msg; }
}


class Konto{
    private int stan;

    Konto() { stan = 0; }

    public void operacja(int ile) throws DebetException {
        if (stan + ile >= 0 )
            stan += ile;
        else
            throw new DebetException("o " + (-1)*(ile + stan) + " za duzo");
    }

    public int dajStan() { return stan ; }
}


public class GIdoObl extends JFrame{
    JTextField rezultat  = new JTextField(50);
    JTextField stan  = new JTextField(50);
    JTextField wartosc  = new JTextField(50);

    Konto k = new Konto();

    JButton wplata = new JButton("wplata/wyplata");
    JButton odblokuj = new JButton("odblokuj");

    GIdoObl(){
        setTitle("GI do Konto");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(3,2,50,50));

        cp.add(new JLabel("stan:"));
        cp.add(stan);
        stan.setText(Integer.toString(k.dajStan()));
        stan.setEnabled(false);

        cp.add(wplata);
        cp.add(wartosc);
        wplata.addActionListener(new Wplata());

        cp.add(rezultat);
        cp.add(odblokuj);
        odblokuj.addActionListener(new Odblokuj());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    int dajLiczbe(JTextField tf){
        try{
            return Integer.parseInt(tf.getText()) ;
        } catch (NumberFormatException e){ return 0 ; }
    }


    class Wplata implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try {
                k.operacja(dajLiczbe(wartosc));
                rezultat.setText("OK");
            }
            catch(DebetException de)
            {
                rezultat.setText(de.msg);
            }

            stan.setText(Integer.toString(k.dajStan()));

            wplata.setEnabled(false);
            wartosc.setEnabled(false);
        }
    }


    class Odblokuj implements ActionListener {
        public void actionPerformed(ActionEvent e){
            wartosc.setText("");
//            rezultat.setText("");
            wplata.setEnabled(true);
            wartosc.setEnabled(true);
        }
    }


    public static void main(String[] arg){
        JFrame gi = new GIdoObl() ;
        gi.setSize(400,300) ;
    }
}
