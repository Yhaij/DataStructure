package my.test;

import my.util.ArrayQueue;
import my.util.Queue;

public class ArrayQueueTest {
	public static void main(String[] args) {
		Queue<Integer> queue  = new ArrayQueue<Integer>();
		for(int i = 0;i<5;i++){
			queue.enqueue(i);
		}
		for(int i = 0;i<4;i++){
			System.out.println(queue.dequeue());
		}
		for(int i = 5;i<5+7;i++){
			queue.enqueue(i);
		}
		for(int i = 0;i<8;i++){
			System.out.println(queue.dequeue());
		}
	}
}
