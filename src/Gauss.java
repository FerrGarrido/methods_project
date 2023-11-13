import java.util.Scanner;

public class Gauss {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el número de ecuaciones:");
        int n = scanner.nextInt();
        double[][] matriz = new double[n][n+1];
        double[] solucion = new double[n];

        System.out.println("Introduce los coeficientes de las ecuaciones y los términos independientes:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                matriz[i][j] = scanner.nextDouble();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double factor = matriz[j][i] / matriz[i][i];
                for (int k = i; k <= n; k++) {
                    matriz[j][k] -= factor * matriz[i][k];
                }
            }
        }

        System.out.println("La matriz escalonada es:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = n-1; i >= 0; i--) {
            double suma = 0;
            for (int j = i+1; j < n; j++) {
                suma += matriz[i][j] * solucion[j];
            }
            solucion[i] = (matriz[i][n] - suma) / matriz[i][i];
        }

        System.out.println("La solución es:");
        for (int i = 0; i < n; i++) {
            System.out.println("x" + (i+1) + " = " + solucion[i]);
        }
    }
}
