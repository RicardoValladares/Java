import javax.swing.*;
public class funciones {
    public static void main(String[] args) {
        int num, resultado;
	String cnum;
	/*pedimos un numero*/
	cnum=JOptionPane.showInputDialog("Ingrese un numero:");
	/*convertimos el texto de entrada en numero entero*/
	num=Integer.parseInt(cnum);
	/*invocamos la funcion enviandole un numero y capturando el retorno en una variable*/
	resultado=cuadrado(num);
	/*mostramos la salida de datos*/
	JOptionPane.showMessageDialog(null,"El cuadrado es: "+resultado);
	System.exit(0);
    }
    /*funcion de tipo entero porque retorna un valor entero*/
    static int cuadrado(int y){
	y=y*y;
	return y;
    }
}
