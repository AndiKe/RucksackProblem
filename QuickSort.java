public class QuickSort {
   public static void sortiere(int[] x, int[] y) {
      qSort(x,y, 0, x.length-1);
   }
    
   public static void qSort(int[] x, int[] y, int links, int rechts) {
      if (links < rechts) {
         int i = partition(x,y,links,rechts);
         qSort(x,y,links,i-1);
         qSort(x,y,i+1,rechts);
      }
   }
    
   public static int partition(int[] x, int[] y, int links, int rechts) {
      float pivot;
      int i,j,help;
      pivot = (float)x[rechts]/(float)y[rechts];               
      i     = links;
      j     = rechts-1;
      while(i<=j) {
         if ((float)x[i]/(float)y[i] < pivot) {     
            // tausche x[i] und x[j]
            help = x[i]; 
            x[i] = x[j]; 
            x[j] = help; 
            help = y[i];
            y[i] = y[j];
            y[j] = help;
            j--;
         } else i++;            
      }
      // tausche x[i] und x[rechts]
      help      = x[i];
      x[i]      = x[rechts];
      x[rechts] = help;
      help      = y[i];
      y[i]      = y[rechts];
      y[rechts] = help;
        
      return i;
   }
}