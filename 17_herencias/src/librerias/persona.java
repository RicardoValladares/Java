package librerias; //carpeta en la que empacketa las classes como librerias
public class persona {
    /*atributos privados*/
    private String nombre;
    private String apellidos;
    private int edad;
    /*constructor de la classe persona con sus atributos*/
    public persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;                   
    }
    /*metodos para editar datos*/
    public void setNombre(String nombre){ 
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos){ 
        this.apellidos = apellidos;
    }
    public void setEdad(int edad){ 
        this.edad = edad;          
    }
    /*metodos para retornar datos*/
    public String getNombre(){ 
        return nombre;  
    }
    public String getApellidos(){ 
        return apellidos;  
    }
    public int getEdad(){ 
        return edad;   
    }
    /*metodo para retornar los atributos de la classe*/
    public String getPersona(){ 
        return "Nombre: " + getNombre() + " " +  getApellidos() + "\nEdad: " + getEdad() ;
    }
}
