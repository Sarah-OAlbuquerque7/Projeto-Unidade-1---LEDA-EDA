public class SelectionSort {

    // versão do slide: acha o menor do resto e troca com a posição i (não é estável)
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

    // versão estável: em vez de trocar, empurra todo mundo uma casa pra frente
    // e coloca o menor em i (assim os iguais não invertem de ordem)
    public static void ordenarEstavel(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) { // só < (não <=) pra pegar o primeiro dos iguais
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
