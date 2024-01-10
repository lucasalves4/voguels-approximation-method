package br.com.vogelsapproximationmethod;

import java.util.Arrays;

public class ProblemaTransporte {

    static double[][] matriz = {
            {11.27, 58.40, 8.74, 53.58, 58.10, 27.65},
            {16.33, 11.55, 51.25, 54.46, 28.39, 16.57},
            {31.25, 11.69, 38.41, 21.82, 38.65, 38.97},
            {24.33, 29.98, 8.75, 58.13, 2.10, 19.14},
            {21.08, 14.28, 42.77, 26.05, 9.33, 19.06},
            {9.74, 28.57, 18.68, 16.43, 17.28, 25.09},
            {13.18, 55.43, 18.67, 9.95, 0.98, 28.65},
            {41.86, 37.28, 31.99, 41.04, 3.52, 4.71},
            {43.02, 20.44, 36.43, 46.77, 1.94, 51.38},
            {31.80, 33.49, 49.50, 23.83, 4.25, 35.79},
            {38.70, 38.18, 2.98, 7.49, 20.57, 0.94},
            {20.26, 24.97, 30.53, 0.88, 38.09, 51.28},
            {44.61, 34.84, 36.92, 11.74, 16.60, 56.34},
            {34.71, 40.53, 15.59, 8.62, 38.27, 40.41},
            {39.26, 34.96, 51.84, 40.16, 24.25, 6.89},
            {14.89, 44.99, 34.23, 31.66, 15.59, 0.39},
            {10.27, 16.57, 48.56, 29.36, 51.14, 5.43},
            {50.90, 6.92, 31.88, 49.68, 38.20, 38.73},
            {55.79, 46.34, 9.19, 29.52, 35.89, 30.00},
            {42.73, 34.32, 34.74, 4.67, 21.09, 5.48},
            {49.58, 27.39, 22.15, 17.81, 15.37, 42.16},
            {33.51, 33.08, 17.19, 25.13, 20.17, 16.77},
            {19.29, 38.05, 32.07, 45.24, 12.53, 9.39},
            {22.99, 53.46, 27.48, 42.37, 11.52, 36.70},
            {25.73, 45.88, 12.20, 18.87, 3.07, 36.46},
            {0, 0, 0, 0, 0, 0},
    };

    static double[][] novaMatriz = new double[26][6];

    static double[] oferta = {5147, 3053, 5900, 5044, 6149, 4312};
    static double[] demanda = {1297, 656, 1124, 837, 1445, 1495, 1981, 1440, 1228, 719, 1023, 763, 221, 628, 188, 1119, 1556, 1411, 1017, 1467, 1424, 2396, 913, 1303, 976, 978};

    public static double[] calcularPenalidadeLinha(double[][] matriz) {
        double[] diferenca = new double[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            double primeiroMinimo = Double.MAX_VALUE;
            double segundoMinimo = Double.MAX_VALUE;

            for (double valor : matriz[i]) {
                if (valor < primeiroMinimo) {
                    segundoMinimo = primeiroMinimo;
                    primeiroMinimo = valor;
                } else if ((valor < segundoMinimo && valor != primeiroMinimo) || valor == primeiroMinimo) {
                    segundoMinimo = valor;
                }
            }

            // A diferença entre o segundo e o primeiro menor valor
            diferenca[i] = segundoMinimo - primeiroMinimo;
        }

        return diferenca;
    }

    public static double[] calcularPenalidadeColuna(double[][] matriz) {
        int coluna = matriz[0].length;
        double[] diferenca = new double[coluna];

        for (int j = 0; j < coluna; j++) {
            double primeiroMinimo = Double.MAX_VALUE;
            double segundoMinimo = Double.MAX_VALUE;

            for (double[] valor : matriz) {
                if (valor[j] < primeiroMinimo) {
                    segundoMinimo = primeiroMinimo;
                    primeiroMinimo = valor[j];
                } else if ((valor[j] < segundoMinimo && valor[j] != primeiroMinimo) || valor[j] == primeiroMinimo) {
                    segundoMinimo = valor[j];
                }
            }

            // Subtrai o segundo menor valor pelo primeiro menor valor da coluna e armazena no array
            diferenca[j] = segundoMinimo - primeiroMinimo;
        }

        return diferenca;
    }

    public static double acharMaior(double[] diferenca) {
        double max = diferenca[0];
        for (int i = 1; i < diferenca.length; i++) {
            if (diferenca[i] > max) {
                max = diferenca[i]; // Atualiza o max se encontrar um valor maior
            }
        }
        return max;
    }

