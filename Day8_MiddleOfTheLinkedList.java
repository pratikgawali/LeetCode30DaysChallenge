/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a non-empty, singly linked list with head node head, return a middle 
node of linked list. If there are two middle nodes, return the second middle 
node.

Example 1:

Input: [1,2,3,4,5]
Output: Node 3 from this list (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.

Example 2:

Input: [1,2,3,4,5,6]
Output: Node 4 from this list (Serialization: [4,5,6])
Since the list has two middle nodes with values 3 and 4, we return the second one.
 

Note:

The number of nodes in the given list will be between 1 and 100.
---------------------------------------------------------------------------
*/

// SOLVED IN 02:51 mins

package leetcode.challenge;

public class Day8_MiddleOfTheLinkedList {

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	private static ListNode findMid(ListNode head) {
		
		ListNode fastPtr = head;
		while (fastPtr != null && fastPtr.next != null) {
			head = head.next;
			fastPtr = fastPtr.next.next;
		}
		
		return head;
	}

	public static void main(String[] args) {

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		
		System.out.println("Mid node of the linked list is: " + findMid(head).val);
	}
}
