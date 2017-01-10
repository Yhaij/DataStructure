package my.test;

import my.util.ArrayStack;
import my.util.Stack;

public class ArraySackTest {
	public static void main(String[] args) {
		Stack<Integer> stack = new ArrayStack<Integer>();
		for(int i=0;i<200;i++)
			stack.push(i);
		for(int i=0;i<150;i++)
		System.out.println(stack.Pop());
	}
}
