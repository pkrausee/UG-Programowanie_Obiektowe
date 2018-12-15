// ilustracja ArrayList i iteratora w Javie

import java.util.*;

class Punkt{
    int x,y ;
    public boolean equals(Object o)
    {
        if (!(o instanceof Punkt)) return false;
        Punkt p = (Punkt)o;
        return (p.x==x) && (p.y==y);
    }
    public String toString() { return "(" + x  + "," + y +")"; }
    Punkt(int x,int y) { this.x=x; this.y=y; }
}

class TestList{
    public static void main(String[] args){
        ArrayList<Punkt> lp = new ArrayList<Punkt>();
        lp.add(new Punkt(0,0)) ;  // wstaw
        lp.add(new Punkt(1,1)) ;  // wstaw
        System.out.println(lp);
        lp.set(0,new Punkt(0,3)); // zastap
        System.out.println(lp);

        Punkt p,q;
        q = new Punkt(0,3);
        int k = lp.indexOf(q);   // szukaj (uzywa equals() !)
        System.out.println(q + " na pozycji " + k);

        for (int j=0; j<lp.size(); j++){   // petla  po liscie
            p = lp.get(j);
            System.out.println(p.x + "-");
        }

        // iterator po liscie
        for (Iterator<Punkt> i = lp.iterator(); i.hasNext(); ){
            p = i.next();
            if (p.x == p.y ) i.remove();
        }
        System.out.println(lp);

        // petla "for each"
        lp.add(new Punkt(12,12)) ;
        lp.add(new Punkt(13,13)) ;
        for (Punkt r : lp) {
            System.out.println(r.y + " ");
        }
        System.out.println(lp);
    }
}
