package my.tree;

import java.util.DuplicateFormatFlagsException;

/*import my.util.ArrayQueue;
import my.util.Queue;*/

public class AATree<AnyType extends Comparable<? super AnyType>> {
	private AANode nullNode;
	private AANode root;
	private AANode lastNode;
	private AANode deleteNode;
	private class AANode{
		private AnyType element;
		private AANode left;
		private AANode right;
		private int level;
		public AANode(AnyType element) {
			// TODO Auto-generated constructor stub
			this(element,nullNode,nullNode);
		}
		public AANode(AnyType element,AANode left,AANode right){
			this.element = element;
			this.left = left;
			this.right = right;
			this.level = 1;
		}
	}
	public AATree() {
		// TODO Auto-generated constructor stub
		nullNode = new AANode(null,null,null);
		nullNode.left = nullNode.right = nullNode;
		nullNode.level = 0;
		root = nullNode;
	}
	
	public void insert(AnyType x){
		root = insert(x,root);
	}
	public void remove(AnyType x){
		root  = remove(x,root);
	} 
	public AnyType find(AnyType x){
		AANode t = root;
		nullNode.element = x;
		while(true){
			if(x.compareTo(t.element) < 0){
				t = t.left;
			}else if(x.compareTo(t.element) > 0){
				t = t.right;
			}else if(t != nullNode){
				return t.element;
			}else
				return null;
		}
	}
	public AnyType findMax(){
		AANode t = root;
		while(t.right != nullNode){
			t = t.right;
		}
		return t.element;
	}
	public AnyType findMin(){
		AANode t = root;
		while(t.left != nullNode){
			t = t.left;
		}
		return t.element;
	}
	
	private AANode insert(AnyType x,AANode t){
		if(t == nullNode){
			t = new AANode(x);
			return t;
		}
		if(x.compareTo(t.element) < 0){
			t.left = insert(x,t.left);
		}else if(x.compareTo(t.element) > 0){
			t.right = insert(x, t.right);
		}else{
			throw new DuplicateFormatFlagsException(x.toString());
		}
		t = skew(t);
		t = split(t);
		return t;
	}
	private AANode remove(AnyType x,AANode t){
		if(t != nullNode){
			lastNode = t;
			if(x.compareTo(t.element)<0){
				t.left = remove(x,t.left);
			}else{
				deleteNode = t;
				t.right = remove(x, t.right);
			}
		}
		if(t == lastNode){
			if(deleteNode == nullNode || x.compareTo(t.element) !=0){
				throw new ItemNotFoundException();
			}
			deleteNode.element = t.element;
			t = t.right;
		}else{
			if(t.left.level < t.level-1 || t.right.level <t.level-1){
				if(t.right.level > --t.level){
					t.right.level = t.level;
				}
				t = skew(t);
				t.right = skew(t.right);
				t.right.right = skew(t.right.right);
				t = split(t);
				t.right = split(t.right);
			}
		}
		return t;
	}
	private AANode skew(AANode t){
		if(t.level == t.left.level){
			t = rotateWithLeftChild(t);
		}
		return t;
	}
	private AANode split(AANode t){
		if(t.right.right.level == t.level){
			t = rotateWithRightChild(t);
			t.level++;
		}
		return t;
	}
	
	private AANode rotateWithLeftChild(AANode t){
		AANode leftChild = t.left;
		t.left = leftChild.right;
		leftChild.right = t;
		return leftChild;
	}
	private AANode rotateWithRightChild(AANode t){
		AANode rightChild = t.right;
		t.right = rightChild.left;
		rightChild.left = t ;
		return rightChild;
	} 
	public void printTree(){
		printTree(root);
	}
	private void printTree(AANode t){
		if(t != nullNode){
			printTree(t.left);
			System.out.print(t.element+" ");
			printTree(t.right);
		}
		/*Queue<AANode> queue = new ArrayQueue<AANode>();
		AANode q;
		if(t != nullNode)
			queue.enqueue(t);
		while(!queue.isEmpty()){
			q = queue.dequeue();
			System.out.print(q.element+" "+q.level+"     ");
			if(q.left != nullNode)
				queue.enqueue(q.left);
			if(q.right != nullNode)
				queue.enqueue(q.right);
		}*/
	}
}
