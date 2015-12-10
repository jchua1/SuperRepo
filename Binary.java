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
	if (!(o instanceof Binary)) {
	    throw new ClassCastException("\ncompareTo() input not a Binary");
	}
	if (o == null) {
	    throw new NullPointerException("\ncompareTo() input is null");
	}
	else {
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
	return ret;
    }

    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

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

    }
}
