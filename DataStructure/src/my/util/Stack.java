package my.util;


public interface Stack<AnyType>{
	AnyType top();
	boolean push(AnyType x);
	AnyType Pop();
	boolean isEmpty();
	void clear();
}