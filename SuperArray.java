/*
  Team Gotee -- Jason Chua, Conan Wong
  APCS1 pd9
  HW42 -- Array of Titanium
  2015-12-06
*/

public class SuperArray {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private int[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor initializes 10-item array
    public SuperArray() 
    { 
	_data = new int[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	int[] temp = new int[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public int get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public int set( int index, int newVal ) 
    { 
 	int temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( int newVal ) { 
    	//creates temporary integer array with length of 1 greater than _size
    	int[] temp = new int[_size+1];
    	//populates temp with elements in _data
	for (int i = 0; i < _size; i++) {
	    temp[i] = _data[i];
	}
	//last element in temp is given the value of newVal
	temp[temp.length-1] = newVal;
	//_data is given value of temp
	_data = temp;
	//_lastPos and _size incremented by 1
	_lastPos += 1;
	_size += 1;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, int newVal ) { 
    	//creates temporary integer array with length of 1 greater than _size
    	int[] temp = new int[_size+1];
    	//populates temp with elements in _data up to the given index
    	for (int i = 0; i < index; i++){
	    temp[i] = _data[i];
    	}
    	//the element at the given index is given the value of newVal
    	temp[index] = newVal;
    	//starting from the end, temp is populated with elements in _data up to
    	//the given index
    	for (int i = _lastPos + 1; i > index; i--){
	    temp[i] = _data[i - 1];
    	}
    	//_data is given value of temp
    	_data = temp;
    	//_lastPos and _size incremented by 1
    	_lastPos += 1;
    	_size += 1;
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
    	//creates temporary integer array with length of 1 less than _size
    	int[] temp = new int[_size-1];
    	//populates temp with elements in _data up to the given index
    	for (int i = 0; i < index; i++) {
	    temp[i] = _data[i];
    	}
    	//starting from the end, temp is populated with elements in _data up to
    	//the given index
    	for (int i = _lastPos - 1; i >= index; i--){
	    temp[i] = _data[i + 1];
    	}
    	//_data is given value of temp
    	_data = temp;
    	//_lastPos and _size decremented by 1
    	_lastPos -= 1;
    	_size -= 1;    	
    }


    //return number of meaningful items in _data
    public int size() { 
	return _size;
    }


    //main method for testing
    public static void main( String[] args ) 
    {
        ListInt a = new SuperArray();
        a.add(5);
        System.out.println(a);
        a.add(4);
        System.out.println(a);
        a.add(3);
        System.out.println(a);
        
        System.out.println("Size: " + a.size());
        
        System.out.println("Index 2: " + a.get(2));
        System.out.println("Index 1: " + a.get(1));
        
        a.add(3,2);
        System.out.println(a);
        a.add(4,0);
        System.out.println(a);
        a.add(4,1);
        System.out.println(a);
        
        System.out.println("Size: " + a.size());
        
        System.out.println("Index 5: " + a.get(5));
        System.out.println("Index 3: " + a.get(3));
        
        a.remove(1);
        System.out.println(a);
        a.remove(1);
        System.out.println(a);
        a.remove(0);
        System.out.println(a);
        System.out.println("Size: " + a.size());
        
        System.out.println("Old Index 2: " + a.set(2,5));
        System.out.println("Old Index 1: " + a.set(1,5));
        System.out.println("Old Index 0: " + a.set(0,5));
        System.out.println(a);
    }
}
