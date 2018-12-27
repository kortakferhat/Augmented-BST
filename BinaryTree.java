
public class BinaryTree {
    long sumAll = 0;
	private Node root;

	public BinaryTree() {
		this.root = null;
	}

	public void Insert(int additive) {
		// If root is available
		if (root == null)
			root = new Node(additive);
		else {
			Node temp = root;
			while (temp != null) {
				if (additive < temp.data) {
					if (temp.left == null) {
						temp.left = new Node(additive);
						return;
					} else
						temp = temp.left;
				} else if (additive > temp.data && temp.right == null) {
					temp.right = new Node(additive);
					return;
				} else
					temp = temp.right;

			}
		}
	}

	public int GetMax() {
		if (root == null) {
			System.out.println("There is no element.");
			return -1;
		} else {
			Node temp = root;
			while (temp.right != null)
				temp = temp.right;
			return temp.data;
		}
	}

	public int GetMin() {
		if (root == null) {
			System.out.println("There is no element.");
			return -1;
		} else {
			Node temp = root;
			while (temp.left != null)
				temp = temp.left;
			return temp.data;
		}
	}

	public long startGetSumSmaller(int keyNumber) {
		return GetSumSmaller(root, keyNumber);
	}

	private long GetSumSmaller(Node subRoot, int key) {

		Node temp=subRoot;
		if (temp != null) {
			GetSumSmaller(temp.left, key);
			if (temp.data < key)
				sumAll += temp.data;
			GetSumSmaller(temp.right, key);
		}
		return sumAll;
	}
}
