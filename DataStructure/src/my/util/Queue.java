package my.util;

public interface Queue<AnyType> {
	void enqueue(AnyType x);
	AnyType dequeue();
	AnyType getFront();
	void clear();
	boolean isEmpty();
}
