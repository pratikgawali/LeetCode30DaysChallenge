/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:
All inputs will be in lowercase.
The order of your output does not matter.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6_GroupAnagrams {

	private static final int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
			73, 79, 83, 89, 97, 101 };

	private static List<List<String>> groupAnagrams(String[] strs) {

		Map<Integer, Integer> anagramHashMap = new HashMap<>();
		List<List<String>> outputList = new ArrayList<>();

		int outputIndexSoFar = 0;

		for (int i = 0; i < strs.length; i++) {

			int hash = anagramHash(strs[i]);

			List<String> list;
			if (anagramHashMap.containsKey(hash)) {
				list = outputList.get(anagramHashMap.get(hash));
			} else {
				list = new ArrayList<String>();
				outputList.add(list);
				anagramHashMap.put(hash, outputIndexSoFar++);
			}

			list.add(strs[i]);
		}

		return outputList;
	}

	// great way to hash anagrams
	private static int anagramHash(String str) {

		int hash = 1;

		for (int i = 0, n = str.length(); i < n; i++) {
			char c = str.charAt(i);
			hash *= PRIMES[c - 'a'];
		}

		return hash;
	}

	public static void main(String[] args) {

		String[] anagrams = { "eat", "tea", "tan", "ate", "nat", "bat" };

		List<List<String>> anagramGroupList = groupAnagrams(anagrams);
		printAnagramGroups(anagramGroupList);
	}

	private static void printAnagramGroups(List<List<String>> anagramGroupList) {

		for (int i = 0, n = anagramGroupList.size(); i < n; i++) {

			System.out.print("[");
			List<String> anagramGroup = anagramGroupList.get(i);

			for (int j = 0, m = anagramGroup.size(); j < m; j++) {
				System.out.print(" " + anagramGroup.get(j) + " ");
			}

			System.out.println("]");
		}
	}

}
