
import java.util.Scanner;
import java.util.Stack;

/**
 * Objective - To use and understand binary search trees
 * Date Last Modified - 2/26/18 
 * Course - Data Structures 
 * Lab 3
 * Instructor - Olac Fuentues 
 * Teaching Assistants - Zakia Al Kadri
 * @author Isaias Leos Ayala
 */
public class bst_ops
{

	/**
	 * Insert a node into the Binary Search Tree
	 * @param T Binary Search Tree
	 * @param i integer your inserting
	 * @return 
	 */
	public static bstNode insert(bstNode T, int i)
	{
		if(T == null)//if null
		{
			T = new bstNode(i);
		}
		else if(i < T.item)//to the left
		{
			T.left = insert(T.left, i);
		}
		else//to the right
		{
			T.right = insert(T.right, i);
		}
		return T;
	}

	/**
	 * Recursive method that searches for integer in a binary search tree
	 * @param T Binary Search Tree
	 * @param i integer being searched
	 * @return 
	 */
	public static bstNode search(bstNode T, int i)
	{
		if(T == null || T.item == i)//if item is found
		{
			return T;
		}
		if(i < T.item)//search left if true
		{
			return search(T.left, i);
		}
		else//search right if false
		{
			return search(T.right, i);
		}
	}

	/**
	 * Find the minimum integer in the Binary Search Tree
	 * @param T Binary Search Tree
	 * @return smallest value
	 */
	public static bstNode min(bstNode T)
	{
		if(T.left == null)
		{
			return T;
		}
		return min(T.left);
	}

	/**
	 * Find the height of the Binary Search Tree
	 * @param T Binary Search Tree
	 * @return 
	 */
	public static int height(bstNode T)
	{
		if(T == null)
		{
			return -1;
		}
		int hl = height(T.left);//height of the left side
		int hr = height(T.right);//height of the right side
		if(hl > hr)
		{
			return 1 + hl;
		}
		return 1 + hr;
	}

	/**
	 * Deletes a node from the Binary Search Tree
	 * @param T Binary Search Tree
	 * @param i integer your deleting
	 * @return 
	 */
	public static bstNode delete(bstNode T, int i)
	{
		if(T == null)
		{
			return null;
		}
		if(i < T.item)
		{
			T.left = delete(T.left, i);
		}
		else if(i > T.item)
		{
			T.right = delete(T.right, i);
		}
		else // i == T.item
		if(T.left == null && T.right == null)
		{
			T = null;
		}
		else if(T.left == null && T.right != null)
		{
			T = T.right;
		}
		else if(T.left != null && T.right == null)
		{
			T = T.left;
		}
		else
		{
			bstNode succ = min(T.right);
			T.item = succ.item;
			T.right = delete(T.right, succ.item);
		}
		return T;
	}

	/**
	 * A form of traversing the Binary Search Tree
	 * @param T Binary Search Tree
	 */
	public static void inOrder(bstNode T)
	{//Eg. 1,2,3,4,5
		if(T != null)
		{//Check left then check right if empty
			inOrder(T.left);
			System.out.print(T.item + " ");
			inOrder(T.right);
		}
	}

	/**
	 * A form of traversing the Binary Search Tree
	 * Iterative Version
	 * Eg. 1,2,3,4,5
	 * @param T 
	 */
	public static void inOrderWithoutRecursion(bstNode T)
	{
		Stack<bstNode> stack = new Stack<>();//Create Generic Stack
		bstNode root = T;//Make a copy to the reference

		while(!stack.isEmpty() || root != null)//check if its empty
		{
			if(root != null)
			{//not empty push the left side to the stack
				stack.push(root);
				root = root.left;
			}
			else
			{//go to the right side
				bstNode node = stack.pop();
				System.out.print(node.item + " ");//Print the node value
				root = node.right;
			}

		}
	}

	/**
	 * Iterative method that searches for integer in a binary search tree
	 * @param T Binary Search Tree
	 * @param i integer being searched
	 * @return 
	 */
	public static bstNode searchIterative(bstNode T, int i)
	{
		bstNode root = T;
		while(root != null)
		{
			if(root.item == i)//If item is found
			{
				return root;
			}
			if(i < root.item)//if smaller
			{//check the left side
				root = root.left;
			}
			else//if bigger
			{//check the right side
				root = root.right;
			}
		}
		return root;//return the node if found
	}

