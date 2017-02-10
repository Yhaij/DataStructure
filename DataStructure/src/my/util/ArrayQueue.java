package my.util;

public class ArrayQueue<AnyType> implements Queue<AnyType> {

	private static final int DEFUALT_CAPACITY = 10;
	private AnyType[] item;
	private int front;
	private int rear;
	private int theSize;
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		// TODO Auto-generated constructor stub
		item = (AnyType[]) new Object [DEFUALT_CAPACITY];
		theSize = DEFUALT_CAPACITY;
		front = rear = 0;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(AnyType x) {
		// TODO Auto-generated method stub
		if((rear+1)%theSize == front){
			AnyType[] oldArray = item;
			item = (AnyType[])new Object [oldArray.length*2+1];
			//System.out.println(item.length);
			for(int i = 0;i<oldArray.length;i++){
				item[i] = oldArray[front];
				front = (front+1)%theSize;
			}
			theSize = item.length;
			front = 0;
			rear = oldArray.length-1;
		}
		item[rear] = x;
		rear = (rear+1)	%theSize;
		//System.out.println("rear:"+rear);
	}

	@Override
	public AnyType dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new UnderFlowException("对不起，队列里没有元素");
		AnyType element = item[front];
		front = (front+1)%theSize;
		//System.out.println("front"+front);
		return element;
	}

	@Override
	public AnyType getFront() {
		// TODO Auto-generated method stub
		if(isEmpty())
			throw new UnderFlowException("对不起，队列里没有元素");
		return item[front];
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		front = rear = 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(front == rear)
			return true;
		return false;
	}

}
