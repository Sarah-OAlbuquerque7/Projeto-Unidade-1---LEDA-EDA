package projeto1_EDA;

public class BuscaBinaria {
	
    
    public static int iterativa(int[] v, int alvo) {
        int inicio = 0;
        int fim = v.length - 1;
        
        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2; 
            
            if (v[meio] == alvo) {
                return meio; // Elemento encontrado
            }
            
            if (v[meio] < alvo) {
                inicio = meio + 1; // Descarta a metade esquerda
            } else {
                fim = meio - 1; // Descarta a metade direita
            }
        }
        
        return -1; // Pior caso: varreu as divisões e não encontrou
    }

    
    public static int recursiva(int[] v, int alvo, int inicio, int fim) {
        // Condição de parada básica (Pior caso: não encontrou)
        if (inicio > fim) {
            return -1; 
        }
        
        int meio = inicio + (fim - inicio) / 2;
        
        if (v[meio] == alvo) {
            return meio;
        }
        
        if (v[meio] < alvo) {
            // Retorna o resultado da busca na metade direita
            return recursiva(v, alvo, meio + 1, fim);
        } else {
            // Retorna o resultado da busca na metade esquerda
            return recursiva(v, alvo, inicio, meio - 1);
        }
    }
}