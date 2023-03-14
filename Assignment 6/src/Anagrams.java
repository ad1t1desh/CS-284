// Aditi Deshmukh
// I pledge my honor that I have abided by the Stevens Honor System. 

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Anagrams {
	//data fields
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;
	
	/* Constructor
	 */
	public Anagrams() {
		letterTable = new HashMap<Character, Integer>();
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}
	
	/** Creates a hash table that maps each letter to its corresponding prime number 
	 * 
	 */
	public void buildLetterTable() {
		Character[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i = 0; i<abc.length; i++) {
			letterTable.put(abc[i], primes[i]);	
		}
	}
	
	/**
	 * Adds a word to anagramTable and computes its hashCode
	 * @param s the word that is being added
	 */
	public void addWord(String s) {
		if (anagramTable.containsKey(myHashCode(s))) {
			ArrayList<String> temp = anagramTable.get(myHashCode(s));
			temp.add(s);
			anagramTable.replace(myHashCode(s), temp);
		} else {
			ArrayList<String> ad = new ArrayList<String>();
			ad.add(s);
			anagramTable.put(myHashCode(s), ad);
		}
	}
	
	/**
	 * Computes hash code of a string 
	 * @param s the string for which the hash code is being computed
	 * @return the hash code of the string
	 */
	public Long myHashCode(String s) {
		long total = 1;
		if (s.isEmpty()) {
			throw new IllegalArgumentException("String cannot be empty!");
		} else {
			for(int i = 0; i<s.length(); i++) {
				total = total*letterTable.get(s.charAt(i));
			}
		} 
		return total; 
	}
	
	/**
	 * Builds the anagramTable based on a file name
	 * @param s the file name
	 * @throws IOException if the file cannot be found
	 */
	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * Finds the entries in the file that have the greatest number of anagrams
	 * @return list of the entries that have the largest number of anagrams
	 */
	public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxs = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		int maxsize = 0;
		for (Map.Entry<Long, ArrayList<String>> entry: anagramTable.entrySet()) {
			if(entry.getValue().size() > maxsize) {
				maxs.clear();
				maxs.add(entry);
				maxsize = entry.getValue().size();
			}
			if (entry.getValue().size() == maxsize) {
				maxs.add(entry);
			}
		} return maxs;
	}


	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime ();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace ();
		}
		ArrayList <Map.Entry <Long ,ArrayList <String >>> maxEntries = a.getMaxEntries ();
		final long estimatedTime = System.nanoTime () - startTime;
		final double seconds = (( double) estimatedTime /1000000000);
		System.out.println("Time: "+ seconds );
		System.out.println("List of max anagrams: "+ maxEntries );

	}

}
