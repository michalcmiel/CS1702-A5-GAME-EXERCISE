package uk.ac.brunel.cs1702;

public class IsObstacle {
	public static boolean Front(int shipx, int shipy){
		switch (GalaxyExplorer.dir){
		case 0:
			if (GalaxyExplorer.grid[shipx][shipy+1] == 1){
				GalaxyExplorer.returning(shipx, shipy+1);
				return true;
			}else return false;
		case 1:
			if (GalaxyExplorer.grid[shipx][shipy-1] == 1){
				GalaxyExplorer.returning(shipx, shipy-1);
				return true;
			}else return false;
		case 2:
			if (GalaxyExplorer.grid[shipx+1][shipy] == 1){
				GalaxyExplorer.returning(shipx+1, shipy);
				return true;
			}else return false;
		case 3:
			if (GalaxyExplorer.grid[shipx-1][shipy] == 1){
				GalaxyExplorer.returning(shipx-1, shipy);
				return true;
			}else return false;
		}
		return false;
	}	
	public static boolean Back(int shipx, int shipy){
		if (GalaxyExplorer.dir ==0){
			if (GalaxyExplorer.grid[shipx][shipy-1] == 1){
				GalaxyExplorer.returning(shipx, shipy-1);
				return true;
			}else return false;
		}else if (GalaxyExplorer.dir == 1){
			if (GalaxyExplorer.grid[shipx][shipy+1] == 1){
				GalaxyExplorer.returning(shipx, shipy+1);
				return true;
			}else return false;
		}else if (GalaxyExplorer.dir == 2){
			if (GalaxyExplorer.grid[shipx-1][shipy] == 1){
				GalaxyExplorer.returning(shipx-1, shipy);
				return true;
			}else return false;
		}else if (GalaxyExplorer.dir == 3){
			if (GalaxyExplorer.grid[shipx+1][shipy] == 1){
				GalaxyExplorer.returning(shipx+1, shipy);
				return true;
			}else return false;
		}
		return false;	
	}
}
