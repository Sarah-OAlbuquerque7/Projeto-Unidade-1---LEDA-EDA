package projeto1_EDA;

public class BuscaLinear {

    // vai de um em um até achar o x; se não achar devolve -1
    public static int iterativa(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // mesma ideia, mas com recursão 
    public static int recursiva(int[] a, int x) {
        return recursiva(a, x, 0);
    }

    private static int recursiva(int[] a, int x, int i) {
        if (i >= a.length) { // chegou no fim e não achou
            return -1;
        }
        if (a[i] == x) {
            return i;
        }
        return recursiva(a, x, i + 1);
    }
    
    public static int iterativaDuasPontas(int[] a, int x) {
    	int i = 0;
    	int j = a.length - 1;
    	
    	while(i <= j) { //até se cruzarem
    		if(a[i] == x) {
    			return i;
    		}
    		if(a[j] == x) {
    			return j;
    		}
    		i++;
    		j--;
    	}
    	
    	return -1; //caso base
    }
}
