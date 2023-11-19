import java.util.Scanner;
//ECUACION x^2 - 5x + 6
public class secante 
{
    static double funcion(double x) 
    {
        return x * x - 5 * x + 6;
    }

    // Método de la secante para encontrar la raíz de la ecuación
    static double metodoSecante(double x0, double x1, double exactitud, int maxIteraciones) 
    {
        int iteracion = 1;

        while (iteracion <= maxIteraciones) 
        {
            // Aplicamos el método
            double x2 = x1 - (funcion(x1) * (x1 - x0)) / (funcion(x1) - funcion(x0));

            //Mostramos cada iteracion
            System.out.println("Iteración " + iteracion + ": x0=" + x0 + ", x1=" + x1 + ", x2=" + x2);
            if (Math.abs(x2 - x1) < exactitud) 
            {
                System.out.println("Solución  " + iteracion + ": " + x2);
                return x2;
            }
            x0 = x1;
            x1 = x2;
            iteracion++;
        }

        System.out.println("El método no convergió después de " + maxIteraciones + " iteraciones.");
        return Double.NaN;
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el primer valor inicial (x0): ");
        double x0 = scanner.nextDouble();

        System.out.print("Ingrese el segundo valor inicial (x1): ");
        double x1 = scanner.nextDouble();

        System.out.print("Ingrese la tolerancia: ");
        double exactitud = scanner.nextDouble();

        System.out.print("Ingrese el número máximo de iteraciones: ");
        int maxIteraciones = scanner.nextInt();

        scanner.close();
        double solucion = metodoSecante(x0, x1, exactitud, maxIteraciones);

        if (!Double.isNaN(solucion)) 
        {
            System.out.println("La solución aproximada es: " + solucion);
        }
    }
}
