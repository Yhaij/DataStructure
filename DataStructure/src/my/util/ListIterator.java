package my.util;

import java.util.Iterator;

public interface ListIterator<AnyType> extends Iterator<AnyType>{
	boolean hasPrevious();
	AnyType previous();
}
