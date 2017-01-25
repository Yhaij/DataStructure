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
	public void printPreOrder(){
		if(root != null)
			root.PrintPreOrder();
	}
	public void printInOrder(){
		if(root != null)
			root.PrintInOrder();
	}
	public void printPosOrder(){
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
		return posOrderIterator();
	}
	public Iterator<AnyType> inOrderIterator(){
		return new InOrder();
	}
	public Iterator<AnyType> preOrderIterator(){
		return new PreOrder();
	}
	public Iterator<AnyType> posOrderIterator(){
		return new PosOrder();
	}
	private class InOrder extends TreeIterator<AnyType>{
		BinaryNode<AnyType> p ;
		private Stack<BinaryNode<AnyType>> s;
		public InOrder() {
			// TODO Auto-generated constructor stub
			s = new ArrayStack<BinaryNode<AnyType>>();
			s.clear();
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
			s.clear();
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
	private class PosOrder extends TreeIterator<AnyType>{
		private Stack<StNode> s;
		protected class StNode{
			private BinaryNode<AnyType> node;
			private int timePop;
			public StNode(BinaryNode<AnyType> node) {
				// TODO Auto-generated constructor stub
				this.node = node;
				this.timePop = 0;
			}
		}
		public PosOrder() {
			// TODO Auto-generated constructor stub
			s = new ArrayStack<StNode>();
			s.clear();
			current = null;
			if(root != null){
				s.push(new StNode(root));
			}
			advance();
		}
		@Override
		protected void advance() {
			// TODO Auto-generated method stub
			if(s.isEmpty()){
				current = null;
				return ;
			}
			StNode stNode;
			int timePop;
			for(;;){
				stNode = s.Pop();
				timePop = ++stNode.timePop;
				if(timePop == 3){
					current = stNode.node;
					return ;
				}
				if(timePop == 1){
					s.push(stNode);
					if(stNode.node.getLeft() != null){
						//s.push(stNode);   不能写在内部，若其左孩子没有也需要将该节点压栈
						s.push(new StNode(stNode.node.getLeft()));
					}
				}
				if(timePop == 2){
					s.push(stNode);
					if(stNode.node.getRight() != null){
						//s.push(stNode);
						s.push(new StNode(stNode.node.getRight()));
					}
				}
			}
		}
		
	}
}