    public static int acharIndice(double[] lista, double valor) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == valor) {
                return i; // Retorna o índice do primeiro encontro
            }
        }
        return -1; // Retorna -1 se o valor não for encontrado
    }

    public static int acharMenorLinha(double[][] matriz, int indice) {
        double min = matriz[0][indice];
        int indiceLinha = 0;
        for (int j = 1; j < 26; j++) {
            if (matriz[j][indice] < min) {
                min = matriz[j][indice]; // Atualiza o min se encontrar um valor maior
                indiceLinha = j;
            }
        }
        return indiceLinha;
    }

    public static int acharMenorColuna(double[][] matriz, int indice) {
        double min = matriz[indice][0];
        int indiceColuna = 0;
        for (int j = 1; j < 6; j++) {
            if (matriz[indice][j] < min) {
                min = matriz[indice][j]; // Atualiza o min se encontrar um valor maior
                indiceColuna = j;
            }
        }
        return indiceColuna;
    }

    public static double somarElementos(double[] numeros) {
        double soma = 0.0;
        for (double numero : numeros) {
            soma += numero;
        }
        return soma;
    }

    public static double menorValor(double valor1, double valor2) {
        return (valor1 < valor2) ? valor1 : valor2;
    }

    public static void imprimirMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) { // Navega pelas linhas
            for (int j = 0; j < matriz[i].length; j++) { // Navega pelas colunas
                System.out.printf("%.2f ", matriz[i][j]); // Imprime o elemento com duas casas decimais
            }
            System.out.println(); // Quebra de linha após terminar de imprimir uma linha da matriz
        }
    }

    public static double metodoVogel() {

        int x = 0;
        int y = 0;
        int cont = 0;
        double custoTotal = 0;

        while (somarElementos(oferta) > 0 && somarElementos(demanda) > 0) {
            cont++;
            System.out.println("***********************");
            System.out.println("Iteração: " + cont);
            System.out.println("***********************\n");
            System.out.println("Matriz de custos: ");

            imprimirMatriz(matriz);

            double[] penalidadesLinhas = calcularPenalidadeLinha(matriz);
            double[] penalidadesColunas = calcularPenalidadeColuna(matriz);

            double maiorPenalidadeLinhas = acharMaior(penalidadesLinhas);
            double maiorPenalidadeColunas = acharMaior(penalidadesColunas);

            System.out.println(maiorPenalidadeLinhas);
            System.out.println(maiorPenalidadeColunas);

            if (maiorPenalidadeLinhas > maiorPenalidadeColunas) {
                x = acharIndice(penalidadesLinhas, maiorPenalidadeLinhas);
                y = acharMenorColuna(matriz, x);
            } else {
                y = acharIndice(penalidadesColunas, maiorPenalidadeColunas);
                x = acharMenorLinha(matriz, y);
            }

            System.out.println("\n");
            System.out.println("Penalidade linha: " + Arrays.toString(calcularPenalidadeLinha(matriz)));
            System.out.println("Penalidade coluna: " + Arrays.toString(calcularPenalidadeColuna(matriz)));


            double quantidadeAlocada = menorValor(oferta[y], demanda[x]);
            custoTotal += quantidadeAlocada * matriz[x][y];

            System.out.println("\n");
            System.out.println("Quantidade alocada: " + quantidadeAlocada);
            System.out.println("\n");
            System.out.println("-----------------------------------------------------------------------------");

            oferta[y] -= quantidadeAlocada;
            demanda[x] -= quantidadeAlocada;

            novaMatriz[x][y] = quantidadeAlocada;

            if (oferta[y] == 0) {
                for (int k = 0; k < matriz.length; k++) {
                    matriz[k][y] = 9999; // Ajuste os custos para um valor alto, pois a oferta é zero.
                }
            }

            if (demanda[x] == 0) {
                for (int k = 0; k < matriz[x].length; k++) {
                    matriz[x][k] = 9999; // Ajuste os custos para um valor alto, pois a demanda é zero.
                }
            }

        }
        return custoTotal;
    }
    public static void main(String[] args) {

        double custoTotal = metodoVogel();

        System.out.println("\nSolução: \n");
        imprimirMatriz(novaMatriz);
        System.out.println("\n");
        System.out.println("**********************");
        System.out.println("Custo total: " + custoTotal);
        System.out.println("**********************");

    }
}