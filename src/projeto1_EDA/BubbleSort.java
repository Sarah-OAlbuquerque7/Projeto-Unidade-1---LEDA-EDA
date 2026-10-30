public class BubbleSort {

    /** Versão do slide (índices ajustados para 0..n-1). */
    public static void ordenar(int[] a) {
        int n = a.length;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 0; j < n - i; j++) {
                if (a[j] > a[j + 1]) {
                    trocar(a, j, j + 1);
                }
            }
        }
    }

    /** Versão otimizada: para se uma passada completa não fez nenhuma troca. */
    public static void ordenarOtimizado(int[] a) {
        int n = a.length;
        for (int i = 1; i <= n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - i; j++) {
                if (a[j] > a[j + 1]) {
                    trocar(a, j, j + 1);
                    trocou = true;
                }
            }
            if (!trocou) {
                break;
            }
        }
    }

    private static void trocar(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
