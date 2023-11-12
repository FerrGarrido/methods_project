import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaMenu extends JFrame implements ActionListener {

    private JButton botonSumar, botonMultiplicar; //botones del menú
    private VentanaOperacion ventanaOperacion; //ventana para realizar la operación

    public VentanaMenu() {
        super("Ventana con menú"); //título de la ventana
        setSize(300, 200); //tamaño de la ventana
        setLocationRelativeTo(null); //centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar el programa al cerrar la ventana
        setLayout(new GridLayout(2, 1)); //usar un layout de rejilla de 2 filas y 1 columna

        //crear los botones del menú
        botonSumar = new JButton("Sumar");
        botonMultiplicar = new JButton("Multiplicar");

        //agregar los botones a la ventana
        add(botonSumar);
        add(botonMultiplicar);

        //agregar un escuchador de acción a cada botón
        botonSumar.addActionListener(this);
        botonMultiplicar.addActionListener(this);
    }

    //método que se ejecuta al pulsar un botón
    @Override
    public void actionPerformed(ActionEvent e) {
        //obtener el botón que ha generado el evento
        JButton boton = (JButton) e.getSource();
        //obtener el texto del botón
        String texto = boton.getText();
        //crear una nueva ventana de operación con el texto del botón
        ventanaOperacion = new VentanaOperacion(texto, this);
        //mostrar la ventana de operación
        ventanaOperacion.setVisible(true);
        //ocultar la ventana del menú
        this.setVisible(false);
    }

    //método principal para ejecutar el programa
    public static void main(String[] args) {
        //crear una instancia de la clase VentanaMenu y mostrarla
        VentanaMenu vm = new VentanaMenu();
        vm.setVisible(true);
    }
}

class VentanaOperacion extends JFrame implements ActionListener {

    private JTextField campo1, campo2, campo3; //campos de texto para los operandos y el resultado
    private JButton botonOperar, botonVolver; //botones para realizar la operación y volver al menú
    private String operacion; //operación a realizar
    private VentanaMenu ventanaMenu; //ventana del menú principal

    public VentanaOperacion(String operacion, VentanaMenu ventanaMenu) {
        super("Ventana de " + operacion); //título de la ventana
        setSize(300, 200); //tamaño de la ventana
        setLocationRelativeTo(null); //centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar el programa al cerrar la ventana
        setLayout(new GridLayout(5, 1)); //usar un layout de rejilla de 5 filas y 1 columna

        //asignar el valor de los parámetros a los atributos
        this.operacion = operacion;
        this.ventanaMenu = ventanaMenu;

        //crear los campos de texto y los botones
        campo1 = new JTextField();
        campo2 = new JTextField();
        campo3 = new JTextField();
        campo3.setEditable(false); //el tercer campo no se puede editar
        botonOperar = new JButton(operacion);
        botonVolver = new JButton("Volver al menú");

        //agregar los componentes a la ventana
        add(campo1);
        add(campo2);
        add(campo3);
        add(botonOperar);
        add(botonVolver);

        //agregar un escuchador de acción a cada botón
        botonOperar.addActionListener(this);
        botonVolver.addActionListener(this);
    }

    //método que se ejecuta al pulsar un botón
    @Override
    public void actionPerformed(ActionEvent e) {
        //obtener el botón que ha generado el evento
        JButton boton = (JButton) e.getSource();
        //obtener el texto del botón
        String texto = boton.getText();
        //si el texto es igual al de la operación
        if (texto.equals(operacion)) {
            try {
                //obtener los números de los campos de texto y convertirlos a enteros
                int num1 = Integer.parseInt(campo1.getText());
                int num2 = Integer.parseInt(campo2.getText());
                int resultado = 0; //variable para almacenar el resultado
                //si la operación es sumar
                if (operacion.equals("Sumar")) {
                    //calcular la suma de los números
                    resultado = num1 + num2;
                }
                //si la operación es multiplicar
                else if (operacion.equals("Multiplicar")) {
                    //calcular el producto de los números
                    resultado = num1 * num2;
                }
                //mostrar el resultado en el tercer campo de texto
                campo3.setText(String.valueOf(resultado));
            } catch (NumberFormatException ex) {
                //si hay algún error al convertir los números, mostrar un mensaje de error
                JOptionPane.showMessageDialog(this, "Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //si el texto es volver al menú
        else if (texto.equals("Volver al menú")) {
            //mostrar la ventana del menú
            ventanaMenu.setVisible(true);
            //ocultar la ventana de operación
            this.setVisible(false);
        }
    }
}
