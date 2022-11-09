package padre;
public class padre {
    //reserva un espacio de memoria para esta variable por objetos
    public String variable_multiple;
    //reserva solo un espacio de memoria para esta variable y todos los objetos comparten el mismo espacio de memoria
    public static String variable_unica; 
    //al ser constante es mas eficiente reservar solo un espacio de memoria ya que no variara ni por objeto
    public final static String constante_noeditable = "3.1416";
}
