package ua.nure.hordiienko.practice2;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

	private Entry head;
	private Entry tail;
	private int capacity;
	  
  public static void main(String[] args) {
      QueueImpl queue = new QueueImpl();
      queue.enqueue("A");
      queue.enqueue("B");
      System.out.println(queue.top());
    

      System.out.println(queue);

  		Iterator<Object> it = queue.iterator();
  		while (it.hasNext()) {
  			System.out.print(it.next());
  		}
  }

  
  private static final class Entry {

    private Object currentElement;
    private Entry nextElement;
    private Entry prevElement;

    private Entry(Entry prevElement, Object currentElement, Entry nextElement) {

        this.currentElement = currentElement;
        this.nextElement = nextElement;
        this.prevElement = prevElement;
    }
}

  // Appends the specified element to the end.
  public void enqueue(Object element) {
    final Entry f = head;
    final Entry newNode = new Entry(null, element, f);
    head = newNode;
    if (f == null) {
        tail = newNode;
    } else {
        f.prevElement = newNode;
    }
    capacity++;
    }

  // Removes the head.
  public Object dequeue() {
    final Entry l = tail;
    final Object element = l.currentElement;
    final Entry prev = l.prevElement;
    l.currentElement = null;
    l.prevElement = null;
    tail = prev;
    if (prev == null) {
        head = null;
    } else {
        prev.nextElement = null;
    }
    capacity--;
    return element;
  }

  // Returns the head.
  public Object top() {
	  if (capacity == 0) {
       throw new NoSuchElementException();
	  }
	  return tail.currentElement;
  }

  Entry node(int index) {
      if (index < (capacity >> 1)) {
          Entry x = head;
          for (int i = 0; i < index; i++) {
              x = x.nextElement;
          }
          return x;
      } else {
          Entry x = tail;
          for (int i = capacity - 1; i > index; i--) {
              x = x.prevElement;
          }
          return x;
      }
  }

  public void clear() {
	  Entry x = head;
      while (x != null) {
          x.currentElement = null;
          x.nextElement = null;
          x.prevElement = null;
          x = x.nextElement;
      }
      head = tail = null;
      capacity = 0;
  }

  // Returns the number of elements.
  public int size() {
      return capacity;
  }

  // Returns a string representation of this container.
  public String toString() {
		if (head == null) {
		return "[]";
		}
		StringBuilder result = new StringBuilder();
		Entry curr = head;
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
      private Entry lastReturned;
      private int index = size();
      private Entry next = (index == capacity) ? null : node(index);
      private int nextIndex = index;

      public boolean hasNext() {
          return nextIndex > 0;
      }

      public Object next() {
          if (!hasNext()) {
              throw new NoSuchElementException();
          }
          lastReturned = next = (next == null) ? tail : next.prevElement;
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
  void removeEntry(Entry e)  {
      capacity--;
      if (capacity == 0) {
        head = tail = null;
      } else {
          if (e == head) {
              head = e.nextElement;
              e.nextElement.prevElement = null;
            } else if (e == tail) {
              tail = e.prevElement;
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
}
