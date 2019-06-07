package net.mygame;


public class Game2048Controller {
	
	Board b;
	Game2048View  view;

	public Game2048Controller(Board board , Game2048View views){
		b = board;
		view = views;

	}
	public void runGame(){
int counter = 0;
		//makes a move based on user choice
		while(b.canMove()){
		
			UserAction move =view.getUserAction();
			if(move ==UserAction.UP){
				counter++;
				b.up();
				b.addTile();
			}
			if(move ==UserAction.DOWN){
				counter++;
				b.down();
				b.addTile();
			}
			if(move ==UserAction.RIGHT){
				counter++;
				b.right();
				b.addTile();
			}
			if(move ==UserAction.LEFT){
				counter++;
				b.left();
				b.addTile();
			}
			if(move ==UserAction.QUIT){
				System.out.println("You Quit!");
				System.exit(0);

			}
			if(move ==UserAction.RESET){
				b.reset();

			}
			if(move == UserAction.INVALID){
				System.out.println("Invalid key");
			}
			System.out.println("Number of moves: " + counter + "\n");
			view.setBoard(b);
			view.updateDispaly();
			
		}
		System.out.println("No more possible moves.");
	}

}
