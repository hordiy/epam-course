package ua.nure.hordiienko.practice2;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayImpl implements Array {
	
	public static final int CAPACITY = 16;
    public static final int CUTRATE = 4;
    private Object[] arrayList;
    private int pointer;
    
    public ArrayImpl() {
    	arrayList = new Object[CAPACITY];
    }
    
    public ArrayImpl(int capacity) {
    	arrayList = new Object[capacity];
    }
	
    public static void main(String[] args) {
   
      ArrayImpl array = new ArrayImpl();
      
      array.add("A");
      array.add("B");
      array.add("C");
      
      System.out.println(array);
      

      Iterator<Object> it = array.iterator();
      System.out.println(it.next());
	  it.remove();
	  System.out.println(it.next());
	  it.remove();
	  System.out.println(it.next());
	  it.remove();
	  System.out.println(array);
    }
    
    
    
    //Add the specified element to the end.
    public void add(Object item) {
    	if (pointer == arrayList.length-1) {
            resize(arrayList.length*2);
    	}
        arrayList[pointer++] = item;
    }

    //Sets the element at the specified position.
    public void set(int index, Object value) {
        checkIndex(index);
        arrayList[index] = value;
    }
    
 // Returns the element at the specified position.
  	public Object get(int index) {
  		checkIndex(index);
  		return arrayList[index];
  	}
    
    //Returns the index of the first occurrence of the specified element,
    //or -1 if this array does not contain the element.
    //(use 'equals' method to check an occurrence)
    public int indexOf(Object value) {
        int result = -1;
        for (int i = 0; i < pointer; i++) {
            if (arrayList[i] == value) {
                result = i;
                break;
            }
        }
        return result;
    }

    //Remove the element at the specified position.
    public void remove(int index) {
        for (int i = index; i < pointer; i++) {
            arrayList[i] = arrayList[i+1];
        }
        arrayList[pointer] = null;
        pointer--;
        if (arrayList.length > CAPACITY && pointer < arrayList.length / CUTRATE) {
            resize(arrayList.length/2);
        }
    }

    // Removes all of the elements.
    public void clear() {
        for (int i = 0; i < pointer; i++) {
            arrayList[i] = null;
        }
        pointer = 0;

    }

    // Returns the number of elements.
    public int size() {
        return pointer;
    }

    // Returns a string representation of this container.
    public String toString() {
       Object[] ar;
       StringBuilder arrays = new StringBuilder();
       System.out.print("[");
       for (int i = 0; i < arrayList.length; i++) {
    	  if (arrayList[i] != null) {
    		  arrays.append(arrayList[i]);
    	  }
       }
       ar = new Object[arrays.length()];
       arrays = new StringBuilder();
       for (int i = 0; i < ar.length; i++) {
     	  if (arrayList[i].equals(arrayList[ar.length - 1])) {
     		 arrays.append(arrayList[i]);
     	  } else {
     		 arrays.append(arrayList[i] + ", ");
     	  }
        }
       return arrays + "]";
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(arrayList, 0, newArray, 0, pointer);
        arrayList = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= pointer) {
          throw new IllegalStateException();
        }
    }

    public class IteratorImpl implements Iterator<Object> {

        private int currentIndex;
        private int lastRet = -1;
        
        public boolean hasNext() {
            return currentIndex < pointer;
        }

        public Object next() {
        	int i = currentIndex;
        	if (i >= pointer) {
        		throw new NoSuchElementException();
        	}
        	Object[] elements = ArrayImpl.this.arrayList;
        	currentIndex = i + 1;
        	int num = lastRet = i;
        	return elements[num];
        
        }

        @Override
		public void remove() {
        	if (lastRet < 0) {
        		throw new IllegalStateException();
        	}
        	try {
        		ArrayImpl.this.remove(lastRet);
        		currentIndex = lastRet;
        		lastRet = -1;
        	} catch (IndexOutOfBoundsException ex) {
        		throw new IllegalStateException();
        	}
        }
    }

    // Returns an iterator over elements.
    // Iterator must implements the remove method.
    public Iterator<Object> iterator() {
        return new IteratorImpl();
      }
}
