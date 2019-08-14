package ua.nure.hordiienko.practice2;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class StackImpl implements Stack {
	
	private Node fir;
    private Node las;
    private int scale;

    public static void main(String[] args) {

        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.pop());
        
    		Iterator<Object> it = stack.iterator();
    		while (it.hasNext()) {
    			System.out.print(it.next());
    			it.remove();
    			System.out.println(stack);
    		}
    }

    
    private static final class Node {

      private Object currentElement;
      private Node nextElement;
      private Node prevElement;

      private Node(Node prevElement, Object currentElement, Node nextElement) {

          this.currentElement = currentElement;
          this.nextElement = nextElement;
          this.prevElement = prevElement;
      }
  }

    // Pushes the specified element onto the top.
    public void push(Object element) {
    	final Node l = las;
        final Node newNode = new Node(l, element, null);
        las = newNode;
        if (l == null) {
            fir = newNode;
        } else {
            l.nextElement = newNode;
        }
        scale++;
    }

    // Removes and returns the top element.
	public Object pop() {
    	final Node l = las;
        final Object element = l.currentElement;
        final Node prev = l.prevElement;
        l.currentElement = null;
        l.prevElement = null;
        las = prev;
        if (prev == null) {
            fir = null;
        } else {
            prev.nextElement = null;
        }
        scale--;
        return element;
    }

    // Returns the top element.
    public Object top() {
    	final Node l = las;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.currentElement;
    }

    // Removes all of the elements.
    public void clear() {
    	Node x = fir;
        while (x != null) {
            x.currentElement = null;
            x.nextElement = null;
            x.prevElement = null;
            x = x.nextElement;
        }
        fir = las = null;
        scale = 0;
    }

  	// Returns the number of elements.
  	public int size() {
        return scale;
    }

  	// Returns a string representation of this container.
  	public String toString() {
  		if (fir == null) {
  			return "[]";
  			}
  			StringBuilder result = new StringBuilder();
  			Node curr = fir;
  			result.append(curr.currentElement);

  			while (curr.nextElement != null) {
  				curr = curr.nextElement;
  				result.append(curr.currentElement);
  			if (curr.nextElement != null) {
  				result.append("");
  			}
  			}
  			StringBuilder res = new StringBuilder();
  			res.append(result);
  			
  			
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
        private Node lastReturned;
        private int index = size();
        private Node next = (index == scale) ? null : node(index);
        private int nextIndex = index;

        public boolean hasNext() {
            return nextIndex > 0;
        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next = (next == null) ? las : next.prevElement;
            nextIndex--;
            return lastReturned.currentElement;
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
    void removeEntry(Node e)  {
        scale--;
        if (scale == 0) {
          fir = las = null;
        } else {
            if (e == fir) {
                fir = e.nextElement;
                e.nextElement.prevElement = null;
              } else if (e == las) {
                las = e.prevElement;
                e.prevElement.nextElement = null;
              } else {
                e.nextElement.prevElement = e.prevElement;
                e.prevElement.nextElement = e.nextElement;
              }
          }
        }
  	// Returns an iterator over elements.
  	// Iterator must implements the remove method.
  	public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    Node node(int index) {
        if (index < (scale >> 1)) {
            Node x = fir;
            for (int i = 0; i < index; i++) {
                x = x.nextElement;
            }
            return x;
        } else {
            Node x = las;
            for (int i = scale - 1; i > index; i--) {
                x = x.prevElement;
            }
            return x;
        }
    }
}
