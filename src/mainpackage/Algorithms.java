package mainpackage;
import mainpackage.Array;
public class Algorithms {
	public void Algorithms(){
	}
	public int[][] Histoequalize (Array Histo, int[][] image){
		int height = image.length;
		int width = image[0].length;
		int[][] newimage = new int[height][width];
		Array cdfarray = calcCDF(Histo);
		Array newhisto = new Array();
		for (int i =0; i<256;i++){
			newhisto.setvalue(i,cdfarray.getvalue(i)*255/(height*width));
		}
		for (int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				newimage[i][j]=newhisto.getvalue(image[i][j]);
			}
		}
		return newimage;
		
	}
	public Array calcCDF (Array histo){
		Array cdf = new Array();
		cdf.setvalue(0, histo.getvalue(0));
		for (int i=1;i<256;i++){
			cdf.setvalue(i,histo.getvalue(i)+cdf.getvalue(i-1));
		}
		cdf.printall();
		return cdf;
	}
}
