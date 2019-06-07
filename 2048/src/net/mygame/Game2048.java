package net.mygame;


	import java.io.FileNotFoundException;



	public class Game2048 {



		public static void main(String[]args) throws FileNotFoundException{
			Board b = new Board("board.tfe");
			b.printBoard();
			Game2048View view  = new Game2048View(b);
			Game2048Controller controller = new Game2048Controller(b , view);
			
			controller.runGame();
		}
	}
