import javax.swing.JOptionPane;

public class GaussSeidel {

    public static void main(String[] args) {
        // Solicitar al usuario el número de ecuaciones en el sistema
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de ecuaciones en el sistema:"));
        JOptionPane.showMessageDialog(null, "ten en cuenta tener una matriz diagonal dominante =)");

        // Inicializar matrices y arreglos para los coeficientes y términos constantes
        double[][] coeficientes = new double[n][n];
        double[] constantes = new double[n];

        // Solicitar al usuario los coeficientes y términos constantes para cada ecuación
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Solicitar el coeficiente para la variable x_j en la ecuación i
                coeficientes[i][j] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el coeficiente para x" + (j + 1) + " de la ecuación " + (i + 1) + ":"));
            }
            // Solicitar el término constante para la ecuación i
            constantes[i] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el término constante para la ecuación " + (i + 1) + ":"));
        }

        // Solicitar al usuario los valores iniciales
        double[] valoresIniciales = new double[n];
        for (int i = 0; i < n; i++) {
            valoresIniciales[i] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor inicial para x" + (i + 1) + ":"));
        }

        // Solicitar al usuario el número máximo de iteraciones y la tolerancia
        int iteracionesMaximas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número máximo de iteraciones:"));
        double tolerancia = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tolerancia(se recomienda 0.0001 =) ):"));

        // Llamar al método de Gauss-Seidel para resolver el sistema de ecuaciones
        double[] resultados = gaussSeidel(coeficientes, constantes, valoresIniciales, iteracionesMaximas, tolerancia);

        // Mostrar los resultados al usuario en una ventana
        StringBuilder resultadosStr = new StringBuilder("Resultados:\n");
        for (int i = 0; i < resultados.length; i++) {
            resultadosStr.append(String.format("x%d = %.4f%n", i + 1, resultados[i]));
        }
        JOptionPane.showMessageDialog(null, resultadosStr.toString());
    }

    // Método para aplicar el método iterativo de Gauss-Seidel
    public static double[] gaussSeidel(double[][] coeficientes, double[] constantes, double[] valoresIniciales, int iteracionesMaximas, double tolerancia) {
        int n = constantes.length;
        double[] resultados = new double[n];
        double[] resultadosAnteriores = new double[n];

        // Inicializar los resultados con los valores iniciales proporcionados por el usuario
        System.arraycopy(valoresIniciales, 0, resultados, 0, n);

        // Iterar hasta alcanzar el número máximo de iteraciones
        for (int iteracion = 0; iteracion < iteracionesMaximas; iteracion++) {
            // Realizar una iteración de Gauss-Seidel
            for (int i = 0; i < n; i++) {
                resultadosAnteriores[i] = resultados[i];

                double suma = constantes[i];
                for (int j = 0; j < n; j++) {
                    // Ignorar el término correspondiente a la variable que se está calculando
                    if (j != i) {
                        suma -= coeficientes[i][j] * resultados[j];
                    }
                }

                // Calcular el nuevo valor para la variable
                resultados[i] = suma / coeficientes[i][i];
            }

            // Verificar convergencia comparando los resultados actuales y anteriores
            boolean convergencia = true;
            for (int i = 0; i < n && convergencia; i++) {
                if (Math.abs(resultados[i] - resultadosAnteriores[i]) > tolerancia) {
                    convergencia = false;
                }
            }

            // Si se alcanza la convergencia, mostrar un mensaje y devolver los resultados
            if (convergencia) {
                JOptionPane.showMessageDialog(null, "Convergencia alcanzada después de " + (iteracion + 1) + " iteraciones.");
                return resultados;
            }
        }

        // Si no se alcanza la convergencia, mostrar un mensaje de advertencia
        JOptionPane.showMessageDialog(null, "El método de Gauss-Seidel no converge en " + iteracionesMaximas + " iteraciones.");
        return resultados;
    }
}