/*
  Team W-Fd -- Jason Chua, Dhruba Roy
  APCS1 pd9
  HW37 -- Rational Equality
  2015-11-24
*/

public class Rational {

    //instance variables for numerator and denominator
    private int num, denom;

    //default constructor that sets numerator to 0 and denominator to 1
    public Rational() {
	num = 0;
	denom = 1;
    }

    //overloaded constructor that takes two arguments to set numerator and denominator
    //if given denominator is 0, numerator is set to 0 and denominator is set to 1 instead
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

    //returns numerator and denominator separated by a /
    public String toString() {
	String retStr = "";
	retStr += num + "/" + denom;
	return retStr;
    }

    //returns floating point value of the number by changing numerator to double and then dividing by the denominator
    public double floatValue() {
        return ((double)num / denom);
    }

    //multiplies the numerator and denominator by those of the given Rational
    public void multiply(Rational x) {
        num *= x.num;
	denom *= x.denom;
    }

    //divides the numerator and denominator by those of the given Rational
    public void divide(Rational x) {
        num *= x.denom;
	denom *= x.num;
    }

    //num becomes the sum of two products
    //first prod: this numerator and the given Rational's denominator
    //second prod: the given Rational's numerator and this denominator
    //this denominator becomes the product of this one and the other one
    public void add(Rational x) {
        num = (num * x.denom) + (x.num * denom);
        denom *= x.denom;
    }

    //num becomes the difference between two products
    //first prod: this numerator and the given Rational's denominator
    //second prod: the given Rational's numerator and this denominator
    //this denominator becomes the product of this one and the other one
    public void subtract(Rational x) {
        num = (num * x.denom) - (x.num * denom);
        denom *= x.denom;
    }

    //uses local variables to store num and denom so that they are not altered
    //divides tracker by b and if the remainder is not 0,
    //tracker is set to the value of a, a to b, and b to the remainder
    //process is continued until b, the remainder, is 0
    //if b is 0, then a is the gcd
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

    //stores value of gcd() in var gcd so that it need not be run more than once
    //if the gcd is not 1, then both num and denom are divided by it
    //otherwise, nothing is changed
    public void reduce() {
	int gcd = gcd();
	if (gcd != 1) {
	    num /= gcd;
	    denom /= gcd;
	}
    }

    //overloaded gcd method that takes two integers
    //divides tracker by d and if the remainder is not 0,
    //tracker is set to the value of n, n to d, and d to the remainder
    //process is continued until d, the remainder, is 0
    //if d is 0, then n is the gcd
    public static int gcd(int n, int d) {
	int tracker = n;
	while (d != 0) {
	    tracker = n;
	    n = d;
	    d = tracker%d;
	}
	return n;
    }

    //stores floatValues of calling Rational and given Rational in vars r1 and r2
    //if r1 == r2, then 0 is returned
    //if r1 > r2, then 1 is returned
    //if r1 < r2, then -1 is returned
    public int compareTo(Rational x) {
	int thisNumerator, otherNumerator;
	thisNumerator = num * x.denom;
	otherNumerator = denom * x.num;
	return thisNumerator - otherNumerator;
    }

    //overridden equals method
    public boolean equals(Object x) {
	//initializes retVal to boolean result of this == x
        boolean retVal = this == x;
	//if retVal is true, the if statement is not run and retVal is returned
        if (!retVal) {
	    //retVal becomes the boolean result of 
	    //whether x is an instance of rational
	    //and the comparison of the rationals is 0
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
