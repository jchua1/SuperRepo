/*
  Jason Chua
  APCS1 pd9
  HW45 -- Come Together
  2015-12-09
*/

public class Binary implements Comparable {

    private int _decNum;
    private String _binNum;

    public Binary() { 
	_decNum = 0;
	_binNum = "0";
    }

    public Binary( int n ) {
	_decNum = n;
	_binNum = decToBin(n);
    }

    public Binary( String s ) {
	_decNum = binToDec(s);
	_binNum = s;
    }

    public String toString() { 
	return _binNum;
    }

    //while the quotient is not 0, divides quotient by 2 and adds the remainder
    //to the front of String variable remainder
    public static String decToBin( int n ) {
	String remainder = "";
	while (n != 0) {
	    remainder = n%2 + remainder;
	    n /= 2;
	}
	return remainder;
    }

    public static String decToBinR( int n ) { 
        String remainder = "";
	if (n != 0) {
	    remainder += n%2;
	    remainder = decToBinR(n/2) + remainder;
	}
	return remainder;
    }

    public static int binToDec( String s ) {
	int dec = 0;
	for (int i = 0; i < s.length(); i++) {
	    dec += Integer.parseInt(s.substring(i,i+1)) 
		* Math.pow(2,s.length()-i-1);
	}
	return dec;
    }

    public static int binToDecR( String s ) { 
	int dec = 0;
	if (s.length() != 0) {
	    dec += binToDecR(s.substring(1))
		+ Integer.parseInt(s.substring(0,1)) * Math.pow(2,s.length()-1);
	}
	return dec;
    }

    public boolean equals( Object other ) { 
	boolean ret = this == other;
	if (!ret) {
	    ret = other instanceof Binary 
		&& (this.compareTo((Binary)other) == 0);
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
	    //If o is an instance of Binary, o is typecasted to a
	    //Binary, and the decimal numbers of each Binary
	    //are compared.
	    if (o instanceof Binary) {
		Binary x = (Binary)o;
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
	    //If o is an instance of Hexadecimal, o is typecasted to Hexadecimal
	    //and using the hexToDec method from Hexadecimal, the Hexadecimal
	    //number of o is changed to a decimal and stored in int a.
	    //The decimal number of this Hexadecimal is compared to int a.
	    else if (o instanceof Hexadecimal) {
		int a = Hexadecimal.hexToDec(((Hexadecimal)o).toString());
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
	    //If o is an instance of Rational, o is typecasted to Rational and
	    //a new Rational is created with the decimal number of this 
	    //Binary as the numerator and 1 as the denominator.
	    //The numerator of the first Rational is multiplied by the 
	    //denominator of the second and the numerator of the second
	    //is multiplied by the denominator of the first.
	    //These new numerators are then compared.
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
