/*
  Jason Chua
  APCS1 pd9
  HW45 -- Come Together
  2015-12-09
*/

public class Rational implements Comparable {

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
    
    public int getNum() {
	return num;
    }

    public int getDenom() {
	return denom;
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
	int ret = 0;
	int thisNumerator, otherNumerator;
	//If o is not an instance of Comparable, a ClassCastException is thrown.
	if (!(o instanceof Comparable)) {
	    throw new ClassCastException("\ncompareTo() input not a Comparable");
	}
	//If o is null, a NullPointerException is thrown.
	if (o == null) {
	    throw new NullPointerException("\ncompareTo() input is null");
	}
	//Otherwise, tests are run for which instance of Comparable o is.
	else {
	    //If o is an instance of Rational, o is typecasted to a Rational.
	    //The numerator of this Rational is multiplied by the denominator
	    //of the other and the numerator of the other is multiplied by the 
	    //numerator of this one.
	    if (o instanceof Rational) {
		Rational x = (Rational)o;
		thisNumerator = num * x.denom;
		otherNumerator = denom * x.num;
		ret = thisNumerator - otherNumerator;
	    }
	    //If o is an instance of Binary, o is typecasted to Binary and 
	    //using the binToDec method from Binary, the Binary number of o
	    //is changed to a decimal and used as the numerator for a new
	    //Rational. The process for the previous case is then repeated.
	    else if (o instanceof Binary) {
	        Rational x = new Rational(Binary.binToDec
					  (((Binary)o).toString()),1);
		thisNumerator = num * x.denom;
		otherNumerator = denom * x.num;
		ret = thisNumerator - otherNumerator;
	    }
	    //If o is an instance of Hexadecimal, o is typecasted to Hexadecimal
	    //and using the hexToDec method from Hexadecimal, the Hexadecimal 
	    //number of o is changed to a decimal and used as the numerator for
	    //a new Rational. The process for the first case is then repeated.
	    else if (o instanceof Hexadecimal) {
		Rational x = new Rational(Hexadecimal.hexToDec
					  (((Hexadecimal)o).toString()),1);
		thisNumerator = num * x.denom;
		otherNumerator = denom * x.num;
		ret = thisNumerator - otherNumerator;
	    }
	}
	return ret;
    }

    public boolean equals(Object x) {
        boolean retVal = this == x;
        if (!retVal) {
            retVal = x instanceof Rational
                && (this.compareTo((Rational)x) == 0);
        }
        return retVal;
    }
}
