package uk.ac.brunel.cs1702;

public class Movement {
	
	public static void forward (int dir){
		if (dir==0){
				GalaxyExplorer.shipy++;
		}else if (dir==1){
				GalaxyExplorer.shipy--;
		}else if (dir==2){
			GalaxyExplorer.shipx++;
		}else if (dir==3){
			GalaxyExplorer.shipx--;
		}else System.out.println("aha");
	}
	public static void backwards(int dir){
		if (dir ==0){
			GalaxyExplorer.shipy--;
		}else if (dir ==1){
			GalaxyExplorer.shipy++;
		}else if (dir == 2){
			GalaxyExplorer.shipx--;
		}else if (dir == 3){
			GalaxyExplorer.shipx++;
		}
	}
	public static int left(int dir){
		if (dir == 0){
			dir = 3;
		}else if (dir == 1){
			dir = 2;
		}else if (dir == 2){
			dir = 0;
		}else if (dir == 3){
			dir = 1;
		}
		return dir;
	}
	public static int right(int dir){
		if (dir == 0){
			dir = 2;
		}else if (dir == 1){
			dir = 3;
		}else if (dir == 2){
			dir = 1;
		}else if (dir == 3){
			dir = 0;
		}
		return dir;
	}
}
