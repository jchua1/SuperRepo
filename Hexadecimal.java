/*
  Jason Chua
  APCS1 pd9
  HW45 -- Come Together
  2015-12-09
*/

public class Hexadecimal implements Comparable {

    private final static String HEXDIGITS = "0123456789ABCDEF";
    private int _decNum;
    private String _hexNum;

    public Hexadecimal() { 
	_decNum = 0;
	_hexNum = "0";
    }

    public Hexadecimal( int n ) {
	_decNum = n;
	_hexNum = decToHex(n);
    }

    public Hexadecimal( String s ) {
	_decNum = hexToDec(s);
	_hexNum = s;
    }

    public String toString() { 
	return _hexNum;
    }

    public static String decToHex( int n ) {
	String remainder = "";
	while (n != 0) {
	    remainder = HEXDIGITS.substring(n%16,n%16+1) + remainder;
	    n /= 16;
	}
	return remainder;
    }

    public static String decToHexR( int n ) { 
        String remainder = "";
        if (n != 0) {
	    remainder += HEXDIGITS.substring(n%16,n%16+1);
	    remainder = decToHexR(n/16) + remainder;
	}
	return remainder;
    }

    public static int hexToDec( String s ) {
	int dec = 0;
	for (int i = 0; i < s.length(); i++) {
	    dec += HEXDIGITS.indexOf(s.substring(i,i+1)) 
		* Math.pow(16,s.length()-i-1);
	}
	return dec;
    }

    public static int hexToDecR( String s ) { 
	int dec = 0;
	if (s.length() != 0) {
	    dec += hexToDecR(s.substring(1))
		+ HEXDIGITS.indexOf(s.substring(0,1))
		* Math.pow(16,s.length()-1);
	}
	return dec;
    }

    public boolean equals( Object other ) { 
	boolean ret = this == other;
	if (!ret) {
	    ret = other instanceof Hexadecimal 
		&& (this.compareTo((Hexadecimal)other) == 0);
	}
	return ret;
    }

    public int compareTo( Object o ) {
	int ret = 0;
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
	    //if o is an instance of Hexadecimal, o is typecasted to a
	    //Hexadecimal, and the decimal numbers of each Hexadecimal
	    //are compared
	    if (o instanceof Hexadecimal) {
		Hexadecimal x = (Hexadecimal)o;
		if (_decNum == x._decNum) {
		    ret = 0;
		}
		else if (x._decNum > _decNum) {
		    ret = -1;
		}
		else {
		    ret = 1;
		}
	    }
	    //if o is an instance of Binary, o is typecasted to Binary
	    //and using the binToDec method from Binary, the Binary number
	    //of o is changed to a decimal and stored in int a
	    //the decimal number of this Hexadecimal is compared to int a
	    else if (o instanceof Binary) {
		int a = Binary.binToDec(((Binary)o).toString());
		Hexadecimal x = new Hexadecimal(a);
		if (_decNum == a) {
		    ret = 0;
		}
		else if (a > _decNum) {
		    ret = -1;
		}
		else {
		    ret = 1;
		}
	    }
	    //if o is an instance of Rational, o is typecasted to Rational and
	    //a new Rational is created with the decimal number of this 
	    //Hexadecimal as the numerator and 1 as the denominator
	    //the numerator of the first Rational is multiplied by the 
	    //denominator of the second and the numerator of the second
	    //is multiplied by the denominator of the first
	    //these new numerators are then compared
	    else if (o instanceof Rational) {
		int thisNumerator, otherNumerator, num1, denom1, num2, denom2;
		Rational x = new Rational(_decNum,1);
		Rational y = (Rational)o;
		num1 = x.getNum();
		denom1 = x.getDenom();
		num2 = y.getNum();
		denom2 = y.getDenom();
		thisNumerator = num1 * denom2;
		otherNumerator = denom1 * num2;
		ret = thisNumerator - otherNumerator;
	    }
	}
	return ret;
    }
}
