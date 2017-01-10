package my.test;

import my.util.ArrayList;
import my.util.ListIterator;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("word");
		list.add("array");
		list.add("list");
		System.out.println(list.contains("hello"));
		//Iterator<String> itr = list.iterator();
		/*while (itr.hasNext()){
			if(itr.next().equals("word")){
				itr.remove();
			}
			//itr.remove();
		}*/
		ListIterator<String> itr = list.listIterator(list.size());
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
		}
		//System.out.println(list.toString());
	}
}
