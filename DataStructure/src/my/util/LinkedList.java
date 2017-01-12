package my.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<AnyType> extends AbstractCollection<AnyType>
								implements List<AnyType>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7231595004219268531L;
	private int theSize;
	private Node<AnyType> head;
	private Node<AnyType> tail;
	private int modCount;
	private static final int NOT_FOUND = -1;
	private static class Node<AnyType>{
		public AnyType data;
		public Node<AnyType> pre;
		public Node<AnyType> next;
		public Node() {
			// TODO Auto-generated constructor stub
			data = null;
			pre = null;
			next = null;
		}
		public Node(AnyType data,Node<AnyType> pre,Node<AnyType> next){
			this.data = data;
			this.next = next;
			this.pre = pre;
		}
	}
	
	public LinkedList() {
		// TODO Auto-generated constructor stub
		head = new Node<AnyType>();
		tail = new Node<AnyType>(null, head, null);
		clear();
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head.next = tail;
		theSize = 0;
		modCount++;
	}
	
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] arr = new Object[theSize];
		int i = 0;
		for(Node<AnyType> p =head.next; p != tail;p=p.next){
			arr[i++] = p.data;
		}
		return arr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <OtherType> OtherType[] toArray(OtherType[] arr) {
		// TODO Auto-generated method stub
		int i = 0;
		if(arr.length < theSize) {
			arr = (OtherType[])java.lang.reflect.Array.newInstance(arr.getClass().getComponentType(),theSize);
		}
		for(Node<AnyType> p = head.next;p != tail;p= p.next){
			arr[i++] = (OtherType)p.data;
		}
		return arr;
	}
	
	@Override
	public boolean contains(Object x) {
		// TODO Auto-generated method stub
/*		if(findPos(x)!= null)
			return true;
		return false;*/
		if(IndexOf(x) != NOT_FOUND)
			return true;
		return false;
	}
	
	private Node<AnyType> findPos(Object x){
		for(Node<AnyType> p = head;p.next != tail;p=p.next){
			if(x == null){
				if(p.next.data == null)
					return p;
				}
			else
				if(x.equals(p.next.data))
					return p;
		}
		return null;
	}
	
	@Override
	public boolean add(AnyType x) {
		// TODO Auto-generated method stub
		return addLast(x);
	}
	
	public boolean add(int idx,AnyType x){
		Node<AnyType> p = getNode(idx);
		return add(p,x);
	}
	
	boolean add(Node<AnyType> p,AnyType x){
		Node<AnyType> newNode = new Node<AnyType>(x,p.pre,p);
		p.pre.next = newNode;
		p.pre = newNode;
		modCount++;
		theSize++;
		return true;
	}
	
	public boolean addFirst(AnyType x){
		/*Node<AnyType> newNode = new Node<AnyType>(x,head,head.next);
		head.next.pre = newNode;
		head.next = newNode;
		return true;*/
		return add(0,x);
	}
	
	public boolean addLast(AnyType x){
		/*Node<AnyType> newNode = new Node<AnyType>(x,tail.pre,tail);
		tail.pre.next = newNode;
		tail.pre = newNode;
		return true;*/
		return add(size(),x);
	}
	
	public boolean addAll(Collection<? extends AnyType> c){
		return addAll(size(),c);
	}
	
	@SuppressWarnings("unchecked")
	public boolean addAll(int idx,Collection<? extends AnyType> c){			
		if(idx < 0||idx > size())
			throw new IndexOutOfBoundsException();
		Object[] arr = c.toArray();
		int numNew = arr.length;
		if(numNew == 0){
			return false;
		}
		for(int i = numNew-1;i>=0;i--){
			add(idx,(AnyType) arr[i]);
		}
		theSize = theSize + arr.length;
		modCount++;
		return true;
	}
	
	public AnyType remove(int idx){
		Node<AnyType> p = getNode(idx);
		AnyType result = p.data;
		remove(p);
		return result;
	}
	
	@Override
	public boolean remove(Object x) {
		// TODO Auto-generated method stub
		Node<AnyType> p = findPos(x);
		if(p != null){
			return remove(p);
		}
		return false;
	}
	
	boolean remove (Node<AnyType> p){
		p.pre.next = p.next;
		p.next.pre = p.pre;
		modCount++;
		theSize--;
		return true;
	}
	
	public AnyType removeFirst(){
		return remove(size()-1);
	}
	
	public AnyType removeLast(){
		return remove(0);
	}
	
	public int IndexOf(Object x){
		int i = 0;
		if(x == null){
			for(Node<AnyType> p = head.next;p != tail;p = p.next,i++){
				if(p.data == null)
					return i;
			}
		}
		else{
			for(Node<AnyType> p = head.next;p != tail;p=p.next,i++){
				if(x.equals(p.data)){
					return i;
				}
			}
		}
		return NOT_FOUND;
	}
	
	public int IndexOfLast(Object x){
		int i = size()-1;
		if(x == null){
			for(Node<AnyType> p = tail.pre;p != head;p = p.pre,i--){
				if(p.data == null)
					return i;
			}
		}
		else{
			for(Node<AnyType> p = tail.pre;p != head;p=p.pre,i--){
				if(x.equals(p.data)){
					return i;
				}
			}
		}
		return NOT_FOUND;
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
			throw new IndexOutOfBoundsException();
		return getNode(idx).data;
	}
	
	public AnyType getFirst(){
		return get(0);
	}

	public AnyType getLast(){
		return get(size()-1);
	}
	
	private Node<AnyType> getNode(int idx){          //得到idx位置的节点
		if(idx < 0||idx > size())
			throw new IndexOutOfBoundsException();
		Node<AnyType> p;
		if(idx <size() >> 2){                        //size()>>2 = size()/2
			p=head.next;
			for(int i = 0;i<idx;i++)
				p = p.next;
		}
		else{
			p = tail;
			for(int i = size();i>idx;i--){
				p = p.pre;
			}
		}
		return p;
	}
	
	@Override
	public AnyType set(int idx, AnyType newVal) {
		// TODO Auto-generated method stub
		if(isOutOfLength(idx))
			throw new IndexOutOfBoundsException();
		Node<AnyType> p = getNode(idx);
		AnyType oldData = p.data;
		p.data = newVal;
		return oldData;
	}

	public boolean isOutOfLength(int idx){
		if(idx<0 || idx >=size())
			return true;
		return false;
	}
	
	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		if(isOutOfIndex(0))
			throw new IndexOutOfBoundsException();
		return new LinkedListIterator(0);
	}
	
	@Override
	public ListIterator<AnyType> listIterator(int idx) {
		// TODO Auto-generated method stub
		if(isOutOfIndex(idx))
			throw new IndexOutOfBoundsException();
		return new LinkedListIterator(idx);
	}
	
	private boolean isOutOfIndex(int idx){
		if(idx<0 || idx >size())
			return true;
		return false;
	}
	
	private class LinkedListIterator implements ListIterator<AnyType>{
		private Node<AnyType> current;
		private int currentIndex;
		private int exceptModCount;
		private int isNext = -1;
		public LinkedListIterator(int idx) {
			// TODO Auto-generated constructor stub
			current = getNode(idx);
			currentIndex = idx;
			exceptModCount = LinkedList.this.modCount;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currentIndex < size();
		}
		@Override
		public AnyType next() {                  //返回在current中元素的值，current跳向下一个
			// TODO Auto-generated method stub
			checkForComodification();
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			AnyType  result = current.data; 
			current = current.next;
			currentIndex++;
			isNext = 1;
			return result;
		}
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return currentIndex >0;
		}
		@Override
		public AnyType previous() {                  //返回在current前一个元素的值，current跳向返回数据所在的节点
			// TODO Auto-generated method stub
			checkForComodification();
			if(!hasPrevious()){
				throw new NoSuchElementException();
			}
			current = current.pre;
			currentIndex--;
			isNext = 0;
			return current.data;
		}
		
		final void checkForComodification(){
			if(exceptModCount != modCount){
				throw new ConcurrentModificationException();
			}
		}
		
		@Override
		public void remove() {                   //调用此函数之前必须调用next()或previous()
			// TODO Auto-generated method stub	 //删除的是next()或previous()返回的数据所在元素的节点
			checkForComodification();
			if (isNext == 1) {                    //之前调用next()
				Node<AnyType> p = current.pre;
				LinkedList.this.remove(p);
				currentIndex--;
			} else if (isNext == 0) {            //之前调用previous()
					Node<AnyType> p = current;
					current = current.next;
					LinkedList.this.remove(p);
				} else
					throw new IllegalStateException();
			isNext = -1;
			exceptModCount++;
		}
		
		@Override
		public void set(AnyType x) {            //道理和remove()一样
			// TODO Auto-generated method stub
			checkForComodification();
			if(isNext == 1){
				current.pre.data = x;
			}else if(isNext == 0){
					current.data = x;
				}else{
					throw new IllegalStateException();
				}
		}
		@Override
		public void add(AnyType x) {           //在next()或previous()返回的数据所在元素的节点前添加元素
			// TODO Auto-generated method stub //也可以在初始化currentIndex个节点添加元素
			checkForComodification();
			if(isNext == 1){
				LinkedList.this.add(current.pre,x);
			}else
				LinkedList.this.add(current,x);
			currentIndex++;
			exceptModCount ++;
		}
	}
	
}
