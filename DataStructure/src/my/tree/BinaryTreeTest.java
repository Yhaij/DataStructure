package my.tree;

public class BinaryTreeTest {
	public static void main(String[] args) {
		BinaryTree<Integer> a = new BinaryTree<>(16);
		BinaryTree<Integer> b = new BinaryTree<>(15);
		for(int i=14;i>=0;i=i-2){
			a.merge(i, a, b);
			b = new BinaryTree<Integer>(i-1);
		}
		a.PrintInOrder();
		System.out.println("");
		for(Integer obj:a){
			System.out.print(obj+" ");
		}
		System.out.println("");
		a.PrintPreOrder();
		System.out.println("");
		for(Integer obj:a){
			System.out.print(obj+" ");
		}
		/*System.out.println("");
		a.printPosOrder();
		System.out.println("");
		Iterator<Integer> iterator = a.PostOrderIterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next()+"\t");
		}
		System.out.println("");
		a.printPreOrder();
		System.out.println("");
		Iterator<Integer> iterator2 = a.PreOrderIterator();
		while(iterator2.hasNext()){
			System.out.print(iterator2.next()+"\t");
		}
		System.out.println();
		Iterator<Integer> iterator3 = a.LeverOrderIterator();
		while(iterator3.hasNext()){
			System.out.print(iterator3.next()+"\t");
		}*/
	}
}
