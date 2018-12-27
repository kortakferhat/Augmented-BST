
public class AugmentedTree {
	AugmentedNode root, predecessor;
	long sumAll = 0;
	long difference = Integer.MAX_VALUE;

	public AugmentedTree() {
		this.root = null;
	}

	public void Insert(int data) {
		if (root == null) {
			AugmentedNode temp = new AugmentedNode(data, 0);
			temp.parent = temp;
			root = temp;
		} else {
			AugmentedNode temp = root;
			while (temp != null) {
				if (data < temp.data) {
					if (temp.left == null) {
						AugmentedNode tempLeft = new AugmentedNode(data);
						tempLeft.parent = temp; // Bind the parent with new Node
						temp.left = tempLeft;
						UpdateSum(root, tempLeft); // Update the sum
						return;
					} else
						temp = temp.left;
				} else if (data > temp.data && temp.right == null) {
					AugmentedNode tempRight = new AugmentedNode(data);
					tempRight.parent = temp; // Bind the parent with new Node
					temp.right = tempRight;
					UpdateSum(root, tempRight); // Update the sum
					return;
				} else
					temp = temp.right;
			}
		}
	}

	public void UpdateSum(AugmentedNode newNode, AugmentedNode node) {
		// Based on In Order Tree Traversal
		if (newNode != null) {
			UpdateSum(newNode.left, node);
			if (newNode.data > node.data)
				newNode.sumofSmaller += node.data;
			else if (newNode.data < node.data)
				node.sumofSmaller += newNode.data;
			UpdateSum(newNode.right, node);
		}

		// // Based on successor and predecessor
		// AugmentedNode currentNode = newNode;
		// while (currentNode != root) {
		// AugmentedNode predecessor = FindPredecessor(currentNode);
		// if (predecessor.data < currentNode.data)
		// currentNode.sumofSmaller = predecessor.data +
		// predecessor.sumofSmaller;
		// currentNode = FindSuccessor(currentNode); // Skip to the next
		// // element
		// if (currentNode == root && (root.left != null)) {
		// predecessor = FindPredecessor(root);
		// currentNode.sumofSmaller = predecessor.data +
		// predecessor.sumofSmaller;
		// }
		// }

	}

	public long GetMax() {
		if (root == null) {
			System.out.println("There is no element.");
			return -1;
		} else {
			AugmentedNode temp = root;
			while (temp.right != null)
				temp = temp.right;
			return temp.data;
		}
	}

	public AugmentedNode FindSuccessor(AugmentedNode node) {
		if (node.right != null)
			return TreeMinimum(node.right);
		AugmentedNode y = node.parent;
		while (y != null && node == y.right) {
			node = y;
			y = y.parent;
		}
		return y;
	}

	public AugmentedNode FindPredecessor(AugmentedNode node) {
		if (node.left != null)
			return TreeMaximum(node.left);
		AugmentedNode y = node.parent;
		while (y != null && node == y.left) {
			node = y;
			y = y.parent;
		}
		return y;
	}

	public AugmentedNode TreeMinimum(AugmentedNode node) {
		AugmentedNode minimum = node;
		while (minimum.left != null)
			minimum = minimum.left;
		return minimum;
	}

	public AugmentedNode TreeMaximum(AugmentedNode node) {
		AugmentedNode maximum = node;
		while (maximum.right != null)
			maximum = maximum.right;
		return maximum;
	}

	public void printSums(AugmentedNode node) {
		if (node != null) {
			printSums(node.left);
			System.out.println("Sum of " + node.data + " is " + node.sumofSmaller);
			printSums(node.right);
		}
	}

	public long startGetSumSmaller(long data) {
		PredecessorWithData(root, data);
		return (predecessor.data + predecessor.sumofSmaller);
	}

	private void PredecessorWithData(AugmentedNode node, long data) {
		if (node != null) {
			PredecessorWithData(node.left, data);
			if ((data - node.data) > 0 && (data - node.data) < difference)
				predecessor = node;
			PredecessorWithData(node.right, data);
		}
	}

	public long getSumAll() {
		AugmentedNode max = TreeMaximum(root);
		return (max.data + max.sumofSmaller);
	}
}
