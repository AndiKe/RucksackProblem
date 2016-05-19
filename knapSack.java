import java.util.Random;

public class knapSack {
	int n = 999;
	int[] p = new int[n];
	int[] w = new int[n];
	float[] x = new float[n];
	float[] xTilde = new float[n];
	//m = Maximalgewicht Rucksack
	
	public static void main(String[] args) {
		
		knapSack kS = new knapSack();
		
		Random r = new Random();
		
		for(int i = 0; i < kS.n; i++){
			kS.p[i] = r.nextInt(300);
			if(kS.p[i] == 0){
				kS.p[i] = 1;
			}
			kS.w[i] = r.nextInt(300);
			if(kS.w[i] == 0){
				kS.w[i] = 1;
			}
		}
		QuickSort.sortiere(kS.p,kS.w);
		float[] pw = new float[kS.n];
		for(int i = 0; i < kS.n; i++){
			
			pw[i] = (float)kS.p[i] / (float)kS.w[i];
		}
		String abc = "asdasd";
	}
	
	
	
	float[] knapSack(int[] p,int[] w,int m){
	greedyItem newItem = greedyKnapsack(p,w,xTilde,m);
	xTilde[newItem.k] = 0;
	

	doRec(getSubArray(x,0,0),m,0);
	return xTilde;	
	}
	
	
	
	void doRec(float[] x, int mStrich, float pStrich){
		int pTilde = 0;
		for(int k = 0; k <= n; k++){
			pTilde += p[k] * xTilde[k];
		}
		int j = x.length-1;
		if(j <= n){
			
			greedyItem newItem = greedyKnapsack(getSubArray(p,j,n),getSubArray(w,j,n),getSubArray(this.x,j,n),mStrich);
			if(pStrich + newItem.d > pTilde){
				x[j] = 0;
				doRec(getSubArray(this.x,1,j+1),mStrich,pStrich);
				if(this.w[j] <= mStrich){
					this.x[j] = 1;
					doRec(getSubArray(this.x,1,j+1),mStrich-this.w[newItem.k],pStrich+this.p[j]);
					
				}
			}
			
		}else if(pStrich > pTilde){
			xTilde = x;		
		}
		
	}

	float[] getSubArray(float[] a,int StartIdx, int EndIdx) {
		
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
}
	
	greedyItem greedyKnapsack(int[] p, int[] w, float[] x, int m){
		
		return new greedyItem(2,2);
	}
	
	
}


