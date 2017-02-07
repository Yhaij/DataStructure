package my.tree;

public class RedBlackTree<AnyType extends Comparable<? super AnyType>> {
	private static final int BLACK = 1;
	private static final int RED = 0;
	
	private RedBlackNode<AnyType> head;
	private RedBlackNode<AnyType> nullNode;
	
	//insert 需要用到
	private RedBlackNode<AnyType> current;
	private RedBlackNode<AnyType> parent;
	private RedBlackNode<AnyType> grant;
	public RedBlackTree() {
		// TODO Auto-generated constructor stub
		nullNode = new RedBlackNode<AnyType>(null);
		nullNode.left = nullNode.right = nullNode;
		head = new RedBlackNode<AnyType>(null);
		head.left = head.right = nullNode;
	}
	private static class RedBlackNode<AnyType>{
		private int color;
		private AnyType element;
		private RedBlackNode<AnyType> left;
		private RedBlackNode<AnyType> right;
		public RedBlackNode(AnyType element) {
			// TODO Auto-generated constructor stub
			this(element,null,null);
		}
		public RedBlackNode(AnyType element,RedBlackNode<AnyType> left,RedBlackNode<AnyType> right){
			this.element = element;
			this.left = left;
			this.right = right;
			this.color = BLACK;
		}
	}
	public void instert(AnyType x){
		grant = parent = current = head;
		nullNode.element = x;
		while(compare(x, current) != 0){
			grant = parent;
			parent = current;
			current = compare(x,current)<0?current.left:current.right;
			if(current.left.color == RED && current.right.color == RED){
				handleReorient(x);
			}
		}
		if(current != nullNode){
			return ;
		}
		current = new RedBlackNode<AnyType>(x);
		current.color = RED;
		if(compare(x, parent) <0){
			parent.left = current;
		}else{
			parent.right = current;
		}
		handleReorient(x);    //最后还要判断一次，父节点可能是红色。
	}

	public AnyType find(AnyType x) {
		nullNode.element = x;
		RedBlackNode<AnyType> t = head.right;
		while (true) {
			if (x.compareTo(t.element) < 0) {
				t = t.left;
			} else if (x.compareTo(t.element) > 0) {
				t = t.right;
			} else {
				if (t != nullNode) {
					return t.element;
				} else {
					return null;
				}
			}
		}
	}
	public AnyType findMin(){
		RedBlackNode<AnyType> t = head.right;
		if(t == nullNode){
			return null;
		}
		while(t.left != nullNode){
			t = t.left;
		}
		return t.element;
	}
	public AnyType findMax(){
		RedBlackNode<AnyType> t = head.right;
		if(t == nullNode){
			return null;
		}
		while(t.right != nullNode){
			t = t.right;
		}
		return t.element;
	}
	public void remove(){
		
	}
	public void makeEmpty(){
		head.left = head.right = nullNode;
	}
	public boolean isEmpty(){
		return head.right == nullNode;
	}
	public void printTree(){
		printTree(head.right);
	}
	private void printTree(RedBlackNode<AnyType> t){
		if(t != nullNode){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	private int compare(AnyType x,RedBlackNode<AnyType> t){
		if(t == head){
			return 1;
		}
		return x.compareTo(t.element);
	}
	private void handleReorient(AnyType x){
		current.color = RED;
		current.left.color = current.right.color = BLACK;
		if(parent.color == RED){
			grant.color = RED;
			if(compare(x, grant)<0){
				if(compare(x, parent)<0){
					parent = roteWithLeftChild(grant);
					parent.color = BLACK;
					current = parent.left;
				}else{
					current = doubleRoteWithLeftChild(grant);
					current.color = BLACK;
				}
			}else{
				if(compare(x, parent)>0){
					parent = roteWithRightChild(grant);
					parent.color = BLACK;
					current = parent.right;
				}else{
					current = doubleRoteWithRightChild(grant);
					current.color = BLACK;
				}
			}
		}
		head.right.color = BLACK;
	}
	private RedBlackNode<AnyType> roteWithLeftChild(RedBlackNode<AnyType> father){
		RedBlackNode<AnyType> leftChild = father.left;
		father.left = leftChild.right;
		leftChild.right = father;
		return leftChild;
	}
	private RedBlackNode<AnyType> roteWithRightChild(RedBlackNode<AnyType> father){
		RedBlackNode<AnyType> rightChild = father.right;
		father.right = rightChild.left;
		rightChild.left = father;
		return rightChild;
	}
	private RedBlackNode<AnyType> doubleRoteWithLeftChild(RedBlackNode<AnyType> grantFather){
		grantFather.left = roteWithRightChild(grantFather.left);
		return roteWithLeftChild(grantFather);
	}
	private RedBlackNode<AnyType> doubleRoteWithRightChild(RedBlackNode<AnyType> grantFather){
		grantFather.right = roteWithLeftChild(grantFather.right);
		return roteWithRightChild(grantFather);
	}
}
