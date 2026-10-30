package projeto1_EDA;

public class MergeSort {
	
    public static void mergeSort(int[] v, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(v, inicio, meio);
            mergeSort(v, meio + 1, fim);
            merge(v, inicio, meio, fim);
        }
    }

    private static void merge(int[] v, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        int[] esquerda = new int[n1];
        int[] direita = new int[n2];

        for (int x = 0; x < n1; x++) {
            esquerda[x] = v[inicio + x];
        }
        for (int y = 0; y < n2; y++) {
            direita[y] = v[meio + 1 + y];
        }

        int i = 0;
        int j = 0;
        int k = inicio;

        while (i < n1 && j < n2) {
            if (esquerda[i] <= direita[j]) {
                v[k] = esquerda[i];
                i++;
            } else {
                v[k] = direita[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            v[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < n2) {
            v[k] = direita[j];
            j++;
            k++;
        }
    }

   
	
}
