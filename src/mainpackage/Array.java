package mainpackage;

import java.io.IOException;

public class Array {
	int[] histo = new int[256];
    /** Creates a new instance of Array */
    public Array() {
        for(int i = 0; i < histo.length; i++) {
            histo[i] = 0;
        }
    }
    //not immune to out of bounds
    public void arrayadd(int i){
    		histo[i]++;
    }
   
    public int getvalue(int i){
    	return histo[i];
    }
    public void setvalue(int i,int j){
    	histo[i]=j;
    }
    public void printval(int i){
    	System.out.print(histo[i]);
    }
    public void printall (){
    	for(int i =0;i<256;i++){
    		System.out.println(histo[i]);
    	}
    }
}
