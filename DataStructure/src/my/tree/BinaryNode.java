package my.tree;

class BinaryNode<AnyType>{
	private AnyType element;
	private BinaryNode<AnyType> left;
	private BinaryNode<AnyType> right;
	public BinaryNode() {
		// TODO Auto-generated constructor stub
		this(null,null,null);
	}
	public BinaryNode(AnyType elemet,BinaryNode<AnyType> left,BinaryNode<AnyType> right){
		this.element = elemet;
		this.left = left;
		this.right = right;
	}
	public AnyType getElment(){
		return element;
	}
	public BinaryNode<AnyType> getLeft(){
		return left;
	}
	public BinaryNode<AnyType> getRight(){
		return right;
	}
	public void setElement(AnyType element){
		this.element = element;
	}
	public void setLeft(BinaryNode<AnyType> left){
		this.left = left;
	}
	public void setRight(BinaryNode<AnyType> right){
		this.right = right;
	}
	public int size(){
		return BinaryNode.size(this);
	}
	public int height(){
		return BinaryNode.height(this);
	}
	public static <AnyType> int size(BinaryNode<AnyType> t){
		if(t == null){
			return 0;
		}else{
			return 1+size(t.getLeft())+size(t.getRight());
		}
	}
	public static <AnyType> int height(BinaryNode<AnyType> t){
		if(t == null){
			return 0;
		}else{
			return 1+Math.max(height(t.getLeft()),height(t.getRight()));
		}
	}
	/**
	 * 树的深拷贝
	 * @return root of tree
	 */
	public BinaryNode<AnyType> duplicate(){
		BinaryNode<AnyType> root = new BinaryNode<AnyType>(this.getElment(), null, null);
		if(left != null){
			root.left = left.duplicate();
		}
		if(right != null){
			root.right = right.duplicate();
		}
		return root;		
	}
	public void PrintPreOrder(){
		/**
		 * 这种写法是错误的，因为无法知道其孩子节点是否存在（在java中没有创建的类是无法调用内部的非static的方法）
		 */
		/*if(this != null){
			System.out.println(this.getElment());
			this.getLeft().PrintOrder();
			this.getRight().PrintOrder();
		}*/
		System.out.println(this.getElment());
		if(this.getLeft() != null)
			this.getLeft().PrintPreOrder();
		if(this.getRight() != null)
			this.getRight().PrintPreOrder();
	}
	public void PrintInOrder(){
		if(this.getLeft() != null)
			this.getLeft().PrintPreOrder();
		System.out.println(this.getElment());
		if(this.getRight() != null)
			this.getRight().PrintPreOrder();
	}
	public void PrintPosOrder(){
		if(this.getLeft() != null)
			this.getLeft().PrintPreOrder();
		if(this.getRight() != null)
			this.getRight().PrintPreOrder();
		System.out.println(this.getElment());
	}
}