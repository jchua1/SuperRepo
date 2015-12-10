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
	    remainder = n%16 + remainder;
	    n /= 16;
	}
	return remainder;
    }

    public static String decToHexR( int n ) { 
        String remainder = "";
        if (n != 0) {
	    remainder += n%16;
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
	if (!(o instanceof Hexadecimal)) {
	    throw new ClassCastException("\ncompareTo() input not a Hexadecimal");
	}
	if (o == null) {
	    throw new NullPointerException("\ncompareTo() input is null");
	}
	else {
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
