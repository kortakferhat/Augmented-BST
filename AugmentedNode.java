
public class AugmentedNode {
	long data;
	long sumofSmaller;
	AugmentedNode left,right,parent;  // [PARENT] for Doubly Linked List

	// For root element
	public AugmentedNode(int data, int sumofSmaller) {
		this.data = data;
		this.sumofSmaller = sumofSmaller;
		left = null;
		right = null;
	}

	public AugmentedNode(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}
