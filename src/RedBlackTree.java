/**
 * A class to implement a red black tree.
 * @author Jackson Boes
 */
public class RedBlackTree {
	
	//enum for colors of nodes
	enum color {BLACK, RED};
	
	//nested class for the nodes of the tree
	public class node {
		public int key;
		public String value;
		public color nodeColor;
		public node parent;
		public node left;
		public node right;
		
		//node constructor
		public node(int key, String value) {
			this.key = key;
			this.value = value;
			this.nodeColor = color.RED;
			this.parent = null;
			this.left = null;
			this.right = null;
		}
		
		//traverse to step through the entire tree
		public String traverse() {
			String returnStr = "";
			
			//brackets surround subtrees, parentheses surround individual nodes
			if (this.left != null) {
				returnStr += "[" + this.left.traverse() + "] ";
			}
			returnStr += "((" + this.key + ", " + this.value + "), " + this.nodeColor + ")";
			if (this.right != null) {
				returnStr += " [" + this.right.traverse() + "]";
			}
			
			return returnStr;
		}
	}
	
	private node root;
	
	/**
	 * No argument constructor.
	 */
	public RedBlackTree() {
		this.root = null;
	}
	
	/**
	 * Method to return the root of the tree.
	 */
	public node getRoot() {
		return this.root;
	}
	
	/**
	 * Insert new nodes into the tree.
	 * @param key
	 * 		The integer key for the new node.
	 * @param value
	 * 		The String value for the new node.
	 * @ensures node (key, value) has been added to the red black
	 * tree and the tree still follows the rules of a red black tree.
	 */
	public void insert(int key, String value) {
		node newNode = new node(key, value);
		if (this.root == null) {
			this.root = newNode;
		} else {
			node traverse = this.root;
			while (newNode.parent == null) {
				if (newNode.key <= traverse.key) { 
					if (traverse.left != null) {
						traverse = traverse.left;
					} else {
						traverse.left = newNode;
						newNode.parent = traverse;
					}
				} else {
					if (traverse.right != null) {
						traverse = traverse.right;
					} else {
						traverse.right = newNode;
						newNode.parent = traverse;
					}
				}
			}
			insertFixup(newNode);
		}
		this.root.nodeColor = color.BLACK;
	}
	
	/**
	 * Helper method to maintain red black tree properties.
	 * @param newNode
	 * 		The node just added to the tree.
	 */
	private void insertFixup(node newNode) {
		//if newNode is a child of the root or other black node no fixing need be done
		if (newNode.parent != this.root && newNode.parent.nodeColor != color.BLACK) {
			newNode = case1(newNode);
			newNode = case2(newNode);
			newNode = case3(newNode);
		}
	}
	
	/**
	 * Private helper for case 1 of insertFixup.
	 * @param newNode
	 * 		The node just added to the tree.
	 * @returns the reference to the current node
	 */
	private node case1(node newNode) {
		
		while (newNode != this.root && newNode.parent.nodeColor != color.BLACK) {
			node sibling = sibling(newNode.parent);
			if (sibling != null && sibling.nodeColor == color.RED) {
				sibling.nodeColor = color.BLACK;
				newNode.parent.nodeColor = color.BLACK;
				newNode.parent.parent.nodeColor = color.RED;
				newNode = newNode.parent.parent;
			} else {
				return newNode;
			}
		}
		return newNode;
	}
	
	/**
	 * Private helper for case 2 of insertFixup.
	 * @param newNode
	 * 		The node just added to the tree.
	 * @returns the reference to the current node
	 */
	private node case2(node newNode) {
		if (newNode == this.root || newNode.parent.nodeColor == color.BLACK) {
			return newNode;
		} else if (newNode.parent.left == newNode && newNode.parent.parent.right == newNode.parent) {
			newNode = newNode.parent;
			rightRotate(newNode);
		} else if (newNode.parent.right == newNode && newNode.parent.parent.left == newNode.parent) {
			newNode = newNode.parent;
			leftRotate(newNode);
		}
		return newNode;
	}
	
	/**
	 * Private helper for case 3 of insertFixup.
	 * @param newNode
	 * 		The node just added to the tree.
	 * @returns the reference to the current node
	 */
	private node case3(node newNode) {
		if (newNode == this.root || newNode.parent.nodeColor == color.BLACK) {
			return newNode;
		} else if (newNode.parent.left == newNode && newNode.parent.parent.left == newNode.parent) {
			newNode.parent.nodeColor = color.BLACK;
			newNode.parent.parent.nodeColor = color.RED;
			rightRotate(newNode.parent.parent);
		} else if (newNode.parent.right == newNode && newNode.parent.parent.right == newNode.parent) {
			newNode.parent.nodeColor = color.BLACK;
			newNode.parent.parent.nodeColor = color.RED;
			leftRotate(newNode.parent.parent);
		}
		
		return newNode;
	}
	
	/**
	 * Helper to find the sibling of node parent.
	 * @param parent
	 * 		The node we are looking for the sibling of. 
	 * @returns the sibling of parent or null if parent has no sibling
	 * (ie parent is the root).
	 */
	private static node sibling(node parent) {
		node grandparent = parent.parent;
		node sibling = null;
		if (grandparent != null) {
			if (grandparent.right == parent) sibling = grandparent.left;
			else sibling = grandparent.right;
		}
		return sibling;
	}
	
	/**
	 * Helper to left rotate a tree node.
	 * @param treeNode
	 * 		The node being left rotated.
	 * @requires treeNode.right != null
	 */
	private void leftRotate(node treeNode) {
		node right = treeNode.right;
		right.parent = treeNode.parent;
		if (treeNode.parent != null) {
			if (treeNode.parent.right == treeNode) right.parent.right = right;
			else right.parent.left = right;
		} else {
			this.root = right;
		}
		treeNode.right = right.left;
		if (right.left != null) {
			treeNode.right.parent = treeNode;
		}
		treeNode.parent = right;
		right.left = treeNode;
	}
	
	/**
	 * Helper to right rotate a tree node.
	 * @param treeNode
	 * 		The node being right rotated.
	 * @requires treeNode.left != null
	 */
	private void rightRotate(node treeNode) {
		node left = treeNode.left;
		left.parent = treeNode.parent;
		if (treeNode.parent != null) {
			if (treeNode.parent.right == treeNode) left.parent.right = left;
			else left.parent.left = left;
		} else {
			this.root = left;
		}
		treeNode.left = left.right;
		if (left.right != null) {
			treeNode.left.parent = treeNode;
		}
		treeNode.parent = left;
		left.right = treeNode;
	}
	
	/**
	 * Returns a string of the red black tree's in order traversal.
	 * @returns a string containing a representation of the in order 
	 * traversal of the red black tree.
	 */
	public String inOrderTraversal() {
		return this.root.traverse();
	}
}
