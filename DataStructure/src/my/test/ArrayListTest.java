package my.test;

import java.util.Iterator;

import my.util.ArrayList;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("word");
		list.add("array");
		list.add("list");
		System.out.println(list.contains("hello"));
		Iterator<String> itr = list.iterator();
		while (itr.hasNext()){
			/*if(itr.next().equals("word")){
				itr.remove();
			}*/
			itr.next();
			itr.remove();
		}
		System.out.println(list.size());
		/*ListIterator<String> itr = list.listIterator(list.size());
		while(itr.hasPrevious()){
			String remaveItem = (String) itr.previous();
			System.out.println(remaveItem);
			if(remaveItem.equals("array")){
				System.out.println("remove start");
				itr.remove();
			}
		}
		for(String obj:list){
			System.out.println(obj);
		}*/
		//System.out.println(list.toString());
	}
}
