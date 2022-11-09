public class Privada {
    //protected String nopo; //variable editable solo desde la misma carpeta o packete
    private String variable; //variable editable solo desde la misma classe
    protected Privada(){
        this.variable = variable;
    }
    protected void editar(String variable){ 
        this.variable = variable;
    }
    protected String retornar(){
        return this.variable;
    }
}
