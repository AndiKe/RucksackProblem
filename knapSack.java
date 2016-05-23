import java.util.Random;

public class knapSack {
	static int n = 4;
	static int[] p = new int[n];
	static int[] w = new int[n];
	static float[] x = new float[n];
	static float[] xTilde = new float[n];
	//m = Maximalgewicht Rucksack

	public enum XArt {
	    TILDE, NICHTTILDE 
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
		p[0] = 5;
		p[1] = 12;
		p[2] = 7;
		p[3] = 12;
		
		w[0] = 1;
		w[1] = 3;
		w[2] = 2;
		w[3] = 4;
		
		float[] ergebnis = knapSack(5);
		for(int i = 0; i < ergebnis.length; i++){
			System.out.print(ergebnis[i] + " ");
		}
	}
	
	
	static float[] knapSack(int m){
		
		greedyItem newItem = greedyKnapsack(0, n-1, XArt.TILDE, m);
		xTilde[newItem.k] = 0;
		doRec(0,m,0);
		return xTilde;	
	}
	
	static void doRec(int end, int mStrich, float pStrich){
		
		int pTilde = 0;
		for(int k = 0; k < n; k++){
			pTilde += p[k] * xTilde[k];
		}
		
		int j = end;
		if(j <= (n-1)){
			greedyItem newItem = greedyKnapsack(j, n, XArt.NICHTTILDE, mStrich);
			if(pStrich + newItem.d > pTilde){
				x[j] = 0;
				doRec(j+1, mStrich, pStrich);
				if(w[j] <= mStrich){
					x[j] = 1;
					doRec(j+1, mStrich-w[newItem.k], pStrich+p[j]);
					
				}
			}
		}else if(pStrich > pTilde){
			xTilde = x;		
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
	static greedyItem greedyKnapsack(int start, int end, XArt xArt, int m){
		
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
					xTilde[k] = (w[k] - (c - m))/w[k];
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
					x[k] = (w[k] - (c - m))/w[k];
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