package my.util;

import java.util.Iterator;

public class LinkList<AnyType> extends AbstractCollection<AnyType>
								implements List<AnyType>{
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
		public Node(AnyType data){
			this.data = data;
			pre = next = null;
		}
		public Node(AnyType data,Node<AnyType> pre,Node<AnyType> next){
			this.data = data;
			this.next = next;
			this.pre = pre;
		}
	}
	
	public LinkList() {
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
	
	public boolean addAll(int idx,Collection<? extends AnyType> c){			//未完成
		Object[] arr = c.toArray();
		int length = arr.length;
		if(length == 0){
			return false;
		}
		return false;
	}
	
	public AnyType remove(int idx){
		Node<AnyType> p = getNode(idx);
		AnyType result = p.next.data;
		p.next = p.next.next;
		p.next.pre = p;
		theSize--;
		modCount++;
		return result;
	}
	
	@Override
	public boolean remove(Object x) {
		// TODO Auto-generated method stub
		Node<AnyType> p = findPos(x);
		if(p != null){
			p.pre.next = p.next;
			p.next.pre = p.pre;
			modCount++;
			theSize--;
			return true;
		}
		return false;
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
		return null;
	}
	
	@Override
	public ListIterator<AnyType> listIterator(int idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
