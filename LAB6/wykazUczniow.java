import java.util.ArrayList;


class Uczen
{
    private String id;
    private String imie;
    private ArrayList<Integer> oceny;

    Uczen( String id, String imie)
    {
        this.id = id;
        this.imie = imie;
        this.oceny = new ArrayList<Integer>();
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Uczen))
            return false;

        Uczen u = (Uczen) o;

        return u.id.equals(id);
    }

    public String toString()
    {
        String info = "";
        StringBuilder sB = new StringBuilder(info);

        sB.append(id);
        sB.append("\n");
        sB.append(imie);
        sB.append("\nOceny: ");

        for (Integer o : oceny)
        {
            sB.append(o);
            sB.append(" ");
        }
        sB.append("\n");

        info = sB.toString();

        return info;
    }

    public void addOcena(Integer ocena)
    {
        if(ocena >= 1 && ocena <= 6)
            oceny.add(ocena);
        else
            System.out.println("Nie mozna dodac oceny spoza zakresu\n");
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getImie()
    {
        return imie;
    }

    public void setImie(String imie)
    {
        this.imie = imie;
    }

    public ArrayList<Integer> getOceny()
    {
        return oceny;
    }

    public void setOceny(ArrayList<Integer> oceny)
    {
        this.oceny = oceny;
    }
}


class WykazU
{
    private ArrayList<Uczen> wykaz;

    WykazU()
    {
        wykaz = new ArrayList<Uczen>();
    }


    public void wstawUcznia(String id, String imie)
    {
        Uczen temp = new Uczen(id, imie);

        if(!wykaz.contains(temp))
            wykaz.add(temp);
        else
            System.out.println("Podane id jest juz zajete\n");
    }

    public void wstawOcene(String id, Integer ocena)
    {
        Uczen temp = new Uczen(id, "");

        int idUcznia = wykaz.indexOf(temp);

        wykaz.get(idUcznia).addOcena(ocena);
    }

    public void wypisz(String id)
    {
        Uczen temp = new Uczen(id, "");

        int idUcznia = wykaz.indexOf(temp);

        System.out.println(wykaz.get(idUcznia).toString());
    }

    public void wypisz()
    {
        String info = "";
        StringBuilder sB = new StringBuilder(info);

        sB.append("Lista uczniow: \n\n");

        for (Uczen u : wykaz)
        {
            sB.append(u.toString());
            sB.append("\n");
        }


        info = sB.toString();

        System.out.println(info);
    }
}


class Main
{
    public static void main(String args[])
    {
        WykazU wu = new WykazU();

        wu.wstawUcznia("K","Kazik");
        wu.wstawUcznia("K1","Kazik");
        wu.wstawUcznia("N","Nikodem");
        wu.wstawUcznia("J","Jan");
        wu.wstawUcznia("W","Wiesiek");

        wu.wstawOcene("K",5);
        wu.wstawOcene("K",4);
        wu.wstawOcene("K",3);
        wu.wstawOcene("K",5);
        wu.wstawOcene("K",2);

        wu.wstawOcene("K1",5);
        wu.wstawOcene("K1",6);

        wu.wstawOcene("N",4);
        wu.wstawOcene("N",5);
        wu.wstawOcene("N",4);

        wu.wstawOcene("J",3);
        wu.wstawOcene("W",5);

        //wu.wypisz();
        wu.wypisz("K");
    }
}