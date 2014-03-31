package mainpackage;
import java.lang.Math.*;
public class Noisemaker {
	public void Noisemaker(){}
	
	public int [][] saltandpepper(int[][] inital, int n){
		int height = inital.length;
		int width = inital[0].length;
		for (int i=0; i<height; i++){
			for(int j=0; j<width;j++){
				int random = (int) Math.floor(Math.random() * n);
				if (random == 1){
					if(inital[i][j]>=128){
						inital[i][j]=0;
					}
					else{
						inital[i][j]=255;
					}
				}
			}
		}
		return inital;
	}
}
