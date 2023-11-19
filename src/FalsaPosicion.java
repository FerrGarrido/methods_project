
//ECUACION ln(x^3 + 2) * tan(x) Valores iniciales de 2 y 4
import java.util.Scanner;

public class falsaposicion 
{
    static double funcion(double x) 
    {
        return Math.log(x*x*x + 2) * Math.tan(x);
    }
    static double metodoFalsaPosicion(double a, double b, double tolerancia, int maxIteraciones) 
    {
        int iteracion = 1;
        while (iteracion <= maxIteraciones) 
        {
            // método de la falsa posición
            double fa = funcion(a);
            double fb = funcion(b);
            double c = (a * fb - b * fa) / (fb - fa);
            
            // Mostrar información de la iteración
            System.out.println("Iteración " + iteracion + ": a=" + a + ", b=" + b + ", c=" + c);

            if (Math.abs(funcion(c)) < tolerancia) 
            {
                System.out.println("Solución encontrada en la iteración " + iteracion + ": " + c);
                return c;
            }
            // Actualizar los extremos del intervalo
            if (funcion(c) * fa < 0) 
            {
                b = c;
            } 
            else 
            {
                a = c;
            }

            iteracion++;
        }

        System.out.println("El método no convergió después de " + maxIteraciones + " iteraciones.");
        return Double.NaN; 
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el extremo izquierdo del intervalo (a): ");
        double a = scanner.nextDouble();

        System.out.print("Ingrese el extremo derecho del intervalo (b): ");
        double b = scanner.nextDouble();

        System.out.print("Ingrese la tolerancia: ");
        double tolerancia = scanner.nextDouble();

        System.out.print("Ingrese el número máximo de iteraciones: ");
        int maxIteraciones = scanner.nextInt();

        scanner.close();

        double solucion = metodoFalsaPosicion(a, b, tolerancia, maxIteraciones);

        if (!Double.isNaN(solucion)) {
            System.out.println("La solución aproximada es: " + solucion);
        }
    }
}
