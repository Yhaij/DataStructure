package my.util;

import java.util.Iterator;

public interface Collection<AnyType> extends Iterable<AnyType>,java.io.Serializable{
	int size();
	boolean isEmpty();
	boolean add(AnyType x);
	boolean remove(Object x);
	boolean contains(Object x);
	void clear();
	Object [] toArray();
	<OtherType> OtherType [] toArray(OtherType [] arr);
	@Override
	Iterator<AnyType> iterator();
}
