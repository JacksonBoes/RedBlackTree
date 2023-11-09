import java.util.Scanner;

/**
 * Program to allow a user to interact with the implemented RedBlackTree class. 
 * @author Jackson Boes
 *
 */
public class MainClass {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice;
		RedBlackTree tree = new RedBlackTree();
		
		System.out.println("Hello and welcome the the red black tree extravaganza!");
		System.out.println("This main method will allow you as a user to add nodes to a red black tree "
				+ "\n(as (int, String) pairs) and also print all of the nodes in order. Have fun!\n");
		
		do {
			System.out.print("Enter a 1 to add a node to the tree, enter a 2 to print the in order representation of the"
					+ "\ntree and enter a 0 to quit the program: ");
			choice = Integer.parseInt(input.nextLine());
			if (choice == 1) {
				System.out.print("Enter the key for your new node: ");
				int key = Integer.parseInt(input.nextLine());
				System.out.print("Enter the value for your new node: ");
				String value = input.nextLine();
				tree.insert(key, value);
			} else if (choice == 2) {
				System.out.println(tree.inOrderTraversal());
			}
			
			
		} while(choice != 0);
					
		input.close();
	}

}
