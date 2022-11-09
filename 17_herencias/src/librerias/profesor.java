package librerias; //carpeta en la que empacketa las classes como librerias
public class profesor extends persona { //heredamos de la classe persona sus atributos
    /*atributo no heredado*/
    private String carnet;
    /*constructor de la classe profesor con objeto poliformico por atributos heredados*/
    public profesor(String nombre, String apellidos, int edad, String carnet){
        super(nombre, apellidos, edad);
        this.carnet = carnet;
    }
    /*metodos para editar datos no heredados*/
    public void setCarnet(String carnet){ 
        this.carnet = carnet;   
    }
    /*metodos para retornar datos no heredados*/
    public String getCarnet(){ 
        return carnet;   
    }
    /*metodo para retornar los atributos de la classe profesor*/
    public String getProfesor(){
        return "Nombre: " + getNombre() + " " +  getApellidos() + "\nEdad: " + getEdad() + "\nCarnet del profesor: " + getCarnet();
    }
}