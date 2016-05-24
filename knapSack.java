import java.util.Random;

import knapSack.XArt;

public class knapSack {
	int[] p;
	int[] w;
	float[] x;
	float[] xTilde;
	//m = Maximalgewicht Rucksack

	public enum XArt {
	    TILDE, NICHTTILDE 
	}
	
	public knapSack(int[] p, int[] w){
		this.p = p;
		this.w = w;
		
		x = new float[p.length];
		xTilde = new float[p.length];
	}
	
	public static void main(String[] args) {
		
		//Test QuickSort
		/*Random r = new Random();
		
		for(int i = 0; i < kS.n; i++){
			p[i] = r.nextInt(300);
			if(p[i] == 0){
				p[i] = 1;
			}
			w[i] = r.nextInt(300);
			if(w[i] == 0){
				w[i] = 1;
			}
		}
		QuickSort.sortiere(p,w);
		
		float[] pw = new float[n];
		
		for(int i = 0; i < n; i++){
			
			pw[i] = (float)p[i] / (float)w[i];
		}*/
		
		//Test knapSack Beispiel Skript
		int[] tempP = {5, 12, 7, 12};
		int[] tempW = {1, 3, 2, 4};
		
		QuickSort.sortiere(tempP, tempW);
		
		knapSack kS = new knapSack(tempP, tempW);
		
		float[] ergebnis = kS.knapSackFunc(5);
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
	}
	
	
	float[] knapSackFunc(int m){
		
		greedyItem newItem = greedyKnapsack(0, p.length-1, XArt.TILDE, m);
		xTilde[newItem.k] = 0;
		doRec(0,m,0,x);
		return xTilde;	
	}
	
	void doRec(int end, int mStrich, float pStrich, float[] rechenX){
		float[] valX = rechenX.clone();
		int pTilde = 0;
		for(int k = 0; k < p.length; k++){
			pTilde += p[k] * xTilde[k];
		}
		
		int j = end;
		if(j <= (p.length-1)){
			x = valX.clone();
			greedyItem newItem = greedyKnapsack(j, p.length-1, XArt.NICHTTILDE, mStrich);
			valX = x.clone();
			if(pStrich + newItem.d > pTilde){
				valX[j] = 0;
				doRec(j+1, mStrich, pStrich, valX);
				if(w[j] <= mStrich){
					valX[j] = 1;
					doRec(j+1, mStrich-w[j], pStrich+p[j], valX);//mStrich-w[newItem.k] ist falsch!
					
				}
			}
		}else if(pStrich > pTilde){
			xTilde = valX;		
		}
	}
	
	/**
	 * @param start Startindex 
	 * @param end Endindex
	 * @param xArt XArt.TILDE bzw. XArt.NICHTTILDE
	 * @param m Maximalmasse
	 * 
	 * @return greedyItem
	 */
	greedyItem greedyKnapsack(int start, int end, XArt xArt, int m){
		
		int c = 0;
		int k = 0;
		float d = 0;
		
		if(xArt == XArt.TILDE){
			for(k = start; k <= end; k++){
				c = c + w[k];
				if(c <= m){
					xTilde[k] = 1;
					d = d + p[k];
				} else {
					xTilde[k] = ((float)(w[k] - (c - m)))/(float)w[k];
					d = d + p[k]*xTilde[k];
					break;
				}
			}
		} else {
			for(k = start; k <= end; k++){
				c = c + w[k];
				if(c <= m){
					x[k] = 1;
					d = d + p[k];
				} else {
					x[k] = ((float)(w[k] - (c - m)))/(float)w[k];
					d = d + p[k]*x[k];
					break;
				}
			}
		}
		
		return new greedyItem(k, d);
	}
	
	/*float[] getSubArray(float[] a,int StartIdx, int EndIdx) {
	
	float[] newArr = new float[(EndIdx-StartIdx)+1];
	for(int i = StartIdx; i <= EndIdx; i++){
		newArr[i-StartIdx] = a[i];
	}
	return newArr;
	}
	
	int[] getSubArray(int[] a,int StartIdx, int EndIdx) {
	
		int[] newArr = new int[(EndIdx-StartIdx)+1];
		for(int i = StartIdx; i <= EndIdx; i++){
			newArr[i-StartIdx] = a[i];
		}
		return newArr;
	} */
}