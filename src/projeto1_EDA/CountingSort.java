package projeto1_EDA;

public class CountingSort {
	
	public static void countingSort(int[] v, int[] b, int k) {
		
		int[] c = new int[k + 1];
		for(int i = 0; i <= k; i++) {
			c[i] = 0;
		}
		
		for(int j = 0; j < v.length; j++) {
			c[v[j]] = c[v[j]] + 1;
		}
		
		for(int i = 1; i <= k; i++) {
			c[i] += c[i-1];
		}
		
		for(int j = v.length - 1; j >= 0; j--) {
			b[c[v[j]] - 1] = v[j];
			c[v[j]] = c[v[j]] - 1;
		}
	}
}
