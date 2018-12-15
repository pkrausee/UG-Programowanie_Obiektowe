class Ulamek
{
	public int licznik;
	public int mianownik;

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
	

	public String toString()
	{	
		return this.licznik + "/" + this.mianownik;
	}
}


class TestUlamek
{
	public static void main(String[] args)
	{
		Ulamek u1 = new Ulamek(1,2);
		Ulamek u2 = new Ulamek(2,4);
		Ulamek u3 = u1.razy(u1,u2);

		System.out.println("u1 = " + u1);
		System.out.println("u2 = " + u2);
		System.out.println("u3 = " + u3);
		
		u1.mnozPrzez(u2);
		
		System.out.println("u1 * u2 = " + u1);

		u2.mnozPrzez(4);

		System.out.println("u2 * 4 = " + u2);	
	}
}












