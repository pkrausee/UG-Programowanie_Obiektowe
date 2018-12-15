import  java.io.*;

class Stan
{
    private int x ; // pozycja biezaca
    private int y ;
    private int popX ;  // pozycja poprzednia
    private int popY ;
    private int punkty ;  // punktacja
    private int ciemnePola; // ilosc pozostalych ciemnych ruchow

    boolean koniec = false ;  // koniec = true gdy osiagnieto

    void wPrawo(){ popX = x; popY=y; x++; }
    void wLewo(){ popX = x;  popY=y; x--; }
    void wGore(){ popY = y; popX=x;  y++; }
    void wDol(){ popY = y;  popX=x; y--; }
    void wroc() { x=popX ; y = popY ; } // powrot na poprzednia pozycje

    void zmPunkty(int zm){ punkty+=zm; }
    void zmCiemnePola(int zm){ ciemnePola+=zm; }

    boolean czyCiemno() { return ciemnePola > 0; }

    int getX() { return x ; }
    int getY() { return y ; }

    String opis()
    {
        if (czyCiemno())
            return " /ciemnosc - "+ciemnePola+" ciemne pola/ "+punkty+"punktow\n" ;
        else
            return "("+x+","+y+")  "+punkty+"punktow\n" ;
    }

    Stan(int xPocz, int yPocz, int pPocz)
    {
        x = xPocz ; y = yPocz ; punkty=pPocz; ciemnePola = 0;
    }

}


abstract class Pole
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char czytajZnak()
    {
        //  czyta jeden znak z klawiatury, pomija znak konca linii
        int c = '\0';

        try
        {
            String linia = br.readLine();   //czytanie 1 linijki z klawiatury
            if (linia.length()>0) c = linia.charAt(0) ; // pobranie jednego znaku
        }
        catch (IOException e)
        {
            System.err.println(" error ");
        }

        return (char)c;
    }

    abstract String komentarz(); // daje napis zawierajacy komentarz charakterystyczny dla danego pola

    void ruch(Stan s)
    {
        // "ruch" modyfikuje stan s zgodnie z regulami danego pola
        System.out.print(komentarz());
        System.out.println(s.opis());
        System.out.print(" jaki ruch? [g,d,l,p] ") ;

        switch (czytajZnak())
        {
            case 'g' : s.wGore(); break;
            case 'd' : s.wDol(); break;
            case 'l' : s.wLewo(); break;
            case 'p' : s.wPrawo(); break;
        }

        if(s.czyCiemno())
        {
            s.zmCiemnePola(-1);
        }
    }
}


class Sciana extends Pole
{
    //wypisuje komentarz i wraca na poprzednie miejsce, odejmuje jeden punkt
    String komentarz(){ return "sciana! "; }

    void ruch(Stan s)
    {
        s.zmPunkty(-1);
        s.wroc();
        super.ruch(s);
    }
}


class ZwyklePole extends Pole
{
    // jak Pole, ponadto powinna odejmowac jeden punkt
    String komentarz() { return " zwykle pole"; }

    void ruch(Stan s)
    {
        s.zmPunkty(-1);
        super.ruch(s);
    }
}


class Wyjscie extends Pole
{
    //oferuje mozliwosc zakonczenia gry, a jezeli nie konczymy, to tak jak Pole
    String komentarz() { return " wyjscie. Chcesz kontynuowac? t/n: "; }

    void ruch(Stan s)
    {
        System.out.print(komentarz());
        switch (czytajZnak())
        {
            case 't' : s.wroc(); break;
            case 'n' : s.koniec = true; break;
        }
    }
}


class PolePremia extends Pole
{
    //dodaje 1 punkt
    String komentarz() { return " pole premia" ; }

    void ruch(Stan s)
    {
        s.zmPunkty(1);
        super.ruch(s);
    }
}


class Sciemnij extends Pole
{
    //
    String komentarz() { return " ciemno" ; }

    void ruch(Stan s)
    {
        s.zmCiemnePola(3);
        s.zmPunkty(-1);
        super.ruch(s);
    }
}


class Gra
{
    public static void main(String[] a)
    {
        // inicjalizacja "jaskini"
        int i,j ;
        int rozmiar = 10 ;
        Pole[][] jaskinia = new Pole[rozmiar][rozmiar] ;

        for (i=0; i<rozmiar; i++)
        {
            for (j = 0; j < rozmiar; j++)
            {
                if (i == 0 || i == rozmiar - 1 || j == 0 || j == rozmiar - 1)
                    jaskinia[i][j] = new Sciana();
                else
                    jaskinia[i][j] = new ZwyklePole();
            }
        }

        // gra wlasciwa
        jaskinia[3][2] = new Sciana();
        jaskinia[3][4] = new Wyjscie();
        jaskinia[2][3] = new PolePremia();
        jaskinia[4][3] = new Sciemnij();

        Stan s = new Stan(3,3,11) ;
        while (!s.koniec)
        {
            (jaskinia[s.getX()][s.getY()]).ruch(s) ;
        }

        System.out.println("\nDzieki za gre!");
    }
}