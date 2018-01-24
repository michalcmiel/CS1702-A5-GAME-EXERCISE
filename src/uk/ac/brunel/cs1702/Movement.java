package uk.ac.brunel.cs1702;

public class Movement {
	
	public static void forward (int dir){
		switch (dir){
		case 0:
			GalaxyExplorer.shipy++;
			break;
		case 1:
			GalaxyExplorer.shipy--;
			break;
		case 2:
			GalaxyExplorer.shipx++;
			break;
		case 3:
			GalaxyExplorer.shipx--;
			break;
		}
	}
	
	public static void backwards(int dir){
		switch(dir){
		case 0:
			GalaxyExplorer.shipy--;
			break;
		case 1:
			GalaxyExplorer.shipy++;
			break;
		case 2:
			GalaxyExplorer.shipx--;
			break;
		case 3:
			GalaxyExplorer.shipx++;
			break;
		}
	}
	public static int left(int dir){
		switch (dir){
		case 0:
			dir = 3;
			break;
		case 1:
			dir = 2;
			break;
		case 2:
			dir = 0;
			break;
		case 3:
			dir = 1;
			break;
		}
		return dir;
	}	
	public static int right(int dir){
		switch(dir){
		case 0:
			dir = 2;
			break;
		case 1:
			dir = 3;
			break;
		case 2:
			dir = 1;
			break;
		case 3:
			dir = 0;
			break;
		}
		return dir;
	}
}