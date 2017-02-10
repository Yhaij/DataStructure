package my.test;

import my.tree.AATree;

public class AATreeTest {
	public static void main(String[] args) {
		AATree<Integer> aATree = new AATree<Integer>();
		int a[] = {10,85,15,70,20,60,30,50,65,80,90,40,5,55,35};
		for(int i = 0;i<a.length;i++){
			aATree.insert(a[i]);
		}
		aATree.printTree();
	}
}
