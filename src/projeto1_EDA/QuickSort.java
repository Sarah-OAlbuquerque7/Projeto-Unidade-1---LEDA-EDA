package projeto1_EDA;

import java.util.Random;

public class QuickSort {

    
    public static void quickSort(int[] v, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = partition(v, inicio, fim);
            quickSort(v, inicio, pivoIndex - 1);
            quickSort(v, pivoIndex + 1, fim);
        }
    }

    private static int partition(int[] v, int inicio, int fim) {
        int pivo = v[fim]; // Assumindo o pivô como o último elemento
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (v[j] <= pivo) {
                i++;
                swap(v, i, j);
            }
        }
        swap(v, i + 1, fim);
        return i + 1;
    }

    
    public static void comShuffle(int[] v) {
        embaralhar(v); // Quebra o padrão do vetor para evitar o pior caso
        quickSort(v, 0, v.length - 1); // Chama a versão do slide de forma segura
    }

    // Algoritmo de Fisher-Yates para embaralhamento linear O(n)
    private static void embaralhar(int[] v) {
        Random rand = new Random();
        for (int i = v.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(v, i, j);
        }
    }

    // Método auxiliar para realizar as trocas
    private static void swap(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
}