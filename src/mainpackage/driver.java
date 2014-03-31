package mainpackage;

import java.io.File;
import java.io.IOException;
import mainpackage.*;
public class driver {

	public static void main(String[]args) throws IOException{
		ImportImage ri = new ImportImage();
		Noisemaker ns = new Noisemaker();
		File file = new File("C:\\Users\\Brandon\\Desktop\\applejack.jpg");
		int[][] array = ri.getImage(file);
		int[][] array1 = ns.saltandpepper(array, 10);
		ri.pixeltoimage(array1,"applewithnoise");
		
	}

}
