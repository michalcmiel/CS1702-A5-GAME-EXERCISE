package uk.ac.brunel.cs1702;

import java.util.ArrayList;
import java.util.Arrays;

public class GalaxyExplorer {
	int grid[][] = new int[0][0];
	static int shipx;
	static int shipy;
	static int dir;
	static ArrayList<String> obj = new ArrayList<String>();
	
	//0 = north
	//1 = south
	//2 = east
	//3 = west
	public GalaxyExplorer(int x, int y, String enemyShips){
		this.grid = new int[x][y];
		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++){
				this.grid[i][j]=0;
			}
		}
		String[] coords = enemyShips.split("[(-,]");
		String part1 = null;
		String part2 = null;
		
		for (int i=1; i<coords.length; i+=3){
			part1 = coords[i];	
			part2 = coords[i+1];
			this.grid[Integer.parseInt(part1)][Integer.parseInt(part2)] = 1;
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
	

	public boolean enemy(int shipx, int shipy){
		switch (dir){
		case 0:
			if (this.grid[shipx][shipy+1] == 1){
				returning(shipx, shipy+1);
				return true;
			}else return false;
		case 1:
			if (this.grid[shipx][shipy-1] == 1){
				returning(shipx, shipy-1);
				return true;
			}else return false;
		case 2:
			if (this.grid[shipx+1][shipy] == 1){
				returning(shipx+1, shipy);
				return true;
			}else return false;
		case 3:
			if (this.grid[shipx-1][shipy] == 1){
				returning(shipx-1, shipy);
				return true;
			}else return false;
		}
		return false;
	}
	
	public boolean enemyBack(int shipx, int shipy){
		if (dir ==0){
			if (this.grid[shipx][shipy-1] == 1){
				returning(shipx, shipy-1);
				return true;
			}else return false;
		}else if (dir == 1){
			if (this.grid[shipx][shipy+1] == 1){
				returning(shipx, shipy+1);
				return true;
			}else return false;
		}else if (dir == 2){
			if (this.grid[shipx-1][shipy] == 1){
				returning(shipx-1, shipy);
				return true;
			}else return false;
		}else if (dir == 3){
			if (this.grid[shipx+1][shipy] == 1){
				returning(shipx+1, shipy);
				return true;
			}else return false;
		}
		return false;	
	}
	
	public String returning(int shipx, int shipy){
		StringBuilder sb = new StringBuilder();
		sb.append(shipx);
		sb.append(shipy);
		if (obj.contains(sb.toString())){
		}else obj.add(sb.toString());
		return null;
	}
	
	
	public String executeCommand(String command){
		shipx=0;
		shipy=0;
		dir=0;
		for(int i=0; i<command.length(); i++){
			this.grid[shipx][shipy] = 2;
			switch(command.charAt(i)){
			case 'f':
				if (enemy(shipx, shipy)==true){
				}else Movement.forward(dir);
				break;
			case 'b':
				if (enemyBack(shipx, shipy)==true){
					
				}else Movement.backwards(dir);
				break;
			case 'l':
				dir = Movement.left(dir);
				break;
			case 'r':
				dir = Movement.right(dir);
				break;
			default: System.out.println("unknown direction");
		}
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
		    sb1.append(s);
		}
		StringBuilder sb2 = new StringBuilder();
		for (int i=0; i<sb1.length(); i+=2){
			sb2.append("("+sb1.charAt(i)+";"+(sb1.charAt(i+1))+")");
		}

		String result = ("("+shipx+";"+shipy+";"+direction+")"+sb2);
		System.out.println(result);
		return result;
		/* The command string is composed of any combination of "f" (forward), "b" (backward), "l" (left) and "r" (right)
		 * Starting from (0,0,N) - quadrant (0,0) facing North - the starship executes these commands.
		 * Return value is a string representation of the final location and facing of the starship along with a list of enemy ship locations, if encountered any.
		 * If there is an enemy ship in a quadrant, your starship should not move there, but keep executing remaining commands.
		 * 
		 * Example: 
		 * The starship is on a 100x100 galaxy initially at location (0,0) and facing NORTH. The rover is given the commands "ffrff" and should end up at (2, 2) facing East.
		 
		 * The return string is in the following format: "(x;y;facing)(es1_x;es1_y)(es2_x;es2_y)...(esN_x;esN_y)"  No white spaces.
		 * Where x and y are the final coordinates, facing is the final direction the startship is pointing to, i.e. (N,S,W,E).
		 * The return string should also contain a list of coordinates of the encountered enemy ships, which can be an arbitrary number.
		 * Please note the format of the result, which uses a semi-column rather than a comma, as a delimiter. 
		 */
	}
	
	public int getRows() {
		return this.grid.length; //returning rows
	}

	public int getCols() {
		return this.grid[0].length;	//returning columns
	}
	
	public String toString(){
		for (int i=0; i<getRows(); i++){
			for (int j=0; j<getCols(); j++){
				System.out.print(this.grid[j][i]+" ");
			}
			System.out.println();
		}
		return null;
	}

	public static void main(String[] args){
		GalaxyExplorer enterprise = new GalaxyExplorer(100,100,"(0,20)(3,2)");
		enterprise.executeCommand("ffffffffffffffffffff");
		enterprise.toString();
		System.out.println(obj);
	}
}
