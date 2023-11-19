//ECUACION x^{2}-e^{x}-3x+2=0
public class newton 
{

    // ponemos la funcion
    public static double funcion(double x) 
    {
        return Math.pow(x, 2) - Math.exp(x) - 3 * x + 2;
    }
    //derivada de la funcion
    public static double derivadaFuncion(double x) 
    {
        return 2 * x - Math.exp(x) - 3;
    }
    // aplicamos el metodo
    public static double newton(double x0, int ciclomaximo, double valor) 
    {
        double x = x0;
        int iteracion = 0;

        do 
        {
            double fx = funcion(x);
            double dfx = derivadaFuncion(x);

            // Evitar la división por cero
            if (dfx == 0) 
            {
                System.out.println("Valor de derivada =0, no se puede seguir");
                return Double.NaN;
            }

            // Calcular la siguiente aproximación de la raíz
            double xSiguiente = x - fx / dfx;

            // Verificar la tolerancia
            if (Math.abs(xSiguiente - x) < valor) 
            {
                System.out.println("Convergencia buscada despues de " + iteracion + " iteraciones.");
                return xSiguiente;
            }

            x = xSiguiente;
            iteracion++;

        } 
        while (iteracion < ciclomaximo);

        System.out.println("Ciclos maximos");
        return Double.NaN;
    }

    public static void main(String[] args) 
    {
        double x0 = 1.0; //valor para la aproximacion inicial
        int ciclomaximo = 100;
        double valor = 1e-6;
        //utilizamos el metodo newton
        double raiz = newton(x0, ciclomaximo, valor);
        // Imprimir el resultado
        System.out.println("Raíz: " + raiz);
    }
}
