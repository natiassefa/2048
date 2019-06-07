package net.mygame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

	int currentScore;
	Tile [][] tiles;
	int gameSize;

	public Board(){
		gameSize = 4;
		tiles = new Tile[4][4];
		
		currentScore = 0;
		for(int i= 0; i < this.gameSize; i++){
			for(int j = 0; j< this.gameSize;j++){
				Tile tile = new Tile(0);
				this.tiles[i][j] = tile;
			}
		}
	}
	public Board(int size){
		gameSize = size;
		tiles= new Tile[size][size];
		currentScore =0;
		for(int i= 0; i < this.gameSize; i++){
			for(int j = 0; j< this.gameSize;j++){
				Tile tile = new Tile(0);
				this.tiles[i][j] = tile;
			}
		}
	}
	public Board(int size , int score){
		gameSize = size;
		tiles= new Tile[size][size];
		currentScore =score;
		for(int i= 0; i < this.gameSize; i++){
			for(int j = 0; j< this.gameSize;j++){
				Tile tile = new Tile(0);
				this.tiles[i][j] = tile;
			}
		}
	}
	public Board(String fileName) throws FileNotFoundException {

		loadBoardState(fileName);


	}
	public void loadBoardState(String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		Scanner reader = new Scanner (file);


		if(reader.hasNextInt()){

			this.currentScore = reader.nextInt();
		}
		if(reader.hasNextInt()){

			this.gameSize = reader.nextInt();
		}
		this.tiles = new Tile [this.gameSize][this.gameSize];
		while(reader.hasNextInt()){
			for(int i = 0; i< this.gameSize; i++){
				for(int j = 0; j< this.gameSize; j++){

					Tile tile = new Tile(reader.nextInt());
					this.tiles[i][j] =tile;
				}
			}

		}

	}
	public void reset(){
		for(int i = 0; i< this.gameSize; i++){
			for(int j = 0; j< this.gameSize; j++){
				this.tiles[i][j].setVal(0);
			}
		}
	}
	public int countEmptySpaces(){
		int counter =0;
		for(int i= 0; i< this.gameSize; i++){
			for(int j=0; j< this.gameSize; j++){
				if (this.tiles[i][j].getVal() ==0){
					counter++;
				}

			}
		}

		return counter;
	}
	public int getScore(){
		return this.currentScore;
	}
	public void printBoard(){


		for(int i= 0; i< this.gameSize; i++){


			System.out.println(Arrays.toString(this.tiles[i]));
		}
	}
	private int [][] returnOpen(){
		int [][] empty = new int [this.countEmptySpaces()][2];

		int counter= 0;
		for(int i=0; i< this.gameSize; i++){
			for(int j =0; j< this.gameSize;  j++){
				if(this.tiles[i][j].getVal()==0){
					empty[counter][0] = i;
					empty[counter][1] =j;
					counter++;

				}
			}
		}

		return empty;


	}
	public void addTile(){
		int [][] open = this.returnOpen();
		Tile tile;
		int [] chanceOfVal = {1,1,1,1,1,1,1,1,1,0};
		int random = (int) (Math.random()*open.length);
		int random1 = (int) (Math.random()*chanceOfVal.length);
		if(chanceOfVal[random1]== 0 ){
			tile = new Tile(4);

		}
		else{
			tile = new Tile(2);

		}

		this.tiles[open[random][0]][open[random][1]] = tile;
	}
	
	public Tile[][] getBoard(){

		Tile [][]tiles1 = new Tile[this.gameSize][this.gameSize];

		for(int i = 0; i< this.gameSize; i++){
			for(int j =0; j< this.gameSize; j++){
				Tile tile = new Tile(this.tiles[i][j].getVal());
				tiles1[i][j] = tile;
			}
		}
		return tiles1;
	}
	public void rotateCW(){
		Tile [][] board1 = this.getBoard();

		for(int i=0; i< this.gameSize; i++){

			for(int j=0; j< this.gameSize; j++){
				this.tiles[j][this.gameSize-1-i].setVal(board1[i][j].getVal());

			}

		}

	}
	public boolean canMove(){

		for(int i = 0; i< this.gameSize; i++){
			for(int j =0; j< this.gameSize-1; j++){
				
				if(this.tiles[i][j].getVal() == this.tiles[i][j+1].getVal()){
					return true;
				}
			}
		}
		for(int i = 0; i< this.gameSize-1; i++){
			for(int j =0; j< this.gameSize; j++){
				if(this.tiles[i][j].getVal() == this.tiles[i+1][j].getVal()){
					return true;
				}
			}
		}
		if(this.countEmptySpaces() > 0){
			return true;
		}
		return false;
	}
	public boolean left(){

		//checks if there any possible moves
		if(!(this.canMove())){
			return false;
		}

		//
		for(int i = 0; i< this.gameSize; i++){
			for(int j =0; j< this.gameSize; j++){
				int k = j+1;


				while(k<this.gameSize){
					if(this.tiles[i][j].getVal()==0){
						this.tiles[i][j].setVal(this.tiles[i][k].getVal());
						this.tiles[i][k].setVal(0);

					}
					else if(!((this.tiles[i][k].getVal())==this.tiles[i][j].getVal())){
						k = this.gameSize+2;

					}
					else{
						if(this.tiles[i][j].getVal() == this.tiles[i][k].getVal()){
							this.tiles[i][j].setVal(this.tiles[i][k].getVal()+this.tiles[i][j].getVal());
							this.tiles[i][k].setVal(0);
							k = this.gameSize+2;
						}
					}

					k++;
				}
			}



		}

		return true;
	}
	public boolean right(){
		//checks if there any possible moves
		if(!(this.canMove())){
			return false;
		}

		for(int i = 0; i< this.gameSize; i++){
			for(int j = this.gameSize-1;j>0; j--){
				int k = j -1;
				while ( k >=0){
					if(this.tiles[i][j].getVal() == 0){
						this.tiles[i][j].setVal(this.tiles[i][k].getVal());
						this.tiles[i][k].setVal(0);

					}
					else if(!((this.tiles[i][k].getVal())==this.tiles[i][j].getVal())){
						k = -1;

					}
					else{
						if(this.tiles[i][j].getVal() == this.tiles[i][k].getVal()){
							this.tiles[i][j].setVal(this.tiles[i][k].getVal()+this.tiles[i][j].getVal());
							this.tiles[i][k].setVal(0);
							k = -1;
						}
					}

					k--;
				}
			}
		}
		return true;
	}
	public boolean down(){
		//checks if there any possible moves
		if(!(this.canMove())){
			return false;
		}
		for(int i = 0; i< this.gameSize; i++){
			for(int j = this.gameSize-1; j>0; j--){
				int k = j-1;
				while(k>=0){
					if(this.tiles[j][i].getVal() == 0){
						this.tiles[j][i].setVal(this.tiles[k][i].getVal());
						this.tiles[k][i].setVal(0);
						
					}
					else if (!(this.tiles[k][i].getVal()== this.tiles[j][i].getVal())){
						k = -1;
					}
					else{
						if(this.tiles[j][i].getVal()== this.tiles[k][i].getVal()){
							this.tiles[j][i].setVal(this.tiles[k][i].getVal()+this.tiles[j][i].getVal());
							this.tiles[k][i].setVal(0);
							k = -1;
						}
					}

					k--;
				}
			}
		}
		return true;
	}
	public boolean up(){
		
		//checks if there any possible moves
		if(!(this.canMove())){
			return false;
		}
		for(int i = 0; i< this.gameSize; i++){
			for(int j = 0; j< this.gameSize; j++){
				int k = j+1;
				while(k< this.gameSize){
					if(this.tiles[j][i].getVal() == 0){
						this.tiles[j][i].setVal(this.tiles[k][i].getVal());
						this.tiles[k][i].setVal(0);
						
					}
					else if (!(this.tiles[k][i].getVal()== this.tiles[j][i].getVal())){
						k = this.gameSize +2;
					}
					else{
						if(this.tiles[j][i].getVal()== this.tiles[k][i].getVal()){
							this.tiles[j][i].setVal(this.tiles[k][i].getVal()+this.tiles[j][i].getVal());
							this.tiles[k][i].setVal(0);
							k = this.gameSize +2;
						}
					}

					k++;
				}
			}
		}
		return true;
	}


}
