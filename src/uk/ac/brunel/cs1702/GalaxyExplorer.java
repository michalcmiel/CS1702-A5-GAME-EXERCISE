package uk.ac.brunel.cs1702;

public class GalaxyExplorer {
	static int grid[][] = new int[0][0];
	int shipx, shipy, dir;
	String[] coords;
	StringBuilder sb = new StringBuilder();
	//0 = north
	//1 = south
	//2 = east
	//3 = west
	
	public GalaxyExplorer(int x, int y, String enemyShips){
		grid = new int[x][y];
		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++){
				grid[i][j]=0;
			}
		}
		coords = enemyShips.split("[(-,]");		
		for (int i=1; i<coords.length; i+=3){
			grid[Integer.parseInt(coords[i])][Integer.parseInt(coords[i+1])] = 1;
		}	
	}
	
	public void AddEnemy(int shipx, int shipy){
		if (!sb.toString().contains("("+shipx+";"+shipy+")")){
			sb.append("("+shipx+";"+shipy+")");
		}
	}

	public String direction(int dir){
		String direction = null;
		switch(dir){
		case 0: direction = "N";
				break;
		case 1: direction = "S";
				break;
		case 2: direction = "E";
				break;
		case 3: direction = "W";
				break;
		}
		return direction;
	}
	
	public boolean isObstacleFront(int shipx, int shipy){
		switch (dir){
		case 0:								//north
			if (grid[shipx][shipy+1] == 1){
				AddEnemy(shipx, shipy+1);
				return true;
			}else return false;
		case 1:								//south
			if (grid[shipx][shipy-1] == 1){
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
	
	public boolean isObstacleBack(int shipx, int shipy){
		switch (dir){
		case 0:								//north
			if (grid[shipx][shipy-1] == 1){
				AddEnemy(shipx, shipy-1);
				return true;
			}else return false;
		case 1:								//south
			if (grid[shipx][shipy+1] == 1){
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
	
	public void isObstacleFrontWarp(int dir){
		switch(dir){
		case 0:								//north
			if (grid[shipx][0] == 1){
				AddEnemy(shipx, 0);
			}else shipy=0;
			break;
		case 1:								//south
			if (grid[shipx][getCols()-1] == 1){
				AddEnemy(shipx, getCols()-1);
			}else shipy=getCols()-1;
			break;
		case 2:								//east
			if (grid[0][shipy] == 1){
				AddEnemy(0, shipy);
			}else shipx=0;
			break;
		case 3:								//west
			if (grid[getRows()-1][shipy] == 1){
				AddEnemy(getRows()-1, shipy);
			}else shipx=getRows()-1;
			break;
		}
	}
	
	public void isObstacleBackWarp(int dir){
		switch(dir){
		case 0:								//north
			if (grid[shipx][getCols()-1] == 1){
				AddEnemy(shipx, getCols()-1);
			}else shipy=getCols()-1;
			break;
		case 1:								//south
			if (grid[shipx][0] == 1){
				AddEnemy(shipx, 0);
			}else shipy=0;
			break;
		case 2:								//east
			if (grid[getRows()-1][shipy] == 1){
				AddEnemy(getRows()-1, shipy);
			}else shipx=getRows()-1;
			break;
		case 3:								//west
			if (grid[0][shipy] == 1){
				AddEnemy(0, shipy);
			}else shipx=0;
			break;
		}
	}
	
	public void movementForward (int dir){
		switch (dir){
		case 0:								//north
			shipy++;
			break;
		case 1:								//south
			shipy--;
			break;
		case 2:								//east
			shipx++;
			break;
		case 3:								//west
			shipx--;
			break;
		}
	}
	
	public void movementBackwards(int dir){
		switch(dir){
		case 0:								//north
			shipy--;
			break;
		case 1:								//south
			shipy++;
			break;
		case 2:								//east
			shipx--;
			break;
		case 3:								//west
			shipx++;
			break;
		}
	}
	public int movementLeft(int dir){
		switch (dir){
		case 0:								//north
			dir = 3;
			break;
		case 1:								//south
			dir = 2;
			break;
		case 2:								//east
			dir = 0;
			break;
		case 3:								//west
			dir = 1;
			break;
		}
		return dir;
	}	
	public int movementRight(int dir){
		switch(dir){
		case 0:								//north
			dir = 2;
			break;
		case 1:								//south
			dir = 3;
			break;
		case 2:								//east
			dir = 1;
			break;
		case 3:								//west
			dir = 0;
			break;
		}
		return dir;
	}
	
	public String executeCommand(String command){
		for(int i=0; i<command.length(); i++){
			grid[shipx][shipy] = 2;
			switch(command.charAt(i)){
			case 'f':
				try{
					if (isObstacleFront(shipx, shipy)==false){
						movementForward(dir);
					}
				}
				catch(ArrayIndexOutOfBoundsException E) {		//warping front
					isObstacleFrontWarp(dir);
				}
				break;
			case 'b':
				try{
					if (isObstacleBack(shipx, shipy)==false){
						movementBackwards(dir);
					}
				}
				catch (ArrayIndexOutOfBoundsException E){		//warping back
					isObstacleBackWarp(dir);
				}
				break;
			case 'l':
				dir = movementLeft(dir);		//left command
				break;
			case 'r':
				dir = movementRight(dir);		//right command
				break;
			}
		}
		String result = ("("+shipx+";"+shipy+";"+direction(dir)+")"+sb);
		return result;
	}
	
	public int getRows() {
		return grid.length; //returning rows
	}

	public int getCols() {
		return grid[0].length;	//returning columns
	}

	public static void main(String[] args){
		GalaxyExplorer enterprise = new GalaxyExplorer(3,3,"(0,1)(0,2)");
		System.out.println(enterprise.executeCommand("f"));
	}
}
