/*
  Jason Chua
  APCS1 pd9
  HW45 -- Come Together
  2015-12-09
*/

public class SuperArray {

    private Comparable[] _data;
    private int _lastPos;
    private int _size;

    public SuperArray() { 
	_data = new Comparable[10];
	_lastPos = -1;
	_size = 0;	
    }

    public String toString() { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

    private void expand() { 
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }
	
    public Comparable get( int index ) { 
	return _data[index]; 
    }

    public Comparable set( int index, Comparable newVal ) { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }

    public void add( Comparable newVal ) { 
    	Comparable[] temp = new Comparable[_size+1];
	for (int i = 0; i < _size; i++) {
	    temp[i] = _data[i];
	}
	temp[temp.length-1] = newVal;
	_data = temp;
	_lastPos += 1;
	_size += 1;
    }

    public void add( int index, Comparable newVal ) { 
    	Comparable[] temp = new Comparable[_size+1];
    	for (int i = 0; i < index; i++){
	    temp[i] = _data[i];
    	}
    	temp[index] = newVal;
    	for (int i = _lastPos + 1; i > index; i--){
	    temp[i] = _data[i - 1];
    	}
    	_data = temp;
    	_lastPos += 1;
    	_size += 1;
    }

    public void remove( int index ) {
    	Comparable[] temp = new Comparable[_size-1];
    	for (int i = 0; i < index; i++) {
	    temp[i] = _data[i];
    	}
    	for (int i = _lastPos - 1; i >= index; i--){
	    temp[i] = _data[i + 1];
    	}
    	_data = temp;
    	_lastPos -= 1;
    	_size -= 1;    	
    }

    public int size() { 
	return _size;
    }

    public int linSearch(Comparable x) {
	//Iterates through the array and uses compareTo to see if the two
	//Comparables are the same. If so, the index is returned. -1 is returned
	//if the array is completely iterated through, meaning that Comparable
	//is not in the array.
	for (int i = 0; i < _size; i++) {
	    if (_data[i].compareTo(x) == 0) {
		return i;
	    }
	}
	return -1;
    }

    public boolean isSorted() {
	//Iterates through the array until the second to last element. Compares
	//one element to the next using compareTo. If the result of compareTo 
	//is positive, meaning the current element is greater than the next,
	//false is returned. True is returned if the array is completely
	//iterated through, meaning that all Comparables are less than the one
	//on the immediate right.
	for (int i = 0; i < _size-1; i++) {
	    if (_data[i].compareTo(_data[i+1]) > 0) {
		return false;
	    }
	}
	return true;
    }

    public static void main( String[] args ) {
	SuperArray a = new SuperArray();
	Comparable b = new Hexadecimal(6);
	Comparable c = new Hexadecimal(7);
	Comparable d = new Binary(8);
	Comparable e = new Binary(9);
	Comparable f = new Rational(20,2);
	Comparable g = new Rational(21,2);
	a.add(b);
	a.add(c);
	a.add(d);
	a.add(e);
	a.add(f);
	a.add(g);
	System.out.println(a);
	System.out.println(a.isSorted());//should be true
	System.out.println(a.linSearch(b));//should be 0
	a.add(b);
	System.out.println(a);
	System.out.println(a.isSorted());//should be false
	System.out.println(a.linSearch(b));//should be 0
	a.remove(0);
	System.out.println(a);
	System.out.println(a.linSearch(b));//should be 5
	a.remove(5);
	System.out.println(a);
	System.out.println(a.isSorted());//should be true
    }
}
