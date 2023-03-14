import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	void test1() {
		Anagrams uno = new Anagrams();
		try {
			uno.processFile("words.alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = uno.getMaxEntries(); 
		assertEquals(maxEntries.get(0), 236204078); 
		assertEquals(maxEntries.get(0).getValue().size(), 15);
	}
	
	@Test
	void test2() {
		Anagrams ana = new Anagrams();
		ana.addWord("j");
		assertFalse(ana.anagramTable.containsKey((long) 4)); 
		assertTrue(ana.anagramTable.containsKey((long) 29));
	}

}
