package net.mygame;


public class Tile {
	int value;
	
	public Tile (){
		value = 0;
	}
	public Tile (int val){
		value = val;
	}
	public int getVal(){
		return this.value;
	}
	public boolean isEmpty(){
		if(this.getVal()==0){
			return true;
		}
		else
			return false;
	}
	public void setVal(int val){
		this.value = val;
	}
	public String toString(){
		if(this.getVal()==0){
			return "_";
		}
		else
			return Integer.toString(this.getVal());
	}
	public Tile clone(){
		Tile tile = new Tile(this.getVal());
		
		return tile;
	}
}
