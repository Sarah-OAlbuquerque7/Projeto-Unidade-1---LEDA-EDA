public class SelectionSort {

    /** Versão do slide (tradicional, NÃO estável). */
    public static void ordenar(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            trocar(a, i, min);
        }
    }

    /**
     * Versão estável: em vez de trocar, guarda o menor, desloca os
     * elementos anteriores uma posição para a direita e insere o menor em i.
     * Como o menor escolhido é sempre o primeiro (mais à esquerda) entre os iguais
     * (comparação estrita <), a ordem relativa dos iguais é preservada.
     */
    public static void ordenarEstavel(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            int menor = a[min];
            while (min > i) {
                a[min] = a[min - 1];
                min--;
            }
            a[i] = menor;
        }
    }

    private static void trocar(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
