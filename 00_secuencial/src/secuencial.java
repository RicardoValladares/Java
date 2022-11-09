import javax.swing.JOptionPane; //importamos las classes que necesitaremos
public class secuencial {
    public static void main(String[] args) {
        /*declaramos las variables*/
	String salida;
	int entrada;
	/*halamos la opcion que elija sobre el cuadro de dialogo*/
	entrada = JOptionPane.showConfirmDialog(null,"Elige una opcion:","Titulo",0);
	//entrada = JOptionPane.showConfirmDialog(null,"Elige una opcion:","Titulo",1);
	//entrada = JOptionPane.showConfirmDialog(null,"Elige una opcion:","Titulo",2);
	salida = "Opcion seleccionada: " + entrada;
	/*mostramos datos de salida en un cuadro de dialogo*/
	JOptionPane.showMessageDialog(null,salida,"Titulo",3);
	//JOptionPane.showMessageDialog(null,salida,"Titulo",0);
	//JOptionPane.showMessageDialog(null,salida,"Titulo",1);
	//JOptionPane.showMessageDialog(null,salida,"Titulo",2);
	System.exit(0); //finalizamos la secuencia
    }    
}
