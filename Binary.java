/*
  Jason Chua
  APCS1 pd9
  HW43 -- This or That
  2015-12-07
*/

//skeleton file for class Binary

public class Binary {

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	_decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_decNum = n;
	//uses decToBin to convert given int to binary equivalent
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	//uses binToDec to convert given String to decimal equivalent
	_decNum = binToDec(s);
	_binNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	//returns binary value of object
	return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	//initializes String remainder to store remainder amount
	String remainder = "";
	//while n, the quotient, is not 0,
	//the remainder becomes the remainder of the quotient and 2 added to
	//the current remainder
	//the quotient becomes the current quotient divided by 2
	while (n != 0) {
	    remainder = n%2 + remainder;
	    n /= 2;
	}
	return remainder;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	//initializes String remainder to store remainder amount
        String remainder = "";
	//(base case, n == 0, would modify nothing, so only cases where 
	//n, the quotient, is not 0 should be dealt with)
	//if n is not 0, the remainder of n/2 is added to String remainder
	//and remainder becomes decToBinR(n/2) plus the current remainder
        if (n != 0) {
	    remainder += n%2;
	    remainder = decToBinR(n/2) + remainder;
	}
	return remainder;
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	//initializes int dec to store decimal value
	int dec = 0;
	//iterates through the String using a for loop
	//and adds product of the index value and the 
	//respective base 2 exponent, which is found by subtracting i+1
	//from the string length
	for (int i = 0; i < s.length(); i++) {
	    dec += Integer.parseInt(s.substring(i,i+1)) 
		* Math.pow(2,s.length()-i-1);
	}
	return dec;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
	//initializes int dec to store decimal value
	int dec = 0;
	//works by dealing with a shorter version of the string each time
	//(base case, when string length is 0, modifies nothing
	//so only cases where string length is not 0 should be dealt with)
	//if the string length is not 0, the first index of the string
	//is multiplied by its respective base 2 exponent, found by subtracting
	//1 from the string length
	//the sum of this product and binToDecR() of the string with the first
	//character removed is added to dec
	if (s.length() != 0) {
	    dec += binToDecR(s.substring(1))
		+ Integer.parseInt(s.substring(0,1)) * Math.pow(2,s.length()-1);
	}
	return dec;
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	//checks for aliasing using ==
	boolean ret = this == other;
	//if the two objects are not aliases,
	//then ret becomes the boolean value representing
	//whether other is an instance of Binary and
	//this object and compareTo is 0 when comparing the two objects
	if (!ret) {
	    ret = other instanceof Binary 
		&& (this.compareTo((Binary)other) == 0);
	}
	return ret;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	//initializes int ret as 1 to represent the positive integer
	//if the other object is not a Binary
	//or if this object is greater
	int ret = 1;
	//if other is an instance of Binary
	//it is typecasted to a Binary
	//and stored in x
	//if the _decNums of both are equal, then ret is 0,
	//if other's _decNum is greater, then ret is -1
	//if neither of the above cases are true, then ret remains 1
	if (other instanceof Binary) {
	    Binary x = (Binary)other;
	    if (_decNum == x._decNum) {
		ret = 0;
	    }
	    else if (x._decNum > _decNum) {
		ret = -1;
	    }
	}
	return ret;
    }

    //main method for testing
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
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
