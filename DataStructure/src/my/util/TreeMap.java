package my.util;

import java.util.Comparator;

public class TreeMap<K,V> implements Map<K, V> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	private Entry<K,V> root;
	private int size;
	private Comparator<? super K> comparator;
	private int modCount;
	public TreeMap() {
		// TODO Auto-generated constructor stub
		this.comparator = null;
	}
	public TreeMap(Comparator<? super K> comparator){
		this.comparator = comparator;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return getEntry(key) == null?false:true;
	}
	
	final Entry<K,V> getEntry(Object key){
		if(comparator != null){
			return getEntryUsingComparator(key);
		}
		if(key == null)
			throw new NullPointerException();
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>)key;
		Entry<K,V> t = root;
		while(t != null){
			int cpr = k.compareTo(t.key);
			if(cpr < 0)
				t = t.left;
			else if(cpr > 0)
				t = t.right;
			else
				return t;
		}
		return null;
	}
	
	final Entry<K,V> getEntryUsingComparator(Object key){
		@SuppressWarnings("unchecked")
		K k = (K) key;
		Comparator<? super K> cpr = comparator;
		if (cpr != null) {
			Entry<K, V> t = root;
			while (t != null) {
				int cmp = comparator.compare(k, t.key);
				if (cmp < 0) {
					t = t.left;
				} else if (cmp > 0) {
					t = t.right;
				} else
					return t;
			}
		}
		return null;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		for(Entry<K,V> t = getFiretEntry();t != null;t = successor(t)){
			if(valEquals(value, t.getValue()))
				return true;
		}
		return false;
	}

	final static boolean valEquals(Object o1,Object o2){
		return o1 == null?o2 == null:o1.equals(02);
	}
	
	final Entry<K,V> getFiretEntry(){
		Entry<K,V> t = root;
		if(t == null)
			return t;
		while(t.left != null){
			t = t.left;
		}
		return t;
	}
	
	final Entry<K,V> successor(Entry<K,V> t){
		if(t == null)
			return null;
		if(t.right != null){
			t = t.right;
			while(t.left != null){
				t = t.left;
			}
			return t;
		}else{
			Entry<K,V> ch = t.parent;
			while(ch != null && t == ch.right){
				t = ch;
				ch = t.parent;
			}
			return ch;
		}
	}
	
	final Entry<K,V> predecessor(Entry<K,V> t){
		if(t == null)
			return null;
		if(t.left != null){
			t = t.left;
			while(t.right != null){
				t = t.right;
			}
			return t;
		}else{
			Entry<K,V> ch = t.parent;
			while(ch != null && t == ch.left){
				t = ch;
				ch = t.parent;
			}
			return ch;
		}
	}
	
	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setColor(Entry<K,V> t,boolean color){
		if(t != null)
			t.color = color;
	}
	
	private boolean colorOf(Entry<K,V> t){
		return t == null?BLACK:t.color;
	}
	
	private Entry<K,V> leftOf(Entry<K,V> t){
		if(t != null){
			return t.left;
		}
		return null;
	}
	
	private Entry<K,V> rightOf(Entry<K,V> t){
		return t == null?null:t.right;
	}
	
	private Entry<K,V> parentOf(Entry<K,V> t){
		return t == null?null:t.parent;
	}
	
	
	/**
	 * 自上而下插入，在自下而上调整
	 * 也可采用自上而下插入（见RedBlackTree的insert()），避免回溯
	 */
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		Entry<K,V> t = root;
		if(t == null){
			root = new Entry<K,V>(key,value, null);
			root.color = BLACK;
			size ++;
			modCount ++;
			return null;
		}
		int cmp = 0;
		Entry<K,V> parent = null;
		if(comparator != null){
			cmp = comparator.compare(key, t.key);
			while(t != null){
				parent = t;
				if(cmp<0){
					t = t.left;
				}else if(cmp>0){
					t = t.right;
				}else
					return t.setValue(value);
			} 
		}else{
			if(key == null)
				throw new NullPointerException();
			@SuppressWarnings("unchecked")
			Comparable<? super K> k = (Comparable<? super K>)key;
			while(t != null){
				parent  = t;
				cmp = k.compareTo(t.key);
				if(cmp<0){
					t = t.left;
				}else if(cmp>0){
					t = t.right;
				}else
					return t.setValue(value);
			}
		}
		t = new Entry<K,V>(key,value,parent);
		if(cmp<0){
			parent.left = t;
		}else
			parent.right = t;
		fixAfterInsert(t);
		size++;
		modCount ++;
		return null;
	}

	private void fixAfterInsert(Entry<K,V> t){
		while(t != null && t != root && colorOf(parentOf(t)) == RED){
			if(parentOf(t) == leftOf(parentOf(parentOf(t)))){
/*				Entry<K,V>
 *  grant = parentOf(parentOf(t));
				Entry<K,V> parent = parentOf(t);*/ //还是不用这种表示方式，因为这在旋转之后容易混乱，还是直接按照t为参照来
				Entry<K,V> y = rightOf(parentOf(parentOf(t)));
				if(colorOf(y) == RED){
					setColor(parentOf(parentOf(t)), RED);
					setColor(parentOf(t), BLACK);
					setColor(y, BLACK);
					t = parentOf(parentOf(t));
				}else{
					if(t == rightOf(parentOf(t))){
						t = roteLeft(parentOf(t));
					}
					t = roteRight(parentOf(t));
					setColor(t, BLACK);
					setColor(rightOf(t), RED);
				}
			}else{
				Entry<K,V> y = leftOf(parentOf(parentOf(t)));
				if(colorOf(y)== RED){
					setColor(parentOf(parentOf(t)), RED);
					setColor(parentOf(t), BLACK);
					setColor(y, BLACK);
					t = parentOf(parentOf(t));
				}else{
					if(t == leftOf(parentOf(t))){
						t = roteRight(parentOf(t));
					}
					t= roteLeft(parentOf(t));
					setColor(t, BLACK);
					setColor(leftOf(t), RED);
				}
			}
			setColor(root, BLACK);
		}
	}
	
	private Entry<K,V> roteLeft(Entry<K,V> t){
		//t一定会有父节点和祖父节点，因为只有父节点是红的才会调用这方法，null的颜色默认为黑色，而root必为黑色
		Entry<K, V> rightChild = t.right;
		t.right = rightChild.left;
		if (rightChild.left != null) {
			rightChild.left.parent = t;
		}
		rightChild.parent = t.parent;
		rightChild.left = t;
		if (t.parent == null)
			root = rightChild;
		else if (t == t.parent.left) {
			t.parent.left = rightChild;
		} else {
			t.parent.right = rightChild;
		}
		t.parent = rightChild;
		return rightChild;
	}
	
	private Entry<K,V> roteRight(Entry<K,V> t){
		Entry<K,V> leftChild = t.left;
		t.left = leftChild.right;
		if(leftChild.right != null){
			leftChild.right.parent = t;
		}
		leftChild.parent = t.parent;
		leftChild.right = t;
		if(t.parent == null){
			root = leftChild;
		}else if(t == t.parent.left){
			t.parent.left = leftChild;
		}else
			t.parent.right = leftChild;
		t.parent = leftChild;
		return leftChild;
	} 
	
	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		Entry<K,V> p = getEntry(key);
		if( p == null)
			return null;
		V oldValue = p.getValue();
		deleteEntry(p);
		return oldValue;
	}

	private void deleteEntry(Entry<K,V> t){
		if(t.left != null && t.right != null){
			Entry<K,V> p = successor(t);
			t.key = p.key;
			t.value = p.value ;
			t = p;
		}
		Entry<K,V> replacement = t.left == null?t.right:t.left;
		if(replacement != null){
			replacement.parent = t.parent;
			if(t.parent == null){
				root = replacement;
				setColor(root, BLACK);
				return ;
			}else if(t == t.parent.left){
				t.parent.left = replacement;
			}else{
				t.parent.right = replacement;
			}
			t.left = t.right = t.parent = null;
			if(t.color == BLACK){
				fixAfterRemove(replacement);
			}
		}else{
			if(t.parent == null){
				root = null;
				return ;
			}else if(t.color == BLACK){
				fixAfterRemove(t);
			}
			if(t.parent != null){
				if(t == t.parent.left){
					t.parent.left = null;
				}else{
					t.parent.right = null;
				}
			}
			t.parent = t.left = t.right = null;
		}
	}
	
	private void fixAfterRemove(Entry<K,V> t){
		while(t != root && t.color == BLACK){
			if(t == t.parent.left){       
				Entry<K,V> w = t.parent.right;    //w可能为空，所以区w的颜色用colorOf()
				if(colorOf(w) == RED){
					setColor(w, BLACK);
					setColor(t.parent, RED);
					t = roteLeft(t.parent).left.left;
					w = t.parent.right;
				}
				if(colorOf(leftOf(w)) == BLACK && colorOf(leftOf(w)) == BLACK){
					setColor(w, RED);
					t = t.parent;
				}else{
					if(colorOf(w.right) == BLACK){
						setColor(w, RED);
						setColor(w.left, BLACK);
						t = roteRight(w).parent.parent.left;
						w = t.parent.right;
					}
					setColor(w, colorOf(t.parent));
					setColor(t.parent, BLACK);
					setColor(w.left, BLACK);
					roteLeft(t.parent);
					t = root;
				}
			}else{
				Entry<K,V> w = t.parent.left;
				if(colorOf(w) == RED){
					setColor(w, BLACK);
					setColor(t.parent,RED);
					t = roteRight(t.parent).right.right;
					w = t.parent.left;
				}
				if(colorOf(w.left) == BLACK && colorOf(w.right) == BLACK){
					setColor(w, RED);
					t = t.parent;
				}else{
					if(colorOf(w.left) == BLACK){
						setColor(w.right,BLACK);
						setColor(w, RED);
						t = roteLeft(t).parent.parent.right;
						w = t.parent.left;
					}
					setColor(w, colorOf(w.parent));
					setColor(w.parent, BLACK);
					setColor(w.left, BLACK);
					roteRight(t.parent);
					t = root;
				}
			}
		}
		setColor(t, BLACK);
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void claer() {
		// TODO Auto-generated method stub
		root = null;
		size = 0;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	static final class Entry<K,V> implements Map.Entry<K, V>{
		K key;
		V value;
		boolean color;
		Entry<K,V> left;
		Entry<K,V> right;
		Entry<K,V> parent;
		public Entry(K key,V value,Entry<K,V> parent) {
			// TODO Auto-generated constructor stub
			this(key,value,null,null,parent);
		}
		public Entry(K key,V value,Entry<K,V> left,Entry<K,V> right,Entry<K,V> parent){
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
			color = RED;
		}
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public boolean equal(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	public void show(){
		show(root);
	}
	private void show(Entry<K,V> t){
		if(t != null){
			show(t.left);
			System.out.print(t.key+" ");
			show(t.right);
		}
	}
}
