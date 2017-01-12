package my.util;

import java.util.Iterator;

public interface ListIterator<AnyType> extends Iterator<AnyType>{
	boolean hasPrevious();
	AnyType previous();
	void set(AnyType x);
	void add(AnyType x);
}
