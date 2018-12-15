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

	//Zadanie 1
	public void trzyRazy()
	{
		licznik *= 3;
	}
}


class Para
{
	public Ulamek x;
	public Ulamek y;
	
	public Para(Ulamek x, Ulamek y)
	{
		this.x = x;
		this.y = y;
	}

	public void zwieksz()
	{
		x.trzyRazy();
		y.trzyRazy();
	}

	public String toString()	
	{
		return x.toString() + " , " + y.toString();
	}
}


class TestUlamek
{
	public static void main(String[] args)
	{
		//Test zadanie 1
		Ulamek u = new Ulamek(3,4);		
		Ulamek v = new Ulamek(2,3);
		u.trzyRazy();
		System.out.println("u.trzyRazy(): " + u);
		Para p = new Para(v,u);
		System.out.println(p);
		p.zwieksz();
		System.out.println(p);
	}
}












