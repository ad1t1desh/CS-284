import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test

	void testTreap() {
		Treap<String> newTreap = new Treap<String>();
		assertEquals(newTreap.add("a", 1), true);
		assertEquals(newTreap.add("hello", 4), true);
		assertEquals(newTreap.add("cheese", 12), true);
		assertEquals(newTreap.add("you", 8), true);
		assertEquals(newTreap.add("a"), false);
		assertEquals(newTreap.find("cs"), false);
		assertEquals(newTreap.delete("a"), true);
		assertEquals(newTreap.find("a"), false); 
	}
	
	void testTreapint() {
		Treap<Integer> newTreap = new Treap<Integer>();
		assertEquals(newTreap.add(45, 1), true);
		assertEquals(newTreap.add(1, 4), true);
		assertEquals(newTreap.add(67, 12), true);
		assertEquals(newTreap.add(12, 8), true);
		assertEquals(newTreap.add(40), false);
		assertEquals(newTreap.find(45), true);
		assertEquals(newTreap.delete(82), false);
		assertEquals(newTreap.delete(45), true);
		assertEquals(newTreap.find(45), false); 
	}
	

}
