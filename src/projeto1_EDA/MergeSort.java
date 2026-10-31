package projeto1_EDA;

public class MergeSort {

    // Ordena v[inicio..fim] dividindo ao meio
    public static void mergeSort(int[] v, int inicio, int fim) {
        // Caso base: com 0 ou 1 elemento, o trecho já está ordenado
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSort(v, inicio, meio);       // ordena a metade esquerda
            mergeSort(v, meio + 1, fim);      // ordena a metade direita
            merge(v, inicio, meio, fim);      // junta as duas metades já ordenadas
        }
    }

    // Intercala v[inicio..meio] e v[meio+1..fim], que já estão ordenados
    private static void merge(int[] v, int inicio, int meio, int fim) {
        // Tamanho de cada metade
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        // Vetores auxiliares para guardar uma cópia de cada metade
        int[] esquerda = new int[n1];
        int[] direita = new int[n2];

        // Copia as duas metades para os vetores auxiliares
        for (int x = 0; x < n1; x++) {
            esquerda[x] = v[inicio + x];
        }
        for (int y = 0; y < n2; y++) {
            direita[y] = v[meio + 1 + y];
        }

        int i = 0;        // posição atual em esquerda
        int j = 0;        // posição atual em direita
        int k = inicio;   // posição onde escrever em v

        // Enquanto as duas metades tiverem elementos, escreve o menor em v
        while (i < n1 && j < n2) {
            // "<=" faz o empate pegar o da esquerda primeiro (mantém a estabilidade)
            if (esquerda[i] <= direita[j]) {
                v[k] = esquerda[i];
                i++;
            } else {
                v[k] = direita[j];
                j++;
            }
            k++;
        }

        // Se sobrou algo na esquerda, copia o resto (já está ordenado)
        while (i < n1) {
            v[k] = esquerda[i];
            i++;
            k++;
        }

        // Se sobrou algo na direita, copia o resto (já está ordenado)
        while (j < n2) {
            v[k] = direita[j];
            j++;
            k++;
        }
    }

}