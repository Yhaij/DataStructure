package my.test;

import my.util.TreeMap;

public class TreeMapTest {
	public static void main(String[] args) {
		TreeMap<Integer,Integer>  treeMap = new TreeMap<>();
		int a[] = {10,85,15,70,20,60,30,50,65,80,90,40,5,55};
		for(int i = 0;i<a.length;i++){
			treeMap.put(a[i], a[i]);
		}
		treeMap.show();
	}
}
