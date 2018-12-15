


class X extends Exception {
    String msg;
    X(){ super(); }
    X(String msg){ this.msg = msg; }
}


class A {
    int g(int i) throws X {
        if(i < 15)
            return i;
        else
            throw (new X());
    }

    int f(int i) {
        return i + 1;
    }
}


class Test {
    public static void main(String[] args) {
        A a = new A();
        int k = Integer.parseInt("10");
        try {
            System.out.println(a.g(k));
        } catch (X ex) {
            System.out.println(a.f(k));
        }
    }
}
