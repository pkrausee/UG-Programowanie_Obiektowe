class Ulamek
{
    private int licznik;
    private int mianownik;

    public Ulamek(){}

    public Ulamek(int licz, int mian)
    {
        licznik = licz;
        mianownik = mian;
    }

    public static Ulamek razy(Ulamek u, Ulamek v)
    {
        Ulamek u2 = new Ulamek(u.licznik * v.licznik, u.mianownik * v.mianownik);
        return u2;
    }

    public void mnozPrzez(Ulamek v)
    {
        licznik = licznik * v.licznik;
        mianownik = mianownik * v.mianownik;
    }

    public void mnozPrzez(int i)
    {
        licznik = licznik * i;
    }

    public int getLicznik()
    {
        return licznik;
    }

    public int getMianownik()
    {
        return mianownik;
    }

    public void setLicznik(int l)
    {
        licznik = l;
    }

    public void setMianownik(int m)
    {
        mianownik = m;
    }

    public String toString()
    {
        return licznik + "/" + mianownik;
    }
}


class UlamekZP extends Ulamek
{
    private int pamLicznik;
    private int pamMianownik;
    private boolean cofniecie;

    public UlamekZP(int licz, int mian)
    {
        super(licz, mian);

        pamLicznik = licz;
        pamMianownik = mian;
        cofniecie = false;
    }

    public void mnozPrzez(Ulamek v)
    {
        pamLicznik = getLicznik();
        pamMianownik = getMianownik();
        cofniecie = true;

        super.mnozPrzez(v);
    }

    public void mnozPrzez(int i)
    {
        pamLicznik = getLicznik();
        pamMianownik = getMianownik();
        cofniecie = true;

        super.mnozPrzez(i);
    }

    public void cofnij()
    {
        if(sprawdz())
        {
            setLicznik(pamLicznik);
            setMianownik(pamMianownik);

            cofniecie = false;
        }
        else
            System.out.println("Cofniecie nie jest mozliwe.");
    }


    public boolean sprawdz()
    {
        /*
        if(pamMianownik == getMianownik() && pamLicznik == getLicznik())
            return false;
        else
            return true;
        */

        return cofniecie;
    }
}


class Main
{
    public static void main(String[] args)
    {
	UlamekZP u = new UlamekZP(1,3);
	System.out.println("u: " + u);  
	u.mnozPrzez(2);
	System.out.println("u * 2: " + u);
	System.out.println("Mozliwosc cofniecia: " + u.sprawdz());	
	u.cofnij();
	System.out.println("Po cofnieciu: " + u);
	System.out.println("Mozliwosc cofniecia: " + u.sprawdz());	
	u.cofnij();
    }
}




