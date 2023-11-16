
import java.util.Scanner;

public class Crout {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los coeficientes del sistema de ecuaciones 3x3:");
        double[][] sistemaEcuaciones = new double[3][4];

        // Entrada de coeficientes
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("Coeficiente en la posición [" + i + "][" + j + "]: ");
                sistemaEcuaciones[i][j] = scanner.nextDouble();
            }
        }

        // Resolución mediante el método de Crout
        double[][] resultado = resolverSistemaCrout(sistemaEcuaciones);

        // Imprimir el resultado
        System.out.println("Solución del sistema de ecuaciones:");
        for (int i = 0; i < 3; i++) {
            System.out.println("x" + (i + 1) + " = " + resultado[i][3]);
        }

        scanner.close();
    }

    private static double[][] resolverSistemaCrout(double[][] sistemaEcuaciones) {
        int n = 3; // Tamaño de la matriz

        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        // Inicializar matrices L y U
        for (int i = 0; i < n; i++) {
            L[i][i] = 1; // Los elementos diagonales de L son 1
            for (int j = 0; j < n; j++) {
                U[i][j] = 0;
            }
        }

        // Descomposición LU
        for (int k = 0; k < n; k++) {
            for (int i = k; i < n; i++) {
                double sum = 0;
                for (int p = 0; p < k; p++) {
                    sum += L[i][p] * U[p][k];
                }
                L[i][k] = sistemaEcuaciones[i][k] - sum;
            }

            for (int j = k + 1; j < n; j++) {
                double sum = 0;
                for (int p = 0; p < k; p++) {
                    sum += L[k][p] * U[p][j];
                }
                U[k][j] = (sistemaEcuaciones[k][j] - sum) / L[k][k];
            }
        }

        // Resolver LY = B (donde Y es un vector columna)
        double[] Y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * Y[j];
            }
            Y[i] = (sistemaEcuaciones[i][3] - sum) / L[i][i];
        }

        // Resolver UX = Y
        double[][] resultado = new double[n][4];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += U[i][j] * resultado[j][3];
            }
            resultado[i][3] = (Y[i] - sum) / U[i][i];
        }

        return resultado;
    }
}
