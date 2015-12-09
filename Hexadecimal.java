/*
  Team Hexane -- Jason Chua, Pardeep Singh
  APCS1 pd9
  HW43 -- This or That or Fourteen Other Things
  2015-12-08
*/

//skeleton file for class Hexadecimal

public class Hexadecimal {

    //String to refer to for decimal value of each hexdigit
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
	//adds the remainder of n/16 to the front of String variable remainder 
	//and divides n by 16
	//repeats until n is 0
	while (n != 0) {
	    remainder = n%16 + remainder;
	    n /= 16;
	}
	return remainder;
    }

    public static String decToHexR( int n ) { 
        String remainder = "";
	//if the given argument, n, is not 0, then the remainder of n/16
	//is added to String variable remainder
	//decToHexR(n/16) is added to the front of remainder
        if (n != 0) {
	    remainder += n%16;
	    remainder = decToHexR(n/16) + remainder;
	}
	return remainder;
    }

    public static int hexToDec( String s ) {
	int dec = 0;
	//iterates through the String and adds the product of the index of 
	//the current String index in HEXDIGITS and the respective base 16 exponent
	for (int i = 0; i < s.length(); i++) {
	    dec += HEXDIGITS.indexOf(s.substring(i,i+1)) 
		* Math.pow(16,s.length()-i-1);
	}
	return dec;
    }

    public static int hexToDecR( String s ) { 
	int dec = 0;
	//if the length of the given String, s, is not 0, the product of the index
	//of the first character in the given String in HEXDIGITS and the respective
	//base 16 exponent is added to dec
	//the result of hexToDecR() with a shrunken String is then added
	if (s.length() != 0) {
	    dec += hexToDecR(s.substring(1))
		+ HEXDIGITS.indexOf(s.substring(0,1))
		* Math.pow(16,s.length()-1);
	}
	return dec;
    }

    public boolean equals( Object other ) { 
	//checks for aliasing
	boolean ret = this == other;
	//if not aliases, checks if other is an instance of Hexadecimal
	//and if this Hexadecimal compared to other results in 0
	if (!ret) {
	    ret = other instanceof Hexadecimal 
		&& (this.compareTo((Hexadecimal)other) == 0);
	}
	return ret;
    }

    public int compareTo( Object other ) {
	int ret = 0;
	//if other is an instance of Hexadecimal,
	//typecasts other to Hexadecimal x
	//and compares _decNum of each
	//if other is not an instance of Hexadecimal,
	//error message is thrown
	if (other instanceof Hexadecimal) {
	    Hexadecimal x = (Hexadecimal)other;
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
	else {
	    throw new ClassCastException("\nMy first error message! "
					 + "compareTo() input not a Hexadecimal");
	}
	return ret;
    }

    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(18);
	Hexadecimal b2 = new Hexadecimal(18);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(32);
	int x = 5;

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\nhexToDec..." );
	System.out.println( hexToDec(b1.toString()) );
	System.out.println( hexToDec(b2.toString()) );
	System.out.println( hexToDec(b3.toString()) );       
	System.out.println( hexToDec(b4.toString()) );  

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	System.out.println( b1.compareTo(x) ); //should throw error message
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
