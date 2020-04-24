/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key 
exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently 
used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
---------------------------------------------------------------------------
*/
package leetcode.challenge;

import java.util.HashMap;
import java.util.Map;

public class Day24_LRU_Cache {

	private static class LRUCache {

		Map<Integer, Node> map;
		Node head;
		Node last;

		int c;

		LRUCache(int capacity) {

			map = new HashMap<>(capacity);
			head = null;
			last = null;

			c = capacity;
		}

		public int get(int key) {

			if (map.containsKey(key)) {

				Node node = map.get(key);
				move(node);

				return node.val;
			}

			return -1;
		}

		public void put(int key, int value) {

			if (map.containsKey(key)) {

				Node node = map.get(key);
				node.val = value;
				move(node);

				return;
			}

			if (map.size() == c) {

				map.remove(head.key);
				head = head.next;
				if (head != null) {
					head.pre = null;
				} else {
					last = null;
				}
			}

			Node node = new Node();
			node.key = key;
			node.val = value;
			node.pre = last;

			if (last != null) {
				last.next = node;
			}

			last = node;

			if (head == null) {
				head = node;
			}

			map.put(key, node);
		}

		private class Node {

			int key;
			int val;
			Node pre;
			Node next;
		}

		private void move(Node node) {

			if (node == last) {
				return;
			}

			if (node == head) {
				head = head.next;
				head.pre = null;
			} else {
				node.pre.next = node.next;
				node.next.pre = node.pre;
			}

			node.next = null;
			node.pre = last;

			last.next = node;
			last = node;
		}
	}

	public static void main(String[] args) {

		LRUCache cache = new LRUCache(2);

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));        // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));        // returns -1 (not found)
		System.out.println(cache.get(3));        // returns 3
		System.out.println(cache.get(4));        // returns 4
	}
}
