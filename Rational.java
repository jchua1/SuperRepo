public class Rational {

    private int num, denom;

    public Rational() {
	num = 0;
	denom = 1;
    }

    public Rational(int x, int y) {
	this();
	if (y == 0) {
	    System.out.println("You cannot have a denominator of 0. Your rational number has automatically been set to 0/1.");
	}
	else {
	    num = x;
	    denom = y;
	}
    }

    public String toString() {
	String retStr = "";
	retStr += num + "/" + denom;
	return retStr;
    }

    public double floatValue() {
        return ((double)num / denom);
    }

    public void multiply(Rational x) {
        num *= x.num;
	denom *= x.denom;
    }

    public void divide(Rational x) {
        num *= x.denom;
	denom *= x.num;
    }

    public void add(Rational x) {
        num = (num * x.denom) + (x.num * denom);
        denom *= x.denom;
    }

    public void subtract(Rational x) {
        num = (num * x.denom) - (x.num * denom);
        denom *= x.denom;
    }

    public int gcd(){
	int tracker = num;
	int a = num;
	int b = denom;
	while (b != 0){
	    tracker = a;
	    a = b;
	    b = tracker%b;
	}
	return a;
    }

    public void reduce() {
	int gcd = gcd();
	if (gcd != 1) {
	    num /= gcd;
	    denom /= gcd;
	}
    }

    public static int gcd(int n, int d) {
	int tracker = n;
	while (d != 0) {
	    tracker = n;
	    n = d;
	    d = tracker%d;
	}
	return n;
    }

    public int compareTo(Object o) {
	int thisNumerator, otherNumerator;
	if (!(o instanceof Rational)) {
	    throw new ClassCastException("\ncompareTo() input not a Rational");
	}
	if (o == null) {
	    throw new NullPointerException("\ncompareTo() input is null");
	}
	else {
	    Rational x = (Rational)o;
	    thisNumerator = num * x.denom;
	    otherNumerator = denom * x.num;
	}
	return thisNumerator - otherNumerator;
    }

    //overridden equals method
    public boolean equals(Object x) {
        boolean retVal = this == x;

        if (!retVal) {

            retVal = x instanceof Rational
                && (this.compareTo((Rational)x) == 0);
        }
        return retVal;
    }

    public static void main(String[] args){
	Rational r = new Rational(2,3);
	Rational s = new Rational(1,2);
	System.out.println(r.floatValue());
	System.out.println(s.floatValue());
	r.multiply(s);
	System.out.println(r);
	System.out.println(s);
	System.out.println(r.floatValue());

	Rational t = new Rational();
	Rational u = new Rational(1,0);
	Rational v = new Rational(5,1);
	System.out.println(t);
	System.out.println(u);
	System.out.println(v);
	System.out.println(v.floatValue());
	u.divide(v);
	System.out.println(u);

        Rational a = new Rational(2,5);
        Rational b = new Rational(1,3);
        a.add(b);
        System.out.println(a);

        Rational x = new Rational(2,5);
        Rational y = new Rational(1,3);
        x.subtract(y);
        System.out.println(x);

	Rational c = new Rational(36,45);
	Rational d = new Rational(45,36);
	System.out.println(c.gcd());
	System.out.println(d.gcd());
	c.reduce();
	System.out.println(c);

	Rational e = new Rational(2,5);
	e.reduce();
	System.out.println(e);

	Rational f = new Rational(2,3);
	Rational g = new Rational(1,2);
	Rational h = new Rational(4,18);
	f.add(g);
	System.out.println(f);
	System.out.println(g);
	h.reduce();
	System.out.println(h);

	System.out.println(gcd(2,5));
	System.out.println(gcd(3,6));
	System.out.println(gcd(36,45));

	Rational i = new Rational(1,2);
	Rational j = new Rational(2,3);
	System.out.println(j.compareTo(i));
	System.out.println(i.compareTo(j));
	Rational k = new Rational(1,2);
	Rational l = new Rational(2,4);
	System.out.println(i.compareTo(k));
	System.out.println(k.compareTo(l));

	Rational m = new Rational(-1,2);
	Rational n = new Rational(2,-4);
	System.out.println(i.equals(k));
	System.out.println(k.equals(l));
	System.out.println(j.equals(k));
	System.out.println(m.equals(n));
    }
}
