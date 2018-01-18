package uk.ac.brunel.cs1702;

public class GalaxyExplorer {
	
	public GalaxyExplorer(int x, int y, String enemyShips){
		
		/*	x and y represent the size of the galaxy grid.
		 *  enemyShips is a String formatted as follows: "(es1_x,es1_y)(es2_x,es2_y)...(esN_x,eN_y)" with no white spaces, 
		 *  representing the location of enemy ships. 
		 *  
			Example use:
			GalaxyExplorer enterprise = new GalaxyExplorer(100,100,"(5,5)(7,8)")  
			//A 100x100 galaxy grid with two enemy ships at coordinates (5,5) and (7,8) 
		 */		
	}
	
	public String executeCommand(String command){
		
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
		
		return null;
		
	}
}
