class Ulamek
{
	private int licznik;
	private int mianownik;

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
		this.licznik = this.licznik * v.licznik;		
		this.mianownik = this.mianownik * v.mianownik;
	}
	
	public void mnozPrzez(int i)
	{
		this.licznik = this.licznik * i;
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
		return this.licznik + "/" + this.mianownik;
	}
}


class LiczbaU
{
	private int calosci;
	public Ulamek czescU;
	
	public void wylaczC()
	{
		if(czescU.getLicznik() > czescU.getMianownik())
		{
			calosci += czescU.getLicznik() / czescU.getMianownik();
			czescU.setLicznik(czescU.getLicznik() % czescU.getMianownik());
		}
	}

	LiczbaU(int c, int l, int m)
	{
		calosci = c;
		czescU = new Ulamek(0,0);
		czescU.setLicznik(l);
		czescU.setMianownik(m);
		
		wylaczC();	
	}

	public LiczbaU mnozPrzez(LiczbaU l) 
	{
		Ulamek u1 = new Ulamek(l.calosci*l.czescU.getMianownik() + l.czescU.getLicznik(), l.czescU.getMianownik());

		Ulamek u2 = new Ulamek(calosci*czescU.getMianownik() + czescU.getLicznik(), czescU.getMianownik());

		u1.mnozPrzez(u2);
	
		LiczbaU l2 = new LiczbaU (0,0,0);

		l2.calosci = 0;
		l2.czescU.setLicznik(u1.getLicznik());
		l2.czescU.setMianownik(u1.getMianownik());

		l2.wylaczC();

		return l2;
  	}

	public LiczbaU mnozPrzez(int i) 
	{
		Ulamek u1 = new Ulamek(calosci*czescU.getMianownik() + czescU.getLicznik(), czescU.getMianownik());

		u1.mnozPrzez(i);

		LiczbaU l2 = new LiczbaU (0,0,0);

		l2.calosci = 0;
		l2.czescU.setLicznik(u1.getLicznik());
		l2.czescU.setMianownik(u1.getMianownik());

		l2.wylaczC();

		return l2;
  	}

	public LiczbaU mnozPrzez(Ulamek u) 
	{
		Ulamek u1 = new Ulamek(calosci*czescU.getMianownik() + czescU.getLicznik(), czescU.getMianownik());

		u1.mnozPrzez(u);

		LiczbaU l2 = new LiczbaU (0,0,0);

		l2.calosci = 0;
		l2.czescU.setLicznik(u1.getLicznik());
		l2.czescU.setMianownik(u1.getMianownik());

		l2.wylaczC();

		return l2;
  	}

	public String toString()
	{	
		String message = "";
		if(calosci != 0)
			message += calosci + " ";
		if(czescU.getLicznik() != 0)
			message += czescU.getLicznik() + "/" + czescU.getMianownik();
		return message;
	}
}


class TestUlamek
{
	public static void main(String[] args)
	{
		LiczbaU l1 = new LiczbaU(1,1,2);
		LiczbaU l2 = new LiczbaU(1,3,2);		
		System.out.println(l1);
		System.out.println(l2);

		LiczbaU l3 = l1.mnozPrzez(l2);
		System.out.println(l3);

		LiczbaU l4 = l1.mnozPrzez(2);
		System.out.println(l4);

		Ulamek u1 = new Ulamek(1,2);
		LiczbaU l5 = l1.mnozPrzez(u1);
		System.out.println(l5);
	}
}












