package my.tree;

public class BinaryTree<AnyType>{
	private BinaryNode<AnyType> root;
	public BinaryTree() {
		// TODO Auto-generated constructor stub
		root = null;
	}
	public BinaryTree(BinaryNode<AnyType> root){
		this.root = root;
	}
}
