package hw3;

// Aditi Deshmukh 
// I pledge my honor that I have abided by the Stevens Honor System. 


public class MultiplayerGame {
	private GameEntity turnTracker;
	private GameEntity[] index;
	
	/**
	 * Creates a new Multiplayer Game 
	 * @param n - the number of players in the game
	 */
	public MultiplayerGame (int n) {
		if  (n <= 0) {
			throw new IllegalArgumentException(
					"n cannot be negative");
		}
			index = new GameEntity[n];
			index[0] = new GamePlayer(null, null ,0);			
			for (int i = 1; i <n; i++) {
				index[i] = new GamePlayer(index[i-1], null, i);
				index[i-1].next = index[i];
			}  
	index[0].prev = index[n-1];
	index[n-1].next = index[0];
	}
	
	/**
	 * Returns the size of the Multiplayer Game  
	 */
	public int size() {
		int size = 0;
		GameEntity curr = index[0].next;
		while (curr!= index[0]) {
			size += curr.size();
			curr = curr.next;
		}
		return size;
	}
	
	/**
	 * Adds a Game Piece to the Multiplayer Game 
	 * @param playerId - Player Id of the player who is getting the new piece
	 * @param name - Name of the new Piece
	 * @param strength - Strength of the new player 
	 */
	public void addGamePiece(int playerId, String name, int strength) {
		if (playerId<0 || playerId >= index.length) {
			throw new IllegalArgumentException(
					"add GamePiece: no such player");
		}
		GameEntity curr = index[playerId];
		while (curr.isGamePlayer()==false) {
			if(curr.getName() ==name) {
				throw new IllegalArgumentException(
						"add GamePiece: duplicate identity");
			}
			curr = curr.next;
		}
		curr.next.prev = new GamePiece(curr, curr.next, name, strength);
		curr.next = curr.next.prev;
	}
	
	/**
	 * Removes the specified Piece from the MultiplayerGame
	 * @param playerId - playerId of the Player whose piece is being removed
	 * @param name - Name of the piece being removed
	 */
	public void removeGamePiece(int playerId, String name) {
		if (playerId<0 || playerId >= index.length) {
			throw new IllegalArgumentException(
					"remove GamePiece: no such player");
		}
		GameEntity curr = index[playerId];
		while (curr.next.getName() != name) { 
			if(curr.next.isGamePlayer() == true) {
				throw new IllegalArgumentException(
						"remove GamePiece: entity does not exist");
			} else {
				curr = curr.next;
			}
			curr.next.prev = curr;
			curr.next = curr.next.next;
		} 
	}
	
	/**
	 * Checks if a piece exists in Multiplayer Game
	 * @param name - Name of the piece which we are checking for in the Multiplayer Game
	 * @return true or fale based on whether the specified piece is in the array
	 */
	public boolean hasGamePiece(String name) {
		for (int i = 0; i<index.length; i++) {
			if (index[i].isGamePlayer()== false) {
				if (index[i].getName() == name) {
					return true;
				}
			} index[0] = index[0].next;
		} return false;
	}
	
	/**
	 * Removes all Game Pieces for a particular player
	 * @param playerId - of the player whose pieces are to be removed
	 */
	public void removeAllGamePieces(int playerId) { 
		if (playerId<0) {
			throw new IllegalArgumentException(
					"add GamePiece: no such player");
		}
		if (playerId >= index.length) {
			throw new IllegalArgumentException(
					"add GamePiece: no such player");
		}
		GameEntity curr = index[playerId];
			if (curr.isGamePlayer()== true) {
				curr.prev.next = curr.next;
				curr.next.prev = curr.prev.prev;
			} 
	
	}
	
	/**
	 * Increases the strength of the particular piece in the array 
	 * @param playerId - specifies the player whose piece is being modified
	 * @param n - changes the previous value of strength by n 
	 */
	public void increaseStrength(int playerId, int n) {
		if (playerId<0) {
			throw new IllegalArgumentException(
					"add GamePiece: no such player");
		}
		if (playerId >= index.length) {
			throw new IllegalArgumentException(
					"add GamePiece: no such player");
		}
		for (int i = 0; i<index.length; i++) {
			if (index[i].isGamePlayer()== true) {
				((GamePiece)index[i]).updateStrength(n);
				 index[0]= index[0].next;
			}
		}	
	}

	/**
	 * Converts MultiplayerGame to string
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		GameEntity curr = index[0];
		str.append(curr.toString());
		str.append(" ");
		curr = curr.next;
		while (curr != index[0]) {
			str.append(curr.toString());
			str.append(" ");				
			curr= curr.next;
		}
		return str.toString(); 
	}

	/**
	 * initializes the TurnTracker
	 */
	public void initializeTurnTracker() {	
		turnTracker = index[0];
	}
	
	/**
	 * moves the turnTracker to the next player
	 */
	public void nextPlayer() { 
		turnTracker = turnTracker.next;
		while(turnTracker.isGamePlayer() != true) {
			turnTracker = turnTracker.next;
		}
	}
		
	/**
	 * moves the turnTracker to the next Entity
	 */
	public void nextEntity() {
		turnTracker = turnTracker.next;
	}
	
	/**
	 * moves the turnTracker to the previous player
	 */
	public void prevPlayer() {
		turnTracker = turnTracker.prev;
		while (turnTracker.isGamePlayer() != false) {
			turnTracker = turnTracker.prev;
		}
	}

	/**
	 * converts the turnTracker toString
	 * @return String containing turnTracker
	 */
	public String currentEntryToString() {
		return turnTracker.toString();
	} 
	
	public static void main(String[] args) {
		MultiplayerGame yes = new MultiplayerGame(4);
		//System.out.println(yes.toString());
		yes.addGamePiece(0, "king", 5);
		//System.out.println(yes.toString());
		yes.initializeTurnTracker();
		yes.nextPlayer();
		System.out.println(yes.turnTracker);
	}

}
