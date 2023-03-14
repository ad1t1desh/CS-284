package sorti;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set; 
public class Sorti {

	
	private static class Interval {
		private int upper;
		private int lower;
		
		/**
		 * Constructs a new Interval object
		 * @param lower lower bound
		 * @param upper upper bound
		 */
		public Interval(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		/**
		 * Returns the lower bound
		 * @return lower bound
		 */
		public int getLower() {
			return lower;
		}
		
		/**
		 * Returns the upper bound
		 * @return upper bound
		 */
		public int getUpper() {
			return upper;
		}
		
		/**
		 * Returns true if the two intervals have equal bounds, else false
		 * @param o interval to compare to
		 * @return boolean to represent equality of the two Intervals
		 */
		@Override
		public boolean equals(Object o) {
			if (this.upper != ((Interval)o).upper) {
				return false;
			}
			if (this.lower != ((Interval)o).lower) {
				return false;
			}
			return true;
		}
		
		public int hashCode() {
			return lower * lower + upper;
		}
	}
	
	/**
	 * swap two elements of an array
	 * @param i index 1
	 * @param j index 2
	 * @param array
	 */
	private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * Partition part of quicksort
	 * @param array to partition
	 * @param low first index
	 * @param high last index
	 */
	private static <T extends Comparable<T>> int partition(T[] array, int first, int last) {
		T pivot = array[first];
		int up = first;
		int down = last;
		do {
			while ((up != last) && (pivot.compareTo(array[up]) >= 0)) {
				up++;
			}
			while (pivot.compareTo(array[down]) < 0) {
				down--;
			}
			if (up < down) {
				swap(array, up, down);
			}
		} while (up < down);
		swap(array, first, down);
		return down;
	}
	
	/**
	 * Sorts the given array using modified quicksort, an iterative approach.
	 * @param array Array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		if(array == null || array.length < 2) return;
		
		Interval total = new Interval(0,array.length-1);
		Set<Interval> notSorted = new HashSet<Interval>();
		notSorted.add(total);
		
		Iterator<Interval> itr = notSorted.iterator();
		while(itr.hasNext()) {
			Interval i = itr.next();
			int first = i.getLower();
			int last = i.getUpper();
			
			if (first >= last) {
				notSorted.remove(i);
				itr = notSorted.iterator();
				continue;
			}
			
			if (last - first == 1) {
				if(array[first].compareTo(array[last]) > 0) {
					swap(array, first, last);
				}
				notSorted.remove(i);
				itr = notSorted.iterator();
				continue;
			}
			
			int mid = first + (last - first) / 2;
			if(array[first].compareTo(array[mid]) > 0)
				swap(array, first, mid);
			if(array[mid].compareTo(array[last]) > 0)
				swap(array, mid, last);
			if(array[first].compareTo(array[mid]) > 0) 
				swap(array, first, mid);
			if (last - first == 2) {
				notSorted.remove(i);
				itr = notSorted.iterator();
				continue;
			}
			
			swap(array, first, mid);
			int pivot = partition(array, first, last);
			notSorted.remove(i);
			notSorted.add(new Interval(first, pivot - 1));
			notSorted.add(new Interval(pivot + 1, last));
			itr = notSorted.iterator();
			continue;
		}
	}
	public static void main(String[] args) {
		//Integer[] a = {3,2,1};
		//Integer[] a = {9,2,5,6,7,4,3,8,1};
		//Integer[] a = {10,9,8,7,6,5,4,3,2,1};
		Integer[] a = {2,5,3,0,2,3,0,3};
		//Integer[] a = {3,4,7,1,8,5,2,9,0,6};
		//Integer[] a  = {3,4,7,1,5,8,2,9,0,6};
		//Integer[] a = {5,4,7,1,8,3,2,9,0,6};
		        
		System.out.println("Original: ");
		for (int i=0; i<a.length; i++) {
			System.out.print(i + " ");
			}       
		sort(a);
		System.out.println("\nSorted: ");
		for (int i=0; i<a.length; i++) {
			System.out.print(i + " ");
			}
	}}
		

	