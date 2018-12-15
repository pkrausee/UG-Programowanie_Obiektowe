import java.util.ArrayList;

class Student {
    private String imie;
    private ArrayList<Integer> oceny;

    Student(String imie) {
        this.imie = imie;
        oceny = new ArrayList<Integer>();
    }

    public int dajNajwOcene() {
        int max = 0;
        for (int o : oceny) {
            if (max < o)
                max = o;
        }
        return max;
    }

    public void dodajOcene(int o) {
        oceny.add(o);
    }

    public String getImie() {
        return imie;
    }
}

class WykazS {
    private ArrayList<Student> w = new ArrayList<Student>();

    void test() {
        for (Student s : w) {
            if (s.getImie().equals("Jan"))
                System.out.println(s.dajNajwOcene());
        }
    }

    void dodajStudenta(Student s) {
        w.add(s);
    }
}

class SprawdzianWS {
    public static void main(String[] args) {
        WykazS wykaz = new WykazS();

        Student s1 = new Student("Janek");
        s1.dodajOcene(1);
        s1.dodajOcene(2);
        s1.dodajOcene(4);

        Student s2 = new Student("Jan");
        s2.dodajOcene(2);
        s2.dodajOcene(2);
        s2.dodajOcene(3);

        wykaz.dodajStudenta(s1);
        wykaz.dodajStudenta(s2);

        wykaz.test();
    }
}