package my.tree;

import java.util.DuplicateFormatFlagsException;
import java.util.NoSuchElementException;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	protected BinaryNode root;

	protected class BinaryNode {
		AnyType element;
		BinaryNode left;
		BinaryNode right;

		public BinaryNode(AnyType element) {
			// TODO Auto-generated constructor stub
			this.element = element;
			left = right = null;
		}
	}

	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void makeEmoty() {
		root = null;
	}

	public void insert(AnyType x) {
		insert(root, x);
	}

	public AnyType find(AnyType x) {
		return elementAt(find(root, x));
	}

	public AnyType findMin() {
		return elementAt(findMin(root));
	}

	public AnyType findMax() {
		return elementAt(findMax(root));
	}

	public void remove(AnyType x) {
		root = remove(root, x);
	}

	public void removeMin() {
		root = removeMin(root);
	}

	private AnyType elementAt(BinaryNode t) {
		return t == null ? null : t.element;
	}

	protected void insert(BinaryNode t, AnyType x) {
		if (t != null) {
			if (x.compareTo(t.element) < 0) {
				insert(t.left, x);
			} else if (x.compareTo(t.element) > 0) {
				insert(t.right, x);
			} else
				throw new DuplicateFormatFlagsException(x.toString()); // 插入相同的元素
		} else {
			t = new BinaryNode(x);
		}
	}

	private BinaryNode find(BinaryNode t, AnyType x) {
		while (t != null) {
			if (x.compareTo(t.element) < 0)
				t = t.left;
			else if (x.compareTo(t.element) > 0) {
				t = t.right;
			} else
				return t;
		}
		return null; // Not Found
	}

	protected BinaryNode findMin(BinaryNode t) {
		if (t != null)
			while (t.left != null) {
				t = t.left;
			}
		return t;
	}

	private BinaryNode findMax(BinaryNode t) {
		if (t != null)
			while (t.right != null) {
				t = t.right;
			}
		return t;
	}

	protected BinaryNode remove(BinaryNode t, AnyType x) {
		if (t != null)
			if (x.compareTo(t.element) < 0) {
				t.left = remove(t.left, x);
				return t;
			} else if (x.compareTo(t.element) > 0) {
				t.right = remove(t.right, x);
				return t;
			} else if(t.left != null && t.right != null){
				t.element = elementAt(findMin(t.right));
				t.right = removeMin(t.right);
				return t;
			}else{
				return t.left == null?t.right:t.left;
			}
		else
			throw new NoSuchElementException();
	}

	protected BinaryNode removeMin(BinaryNode t) {
		if (t != null) {
			if (t.left != null) {
				t.left = removeMin(t.left);
				return t;
			} else
				return t.right;
		} else
			throw new NoSuchElementException();
	}
}
