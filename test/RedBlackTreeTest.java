import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RedBlackTreeTest {

	/**
	 * Test inserting one element.
	 */
	@Test
	public void insertOne() {
		RedBlackTree tree = new RedBlackTree();
		tree.insert(1, "1");
		assertEquals(tree.inOrderTraversal(), "((1, 1), BLACK)");
	}
	
	/**
	 * Test inserting three elements in increasing order.
	 */
	@Test
	public void insertThreeIncreasing() {
		RedBlackTree tree = new RedBlackTree();
		tree.insert(1, "1");
		tree.insert(2, "2");
		tree.insert(3, "3");
		assertEquals(tree.inOrderTraversal(), "[((1, 1), RED)] ((2, 2), BLACK) [((3, 3), RED)]");
	}
	
	/**
	 * Test inserting three elements in decreasing order.
	 */
	@Test
	public void insertThreeDecreasing() {
		RedBlackTree tree = new RedBlackTree();
		tree.insert(3, "3");
		tree.insert(2, "2");
		tree.insert(1, "1");
		assertEquals(tree.inOrderTraversal(), "[((1, 1), RED)] ((2, 2), BLACK) [((3, 3), RED)]");
	}
	
	/**
	 * Test inserting elements to force case 1 to occur.
	 */
	@Test
	public void forceCase1() {
		RedBlackTree tree = new RedBlackTree();
		tree.insert(3, "3");
		tree.insert(2, "2");
		tree.insert(1, "1");
		tree.insert(4, "4");
		assertEquals(tree.inOrderTraversal(), "[((1, 1), BLACK)] ((2, 2), BLACK) [((3, 3), BLACK) [((4, 4), RED)]]");
	}
	
	/**
	 * Test inserting elements to force multiple cases to occur.
	 */
	@Test
	public void forceMultipleCases() {
		RedBlackTree tree = new RedBlackTree();
		tree.insert(5, "5");
		tree.insert(2, "2");
		tree.insert(1, "1");
		tree.insert(20, "20");
		tree.insert(3, "3");
		tree.insert(10, "10");
		tree.insert(25, "25");
		tree.insert(30, "30");
		assertEquals(tree.inOrderTraversal(), "[[((1, 1), BLACK)] ((2, 2), RED) [((3, 3), BLACK)]] ((5, 5), BLACK) [[((10, 10), BLACK)] ((20, 20), RED) [((25, 25), BLACK) [((30, 30), RED)]]]");
	}
	
	/**
	 * Test inserting elements to force multiple cases to occur.
	 */
	@Test
	public void forceMultipleCases2() {
		RedBlackTree tree = new RedBlackTree();
		tree.insert(30, "30");
		tree.insert(5, "5");
		tree.insert(2, "2");
		tree.insert(1, "1");
		tree.insert(20, "20");
		tree.insert(3, "3");
		tree.insert(10, "10");
		tree.insert(25, "25");
		assertEquals(tree.inOrderTraversal(), "[[((1, 1), RED)] ((2, 2), BLACK) [((3, 3), RED)]] ((5, 5), BLACK) [[((10, 10), BLACK)] ((20, 20), RED) [[((25, 25), RED)] ((30, 30), BLACK)]]");
	}

}
