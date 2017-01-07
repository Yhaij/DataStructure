package my.util;

public interface Iterator<AnyType> extends java.util.Iterator<AnyType>{
	@Override
	boolean hasNext();
	@Override
	AnyType next();
	@Override
	void remove();
}
