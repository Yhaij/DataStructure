package my.util;

public class ArrayStack<AnyType> implements Stack<AnyType>{
	private static final int DEFAUL_CAPCITY = 10; 
	private AnyType[] item; 
	private int topOfStack;
	
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		// TODO Auto-generated constructor stub
		item = (AnyType[])new Object[DEFAUL_CAPCITY];
		clear();
	}
	
	@Override
	public AnyType top() {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new UnderFlowException("对不起，栈里没有东西");
		return item[topOfStack];
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean push(AnyType x) {
		// TODO Auto-generated method stub
		if(topOfStack == item.length-1){
			AnyType[] array = item;
			item = (AnyType[])new Object[item.length*2+1];
			System.arraycopy(array, 0, item, 0, array.length);
		}
		item[++topOfStack] = x;
		return false;
	}

	@Override
	public AnyType Pop() {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new UnderFlowException("对不起，栈里没有东西");
		return item[topOfStack--];
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(topOfStack <0)
			return true;
		else
			return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		topOfStack = -1;
	}

}
