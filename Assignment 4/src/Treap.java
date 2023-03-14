import java.util.Random;
import java.util.Stack;


public class Treap<E extends Comparable<E>>{
	
	/** Represents a node within the Treap
	 * each node can be given a right and left field
	 */
	private static class Node<E> {
		//data fields
		public E data; //key for the search
		public int priority; //random heap priority
		public Node<E> left;
		public Node<E> right;
		
		//constructors
		public Node(E data, int priority) {
			//creates new Node w given data and priority
			//throw exception if data is null
			if (data == null) {
				throw new IllegalArgumentException(
						"Data cannot be null!");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		/** Performs a right rotation of the Treap
		 * @return new root
		 */
		public Node<E> rotateRight() {
			Node<E> root = this.left;
			Node<E> left = root.right;
			root.right = this;
			this.left = left;
			return root;
		}
		
		/** Performs a left rotation of the Treap
		 * @return new root
		 */
		public Node<E> rotateLeft() {
			Node<E> root = this.right;
			Node<E> right = root.left;
			root.left = this;
			this.right = right;
			return root;
		}
	}
	
	private Random priorityGenerator;
	private Node<E> root;
	
	// Constructors
	public Treap() {
		//create new Treap
		priorityGenerator = new Random();
		//root = null;
	}
	
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		//root = null;
	}
	
	/** Wrapper method for add
	 * 
	 * @param key element that will be added to the tree
	 * @return true if the element was added, false otherwise
	 */
	public boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	
	/** Helper method reheap
	 * 
	 * @param stac
	 * @param c
	 */
	private void reheap(Stack<Node<E>> stac, Node<E> c) {
		while (stac.isEmpty() == false) {
			Node<E> p = stac.pop();
			if (p.priority < c.priority) {
				if (p.data.compareTo(c.data) > 0) { 
					c = p.rotateRight();
				} else {
					c = p.rotateLeft();
				}
				if (stac.isEmpty() == false) {
					if (stac.peek().left == p) {
						stac.peek().left = c;
					} else {
						stac.peek().right = c;
					}
				} else {
					this.root= c;
				}
			} else {
				break;
			}
		}
	}
	
	/** Adds an element to the Treap
	 * 
	 * @param key element that will be added
	 * @param priority 
	 * @return true if the node has been added, false otherwise
	 */
	public boolean add(E key, int priority) {
		Node<E> in = new Node<E>(key, priority);
		if (root == null) {
			root = in;
			return true; 
		} else {
			Node<E> curr = root;
			
			//put all Nodes into a Stack
			Stack<Node<E>> stack = new Stack<Node<E>>();
			
			while (curr != null) {
				if (curr.data.compareTo(key) == 0) {
					return false;
					// element already exists
				}
				if (curr.data.compareTo(key)<0) {
					stack.push(curr);
					if (curr.right == null) {
						curr.right = in;
						reheap(stack, in);
						return true;
					} else {
						curr = curr.right;
					}
				
				} else {
					stack.push(curr);
					if (curr.left == null) {
						curr.left = in;
						reheap(stack, in);
						return true;
					} else {
						curr = curr.left;
					}
				}

			}
			return false;
		} 
		
	}
	
	/** Deletes a node 
	 * 
	 * @param key
	 * @return true if the node has been deleted
	 */
	public boolean delete(E key){
		if(find(key) == false) {
			return false;
		} else {
			Node<E> curr = root;
			Node<E> first = null;
			Stack<Node<E>> stac = new Stack<Node<E>>();
			while (key != curr.data) {
				first = curr;
				stac.push(first);
				if(key.compareTo(curr.data) > 0) {
					curr = curr.right;
				} else {
					curr = curr.left;
				}
			}
			if (curr.equals(root)) {
				if (curr.right == null && curr.left == null) {
					root = null;
					return true;
				} else if (curr.right == null) {
					first = curr.rotateRight();
					root = first;
				} else if(curr.left == null) {
					first = curr.rotateLeft();
					root = first;
				} else {
					first = curr.rotateRight();
					root = first;
				}
				stac.push(first);
			}
		} return true;
	}
			

	
	/** Locates a node in the Treap
	 * 
	 * @param current
	 * @param key
	 * @return true if the node is in the Treap
	 */
	private boolean find(Node<E> current, E key) {
		if (current == null) {
			return false;
		} else if (root.data == key) {
			return true;
		} else {
			return find(root.left, key)  || find(current.left, key);
		}
	}
	
	/** Wrapper method for find
	 * 
	 * @param key element to find in the tree
	 * @return true if the element was found, false otherwise
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	private StringBuilder toString(Node<E> current, int p) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<p;i++) {
			sb.append(" ");
		}
		if (current == null) {
			sb.append("null\n");
		} else {
			sb.append(current.data.toString() + "\n");
			sb.append(toString(current.left, p+1));
			sb.append(toString(current.right, p+1));			
		}
		return sb;
	}
	
	/** Wrapper method for toString
	 * 
	 * @return true if the element was added, false otherwise
	 */
	
	public String toString() {
		return toString(root, 1).toString();
	}

	public static void main(String[] args) {
		Treap<Integer> i = new Treap<Integer>();
		i.add(45, 1);
		System.out.println(i.toString());
		i.delete(45);
		System.out.println(i.toString());

	}

}
