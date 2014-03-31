package mainpackage;
import mainpackage.Array;
public class Algorithms {
	public void Algorithms(){
	}
	public Array calcPDF (Array Histo){
		int[] pdf = new int [256];
		pdf[0]=Histo.getvalue(0);
		for (int i=1;i<256;i++){
			pdf[i]=Histo.getvalue(i)+pdf[i-1];
		}
		return Histo;
	}
}
