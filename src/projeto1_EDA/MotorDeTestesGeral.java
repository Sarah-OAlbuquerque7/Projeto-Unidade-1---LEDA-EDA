package projeto1_EDA;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class MotorDeTestesGeral {

    private static final Random rand = new Random();
    private static final int MAX_VALOR = 100000;

    // ========================================================================
    // 1. GERADORES DE CENÁRIOS (RESPONSABILIDADE DO INTEGRANTE 3)
    // ========================================================================

    // Cenário 1: Vetor Aleatório
    public static int[] gerarAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(MAX_VALOR) + 1;
        }
        return vetor;
    }

    // Cenário 2: Vetor Já Ordenado
    public static int[] gerarOrdenado(int tamanho) {
        int[] vetor = gerarAleatorio(tamanho);
        Arrays.sort(vetor); // Usa o sort nativo apenas para preparar o cenário
        return vetor;
    }

    // Cenário 3: Vetor em Ordem Inversa
    public static int[] gerarInverso(int tamanho) {
        int[] vetor = gerarOrdenado(tamanho);
        // Inverte o array
        for (int i = 0; i < tamanho / 2; i++) {
            int temp = vetor[i];
            vetor[i] = vetor[tamanho - 1 - i];
            vetor[tamanho - 1 - i] = temp;
        }
        return vetor;
    }

    // Cenário 4: Vetor Parcialmente Ordenado (15% embaralhado)
    public static int[] gerarParcialmenteOrdenado(int tamanho) {
        int[] vetor = gerarOrdenado(tamanho);
        int quantidadeParaEmbaralhar = (int) (tamanho * 0.15);
        
        // Realiza trocas aleatórias para simular o embaralhamento de ~15%
        for (int i = 0; i < quantidadeParaEmbaralhar / 2; i++) {
            int pos1 = rand.nextInt(tamanho);
            int pos2 = rand.nextInt(tamanho);
            int temp = vetor[pos1];
            vetor[pos1] = vetor[pos2];
            vetor[pos2] = temp;
        }
        return vetor;
    }

    // ========================================================================
    // 2. VERIFICADOR DE SANIDADE
    // ========================================================================
    public static boolean isOrdenado(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    // ========================================================================
    // 3. O MOTOR UNIVERSAL DE MEDIÇÃO DE DESEMPENHO
    // ========================================================================
    
    /**
     * @param nomeAlgoritmo Nome para imprimir no terminal
     * @param algoritmo A função do algoritmo (passada via Lambda)
     * @param vetorBase O vetor original do cenário que será clonado
     */
    public static void rodarBenchmark(String nomeAlgoritmo, Consumer<int[]> algoritmo, int[] vetorBase) {
        int totalExecucoes = 25;
        int warmUp = 5;
        long somaTempos = 0;

        for (int i = 0; i < totalExecucoes; i++) {
            // Garante que o algoritmo recebe uma cópia fresca e idêntica a cada rodada
            int[] vetorTeste = vetorBase.clone();

         // ISOLAMENTO DO TEMPO
            long inicio = System.nanoTime();
            try {
                algoritmo.accept(vetorTeste); // Executa o algoritmo injetado
            } catch (StackOverflowError e) {
                System.out.printf("%-15s | Tamanho: %-7d | ERRO: StackOverflow (Pior Caso)%n", nomeAlgoritmo, vetorBase.length);
                return; // Sai do método sem quebrar o resto do programa
            }
            long fim = System.nanoTime();
            
            long duracaoNano = fim - inicio;

            if (!isOrdenado(vetorTeste)) {
                System.out.println("ERRO: " + nomeAlgoritmo + " falhou ao ordenar o vetor!");
                return;
            }

            if (i >= warmUp) {
                somaTempos += duracaoNano;
            }
        }

        long mediaNano = somaTempos / (totalExecucoes - warmUp);
        double mediaMili = mediaNano / 1_000_000.0;
        
        System.out.printf("%-15s | Tamanho: %-7d | Tempo Médio: %10.3f ms%n", 
                          nomeAlgoritmo, vetorBase.length, mediaMili);
    }
 // ========================================================================
    // 4. ÁREA DE TESTES (ONDE OS INTEGRANTES PLUGAM OS SEUS CÓDIGOS)
    // ========================================================================
    public static void main(String[] args) {
        int[] tamanhos = {10000, 50000, 100000}; //tamanhos de análise
        
        System.out.println("=================================================");
        System.out.println("       INICIANDO BATERIA DE TESTES DE EDA        ");
        System.out.println("=================================================");

        
        System.out.println("\n[Cenário 1: Aleatório]");
        for (int tamanho : tamanhos) {
            int[] baseAleatoria = gerarAleatorio(tamanho);
            
            // Execuções do cenário aleatório:
            rodarBenchmark("InsertionSort", vetor -> InsertionSort.ordenar(vetor), baseAleatoria);
            rodarBenchmark("BubbleSort", vetor -> BubbleSort.ordenar(vetor), baseAleatoria);
            rodarBenchmark("BubbleSort Otimizado", vetor -> BubbleSort.ordenarOtimizado(vetor), baseAleatoria);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenar(vetor), baseAleatoria);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenarEstavel(vetor), baseAleatoria);
            rodarBenchmark("CountingSort", vetor -> CountingSort.ordenar(vetor), baseAleatoria);
            rodarBenchmark("Merge Sort", vetor -> MergeSort.mergeSort(vetor, 0, vetor.length - 1), baseAleatoria);
            rodarBenchmark("QuickSort", vetor -> QuickSort.quickSort(vetor, 0, vetor.length - 1), baseAleatoria);
            rodarBenchmark("QuickSort (Shuffle)", vetor -> QuickSort.comShuffle(vetor), baseAleatoria);
            rodarBenchmark("Arrays.sort (Nativo)", vetor -> java.util.Arrays.sort(vetor), baseAleatoria);            
            System.out.println("-------------------------------------------------");
        }

       
        System.out.println("\n[Cenário 2: Já Ordenado]");
        for (int tamanho : tamanhos) {
            int[] baseOrdenada = gerarOrdenado(tamanho);
            
            // Execuções do cenário ordenado:
            rodarBenchmark("InsertionSort", vetor -> InsertionSort.ordenar(vetor), baseOrdenada);
            rodarBenchmark("BubbleSort", vetor -> BubbleSort.ordenar(vetor), baseOrdenada);
            rodarBenchmark("BubbleSort Otimizado", vetor -> BubbleSort.ordenarOtimizado(vetor), baseOrdenada);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenar(vetor), baseOrdenada);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenarEstavel(vetor), baseOrdenada);
            rodarBenchmark("CountingSort", vetor -> CountingSort.ordenar(vetor), baseOrdenada);
            rodarBenchmark("Merge Sort", vetor -> MergeSort.mergeSort(vetor, 0, vetor.length - 1), baseOrdenada);
            rodarBenchmark("QuickSort", vetor -> QuickSort.quickSort(vetor, 0, vetor.length - 1), baseOrdenada);
            rodarBenchmark("QuickSort (Shuffle)", vetor -> QuickSort.comShuffle(vetor), baseOrdenada);
            rodarBenchmark("Arrays.sort (Nativo)", vetor -> java.util.Arrays.sort(vetor), baseOrdenada);
            System.out.println("-------------------------------------------------");
        }

        // ====================================================================
        // 3. CENÁRIO: ORDEM INVERSA[cite: 130]
        // ====================================================================
        System.out.println("\n[Cenário 3: Ordem Inversa]");
        for (int tamanho : tamanhos) {
            int[] baseInversa = gerarInverso(tamanho);
            
            // Execuções do cenário inverso:
            rodarBenchmark("InsertionSort", vetor -> InsertionSort.ordenar(vetor), baseInversa);
            rodarBenchmark("BubbleSort", vetor -> BubbleSort.ordenar(vetor), baseInversa);
            rodarBenchmark("BubbleSort Otimizado", vetor -> BubbleSort.ordenarOtimizado(vetor), baseInversa);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenar(vetor), baseInversa);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenarEstavel(vetor), baseInversa);
            rodarBenchmark("CountingSort", vetor -> CountingSort.ordenar(vetor), baseInversa);
            rodarBenchmark("Merge Sort", vetor -> MergeSort.mergeSort(vetor, 0, vetor.length - 1), baseInversa);
            rodarBenchmark("QuickSort", vetor -> QuickSort.quickSort(vetor, 0, vetor.length - 1), baseInversa);
            rodarBenchmark("QuickSort (Shuffle)", vetor -> QuickSort.comShuffle(vetor), baseInversa);
            rodarBenchmark("Arrays.sort (Nativo)", vetor -> java.util.Arrays.sort(vetor), baseInversa);
            System.out.println("-------------------------------------------------");
        }

        
        System.out.println("\n[Cenário 4: Parcialmente Ordenado]");
        for (int tamanho : tamanhos) {
            int[] baseParcial = gerarParcialmenteOrdenado(tamanho);
            
            // Execuções do cenário parcialmente ordenado:
            rodarBenchmark("InsertionSort", vetor -> InsertionSort.ordenar(vetor), baseParcial);
            rodarBenchmark("BubbleSort", vetor -> BubbleSort.ordenar(vetor), baseParcial);
            rodarBenchmark("BubbleSort Otimizado", vetor -> BubbleSort.ordenarOtimizado(vetor), baseParcial);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenar(vetor), baseParcial);
            rodarBenchmark("SelectionSort", vetor -> SelectionSort.ordenarEstavel(vetor), baseParcial);
            rodarBenchmark("CountingSort", vetor -> CountingSort.ordenar(vetor), baseParcial);
            rodarBenchmark("Merge Sort", vetor -> MergeSort.mergeSort(vetor, 0, vetor.length - 1), baseParcial);
            rodarBenchmark("QuickSort", vetor -> QuickSort.quickSort(vetor, 0, vetor.length - 1), baseParcial);
            rodarBenchmark("QuickSort (Shuffle)", vetor -> QuickSort.comShuffle(vetor), baseParcial);
            rodarBenchmark("Arrays.sort (Nativo)", vetor -> java.util.Arrays.sort(vetor), baseParcial);
            System.out.println("-------------------------------------------------");
        }
        
        
        System.out.println("\n[CENÁRIO: TESTES DE BUSCA]");

        for (int tamanho : tamanhos) {
            // Para Busca Binária funcionar, o vetor TEM que estar ordenado!
            int[] baseOrdenada = gerarOrdenado(tamanho);
            
            int alvoPiorCaso = -1; // Um número que não existe no vetor
            
            
            rodarBenchmark("Busca Linear Iterativa", 
                vetor -> BuscaLinear.iterativa(vetor, alvoPiorCaso), baseOrdenada);
                
            rodarBenchmark("Busca Linear Recursiva", 
                    vetor -> BuscaLinear.recursiva(vetor, alvoPiorCaso), baseOrdenada);
            
            rodarBenchmark("Busca Linear Duas Pontas", 
                    vetor -> BuscaLinear.iterativaDuasPontas(vetor, alvoPiorCaso), baseOrdenada); 
            
            rodarBenchmark("Busca Binária Iterativa", 
                vetor -> BuscaBinaria.iterativa(vetor, alvoPiorCaso), baseOrdenada);

            rodarBenchmark("Busca Binária Recursiva", 
                vetor -> BuscaBinaria.recursiva(vetor, alvoPiorCaso, 0, vetor.length - 1), baseOrdenada);
                
            System.out.println("-------------------------------------------------");
        }
    }
}

