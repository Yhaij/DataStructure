package my.util;

import java.util.Iterator;

public abstract class AbstractCollection<AnyType> implements Collection<AnyType>{
	private static final long serialVersionUID = 241537985904338820L;
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}
	@Override
	public void clear(){
		// TODO Auto-generated method stub
		Iterator<AnyType> itr = iterator();
		while(itr.hasNext()){
			itr.next();
			itr.remove();
		}
	}
	@Override
	public boolean add(AnyType x) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean contains(Object x) {
		// TODO Auto-generated method stub
		if(x == null){
			return false;
		}
		for(AnyType val:this){
			if(x.equals(val)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean remove(Object x) {
		// TODO Auto-generated method stub
		if(x == null){
			return false;
		}
		Iterator<AnyType> itr = iterator();
		while(itr.hasNext()){
			if(x.equals(itr.next())){
				itr.remove();
				return true;}
		}
		return false;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] arr = new Object[size()];
		int i=0;
		for(AnyType val:this){
			arr[i++] = val;
		} 
		return arr;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <OtherType> OtherType[] toArray(OtherType[] arr) {
		// TODO Auto-generated method stub
		int theSize = size();
		int i = 0;
		if(arr.length < theSize){
			arr = (OtherType[])java.lang.reflect.Array.newInstance(arr.getClass().getComponentType(), theSize);
		}
		if(arr.length>theSize){
			arr[theSize] = null;
		}
		Object [] copy = arr;
		for(AnyType val:this){
			copy[i++] = val;
		}
		return (OtherType[])copy;
	}
	public String toString (){
		StringBuilder result = new StringBuilder("[ ");
		for(AnyType val:this){
			result.append(val +" ");
		}
		result.append("]");
		return result.toString();
	}
}
