package my.test;

import java.util.LinkedList;
import java.util.ListIterator;


public class xxx {
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
		ListIterator<Integer> itr = linkList.listIterator(0);
		for(int i = 11;i<16;i++)
		if(itr.hasNext()){
			//itr.previous();
			itr.set(100);
			System.out.println(linkList.toString());
		}
		
		//System.out.println(linkList.size());
	}
}
