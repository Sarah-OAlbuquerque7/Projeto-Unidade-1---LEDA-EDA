<div align="center">

# 📊 Análise de Algoritmos de Busca e Ordenação

**Projeto da Unidade 1 · Laboratório de Estrutura de Dados**
Universidade Estadual da Paraíba (UEPB) · Docente: Janderson Jason

</div>

---

## 🎯 Objetivo

Medir o desempenho de algoritmos de busca e ordenação em diferentes cenários e comparar os resultados práticos com as complexidades assintóticas teóricas (Big-O).

## 🧮 Algoritmos implementados

### Ordenação

| Algoritmo | Versões |
|---|---|
| Insertion Sort | Clássico |
| Bubble Sort | Clássico e Otimizado |
| Selection Sort | Clássico e Estável |
| Merge Sort | Clássico |
| Quick Sort | Clássico e com Shuffle |
| Counting Sort | Clássico |
| `Arrays.sort()` | Referência nativa do Java |

### Busca (em vetores ordenados)

| Algoritmo | Versões |
|---|---|
| Busca Linear | Iterativa, Recursiva e Duas Pontas |
| Busca Binária | Iterativa e Recursiva |

## 🧪 Metodologia dos testes

- **Tamanhos dos vetores:** 10.000, 50.000 e 100.000 elementos
- **Cenários:** aleatório, ordenado, inverso e parcialmente ordenado
- **Execuções:** 25 por combinação (as 5 primeiras são descartadas como aquecimento da JVM)
- **Medição de tempo:** `System.nanoTime()`

## ▶️ Como executar o benchmark

1. Clone este repositório:
   ```bash
   git clone <URL-DO-REPOSITORIO>
   ```
2. Abra o projeto na sua IDE de preferência (Eclipse, IntelliJ ou VS Code).
3. Localize e execute o arquivo principal `MotorDeTestesGeral.java`.
4. Os tempos de cada cenário (Aleatório, Ordenado, Inverso e Parcialmente Ordenado) serão impressos no console.

## 📦 Entregas do projeto

- 📄 [Relatório em PDF](Relatorio_Unidade1_EDA.pdf)

## 👥 Autores

- Alice Ferreira
- Caio César
- Sarah Oliveira
