package my.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<AnyType> extends AbstractCollection<AnyType> implements List<AnyType>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAUL_CAPCITY = 1;
	private static final int NOT_FOUND = -1;
	private AnyType[] item;
	private int theSize; 
	private int modCount = 0;
	public ArrayList() {
		// TODO Auto-generated constructor stub
		clear();
	}
	public ArrayList(Collection<? extends AnyType> other){
		clear();
		for(AnyType val:other){
			this.add(val);			
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		theSize = 0;
		item  = (AnyType[]) new Object[DEFAUL_CAPCITY]; 
		modCount++;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return theSize;
	}

	@Override
	public AnyType get(int idx) {
		// TODO Auto-generated method stub
		if(isOutOfLength(idx))
			throw new ArrayIndexOutOfBoundsException();
		return item[idx];
	}

	@Override
	public AnyType set(int idx, AnyType newVal) {
		// TODO Auto-generated method stub
		if(isOutOfLength(idx))
			throw new ArrayIndexOutOfBoundsException();
		AnyType old = item[idx];
		item[idx] = newVal;
		return old;
	}
	
	public boolean isOutOfLength(int idx){
		if(idx>=0&&idx<theSize){
			return false;
		}
		return true;
	}

	@Override
	public boolean contains(Object x) {
		// TODO Auto-generated method stub
		return findPos(x)!= NOT_FOUND;
	}
	
	public int findPos(Object x){
		for(int i = 0;i<theSize;i++){
			if(x == null){
				if(item[i] == null)
					return i;
			}else
				if(x.equals(item[i]))
					return i;
		}
		return NOT_FOUND;
	}
	
	@Override
	public boolean add(AnyType x) {
		// TODO Auto-generated method stub
/*		if(theSize ==item.length){
			@SuppressWarnings("unchecked")
			AnyType[] arr = (AnyType[])new Object[item.length*2+1];
			for(int i = 0;i<item.length;i++){
				arr[i] = item[i];
			}
			item = arr;
		}
		item[theSize++] = x;
		modCount++;
		return true;*/	
		return add(size(),x);
	}
	
	public boolean add(int idx,AnyType x){
		if(idx <0 || idx >size())
			throw new IndexOutOfBoundsException();
		if(theSize ==item.length){
			@SuppressWarnings("unchecked")
			AnyType[] arr = (AnyType[])new Object[item.length*2+1];
			for(int i = 0;i<item.length;i++){
				arr[i] = item[i];
			}
			item = arr;
		}
		for(int i = size();i<idx;i++){
			item[i] = item[i-1];
		}
		item[idx] = x;
		theSize++;
		modCount++;
		return true;
	}
	
	public boolean addFirst(AnyType x){
		return add(0,x);
	}
	
	@Override
	public boolean remove(Object x) {
		// TODO Auto-generated method stub
		int idx = findPos(x);
		if(idx == NOT_FOUND)
			return false;
		for(int i = idx+1;i<theSize;i++){
			item[i-1] = item[i];
		}
		theSize--;
		modCount++;
		return true;
	}
	
	public AnyType remove(int idx){
		if(isOutOfLength(idx)){
			throw new ArrayIndexOutOfBoundsException();
		}
		AnyType old = item[idx];
		for(int i = idx+1;i<theSize;i++){
			item[i-1] = item[i];
		}
		theSize--;
		modCount++;
		return old;
	}
	
	public AnyType removeFirst(){
		return remove(0);
	}
	
	public AnyType removeLast(){
		return remove(size()-1);
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return  new ArrayListIterator(0);
	}
	
	@Override
	public ListIterator<AnyType> listIterator(int idx) {
		// TODO Auto-generated method stub
		return new ArrayListIterator(idx);
	}
	private class ArrayListIterator implements ListIterator<AnyType>{

		private int current;
		private int exceptModCount = modCount;
		private int isNext = -1;
		public ArrayListIterator(int idx) {
			// TODO Auto-generated constructor stub
			if(idx<0||idx>size())
				throw new IndexOutOfBoundsException();
			current = idx;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(exceptModCount != modCount)
				throw new ConcurrentModificationException();
			return current < size();
		}

		@Override
		public AnyType next() {
			// TODO Auto-generated method stub
			if(!hasNext())
				throw new NoSuchElementException();
			isNext = 1;
			return ArrayList.this.item[current++];
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if(exceptModCount != modCount)
				throw new ConcurrentModificationException();
			if(isNext == 1)
				ArrayList.this.remove(--current);
			else if(isNext == 0)
				ArrayList.this.remove(current);
			else 
				throw new IllegalStateException();
			isNext = -1;
			exceptModCount++;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			if(exceptModCount != modCount)
				throw new ConcurrentModificationException();
			return current >0;
		}

		@Override
		public AnyType previous() {
			// TODO Auto-generated method stub
			if(!hasPrevious())
				throw new NoSuchElementException();
			isNext = 0;
			return ArrayList.this.item[--current];
		}
		@Override
		public void set(AnyType x) {
			// TODO Auto-generated method stub
			if(exceptModCount != modCount)
				throw new ConcurrentModificationException();
			if(isNext == 1)
				ArrayList.this.set(current-1,x);
			else if(isNext == 0)
				ArrayList.this.set(current,x);
			else 
				throw new IllegalStateException();
		}
		@Override
		public void add(AnyType x) {
			// TODO Auto-generated method stub
			if(exceptModCount != modCount)
				throw new ConcurrentModificationException();
			if(isNext == 1)
				ArrayList.this.set(current-1,x);
			else
				ArrayList.this.set(current,x);
		}
		
	}
}
