package uk.ac.brunel.cs1702;

public class GalaxyExplorer {
	int grid[][] = new int[0][0];
	int shipx, shipy, dir;	//ints to hold X AND Y of the ship, and the DIRECTION
	String[] enemyCoords;	//Array that holds the coords of enemy ships
	StringBuilder enemyShips = new StringBuilder();	//string builder to show encountered ships
	
	//direction meaning
	//0 = north
	//1 = south
	//2 = east
	//3 = west
	
	public GalaxyExplorer(int x, int y, String enemyShips){
		grid = new int[x][y];
		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++){		//constructor to create matrix
				grid[i][j]=0;				//and fill it with 0
			}
		}
		enemyCoords = enemyShips.split("[(-,]");		//splits string and puts into an array 
		for (int i=1; i<enemyCoords.length; i+=3){		//goes through the array and fills enemies as "1"
			grid[Integer.parseInt(enemyCoords[i])][Integer.parseInt(enemyCoords[i+1])] = 1;
		}	
	}
	
	public void AddEnemy(int shipx, int shipy){		//method to add encountered ships to a string
		if (!enemyShips.toString().contains("("+shipx+";"+shipy+")")){	//if it doesnt contain the coords
			enemyShips.append("("+shipx+";"+shipy+")");					//add the coords else nothing
		}
	}

	public String direction(){
		String direction = null;
		switch(dir){
		case 0: direction = "N"; break;
		case 1: direction = "S"; break;
		case 2: direction = "E"; break;
		case 3: direction = "W"; break;
		}
		return direction;			//returns the direction as string 
	}
	
	public boolean isObstacleFront(int shipx, int shipy){	//checks if enemy ship is in front
		switch (dir){										//depending on the direction facing
		case 0:								//north
			if (grid[shipx][shipy+1] == 1){	//if it is 1(enemy)
				AddEnemy(shipx, shipy+1);	//add enemy to the string builder
				return true;				//return true
			}else return false;				//else if its not 1 return false.
		case 1:								//south
			if (grid[shipx][shipy-1] == 1){					//(same for each direction)
				AddEnemy(shipx, shipy-1);
				return true;
			}else return false;
		case 2:								//east
			if (grid[shipx+1][shipy] == 1){
				AddEnemy(shipx+1, shipy);
				return true;
			}else return false;
		case 3:								//west
			if (grid[shipx-1][shipy] == 1){
				AddEnemy(shipx-1, shipy);
				return true;
			}else return false;
		}
		return false;
	}	
	
	public boolean isObstacleBack(int shipx, int shipy){	//checks if enemy ship is in back 
		switch (dir){										//depending on the direction
		case 0:								//north
			if (grid[shipx][shipy-1] == 1){	//if it is 1(enemy)
				AddEnemy(shipx, shipy-1);	//add enemy to the string builder
				return true;				//return true
			}else return false;				//else if its not 1 return false
		case 1:								//south
			if (grid[shipx][shipy+1] == 1){					//same for each direction
				AddEnemy(shipx, shipy+1);
				return true;
			}else return false;
		case 2:								//east
			if (grid[shipx-1][shipy] == 1){
				AddEnemy(shipx-1, shipy);
				return true;
			}else return false;
		case 3:								//west
			if (grid[shipx+1][shipy] == 1){
				AddEnemy(shipx+1, shipy);
				return true;
			}else return false;
		}
		return false;
	}
	
	public void isObstacleFrontWarp(int dir){	//checks if enemy is in square after front warping
		switch(dir){							//depending on the directiion
		case 0:								//north
			if (grid[shipx][0] == 1){			//if warping front when facing north enemy is present
				AddEnemy(shipx, 0);				//add enemy to the string builder
			}else shipy=0;						//else if clear square, move to the square
			break;
		case 1:								//south
			if (grid[shipx][getY()] == 1){		//same for each direction
				AddEnemy(shipx, getY());
			}else shipy=getY();
			break;
		case 2:								//east
			if (grid[0][shipy] == 1){
				AddEnemy(0, shipy);
			}else shipx=0;
			break;
		case 3:								//west
			if (grid[getX()][shipy] == 1){
				AddEnemy(getX(), shipy);
			}else shipx=getX();
			break;
		}
	}
	
	public void isObstacleBackWarp(int dir){	//checks if enemy is in square after back warping
		switch(dir){							//depending on the direction
		case 0:								//north
			if (grid[shipx][getY()] == 1){		//if warping back when facing north enemy is present
				AddEnemy(shipx, getY());		//add enemy to the string builder
			}else shipy=getY();					//else if clear, move to the square
			break;
		case 1:								//south
			if (grid[shipx][0] == 1){			//same for each direction
				AddEnemy(shipx, 0);
			}else shipy=0;
			break;
		case 2:								//east
			if (grid[getX()][shipy] == 1){
				AddEnemy(getX(), shipy);
			}else shipx=getX();
			break;
		case 3:								//west
			if (grid[0][shipy] == 1){
				AddEnemy(0, shipy);
			}else shipx=0;
			break;
		}
	}
	
	public void movementForward (int dir){		//method to move the ship forward 
		switch (dir){							//depending on the direction facing
		case 0:shipy++;break;
		case 1:shipy--;break;
		case 2:shipx++;break;
		case 3:shipx--;break;
		}
	}
	
	public void movementBackwards(int dir){		//method to move the ship backwards
		switch(dir){							//depending on the direction facing
		case 0:shipy--;break;
		case 1:shipy++;break;
		case 2:shipx--;break;
		case 3:shipx++;break;
		}
	}
	public int movementLeft(){			//method to switch direction facing left command
		switch (dir){							//depending on the direction facing 
		case 0:dir = 3;break;
		case 1:dir = 2;break;
		case 2:dir = 0;break;
		case 3:dir = 1;break;
		}
		return dir;
	}	
	
	public int movementRight(){			//method to switch direction facing right command
		switch(dir){							//depending on the direction facing
		case 0:dir = 2;break;
		case 1:dir = 3;break;
		case 2:dir = 1;break;
		case 3:dir = 0;break;
		}
		return dir;
	}
	
	public String executeCommand(String command){
		for(int i=0; i<command.length(); i++){		//loop goes through the whole string command
			grid[shipx][shipy] = 2;					//2 represents the ship
			switch(command.charAt(i)){				//switches each character at index of i
			case 'f':											//forward
				try{
					if (isObstacleFront(shipx, shipy)==false){	//if method returns false
						movementForward(dir);					//means clear, and ship goes forward
					}
				}
				catch(ArrayIndexOutOfBoundsException E) {		//warping front
					isObstacleFrontWarp(dir);					//using exception handling
				}
				break;
			case 'b':											//backwards
				try{
					if (isObstacleBack(shipx, shipy)==false){	//if method returns false
						movementBackwards(dir);					//means clear, and ship goes backwards
					}
				}
				catch (ArrayIndexOutOfBoundsException E){		//warping back
					isObstacleBackWarp(dir);					//using exception handling
				}
				break;
			case 'l': dir = movementLeft();break;
			case 'r': dir = movementRight();break;
			}
		}
		String result = ("("+shipx+";"+shipy+";"+direction()+")"+enemyShips); //string to return
		return result;	//	X of ship	Y of ship	Direction		enemy ships encountered
	}
	
	public int getX() {		//returns the X number of grid
		return grid.length-1; //returning size of array -1 
	}

	public int getY() {		//returns the Y number of grid
		return grid[0].length-1;	//returning cols of array -1
	}

	public static void main(String[] args){
		GalaxyExplorer enterprise = new GalaxyExplorer(3,3,"(0,2)");
		System.out.println(enterprise.executeCommand("ff"));

	}
}
