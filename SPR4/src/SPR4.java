import java.sql.*;

class SPR4 {
    public static void showPensja() {
        String query1 = "SELECT * FROM tabela;";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:main");
            c.setAutoCommit(false);

            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                String nazwisko = rs.getString("nazwisko");
                int rocznik = rs.getInt("rocznik");
                int pensja = rs.getInt("pensja");

                System.out.println("\t" + nazwisko + "  " + rocznik + " " + pensja);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static int sumPensja(Connection c) {
        String query = "SELECT SUM(\"pensja\") AS SUMA FROM tabela;";

        try {
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) { ;
                int suma = rs.getInt("SUMA");

                return suma;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return 0;
    }

    public static void dodajSprawdzian() {
        String query = "INSERT INTO tabela (nazwisko, rocznik, pensja) VALUES (";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:main");
            c.setAutoCommit(false);

            Statement stmt = c.createStatement();

            System.out.println("Suma pensji: " + sumPensja(c));
            stmt.executeUpdate(query + "\"Sprawdzian\", 2019, " + sumPensja(c) + ");");
            c.commit();

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}

class Main {
    public static void main (String[] args) {
        SPR4.showPensja();
        SPR4.dodajSprawdzian();
        SPR4.showPensja();
    }

}
