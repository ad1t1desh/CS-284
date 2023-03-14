package hw3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplayerGameTest {


	@Test
	void testSize() {
		MultiplayerGame game = new MultiplayerGame(2);
		game.addGamePiece(0, "pawn", 1);
		game.addGamePiece(1, "bishop", 3);
		game.addGamePiece(0, "knight", 3);
		game.addGamePiece(1, "rook", 5);
		assertEquals(game.size(), 4);
	}

	@Test
	void testAddGamePiece() {
		assertThrows(IllegalArgumentException.class,() -> {
			MultiplayerGame game = new MultiplayerGame(2);
			game.addGamePiece(-1, "king", 10);
			});
		assertThrows(IllegalArgumentException.class,() -> {
			MultiplayerGame game = new MultiplayerGame(2);
			game.addGamePiece(4, "king", 10);
			});
	}

	@Test
	void testRemoveGamePiece() {
		assertThrows(IllegalArgumentException.class,() -> {
			MultiplayerGame game = new MultiplayerGame(2);
			game.addGamePiece(-1, "king", 10);
			});
		assertThrows(IllegalArgumentException.class,() -> {
			MultiplayerGame game = new MultiplayerGame(2);
			game.addGamePiece(4, "king", 10);
			});
	}

	@Test
	void testHasGamePiece() {
		assertThrows(IllegalArgumentException.class,() -> {
			MultiplayerGame game = new MultiplayerGame(2);
			game.addGamePiece(-10, "king", 10);
			});
		assertThrows(IllegalArgumentException.class,() -> {
			MultiplayerGame game = new MultiplayerGame(2);
			game.addGamePiece(4, "king", 10);
			});
	}

}
