package projeto1_EDA;

public class CountingSort {
	
	public static void ordenar(int[] v) {
		// Proteção contra vetores vazios ou com apenas 1 elemento
		if (v == null || v.length <= 1) {
			return; 
		}
		
		// 1. Encontrar dinamicamente o valor máximo (k) do vetor
		int k = v[0];
		for (int i = 1; i < v.length; i++) {
			if (v[i] > k) {
				k = v[i];
			}
		}
		
		// 2. Criar os vetores auxiliares internamente
		// O Java já inicializa arrays de inteiros com zeros por padrão 
		int[] c = new int[k + 1]; 
		int[] b = new int[v.length];
		
		// 3. Frequência: Contar ocorrências de cada elemento
		for (int j = 0; j < v.length; j++) {
			c[v[j]] = c[v[j]] + 1; // ou apenas c[v[j]]++
		}
		
		// 4. Cumulativa: Somar com a posição anterior
		for (int i = 1; i <= k; i++) {
			c[i] += c[i - 1];
		}
		
		// 5. Ordenação: Preencher o vetor 'b' de trás para frente para garantir estabilidade
		for (int j = v.length - 1; j >= 0; j--) {
			b[c[v[j]] - 1] = v[j];
			c[v[j]] = c[v[j]] - 1;
		}
		
		// 6. Sobrescrita: Copiar o vetor ordenado 'b' de volta para o vetor original 'v'
		for (int i = 0; i < v.length; i++) {
			v[i] = b[i];
		}
	}
}