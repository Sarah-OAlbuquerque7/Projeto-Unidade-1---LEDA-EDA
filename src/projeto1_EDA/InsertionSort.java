package projeto1_EDA;

public class InsertionSort {
	
	public static void ordenar(int[] v) {
		int n = v.length;
		
		for(int j = 1; j < n; j++) {
			int key = v[j];
			
			int i = j - 1;
			
			while(i >= 0 && v[i]> key) {
				v[i+1] = v[i];
				i--;
			}
			
			v[i+1]= key;
		}
	}
}
