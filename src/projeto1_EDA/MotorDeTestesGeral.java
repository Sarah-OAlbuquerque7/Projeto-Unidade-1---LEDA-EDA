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
            algoritmo.accept(vetorTeste); // Executa o algoritmo injetado
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
        int[] tamanhos = {50000, 150000, 500000};
        
        System.out.println("=================================================");
        System.out.println(" INICIANDO BATERIA DE TESTES - CENÁRIO ALEATÓRIO ");
        System.out.println("=================================================");

        for (int tamanho : tamanhos) {
            int[] baseAleatoria = gerarAleatorio(tamanho);

            // Exemplo de como o Integrante 3 testa o Insertion Sort:
            // rodarBenchmark("InsertionSort", vetor -> insertionSort(vetor), baseAleatoria);
            
            // Exemplo de como o Integrante 2 testa o nativo do Java:
            rodarBenchmark("Arrays.sort", vetor -> Arrays.sort(vetor), baseAleatoria);

            // Exemplo de como o Integrante 2 testa o QuickSort (que exige parâmetros de índice):
            // rodarBenchmark("QuickSort", vetor -> quickSort(vetor, 0, vetor.length - 1), baseAleatoria);
            
            System.out.println("-------------------------------------------------");
        }
        
        // O grupo deve repetir os blocos acima para os cenários: 
        // gerarOrdenado(), gerarInverso() e gerarParcialmenteOrdenado().
    }
}