	/**
	 * Converts the sorted array to a Binary Search Tree
	 * @param T Binary Search Tree
	 * @param array given sorted array
	 * @param i index of the array
	 * @return 
	 */
	public static int treeToArray(bstNode T, int[] array, int i)
	{
		if(T != null)
		{
			i = treeToArray(T.left, array, i);//insert left side
			array[i] = T.item;
			i++;
			i = treeToArray(T.right, array, i);//insert right side
		}
		return i;
	}

	/**
	 * Creates a Balanced Binary Search Tree from a sorted array
	 * @param array
	 * @param begin
	 * @param end
	 * @return
	 */
	public static bstNode arrayToTree(int array[], int begin, int end)
	{
		if(begin > end)
		{
			return null;
		}
		int mid = (begin + end) / 2;//Middle element
		bstNode node = new bstNode(array[mid]);//Make middle element root
		//Make the left subtree
		node.left = arrayToTree(array, begin, mid - 1);//make it left child
		//Make the right subtree
		node.right = arrayToTree(array, mid + 1, end);//make it right child
		return node;
	}

	/**
	 * Prints the nodes at the given depth
	 * @param T
	 * @param k Depth
	 */
	public static void printNodesAtDepth(bstNode T, int k)
	{
		if(T == null)
		{
			return;
		}
		if(k == 0)
		{
			System.out.print(T.item + " ");
			return;
		}
		else if(T.right != null || T.left != null)
		{
			printNodesAtDepth(T.left, k - 1);
			printNodesAtDepth(T.right, k - 1);
		}
	}

	/**
	 * Counts how many nodes are in the tree
	 * @param T Binary Tree Node
	 * @return
	 */
	public static int count(bstNode T)
	{
		if(T == null)
		{
			return 0;
		}
		return 1 + count(T.left) + count(T.right);//Count left and right side
	}

	public static void draw_tree(bstNode T, double x0, double x1, double y, double y_inc)
	{
		if(T == null)
		{
			return;
		}
		double xm = (x0 + x1) / 2;
		double yn = y - y_inc;
		if(T.left != null)
		{
			StdDraw.line(xm, y, (x0 + xm) / 2, yn);
			draw_tree(T.left, x0, xm, yn, y_inc);
		}
		if(T.right != null)
		{
			StdDraw.line(xm, y, (x1 + xm) / 2, yn);
			draw_tree(T.right, xm, x1, yn, y_inc);
		}
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledCircle(xm, y, 3);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.circle(xm, y, 3);
		StdDraw.text(xm, y, Integer.toString(T.item));
	}

	/**
	 * Pause the animation until the user presses ENTER
	 */
	public static void pause()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Press enter to continue.....");
		s.nextLine();
		StdDraw.clear();
	}

	public static void main(String[] args)
	{
		
		int x_max = 100;
		int y_max = 100;
		StdDraw.setXscale(0, x_max);
		StdDraw.setYscale(0, y_max);
		StdDraw.setPenColor(StdDraw.BLACK);

		int[] Array1 =
		{
			10, 16, 13, 17, 20, 19, 15, 4, 14, 8, 9, 5, 2, 3, 7, 12, 18, 1, 6, 11,19,21,55,62,32
		};
		bstNode firstBST = null;
		for(int i = 0; i < Array1.length; i++)
		{//insert nodes into tree
			firstBST = insert(firstBST, Array1[i]);
			StdDraw.pause(1000);
			draw_tree(firstBST, 0, x_max, y_max - 5, (y_max - 10.0) / height(firstBST));//draw tree
		}
		System.out.print("inOrder Iterative: ");
		inOrderWithoutRecursion(firstBST);

		int finding = 10;
		bstNode found = searchIterative(firstBST, finding);//Search for the integer in A
		System.out.println("");
		System.out.println("Item Searched: " + found.item);

		int[] Array2 =
		{
			2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 22, 33, 44, 55, 66, 77, 88, 99, 1010
		};//sorted array
		pause();//pause tree drawing
		bstNode secondBST = arrayToTree(Array2, 0, Array2.length - 1);
		draw_tree(secondBST, 0, x_max, y_max - 5, (y_max - 10.0) / height(secondBST));//draw the binary tree

		int size = count(firstBST);
		int[] sortedArray = new int[size - 1];

		bstNode secondBSTCopy = secondBST;
		treeToArray(secondBSTCopy, sortedArray, 0);//tree to array
		for(int i = 0; i < sortedArray.length; i++)
		{
			System.out.print(sortedArray[i] + " ");
		}//print the sorted array from the binary tree

		System.out.println("");//new line
		int depth = 4;
		System.out.print("Depth Searched: " + depth);
		printNodesAtDepth(secondBST, depth);//print nodes at certain depth
		
	}
}
