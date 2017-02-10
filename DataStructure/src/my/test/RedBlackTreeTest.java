package my.test;

import my.tree.RedBlackTree;

public class RedBlackTreeTest {
	public static void main(String[] args) {
		int a[] = {10,85,15,70,20,60,30,50,65,80,90,40,5,55};
		RedBlackTree<Integer> redBlackTree = new RedBlackTree<Integer>();
		for(int i =0;i<a.length;i++){
			redBlackTree.instert(a[i]);
		}
		redBlackTree.printTree();
	}
}
