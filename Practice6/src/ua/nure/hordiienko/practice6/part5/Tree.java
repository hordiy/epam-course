package ua.nure.hordiienko.practice6.part5;


public class Tree<E extends Comparable<E>> {
	
	private static final String SB = "  ";
	
	private Node<E> general;
	private Node<E> par;
	private Node<E> cur;
	
	public E get() {
		return general.el;
	}

	public void setParent(Node<E> parent) {
		this.par = parent;
	}

	public void setCurrent(Node<E> current) {
		this.cur = current;
	}

	public Node<E> getParent() {
		return par;
	}

	public Node<E> getCurrent() {
		return cur;
	}
	
	public boolean isNodeToRep() {
		Node<E> nodeToReplace = cur.ri;
		par = cur;
		while (nodeToReplace.le != null) {
			par = nodeToReplace;
			nodeToReplace = nodeToReplace.le;
		}
		cur.el = nodeToReplace.el;
		if (par.equals(cur)) {
			par.ri = nodeToReplace.ri;
		} else {
			par.le = nodeToReplace.ri;
		}
		return true;
	}

	public boolean remove(E element) {
		final int left = -1;
		final int right = 1;
		final int noStep = 0;

		setParent(null);
		setCurrent(general);
		int comp = 0;
		int lastStep = noStep;

		while (cur != null && (comp = cur.el.compareTo(element)) != 0) {
			par = cur;
			if (comp < 0) {
				lastStep = right;
				cur = cur.ri;
			} else {
				lastStep = left;
				cur = cur.le;
			}
		}
		if (cur == null) {
			return false;
		}
		if (cur.le == null) {
			if (lastStep == right) {
				par.ri = cur.ri;
			} else if (lastStep == left) {
				par.le = cur.ri;
			} else {
				general = cur.ri;
			}
		} else if (cur.ri == null) {
			if (lastStep == right) {
				par.ri = cur.le;
			} else if (lastStep == left) {
				par.le = cur.le;
			} else {
				general = cur.le;
			}
		} else {
			isNodeToRep();
		}
		return true;
	}

	public void add(E[] elements) {
		for (int i = 0; i < elements.length; i++) {
			add(elements[i]);
		}
	}

	public boolean add(E e) {
		if (general == null) {
			general = new Node<>(e, null, null);
			return true;
		}
		return add(general, e);
	}

	public boolean add(Node<E> node, E e) {
		if (e.compareTo(node.el) < 0) {
			if (node.le == null) {
				node.le = new Node<>(e, null, null);
				return true;
			}
			return add(node.le, e);
		}
		if (e.compareTo(node.el) > 0) {
			if (node.ri == null) {
				node.ri = new Node<>(e, null, null);
				return true;
			}
			return add(node.ri, e);
		}
		return false;
	}

	void print(String prefix, Node<E> node) {
        if (node != null) {
			print(prefix + SB, node.le);
			if (node.equals(general)) {
				System.out.println(node.el);
			} else {
				System.out.println(prefix + node.el);
			}	
			print(prefix + SB, node.ri);
		}
    }
    public void print() {
    	String s = "";
        print(s, general);
    }

	public static class Node<E> {

		private E el;
		private Node<E> le;
		private Node<E> ri;

		Node(E element, Node<E> left, Node<E> right) {
			this.el = element;
			this.le = left;
			this.ri = right;

		}

	}
}