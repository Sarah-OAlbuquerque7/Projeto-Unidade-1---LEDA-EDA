package projeto1_EDA;

public class BubbleSort {

    // versão do slide: vai comparando de dois em dois e jogando o maior pro final
    public static void ordenar(int[] a) {
        int n = a.length;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 0; j < n - i; j++) { // o final já tá ordenado, então não precisa ir até lá
                if (a[j] > a[j + 1]) {
                    trocar(a, j, j + 1);
                }
            }
        }
    }

    // igual a de cima, mas se uma passada inteira não trocou nada, já tá ordenado e para
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
