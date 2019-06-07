package net.mygame;

import java.util.Arrays;
import java.util.Scanner;

public class Game2048View {
	 Board b  ;
	 
	 public Game2048View(Board board){
		 b = board;
	 }
	 
	 public void setBoard(Board updated){
		 this.b = updated;
	 }
	 public  UserAction getUserAction(){
		 Scanner reader = new Scanner(System.in);
		 System.out.println("Next action: [W]Up, [A]Left, [S]Down, [D]Right, [Q]uit, [R]eset:");
		 String dir = reader.next().toUpperCase();
		 switch(dir){
		 case "W":
			 return UserAction.UP;
			 
		 case "A" :
			 return UserAction.LEFT;
		 case "S":
			 return UserAction.DOWN;
		 case "D":
			 return UserAction.RIGHT;
		 case "Q":
			 return UserAction.QUIT;
		 case "R":
			 return UserAction.RESET;
		 }
		 
		 return UserAction.INVALID;
		 
	 }
	 public void updateDispaly(){
		 
		 for(int i = 0; i< b.tiles.length; i++){
			 System.out.println(Arrays.toString(b.tiles[i]));
		 }
	 }
}
