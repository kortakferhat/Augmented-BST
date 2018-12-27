import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
// 2015510099_Ferhat_Kortak
public class test {
	public static int[] orderedNumbers = new int[1000];
	private static BinaryTree bst = new BinaryTree();
	private static AugmentedTree atree = new AugmentedTree();
	public static int counter = 0;
	public static Random rnd = new Random();

	public static void main(String[] args) {
		// BST PART
		CreateNumbers();
		ShuffleNumbers();
		System.out.println("------ Binary Search Tree --------");
		CallBstInsert();
		int upperBound = 10;
		System.out.println("The result of GETSUMSMALLER for the item with value " + upperBound + " is "
				+ bst.startGetSumSmaller(upperBound));
		bst.sumAll = 0;
		System.out.println("The maximum value of all items is: " + bst.GetMax());
		System.out.println("The minimum value of all items is: " + bst.GetMin());
		long start = 0, finish = 0;

		start = System.nanoTime();
		long sumAllItems = bst.startGetSumSmaller(Integer.MAX_VALUE);
		finish = System.nanoTime();
		System.out.println("The summation of all items is " + sumAllItems);
		System.out.println("The time elapsed for GETSUM is " + (finish - start));

		// Augmented-Tree Part
		System.out.println("------ Augmented Binary Search Tree --------");
		AugmentedInsert();
		System.out.println("The result of GETSUMSMALLER for the item with value " + upperBound + " is "
				+ atree.startGetSumSmaller(upperBound));
		System.out.println("The maximum value of all items is : " + atree.TreeMaximum(atree.root).data);
		System.out.println("The minimum value of all items is: " + atree.TreeMinimum(atree.root).data);
		long startSum = 0, finishSum = 0;
		startSum = System.nanoTime();
		long sumAll = atree.getSumAll();
		finishSum = System.nanoTime();
		System.out.println("The summation of all items is: " + sumAll);
		finish = System.nanoTime();
		System.out.println("The time elapsed for GETSUM is: " + (finishSum - startSum) + " nanoseconds");
		System.out.println("All operations were done");
	}

	private static void AugmentedInsert() {
		long start = 0, finish = 0;
		start = System.nanoTime();
		// IN ORDER TO INSERT NUMBERS CHANGE UNCOMMENT BELOW
		//orderedNumbers = new int[] { 3, 10, 2, 5, 6, 7, 8, 4, 17 };
		for (int i = 0; i < orderedNumbers.length; i++)
			atree.Insert(orderedNumbers[i]);
		finish = System.nanoTime();
		System.out.println("All items were inserted.");
		System.out.println("The time elapsed for the insertion of all items is: " + (finish - start) + "nanoseconds");
	}

	private static void CallBstInsert() {
		long start = 0, finish = 0;
		start = System.nanoTime();
		// IN ORDER TO INSERT NUMBERS CHANGE UNCOMMENT BELOW
		//orderedNumbers = new int[] { 3, 10, 2, 5, 6, 7, 8, 4, 17 };
		for (int i = 0; i < orderedNumbers.length; i++)
			bst.Insert(orderedNumbers[i]);
		finish = System.nanoTime();
		System.out.println("All items were inserted.");
		System.out.println("The time elapsed for the insertion of all items is: " + (finish - start) + "nanoseconds");
	}

	private static void ShuffleNumbers() {
		// Implementing [Fisher–Yates] shuffle
		Random rnd = ThreadLocalRandom.current();
		for (int i = orderedNumbers.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Swap operation
			int temp = orderedNumbers[index];
			orderedNumbers[index] = orderedNumbers[i];
			orderedNumbers[i] = temp;
		}

	}

	private static void CreateNumbers() {
		int increaseValue = 0;
		for (int i = 0; i < orderedNumbers.length; i++) {
			increaseValue = rnd.nextInt(2) + 1;
			if (i > 0)
				orderedNumbers[i] = orderedNumbers[i - 1] + increaseValue;
			else
				orderedNumbers[i] = increaseValue;
		}
	}

}
