import java.util.*;

class ZlaOcenaException extends Exception
{
	int i;
	ZlaOcenaException(){ super(); }
	ZlaOcenaException(String msg){ super(msg); }
	ZlaOcenaException(int i){ this.i = i; };
}


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

	public void addOcena(Integer ocena) throws ZlaOcenaException
	{
		if(ocena >= 1 && ocena <= 6)
			oceny.add(ocena);
		else
			throw new ZlaOcenaException("Zla ocena: " + ocena);
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
	private ArrayList<Uczen> wykaz = new ArrayList<Uczen>();

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

		try
		{
			wykaz.get(idUcznia).addOcena(ocena);
		}
		catch(ZlaOcenaException zoe)
		{
			System.out.println(zoe.getMessage());
		}
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

	public double srednia()
	{
		int sumO = 0;
		int ilO = 0;

		for(Uczen u : wykaz)
		{
			for(int o : u.getOceny())
			{
				sumO += o;
				ilO++;
			}
		}

		return (double)sumO / ilO;
	}


	public ArrayList<Uczen> getWykaz()
	{
		return wykaz;
	}
}

class SortA implements Comparator<Uczen>
{
	public int compare(Uczen u1, Uczen u2)
	{
		return (u1.getImie().compareTo(u2.getImie()));
	}
}

class SortS implements Comparator<Uczen>
{
	public int compare(Uczen u1, Uczen u2)
	{
		double sumU1 = 0;
		double sumU2 = 0;

		for(int a : u1.getOceny())
		{
			sumU1 += a;
		}

		for(int b : u2.getOceny())
		{
			sumU2 += b;
		}

		sumU1 = sumU1 /  u1.getOceny().size();
		sumU2 = sumU2 / u2.getOceny().size();


		return (Double.compare(sumU1, sumU2));
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
		//wu.wypisz("K");

		Collections.sort(wu.getWykaz(), new SortA());

		wu.wypisz();

		Collections.sort(wu.getWykaz(), new SortS());

		wu.wypisz();

		System.out.println("Srednia ocen uczniow: " + wu.srednia());


		wu.wstawOcene("K", -1);

	}
}