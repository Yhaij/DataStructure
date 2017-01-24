package my.tree;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class TreeIterator<AnyType> implements Iterator<AnyType>{
	protected BinaryNode<AnyType> current;
	abstract protected void advance();
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return current != null;
	}
	@Override
	public AnyType next() {
		// TODO Auto-generated method stub
		if(!hasNext())
			throw new NoSuchElementException();
		AnyType result = current.getElment();
		advance();
		return result;
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		try {
			throw new NoSuchAlgorithmException();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
