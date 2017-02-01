package my.tree;

import java.util.DuplicateFormatFlagsException;

public class BinarySearchTreeWithRank<AnyType extends Comparable<? super AnyType>> extends BinarySearchTree<AnyType>{
	private class BinaryNodeWithRank extends BinaryNode{
		protected int size;
		public BinaryNodeWithRank(AnyType element ) {
			// TODO Auto-generated constructor stub
			super(element);
			size = 0;
		}
	}
	public AnyType findKth(int k){
		return findKth(k,root).element;
	}
	private  BinaryNode findKth(int k,BinaryNode tt){
		if(tt == null)
			throw new IllegalArgumentException();
		@SuppressWarnings("unchecked")
		BinaryNodeWithRank t = (BinaryNodeWithRank) tt;
		if(k < t.size){
			return findKth(k,tt.left);
		}else if(k > t.size){
			return findKth(k,tt.right);
		}else
			return t;
	} 
	@SuppressWarnings("unchecked")
	protected BinaryNode insert(AnyType x,BinaryNode tt){
		if(tt == null){
			tt = new BinaryNode(x);
		}else{
			if(tt.element.compareTo(x) < 0){
				tt.left = insert(x,tt.left);
			}else if(tt.element.compareTo(x) > 0){
				tt.right = insert(x,tt.right);
			}else{
				throw new DuplicateFormatFlagsException(x.toString());
			}
		}
		((BinaryNodeWithRank)tt).size++;
		return tt;
	}
	@SuppressWarnings("unchecked")
	protected BinaryNode removeMin(BinaryNode tt){
		if(tt == null){
			throw new IllegalArgumentException();
		}
		if(tt.left != null){
			tt.left = removeMin(tt.left);
		}else{
			return tt.right;                //不是tt = tt.right;，从这之后的size都不用减
		}
		((BinaryNodeWithRank)tt).size--;
		return tt;
	}
	@SuppressWarnings("unchecked")
	protected BinaryNode remove(BinaryNode tt,AnyType x){
		if(tt == null)
			throw new IllegalArgumentException();
		if(tt.element.compareTo(x) <0){
			tt.left = remove(tt.left, x);
		}else if(tt.element.compareTo(x) >0){
			tt.right = remove(tt.right,x);
		}else if(tt.left != null && tt.right!= null){
			tt.element = findMin(tt.right).element;
			tt.right = removeMin(tt.right);
		}else
			return (tt.left == null)?tt.right:tt.left;   //同理tt = (tt.left == null)?tt.right:tt.left;
		((BinaryNodeWithRank)tt).size--;
		return tt;
	}
}
