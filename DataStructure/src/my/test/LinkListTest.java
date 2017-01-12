package my.test;

import my.util.LinkedList;
import my.util.ListIterator;

public class LinkListTest {
	public static void main(String[] args) {
		LinkedList<Integer> linkList = new LinkedList<Integer>();
		for(int i = 0;i<10;i++){
			linkList.addLast(i);
		}
		/*ListIterator<Integer> itr = linkList.listIterator(0);
		while(itr.hasNext()){
			itr.next();
			itr.remove();
			System.out.println(linkList.toString());
		}*/
		ListIterator<Integer> itr = linkList.listIterator(10);
		while(itr.hasPrevious()){
			itr.previous();
			itr.remove();
			System.out.println(linkList.toString());
		}
		for(int obj:linkList){
			System.out.println(obj);
		}
		//System.out.println(linkList.size());
	}
}
