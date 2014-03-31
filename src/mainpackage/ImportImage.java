package mainpackage;

import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.lang.Math.*;

import javax.imageio.*;

import mainpackage.Noisemaker;
import mainpackage.Array;
public class ImportImage {
	Array array = new Array();
	public int[][] getImage(File string){
		try{
			BufferedImage image = ImageIO.read(string);
			byte[] pixels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
			int width = image.getWidth();
			int height = image.getHeight();
			boolean hasAlpha = image.getAlphaRaster() != null;
		    int[][] result = new int[height][width];
			     if (hasAlpha) {
			         final int pixelLength = 4;
			         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			            int argb = 0;
			            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
			            argb += ((int) pixels[pixel + 1] & 0xff); // blue
			            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
			            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
			            result[row][col] = argb;
			            col++;
			            if (col == width) {
			               col = 0;
			               row++;
			            }
			         }
			      } 
			     else {
			         final int pixelLength = 3;
			         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			            int argb = 0;
			            argb += -16777216; // 255 alpha
			            int blue = ((int) pixels[pixel] & 0xff); // blue
			            int green = (((int) pixels[pixel + 1] & 0xff) << 8)>>8 ; // green
			            int red = (((int) pixels[pixel + 2] & 0xff) << 16)>> 16 ; // red
			            int newcolor = (int) Math.floor(0.216*red + 0.7152*green + 0.0722*blue);
			            Array.arrayadd(newcolor);
			            result[row][col] = newcolor;
			            col++;
			            if (col == width) {
			               col = 0;
			               row++;
			            }
			         }
			      }
			     return result;
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	public static int[][] threetoone (int[][] result){
		for(int i =0;i<result.length;i++){
			for(int j=0;j<result[0].length;j++){
				result[i][j]=result[i][j]& 0xff;
				}
		}
		return result;

	}
	public static void pixeltoimage (int[][] result,String filename) throws IOException{
		int height = result.length;
		int width = result[0].length;
		int NUM_BANDS = 3;
	    int[] array1d = new int[width * height * NUM_BANDS];
	    result=threetoone(result);
	    for (int k = 0; k < width; k++){
	      for (int j = 0; j < height; j++) {
	        for (int band = 0; band < NUM_BANDS; band++)
	        	array1d[((j * width) + k)*NUM_BANDS + band] = result[j][k];
	      }
	    }
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = (WritableRaster) bi.getData();
        raster.setPixels(0,0,width,height,array1d);
        bi.setData(raster);
        ImageIO.write(bi, "jpg",new File("C:\\Users\\Brandon\\workspace\\imageprocessing\\"+filename+".jpg"));
		
	}
	public static void main(String[]args) throws IOException{
		ImportImage ri = new ImportImage();
		Noisemaker ns = new Noisemaker();
		File file = new File("C:\\Users\\Brandon\\Desktop\\applejack.jpg");
		int[][] array = ri.getImage(file);
		int[][] array1 = ns.saltandpepper(array, 10);
		ri.pixeltoimage(array1,"applewithnoise");
		
	}


} 

