package ua.nure.hordiienko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
	
	private Knot first;
	private Knot last;
	private int size;


  public static void main(String[] args) {
      ListImpl list = new ListImpl();
      
      list.addLast(1);
      list.addLast(2);
      list.addLast(1);
            
      list.remove(1);
      System.out.println(list);

      list.remove(2);
      System.out.println(list);
  }
  
    private static final class Knot {

      private Object dataElement;
      private Knot nextElem;
      private Knot prevElem;

      private Knot(Knot prevElement, Object currentElement, Knot nextElement) {

          this.dataElement = currentElement;
          this.nextElem = nextElement;
          this.prevElem = prevElement;
      }
  }
    
  // Insert the specified element at the beginning.
    public void addLast(Object element) {
        final Knot fi = first;
        final Knot newNode = new Knot(null, element, fi);
        first = newNode;
        if (fi == null) {
            last = newNode;
        } else {
            fi.prevElem = newNode;
        }
        size++;
    }
    // Appends the specified element to the end.
    public void addFirst(Object element) {
        final Knot la = last;
        final Knot newNode = new Knot(la, element, null);
        last = newNode;
        if (la == null) {
            first = newNode;
        } else {
            la.nextElem = newNode;
        }
        size++;
    }

    // Removes the first element.
   
	public void removeLast() {
        final Knot f = first;
        final Knot next = f.nextElem;
        f.dataElement = null;
        f.nextElem = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prevElem = null;
        }
        size--;
    }

    // Remove the last element.
	public void removeFirst() {
        final Knot l = last;
        final Knot prev = l.prevElem;
        l.dataElement = null;
        l.prevElem = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.nextElem = null;
        }
        size--;
    }

    // Returns the first element.
    public Object getLast() {
        final Knot f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.dataElement;
    }

    // Returns the last element.
    public Object getFirst() {
        final Knot l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.dataElement;
    }

    // Returns the first occurrence of the specified element.
    // Returns null if no such element.
    // (use 'equals' method to check an occurrence)
    public Object search(Object element) {
        int index = indexOf(element);
        if (index == -1) {
            return null;
        } else {
            checkIndex(index);
            return node(index).dataElement;
        }
    }

    public int indexOf(Object o) {
      int index = 0;
      if (o == null) {
          for (Knot x = first; x != null; x = x.nextElem) {
              if (x.dataElement == null) {
                return index;
              }
              index++;
          }
      } else {
          for (Knot x = first; x != null; x = x.nextElem) {
              if (o.equals(x.dataElement)) {
                  return index;
              }
              index++;
          }
        }
        return -1;
    }
    // Remove the first occurrence of the specified element.
    // Returns true if this list contained the specified element.
    // (use 'equals' method to check an occurrence)
	public boolean remove(Object element) {
    	final Knot l = last;
        final Knot prev = l.prevElem;
          
        l.dataElement = null;
        l.prevElem = null;
        last = prev;
        if (prev == null) {
        	first = null;
	    } else {
	    	prev.nextElem = null;
	    }
        size--;
        return true;
    }

    Object unlink(Knot x) {
        final Object element = x.dataElement;
        final Knot next = x.nextElem;
        final Knot prev = x.prevElem;

        if (prev == null) {
            first = next;
        } else {
            prev.nextElem = next;
            x.prevElem = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prevElem = prev;
            x.nextElem = null;
        }

        x.dataElement = null;
        size--;
        return element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
        	throw new IllegalStateException();
        }
    }

    Knot node(int index) {
        if (index < (size >> 1)) {
        	Knot x = first;
            for (int i = 0; i < index; i++) {
                x = x.nextElem;
            }
            return x;
        } else {
        	Knot x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prevElem;
            }
            return x;
        }
    }

    public void clear() {
    	Knot x = first;
        while (x != null) {
            x.dataElement = null;
            x.nextElem = null;
            x.prevElem = null;
            x = x.nextElem;
        }
        first = last = null;
        size = 0;
    }

  	// Returns the number of elements.
  	public int size() {
        return size;
    }

  	// Returns a string representation of this container.
  	public String toString() {
  		if (first == null) {
  		return "[]";
  		}
  		StringBuilder result = new StringBuilder();
  		Knot curr = first;
  		result.append(curr.dataElement);

  		while (curr.nextElem != null) {
  			curr = curr.nextElem;
  			result.append(curr.dataElement);
  		if (curr.nextElem != null) {
  			result.append("");
  		}
  		}
  		StringBuilder res = new StringBuilder();
  		res.append(result);
  		res.reverse();
  		
  		char[] ar = res.toString().toCharArray();
  		res = new StringBuilder("[");
  		for (int i = 0; i < ar.length; i++) {
  		if (i > 0) {
  			res.append(", ");
  		}
  		res.append(ar[i]);
  			}	
  		
  		res.append("]");
 
  		return res.toString();
  		}

    private class IteratorImpl implements Iterator<Object> {
        private Knot lastReturned;
        private int index = size();
        private Knot next = (index == size) ? null : node(index);
        private int nextIndex = index;

        public boolean hasNext() {
            return nextIndex > 0;
        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next = (next == null) ? last : next.prevElem;
            nextIndex--;
            return lastReturned.dataElement;
        }
        
        @Override
        public void remove() {
        	if (lastReturned == null) {
        		throw new IllegalStateException();
        	}
        	removeEntry(lastReturned);
        	lastReturned = null;
        	
        	}
        }
        
        void removeEntry(Knot e)  {
             size--;
             if (size == 0) {
               first = last = null;
             } else {
                 if (e == first) {
                     first = e.nextElem;
                     e.nextElem.prevElem = null;
                   } else if (e == last) {
                     last = e.prevElem;
                     e.prevElem.nextElem = null;
                   } else {
                     e.nextElem.prevElem = e.prevElem;
                     e.prevElem.nextElem = e.nextElem;
                   }
               }
        }
   	// Returns an iterator over elements.
  	// Iterator must implements the remove method.
  	public Iterator<Object> iterator() {
        return new IteratorImpl();
    }
}
