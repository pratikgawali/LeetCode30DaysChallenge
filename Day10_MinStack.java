/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Design a stack that supports push, pop, top, and retrieving the minimum 
element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day10_MinStack {

	private Deque<int[]> dq;

	/** initialize your data structure here. */
	public Day10_MinStack() {

		dq = new ArrayDeque<>();
	}

	public void push(int x) {

		if (dq.isEmpty()) {
			dq.addLast(new int[] { x, x });
		} else {
			dq.addLast(new int[] { x, Math.min(x, dq.peekLast()[1]) });
		}
	}

	public void pop() {

		dq.removeLast();
	}

	public int top() {

		return dq.peekLast()[0];
	}

	public int getMin() {

		return dq.peekLast()[1];
	}

	public static void main(String[] args) {

		Day10_MinStack minStack = new Day10_MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); // --> Returns -3.
		minStack.pop();
		System.out.println(minStack.top()); // --> Returns 0.
		System.out.println(minStack.getMin()); // --> Returns -2.
	}
}