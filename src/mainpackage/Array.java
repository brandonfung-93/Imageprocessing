package mainpackage;

public class Array {
	static int[] histo = new int[256];
    /** Creates a new instance of Array */
    public Array() {
        for(int i = 0; i < histo.length; i++) {
            histo[i] = 0;
        }
    }
    //not immune to out of bounds
    public static void arrayadd(int i){
    		histo[i]++;
    }
   
    public int getvalue(int i){
    	return histo[i];
    }
    public void setvalue(int i,int j){
    	histo[i]=j;
    }
}
