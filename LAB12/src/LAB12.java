import java.sql.*;

class LAB12 {
    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
	Statement stmt2 = null;
       
	showPensja(1995, c);
	updatePensja(1997, c);
	showPensja(1995, c);

    }

    public static void updatePensja (int roczniki, Connection c) {
	String query1 = "SELECT * FROM tabela;";
	String query2 = "UPDATE tabela SET pensja = pensja*1.1 WHERE nazwisko = ";

	try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:main");
            c.setAutoCommit(false);

	    Statement stmt = c.createStatement();
            Statement stmt2 = c.createStatement();

            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {

                String nazwisko = rs.getString("nazwisko");
		int rocznik = rs.getInt("rocznik");
                int pensja = rs.getInt("pensja");
		if(rocznik >= roczniki) { 
			stmt2.executeUpdate(query2 + "\"" + nazwisko + "\"" + " AND rocznik = " + rocznik + ";");
			c.commit();
		}

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

	public static void showPensja (int roczniki, Connection c) {
	String query1 = "SELECT * FROM tabela;";

	try {
	    Class.forName("org.sqlite.JDBC");
	    c = DriverManager.getConnection("jdbc:sqlite:main");
	    c.setAutoCommit(false);

	    Statement stmt = c.createStatement();

	    ResultSet rs = stmt.executeQuery(query1);
	    while (rs.next()) {

		String nazwisko = rs.getString("nazwisko");
		int rocznik = rs.getInt("rocznik");
		int pensja = rs.getInt("pensja");
		if(rocznik >= roczniki) 
			System.out.println(nazwisko + "  " + rocznik + " " + pensja);

	    }
	    rs.close();
	    stmt.close();
	    c.close();
	} catch (Exception e) {
	    System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    System.exit(0);
	}
    }


}
