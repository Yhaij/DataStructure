package my.util;

public interface List<AnyType> extends Collection<AnyType>{
	AnyType get(int idx);
	AnyType set(int idx,AnyType newVal);
	ListIterator<AnyType> listIterator(int idx);
}
