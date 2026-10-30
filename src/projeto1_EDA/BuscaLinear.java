public class BuscaLinear {

    /** Busca linear iterativa. Retorna o índice de x ou -1 se ausente. */
    public static int iterativa(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /** Busca linear recursiva. Retorna o índice de x ou -1 se ausente. */
    public static int recursiva(int[] a, int x) {
        return recursiva(a, x, 0);
    }

    private static int recursiva(int[] a, int x, int i) {
        if (i >= a.length) {
            return -1;
        }
        if (a[i] == x) {
            return i;
        }
        return recursiva(a, x, i + 1);
    }
}
