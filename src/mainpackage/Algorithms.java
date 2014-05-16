package mainpackage;
import mainpackage.Array;
public class Algorithms {
	public void Algorithms(){
	}
	//newhisto is t(s)
	interface filterfunction {
		int operation(int a, int b);
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
		newhisto.printall();
		return newimage;

	}
	public Array calcCDF (Array histo){
		Array cdf = new Array();
		cdf.setvalue(0, histo.getvalue(0));
		for (int i=1;i<256;i++){
			cdf.setvalue(i,histo.getvalue(i)+cdf.getvalue(i-1));
		}
		return cdf;
	}
	public int[][] gausssmoothbad (int[][] image){
		int height = image.length;
		int width = image[0].length;
		int[][] newimage = new int[height][width];
		for (int i=2;i<width-2;i++){
			for (int j = 2; j<height-2;j++){
				int sumtwo = 2*(image[j-2][i-2]+image[j+2][i-2]+image[j-2][i+2]+image[j+2][i+2]);
				int sumfour = 4*(image[j-2][i-1]+image[j-1][i-2]+image[j-2][i+1]+image[j-1][i+1]
						+image[j-1][i+2]+image[j+1][i+2]+image[j+1][i+2]+image[j+2][i+1]+image[j+2][i-1]+image[j+1][i-2]);
				int sumfive = 5*(image[j-2][i]+image[j][i+2]+image[j+2][i]+image[j][i-2]);
				int sumtwelve = 12*(image[j-1][i]+image[j][i-1]+image[j+1][i]+image[j][i+1]);
				int sumnine = 9*(image[j-1][i-1]+image[j-1][i+1]+image[j+1][i-1]+image[j+1][i+1]);
				int sumfifteen = 15*image[j][i];
				newimage[j][i]= (int) Math.floor((sumtwo+sumfour+sumfive+sumtwelve+sumnine+sumfifteen)/159);
			}
		}
		for(int i=0;i<height;i++){
			newimage[i][0] = image[i][0];
			newimage[i][1] = image[i][1];
			newimage[i][height-2] = image[i][height-2];
			newimage[i][height-1] = image[i][height-1];
				
		}
		for(int i=0;i<width;i++){
			newimage[0][i] = image[0][i];
			newimage[1][i] = image[1][i];
			newimage[width-2][i] = image[width-2][i];
			newimage[width-1][i] = image[width-1][i];
		}
		return newimage;
	}
	public int[][] twodconvolution (int[][] img, int[][] kernal){
		int aheight = img.length;
		int awidth = img[0].length;
		int bheight = kernal.length;
		int bwidth = kernal[0].length;
		int kcenterx = (int) Math.floor(bwidth/2);
		int kcentery = (int) Math.floor(bheight/2);
		for (int i=0;i<awidth;i++){
			for(int j=0; j<aheight;j++){
				int val = 0;
				for(int k=0;k<bwidth;k++){
					for (int l=0;l<bheight;l++){
						if((j-(l-kcentery))>=0 && (i-(k-kcenterx))>=0){
							val = val + kernal[k][j]*img[j-(l-kcentery)][i-(k-kcenterx)];
						}
					}
				}
			}
		}
		int[][] result = new int[aheight][awidth];
		return result;
	}
	
	/*public int[][] histomatch (Array oldhisto, Array targethisto, int[][] image){
		int height = image.length;
		int width = image[0].length;
		int[][] newimage = new int[height][width];
		Array eqlold = new Array();
	}*/
}
