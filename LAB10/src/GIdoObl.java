import java .awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;


class DebetException extends Exception
{
    String msg;
    DebetException(){ super(); }
    DebetException(String msg){ this.msg = msg; }
}


class Konto implements Serializable
{
    private int stan;
    private ArrayList<Integer> ostOperacje = new ArrayList<>();

    Konto() { stan = 0; }

    public void operacja(int ile) throws DebetException
    {
        if (stan + ile >= 0)
        {
            ostOperacje.add(stan);

            stan += ile;
        }
        else
            throw new DebetException("o " + (-1)*(ile + stan) + " za duzo");
    }

    public void cofnijOperacje() throws DebetException
    {
        if(ostOperacje.size() > 0)
        {
            stan = ostOperacje.get(ostOperacje.size() - 1);

            ostOperacje.remove(ostOperacje.size() - 1);
        }
        else
            throw new DebetException("brak operacji do cofniecia");
    }

    public int dajOstOperacje()
    {
        if (ostOperacje.size() > 0)
            return ostOperacje.get(ostOperacje.size() - 1);
        else
            return 0;
    }

    public int dajStan() { return stan ; }
}


public class GIdoObl extends JFrame{
    JTextField rezultat  = new JTextField(50);
    JTextField stan  = new JTextField(50);
    JTextField wartosc  = new JTextField(50);
    JTextField ostOper = new JTextField(50);

    Konto k = new Konto();

    JButton wplata = new JButton("wplata/wyplata");
    JButton odblokuj = new JButton("odblokuj");
    JButton cofnij = new JButton("cofnij");
    JButton zapisz = new JButton("zapisz");
    JButton wczytaj = new JButton("wczytaj");

    GIdoObl()
    {
        setTitle("GI do Konto");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(5,2,50,50));

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

        cp.add(cofnij);
        cp.add(ostOper);
        cofnij.addActionListener(new CofnijOperacje());

        cp.add(zapisz);
        cp.add(wczytaj);
        zapisz.addActionListener(new Zapisz());
        wczytaj.addActionListener(new Wczytaj());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    class CofnijOperacje implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                k.cofnijOperacje();
                rezultat.setText("OK");
                ostOper.setText(String.valueOf(k.dajOstOperacje()));
            }
            catch(DebetException de) { rezultat.setText(de.msg); }

            stan.setText(Integer.toString(k.dajStan()));
            wplata.setEnabled(false);
            wartosc.setEnabled(false);
            cofnij.setEnabled(false);
        }
    }


    class Wplata implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                k.operacja(Integer.parseInt(wartosc.getText()));
                ostOper.setText(String.valueOf(k.dajOstOperacje()));
                rezultat.setText("OK");
            }
            catch(DebetException de) { rezultat.setText(de.msg); }
            catch(NumberFormatException nfe) { rezultat.setText("Bledna wartosc"); }

            stan.setText(Integer.toString(k.dajStan()));
            wplata.setEnabled(false);
            wartosc.setEnabled(false);
            cofnij.setEnabled(false);
        }
    }


    class Odblokuj implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            wartosc.setText("0");
            wplata.setEnabled(true);
            wartosc.setEnabled(true);
            cofnij.setEnabled(true);
        }
    }


    class Zapisz implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                FileOutputStream f = new FileOutputStream("zapis");
                ObjectOutputStream os = new ObjectOutputStream(f);
                os.writeObject(k);
                f.close();

                rezultat.setText("Zapisano do pliku");
            }
            catch (IOException ex){ System.out.println("--wyjatek!"); }
        }
    }


    class Wczytaj implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                ObjectInputStream is = new ObjectInputStream(
                        new FileInputStream("zapis"));
                k = (Konto)is.readObject();
                is.close();

                rezultat.setText("Wczytano z pliku");
                stan.setText(Integer.toString(k.dajStan()));
                ostOper.setText(String.valueOf(k.dajOstOperacje()));
            }
            catch (IOException ex){ System.out.println("--wyjatek!"); }
            catch (ClassNotFoundException ex){}
        }
    }


    public static void main(String[] arg)
    {
        JFrame gi = new GIdoObl() ;
        gi.setSize(400,600) ;
    }
}
