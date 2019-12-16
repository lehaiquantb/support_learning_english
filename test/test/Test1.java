package test;

import java.util.*;

class Test1 {
	public static void main(String args[]) {
		SortedSet<Integer> mySet = new TreeSet<>();
		SortedSet<Integer> subSet;

		/* Add Elements to mySet */
		mySet.add(10);
		mySet.add(20);
		mySet.add(15);
		mySet.add(12);
		mySet.add(16);

		Integer from = new Integer(-9);
		Integer to = new Integer(50);

		/* Get the subset in the range of 10 to 15 */
		subSet = mySet.subSet(from, to);

		/* Display Elements */
		System.out.println(subSet);

		subSet.add(14);
		System.out.println(subSet);
		
		System.out.println(mySet);
		/* Add 16, which is out of range between 10 to 15 */
		//subSet.add(16);
	}
}