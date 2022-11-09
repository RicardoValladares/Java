public class clase {
    //declaramos variables
    Double n1;
    Double n2;
    Double suma;
    public clase(){
	//inicializamos variables
	this.n1 = 0.0;
	this.n2 = 0.0;
	this.suma = 0.0;
    }
    //metodos set para modificar datos
    public void setsumar(Double n1, Double n2){
	this.n1 = n1; 
    	this.n2 = n2; 
    }
    public void setn1(Double n1){ 
    	this.n1 = n1; 
    }
    public void setn2(Double n2){ 
    	this.n2 = n2; 
    }
    //metodos get para retornar datos
    public String getsumar(){
	return this.n1 + "+" + this.n2 + "=" + (this.suma=this.n1+this.n2);
    }
    public Double getn1(){
	return this.n1;
    }
    public Double getn2(){
	return this.n2;
    }
    public Double getsuma(){
	return this.suma = this.n1 + this.n2;
    }
}