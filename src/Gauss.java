import java.util.Scanner;

public class Gauss {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el número de ecuaciones: ");
        int n = input.nextInt();

        double[][] matriz = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.out.println("Ingrese los coeficientes de la ecuación " + (i + 1) + ":");
            for (int j = 0; j <= n; j++) {
                matriz[i][j] = input.nextDouble();
            }
        }

        gauss(matriz, n);
    }

    public static void gauss(double[][] matriz, int n) {
        for (int i = 0; i < n; i++) {
            // Buscar la fila máxima en la columna i
            int maxFila = i;
            for (int j = i + 1; j < n; j++)
                if (Math.abs(matriz[j][i]) > Math.abs(matriz[maxFila][i]))
                    maxFila = j;

            // Intercambiar filas máximas e i
            double[] temp = matriz[maxFila];
            matriz[maxFila] = matriz[i];
            matriz[i] = temp;

            // Hacer todos los elementos debajo de este uno cero
            for (int j = i + 1; j < n; j++) {
                double factor = matriz[j][i] / matriz[i][i];
                for (int k = i; k <= n; k++)
                    matriz[j][k] -= factor * matriz[i][k];
            }
        }

        // Imprimir la matriz escalonada
        System.out.println("La matriz escalonada es:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++)
                System.out.print(matriz[i][j] + " ");
            System.out.println();
        }
    }
}



