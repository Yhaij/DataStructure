package my.tree;

import java.io.File;

public class FileSystem extends File{                    /**File先调用io包里面的，以后实现
	 * 
	 */
	private static final long serialVersionUID = -1224535693905808756L;

	long totalSize = 0;
	public FileSystem(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}
	public void printName(int depth){
		for(int i=0;i<depth;i++){
			System.out.print("\t");
		}
		System.out.println(getName());
	}
	public long size(){
		totalSize += length();
		if(isDirectory()){
			String [] str = list();
			for(String obj:str){
				FileSystem child = new FileSystem(getPath()+separator+obj);
				child.size();
			}
		}
		return totalSize;
	}
	public void ListAll(){
		ListAll(0);
	}
	public void ListAll(int depth){
		printName(depth);
		if(isDirectory()){
			String [] str = list();
			for(String obj:str){
				FileSystem child = new FileSystem(getPath()+separator+obj);
				child.ListAll(depth+1);
			}
		}
	}
	public static void main(String[] args) {
		FileSystem f = new FileSystem(".");
		f.ListAll();
		//System.out.println(f.size());
	}
}
