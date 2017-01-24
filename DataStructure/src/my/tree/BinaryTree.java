package my.tree;

import java.util.Iterator;
import my.util.ArrayStack;
import my.util.Stack;

public class BinaryTree<AnyType> implements Iterable<AnyType>{
	private BinaryNode<AnyType> root;
	public BinaryTree() {
		// TODO Auto-generated constructor stub
		root = new BinaryNode<AnyType>();
	}
	public BinaryTree(AnyType rootElement){
		root = new BinaryNode<AnyType>(rootElement, null, null);
	}
	public BinaryNode<AnyType> getRoot() {
		return root;
	}
	public int size(){
		return BinaryNode.size(root);
	}
	public int height(){
		return BinaryNode.height(root);
	}
	public void PrintPreOrder(){
		if(root != null)
			root.PrintPreOrder();
	}
	public void PrintInOrder(){
		if(root != null)
			root.PrintInOrder();
	}
	public void PrintPosDrder(){
		if(root != null)
			root.PrintPosOrder();
	}
	public void merge(AnyType rootElement,BinaryTree<AnyType> t1,BinaryTree<AnyType> t2){
		if(t1 == t2 && t1 != null){
			throw new IllegalArgumentException();
		}
		root = new BinaryNode<AnyType>(rootElement,t1.getRoot(),t2.getRoot());
		if(this != t1){
			t1 = null;
		}
		if(this != t2){
			t2 = null;
		}
	}
	public void makeEmpty(){
		root = null;
	}
	public boolean isEmpty(){
		return root == null;
	}
	public Iterator<AnyType> iterator(){              //增强for循环默认为中序遍历
		return inOrdeerIterator();
	}
	public Iterator<AnyType> inOrdeerIterator(){
		return new InOrder();
	}
	public Iterator<AnyType> preOrdeerIterator(){
		return new PreOrder();
	}
	private class InOrder extends TreeIterator<AnyType>{
		BinaryNode<AnyType> p ;
		private Stack<BinaryNode<AnyType>> s;
		public InOrder() {
			// TODO Auto-generated constructor stub
			s = new ArrayStack<BinaryNode<AnyType>>();
			current = null;
			p = root;
			advance();
		}
		@Override
		protected void advance() {
			// TODO Auto-generated method stub
			if(!s.isEmpty() || p != null){
				while(p != null){
					s.push(p);
					p = p.getLeft();
				}
				p = s.Pop();
				current = p;
				p = p.getRight();
			}else{
				current = p;
			}
			
		}
		
	}
	private class PreOrder extends TreeIterator<AnyType>{
		private Stack<BinaryNode<AnyType>> s;
		public PreOrder() {
			// TODO Auto-generated constructor stub
			s = new ArrayStack<BinaryNode<AnyType>>();
			current = root;
			if(current != null){
				s.push(current);
				advance();
			}
		}
		@Override
		protected void advance() {
			// TODO Auto-generated method stub
			/*if(!s.isEmpty() || p != null){
				while(p != null){
					current = p;
					s.push(p);
					p = p.getLeft();
				}
				p = s.Pop();
				p = p.getRight();
			}else{
				current = null;
			}*/
			if(!s.isEmpty()){
				current = s.Pop();
				if(current.getRight() != null){
					s.push(current.getRight());
				}
				if(current.getLeft() != null){
					s.push(current.getLeft());
				}
			}else{
				current = null;
			}
		}
	}
}
