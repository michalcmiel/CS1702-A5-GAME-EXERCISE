package uk.ac.brunel.cs1702;

import java.util.ArrayList;
import java.util.Arrays;

public class GalaxyExplorer {
	static int grid[][] = new int[0][0];
	static int shipx;
	static int shipy;
	static int dir;
	static ArrayList<String> obj = new ArrayList<String>();	
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
		String[] coords = enemyShips.split("[(-,]");		
		for (int i=1; i<coords.length; i+=3){
			grid[Integer.parseInt(coords[i])][Integer.parseInt(coords[i+1])] = 1;
		}
		/*	x and y represent the size of the galaxy grid.
		 *  enemyShips is a String formatted as follows: "(es1_x,es1_y)(es2_x,es2_y)...(esN_x,eN_y)" with no white spaces, 
		 *  representing the location of enemy ships. 
		 *  
			Example use:
			GalaxyExplorer enterprise = new GalaxyExplorer(100,100,"(5,5)(7,8)")  
			//A 100x100 galaxy grid with two enemy ships at coordinates (5,5) and (7,8) 
		 */		
	}

	public static String AddEnemy(int shipx, int shipy){
		StringBuilder sb = new StringBuilder();
		sb.append(shipx);
		sb.append(";");
		sb.append(shipy);
		if (obj.contains(sb.toString())){
		}else obj.add(sb.toString());
		return null;
	}
	
	public String executeCommand(String command){
		for(int i=0; i<command.length(); i++){
			grid[shipx][shipy] = 2;
			switch(command.charAt(i)){
			case 'f':
				try{
				if (IsObstacle.Front(shipx, shipy)==true){
				}else Movement.forward(dir);
				}
				catch(ArrayIndexOutOfBoundsException E) {
					switch(dir){
					case 0:
						if (grid[shipx][0] == 1){
							AddEnemy(shipx, 0);
						}else shipy=0;
						break;
					case 1:
						if (grid[shipx][getCols()-1] == 1){
							AddEnemy(shipx, getCols()-1);
						}else shipy=getCols()-1;
						break;
					case 2:
						if (grid[0][shipy] == 1){
							AddEnemy(0, shipy);
						}else shipx=0;
						break;
					case 3:
						if (grid[getRows()-1][shipy] == 1){
							AddEnemy(getRows()-1, shipy);
						}else shipy=getRows()-1;
						break;
					}
				}
				break;
			case 'b':
				try{
				if (IsObstacle.Back(shipx, shipy)==true){
					
				}else Movement.backwards(dir);
				}
				catch (ArrayIndexOutOfBoundsException E){
					switch(dir){
					case 0:
						if (grid[shipx][getCols()-1] == 1){
							AddEnemy(shipx, getCols()-1);
						}else shipy=getCols()-1;
						break;
					case 1:
						if (grid[shipx][0] == 1){
							AddEnemy(shipx, 0);
						}else shipy=0;
						break;
					case 2:
						if (grid[getRows()-1][shipy] == 1){
							AddEnemy(getRows()-1, shipy);
						}else shipx=getRows()-1;
						break;
					case 3:
						if (grid[0][shipy] == 1){
							AddEnemy(0, shipy);
						}else shipx=0;
						break;
					}
				}
				break;
			case 'l':
				dir = Movement.left(dir);
				break;
			case 'r':
				dir = Movement.right(dir);
				break;
			default: System.out.println("unknown direction");
		}
//			System.out.println(shipx);
//			System.out.println(shipy);
		}
		
		String direction = null;
		if (dir == 0){
			direction = "N";
		}else if (dir == 1){
			direction = "S";
		}else if (dir == 2){
			direction = "E";
		}else if (dir == 3){
			direction = "W";
		}
		StringBuilder sb1 = new StringBuilder();
		for (String s : obj)
		{
			sb1.append("(");
		    sb1.append(s);
		    sb1.append(")");
		}		
		String[] coords_enemy = sb1.toString().split("[,]");

		String result = ("("+shipx+";"+shipy+";"+direction+")"+Arrays.toString(coords_enemy).replace("[", "").replace("]", ""));
/*		for (int i=0; i<getRows(); i++){
			for (int j=0; j<getCols(); j++){
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}*/	
		return result;
		/* The command string is composed of any combination of "f" (forward), "b" (backward), "l" (left) and "r" (right)
		 * Starting from (0,0,N) - quadrant (0,0) facing North - the starship executes these commands.
		 * Return value is a string representation of the final location and facing of the starship along with a list of enemy ship locations, if encountered any.
		 * If there is an enemy ship in a quadrant, your starship should not move there, but keep executing remaining commands.
		 * 
		 * Example: 
		 * The starship is on a 100x100 galaxy initially at location (0,0) and facing NORTH. The rover is given the commands "ffrff" and should end up at (2, 2) facing East.
		 
		 * The return string is in the followng format: "(x;y;facing)(es1_x;es1_y)(es2_x;es2_y)...(esN_x;esN_y)"  No white spaces.
		 * Where x and y are the final coordinates, facing is the final direction the startship is pointing to, i.e. (N,S,W,E).
		 * The return string should also contain a list of coordinates of the encountered enemy ships, which can be an arbitrary number.
		 * Please note the format of the result, which uses a semi-column rather than a comma, as a delimiter. 
		 */
	}
	
	public int getRows() {
		return grid.length; //returning rows
	}

	public int getCols() {
		return grid[0].length;	//returning columns
	}

	public static void main(String[] args){
		GalaxyExplorer enterprise = new GalaxyExplorer(10,10,"(0,1)");
		System.out.println(enterprise.executeCommand(""));
	}
}
