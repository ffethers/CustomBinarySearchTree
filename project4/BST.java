package project4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This class represents the custom binary search tree structure that will be
 * used to create a representation of the mountain. The tree will be created
 * with nodes that store data of type RestStop
 * 
 * @author Finn Fetherstonhaugh
 * @version 11/25/22
 */
public class BST<E extends Comparable<E>> implements Iterable<E> {

	/**
	 * This class will create nodes that keep track of the tree's memory addresses
	 * 
	 * @author Finn Fetherstonhaugh
	 * @version 12/5/22
	 */
	protected class Node {
		protected E data;
		protected Node left;
		protected Node right;
		protected int height;
		protected int size; // size of node and its subtree

		/**
		 * Constructs a new instance of a Node, starting with a size of 1 and no left or
		 * right children
		 * 
		 * @param data Data to be stored in the node
		 */
		protected Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.size = 1;
		}

		/**
		 * Updates the height of the node, determined by the height of its highest child
		 * + 1
		 */
		private void updateHeight() {
			if (this.left == null && this.right == null) { // if its a leaf node, height is 0
				this.height = 0;
			} else if (this.left == null) {
				this.height = this.right.height + 1;
			} else if (this.right == null) {
				this.height = this.left.height + 1;
			} else { // if node has 2 children, it's height is the height of it's highest child + 1
				this.height = 1 + Math.max(this.left.height, this.right.height);
			}
		}
	}

	/**
	 * This class is an iterator that represents the in order traversal of the tree
	 * 
	 * @author Finn Fetherstonhaugh
	 * @version 12/5/22
	 */
	private class Itr implements Iterator<E> {
		private Queue<E> q = new LinkedList<>(); // stores the in order data

		/**
		 * Constructs a new instance of an Itr, calling the inOrder() method to fill the
		 * Queue data field with the in order traversal
		 */
		public Itr() {
			inOrder();
		}

		/**
		 * Returns the in order traversal of the data in the BST
		 */
		private void inOrder() {
			inOrderRec(root);
		}

		/**
		 * recursive method for inOrder()
		 * 
		 * @param n Current node during traversal
		 */
		private void inOrderRec(Node n) {
			if (n == null) {
				return;
			} else {
				inOrderRec(n.left);
				q.add(n.data);
				inOrderRec(n.right);
			}
		}

		/**
		 * Checks if the Queue still has elements in it
		 * 
		 * @return false if Queue is empty, true if not
		 */
		public boolean hasNext() {
			return q.size() >= 1;
		}

		/**
		 * Returns and removes the first element of the Queue
		 * 
		 * @return the first data element of the Queue
		 */
		public E next() {
			return q.poll();
		}

		/**
		 * Unsupported method of this Iterator class
		 * 
		 * @throws UnsupportedOperationException if user tries to use this method
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * This class is an iterator that represents the pre order traversal of the tree
	 * 
	 * @author Finn Fetherstonhaugh
	 * @version 12/5/22
	 */
	private class preOrderItr implements Iterator<E> {
		private Queue<E> q = new LinkedList<>(); // stores the in order data

		/**
		 * Constructs a new instance of an preOrderItr, calling the preOrder() method to
		 * fill the Queue data field with the in order traversal
		 */
		public preOrderItr() {
			preOrder();
		}

		/**
		 * Returns the pre order traversal of the data in the BST
		 */
		private void preOrder() {
			preOrderRec(root);
		}

		/**
		 * recursive method for preOrder()
		 * 
		 * @param n Current node during traversal
		 */
		public void preOrderRec(Node n) {
			if (n == null) {
				return;
			} else {
				q.add(n.data);
				preOrderRec(n.left);
				preOrderRec(n.right);
			}
		}

		/**
		 * Unsupported method of this Iterator class
		 * 
		 * @throws UnsupportedOperationException if user tries to use this method
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/**
		 * Checks if the Queue still has elements in it
		 * 
		 * @return false if Queue is empty, true if not
		 */
		public boolean hasNext() {
			return q.size() >= 1;
		}

		/**
		 * Returns and removes the first element of the Queue
		 * 
		 * @return the first data element of the Queue
		 */
		public E next() {
			return q.poll();
		}
	}

	/**
	 * This class is an iterator that represents the post order traversal of the
	 * tree
	 * 
	 * @author Finn Fetherstonhaugh
	 * @version 12/5/22
	 */
	private class postOrderItr implements Iterator<E> {
		private Queue<E> q = new LinkedList<>(); // stores the in order data

		/**
		 * Constructs a new instance of an preOrderItr, calling the preOrder() method to
		 * fill the Queue data field with the in order traversal
		 */
		public postOrderItr() {
			postOrder();
		}

		/**
		 * Returns the post order traversal of the data in the BST
		 */
		public void postOrder() {
			postOrderRec(root);
		}

		/**
		 * recursive method for postOrder()
		 * 
		 * @param n Current node during traversal
		 */
		public void postOrderRec(Node n) {
			if (n == null) {
				return;
			} else {
				postOrderRec(n.left);
				postOrderRec(n.right);
				q.add(n.data);
			}
		}

		/**
		 * Checks if the Queue still has elements in it
		 * 
		 * @return false if Queue is empty, true if not
		 */
		public boolean hasNext() {
			return q.size() >= 1;
		}

		/**
		 * Returns and removes the first element of the Queue
		 * 
		 * @return the first data element of the Queue
		 */
		public E next() {
			return q.poll();
		}

		/**
		 * Unsupported method of this Iterator class
		 * 
		 * @throws UnsupportedOperationException if user tries to use this method
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	protected Node root; // reference to the root node of the tree

	private boolean added; // helper variable used by the add method
	private boolean found; // helper variable used by the remove methods

	private int size;

	/**
	 * Constructs a new empty instance of a BST and sets variables accordingly
	 */
	public BST() {
		root = null;
		added = false;
		found = false;
		size = 0;
	}

	/**
	 * Constructs a new balanced instance of a BST using the sorted items in the
	 * collection
	 * 
	 * @param collection array of data that should be added to the tree
	 */
	public BST(E[] collection) {
		Arrays.sort(collection);
		findMiddle(collection);
	}

	/**
	 * Adds the elements from a sorted array to a BST in a balanced way
	 * 
	 * @param collection sorted array to be added to the BST
	 */
	private void findMiddle(E[] collection) {
		recFindMiddle(collection, 0, collection.length - 1); // parameters are a sorted array, a start index, and an end
																// index
	}

	/**
	 * Recursive method for findMiddle
	 * 
	 * @param collection sorted array to find the middle of
	 * @param start      start index
	 * @param end        end index
	 */
	private void recFindMiddle(E[] collection, int start, int end) {
		if (start >= end) { // if there is 1 or less elements (pointers cross)
			return;
		} else {
			int middle = (start + end) / 2;
			this.add(collection[middle]); // add middle to BST
			recFindMiddle(collection, start, middle); // call on left half
			recFindMiddle(collection, middle + 1, end); // call on right half
		}
	}

	/**
	 * Adds a data element to the current BST
	 * 
	 * @citation Code from the BST Ed workspace
	 * 
	 * @param e data to be added
	 * 
	 * @return true if item is added
	 */
	public boolean add(E e) {
		added = false;
		if (e == null)
			throw new NullPointerException("null value found");
		// replace root with the reference to the tree after the new
		// value is added
		root = add(e, root);
		if (added)
			size++;
		return added;
	}

	/**
	 * Recursive method for add method
	 * 
	 * @param e    data to be added
	 * @param node current node
	 * @return true if it was added, false if not
	 */
	private Node add(E e, Node node) {
		if (node == null) {
			added = true;
			return new Node(e);
		}
		// decide how comparisons should be done
		int comp = e.compareTo(node.data);

		// find the location to add the new value
		if (comp < 0) { // add to the left subtree
			node.left = add(e, node.left);
			// node.size+=1;
			node.updateHeight();
			node.size++;
		} else if (comp > 0) { // add to the right subtree
			node.right = add(e, node.right);
			node.updateHeight();
			node.size++;
		} else { // duplicate found, do not add
			added = false;
			// return node;
		}
		return node;
	}

	/**
	 * Returns the least element in this tree greater than or equal to the given
	 * element, or null if there is no such element
	 * 
	 * @param e data to find the ceiling of
	 * 
	 * @return the ceiling data
	 */
	public E ceiling(E e) {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Cannot call on null reference");
		}
		return ceilingRec(e, root, null);
	}

	/**
	 * Recursive method for ceiling method
	 * 
	 * @param e              data to find the ceiling of
	 * @param node           current node
	 * @param smallestLarger current ceiling value
	 * 
	 * @return the ceiling data
	 */
	private E ceilingRec(E e, Node node, E smallestLarger) { // reference data, current node, the current ceiling value
		if (node == null) { // if you reach the bottom of the tree
			return smallestLarger;
		} else if (node.data.compareTo(e) > 0) { // if current data is greater than reference data
			if (node.left == null) { // if there is no lesser than values than the current node, return its data
				return node.data;
			} else {
				if (smallestLarger == null || node.data.compareTo(smallestLarger) < 0) { // if the current node is a
																							// better ceiling value than
																							// smallestLarger, set
																							// smallestLarger value to
																							// current on the next call
					return ceilingRec(e, node.left, node.data);
				} else { // else, continue calling with the smallestLarger value on the left
					return ceilingRec(e, node.left, smallestLarger);
				}
			}
		} else if (node.data.compareTo(e) < 0) {// if current data is lesser than reference data
			return ceilingRec(e, node.right, smallestLarger);
		} else { // if current data is equal to reference data, return it
			return node.data;
		}
	}

	/**
	 * Clears the BST tree so it becomes empty
	 */
	public void clear() {
		size = 0;
		root = null;
	}

	/**
	 * Checks if a specified element is inside the BST
	 * 
	 * @param o data to search for
	 * 
	 * @return true or false if the data is found or not
	 * 
	 * @throws NullPointerException if the argument is null
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		if (o == null) {
			throw new NullPointerException("Argument Cannot Be Null");
		}
		return recContains(root, (E) o);

	}

	/**
	 * Recursive method for contains method
	 * 
	 * @param n current node
	 * @param o data to search for
	 * 
	 * @return true if found and false if not
	 */
	private boolean recContains(Node n, E o) {
		if (n == null) {
			return false;
		}
		int c = n.data.compareTo(o);

		if (c > 0) {
			return recContains(n.left, o);
		} else if (c < 0) {
			return recContains(n.right, o);
		} else {
			return true;
		}
	}

	/**
	 * Checks to see if two trees contain the same elements
	 * 
	 * @citation Code from Equals testing Ed workspace
	 * 
	 * @param BST to be compared to
	 * 
	 * @return true or false if the trees are equal
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BST)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		BST<E> e = (BST<E>) obj;
		if (this.size != e.size) {
			return false;
		}
		Iterator<E> eItr = e.iterator();
		Iterator<E> thisIterator = this.iterator();
		while (eItr.hasNext() && thisIterator.hasNext()) {
			if (!(eItr.next().equals(thisIterator.next()))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Finds and returns the smallest element in the tree
	 * 
	 * @return the smallest data value in the tree
	 * 
	 * @throws NoSuchElementException if the BST is empty
	 */
	public E first() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Cannot call on null reference");
		}
		Node current = root; // starts at root and moves left until leaf node is found
		while (current.left != null) {
			current = current.left;
		}
		return current.data;
	}

	/**
	 * Returns the greatest element in this set less than or equal to the given
	 * element, or null if there is no such element.
	 * 
	 * @param e data to find the floor of
	 * 
	 * @return the floor data
	 * 
	 * @throws NoSuchElementException if the BST is empty
	 */
	public E floor(E e) {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return floorRec(e, root, null);
	}

	/**
	 * Recursive method for floor
	 * 
	 * @param e              data to find the floor of
	 * @param node           current node
	 * @param largestSmaller current floor value
	 * 
	 * @return floor value or null if no value is found
	 */
	private E floorRec(E e, Node node, E largestSmaller) { // reference data, current node, the current ceiling value
		if (node == null) {
			return largestSmaller;
		} else if (node.data.compareTo(e) > 0) { // if current node is greater than reference data, move left
			return floorRec(e, node.left, largestSmaller);
		} else if (node.data.compareTo(e) < 0) { // if current node is lesser than reference data, move right
			if (node.right == null) { // if there is no greater values in the tree, return current
				return node.data;
			} else {
				if (largestSmaller == null || node.data.compareTo(largestSmaller) > 0) { // reset largestSmaller if
																							// necessary and call on the
																							// right
					return floorRec(e, node.right, node.data);
				} else {
					return floorRec(e, node.right, largestSmaller);
				}
			}
		} else { // if current node equals reference data return data
			return node.data;
		}
	}

	/**
	 * Returns the data from a specific index in the tree
	 * 
	 * @param index which index to find
	 * 
	 * @return the data at that index
	 * 
	 * @throws IndexOutOfBoundsExcpetion if the index is invalid for the current
	 *                                   tree
	 */
	public E get(int index) {
		// int offset = root.size - root.left.size;
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("invalid index");
		}

		int rootIndex = size - root.right.size - 1; // find the root index

		return recGet(root, index, rootIndex);
	}

	/**
	 * Recursive method for get method
	 * 
	 * @param current      current node
	 * @param targetIndex  index to find
	 * @param currentIndex index of current node
	 * 
	 * @return the data at the specified index
	 */
	private E recGet(Node current, int targetIndex, int currentIndex) {
		if (currentIndex > targetIndex) { // if current index is greater than target
			int nextIndex = currentIndex - 1;
			if (current.left.right != null)
				nextIndex -= current.left.right.size;
			return recGet(current.left, targetIndex, nextIndex);
		} else if (currentIndex < targetIndex) { // if current index is less than target index
			int nextIndex = currentIndex + 1;
			if (current.right.left != null)
				nextIndex += current.right.left.size;
			return recGet(current.right, targetIndex, nextIndex);
		} else { // if current index equals target index
			return current.data;
		}
	}

	/**
	 * Returns the height of the tree
	 * 
	 * @return the height of the root, or 0 if the root is null
	 */
	public int height() {
		if (root == null) {
			return 0;
		}
		return root.height;
	}

	/**
	 * Returns the least element in this tree strictly greater than the given
	 * element, or null if there is no such element
	 * 
	 * @param e data to find the higher of
	 * 
	 * @return the higher data
	 * 
	 * @throws NoSuchElementException if the BST is empty
	 */
	public E higher(E e) {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return higherRec(e, root, null);
	}

	/**
	 * Recursive method for higher method
	 * 
	 * @param e              data to find higher of
	 * @param node           current node
	 * @param smallestLarger current higher value
	 * 
	 * @return higher value or null if none found
	 */
	private E higherRec(E e, Node node, E smallestLarger) {
		if (node == null) { // if you get to the bottom of the tree, return the current higher value
			return smallestLarger;
		} else if (node.data.compareTo(e) > 0) { // if current node is greater than reference data, move left
			if (node.left == null) { // if there are no more left values,return the current data
				return node.data;
			} else { // else reset the current highest value and call on the left
				if (smallestLarger == null || node.data.compareTo(smallestLarger) < 0) {
					return higherRec(e, node.left, node.data);
				} else {
					return higherRec(e, node.left, smallestLarger);
				}
			}
		} else { // move right
			return higherRec(e, node.right, smallestLarger);
		}
	}

	/**
	 * Determines if the BST is empty
	 * 
	 * @return true if the root is null and false if not
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Find and return the highest value in the BST
	 * 
	 * @return highest value in the tree
	 * 
	 * @throws NoSuchElementExcpetion if BST is empty
	 */
	public E last() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("Cannot call on null BST");
		}
		Node current = root;
		while (current.right != null) { // move right while you can
			current = current.right;
		}
		return current.data;
	}

	/**
	 * Returns the greatest element in this set strictly less than the given
	 * element, or null if there is no such element
	 * 
	 * @param e data to find the lower of
	 * 
	 * @return the lower value or null if none found
	 * 
	 * @throws NoSuchElementExcpetion if BST is null
	 */
	public E lower(E e) {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return lowerRec(e, root, null);
	}

	/**
	 * Recursive method for lower()
	 * 
	 * @param e              data to find the lower of
	 * @param node           current node
	 * @param largestSmaller current lower value
	 * 
	 * @return the lower value
	 */
	private E lowerRec(E e, Node node, E largestSmaller) {
		if (node == null) { // if current is at the bottom of the tree
			return largestSmaller;
		} else if (node.data.compareTo(e) >= 0) { // if current is greater than the reference data, move left
			return lowerRec(e, node.left, largestSmaller);
		} else { // if current is lesser than the reference data
			if (node.right == null) { // if there are no greater than nodes, return current
				return node.data;
			} else { // reset the current lower if necessary and move right
				if (largestSmaller == null || node.data.compareTo(largestSmaller) > 0) {
					return lowerRec(e, node.right, node.data);
				} else {
					return lowerRec(e, node.right, largestSmaller);
				}
			}
		}
	}

	/**
	 * Makes a new instance of an in order iterator
	 * 
	 * @return new instance of the Itr class
	 */
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * Makes a new instance of an post order iterator
	 * 
	 * @return new instance of the postOrderItr class
	 */
	public Iterator<E> postorderIterator() {
		return new postOrderItr();
	}

	/**
	 * Makes a new instance of an post order iterator
	 * 
	 * @return new instance of the preOrderItr class
	 */
	public Iterator<E> preorderIterator() {
		return new preOrderItr();
	}

	/**
	 * Finds the highest value in the left subtree
	 * 
	 * @citation method from the Ed workspace
	 * 
	 * @param subtree the subtree that the method will be called on
	 * 
	 * @return the highest value in the left subtree
	 * 
	 * @throws NullPointerException if the subtree is empty
	 */
	private E getPredecessor(Node subtree) {
		if (subtree == null) // this should not happen
			throw new NullPointerException("getPredecessor called with an empty subtree");
		Node temp = subtree;
		while (temp.right != null)
			temp = temp.right;
		return temp.data;
	}

	/**
	 * Removes the specified element from this tree if it is present. Returns true
	 * if this tree contained the element (or equivalently, if this tree changed as
	 * a result of the call). (This tree will not contain the element once the call
	 * returns.)
	 * 
	 * @citation: Remove method Code from BST Ed workspace
	 * 
	 * @param target object to be removed from this tree, if present
	 * 
	 * @return true if this set contained the specified element
	 * 
	 * @throws NullPointerException if the specified element is null
	 */
	@SuppressWarnings("unchecked")
	public boolean remove​(Object o) {
		// replace root with a reference to the tree after target was removed
		root = recRemove(((E) o), root);
		if (found)
			size--;
		return found;
	}

	/**
	 * Actual recursive implementation of remove method: find the node to remove.
	 *
	 * This function recursively finds and eventually removes the node with the
	 * target element and returns the reference to the modified tree to the caller.
	 * 
	 * @citation: Remove method Code from BST Ed workspace
	 * 
	 * @param target object to be removed from this tree, if present
	 * 
	 * @param node   node at which the recursive call is made
	 */
	private Node recRemove(E target, Node node) {
		if (node == null) { // value not found
			found = false;
			return node;
		}
		// decide how comparisons should be done
		int comp = 0;
		comp = target.compareTo(node.data);
		if (comp < 0) { // target might be in a left subtree
			node.left = recRemove(target, node.left);
			node.updateHeight(); // update the height for every function call
		} else if (comp > 0) {// target might be in a right subtree
			node.right = recRemove(target, node.right);
			node.updateHeight(); // update the height for every function call
		} else if (target.equals(node.data)) { // target found, now remove it
			node = removeNode(node);
			found = true;
		}

		return node;
	}

	/**
	 * Actual recursive implementation of remove method: perform the removal
	 *
	 * @citation: Remove method Code from BST Ed workspace
	 * 
	 * @param target the item to be removed from this tree
	 * 
	 * @return a reference to the node itself, or to the modified subtree
	 */
	private Node removeNode(Node node) {
		E data;
		if (node.left == null) { // handle the leaf and one child node with right subtree
			return node.right;
		} else if (node.right == null) { // handle one child node with left subtree
			return node.left;
		} else { // handle nodes with two children
			data = getPredecessor(node.left);
			node.data = data;
			node.left = recRemove(data, node.left);
			return node;
		}
	}

	/**
	 * Returns the size of the current BST
	 * 
	 * @return size of BST
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns a string representation of this tree. The string representation
	 * consists of a list of the tree's elements in the order they are returned by
	 * its iterator (inorder traversal), enclosed in square brackets ("[]").
	 * Adjacent elements are separated by the characters ", " (comma and space).
	 * Elements are converted to strings as by String.valueOf(Object).
	 * 
	 * @return string representation of the BST
	 */
	@Override
	public String toString() {
		String s = "[";
		Iterator<E> itr = this.iterator();
		while (itr.hasNext()) {
			s += String.valueOf(itr.next());
			if (itr.hasNext()) {
				s += ", ";
			}
		}
		s += "]";
		return s;
	}

	
	/**
	 * Uses preorder traversal to display the tree 
	 * 
	 * @citation Code from BST Ed Workspace
	 * 
	 * @return graphical string representation of the tree
	 */
	public String toStringTreeFormat() {
		StringBuffer sb = new StringBuffer();
		toStringTreeFormatRec(sb, root, 0);
		return sb.toString();
	}

	/**
	 * Recursive method for toStringTreeFormat
	 * 
	 * @citation Code from BST Ed Workspace
	 * 
	 * @param sb string to be built
	 * @param node current node
	 * @param level current level
	 */
	public void toStringTreeFormatRec(StringBuffer sb, Node node, int level) {
		// display the node
		if (level > 0) {
			for (int i = 0; i < level - 1; i++) {
				sb.append("   ");
			}
			sb.append("|--");
		}
		if (node == null) {
			sb.append("->\n");
			return;
		} else {
			sb.append(node.data + " " + node.size + "\n");
		}

		// display the left subtree
		toStringTreeFormatRec(sb, node.left, level + 1);
		// display the right subtree
		toStringTreeFormatRec(sb, node.right, level + 1);
	}
}