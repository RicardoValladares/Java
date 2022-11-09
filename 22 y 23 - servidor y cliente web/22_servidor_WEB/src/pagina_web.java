/*Clase que recive la cadena de datos y las convierte a variables y muestra paginas web*/
public class pagina_web {
    private String[][] variables_y_datos;
    private String[] cadena;
    protected pagina_web(String REQUEST){
        cadena = (((REQUEST.replace("GET /?","")).replace(" HTTP/1.1","")).replace("+"," ")).split("&");
        variables_y_datos = new String[2][cadena.length];
        for(int i=0; i<cadena.length; i++){
            String[] separador = cadena[i].split("=");
            variables_y_datos[0][i] = separador[0];
            variables_y_datos[1][i] = "" + ((((((((((((((separador[1].replace("%0D%0A","\n")).replace("%28","(")).replace("%29",")")).replace("%3D","=")).replace("%3F","?")).replace("%3A",":")).replace("%2C",",")).replace("%2B","+")).replace("%2F","/")).replace("%C3%B1","ñ")).replace("%C3%91","Ñ")).replace("%40","@")).replace("%3B",";")).replace("%C2%A0","&nbsp;")).replace("%A0","&nbsp;");
        }
    }
    protected String GET(String variable){
        for(int i=0; i<cadena.length; i++){
            if(variables_y_datos[0][i].equals(variable)){return variables_y_datos[1][i];}
        }return "";
    }
    protected String index(){
        String html;
        if((GET("paginaweb")).equals("index")){
            html = "<html lang='es'><head><title>Java Server</title><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><link rel='icon' type='image/png' href='http://vignette2.wikia.nocookie.net/java/images/e/ef/Micro_Java_Logo.png'></head>\n";
            html = html + "<body bgColor='#eeeeee' text='black'><h1>Formulario</h1><hr><br>\n";
            html = html + "<form name='formulario' action='http://localhost:9000/' method='GET'>\n<table border='0'>";
            html = html + "<tr><td>Ingrese su nombre: </td><td><input name='nombre' type='text' id='nombre' size='20' value='&nbsp;'></td></tr>\n";
            html = html + "<tr><td>Ingrese su password: </td><td><input name='pass' type='password' id='pass' size='20' value='0000'></td></tr>\n";
            html = html + "<tr><td>Elija su pais: </td><td><select name='pais' width='170' style='width: 170px'><option value='El Salvador' selected>El Salvador</option><option value='Guatemala'>Guatemala</option><option value='Honduras'>Honduras</option><option value='Mexico'>Mexico</option></select></td></tr>\n";
            html = html + "<tr><td>Seleccione su rango de edad: </td><td><input value='Mayor de edad' type='radio' name='edad' checked>Mayor de edad <input value='Menor de edad' type='radio' name='edad'>Menor de edad</td></tr>\n";
            html = html + "<tr><td>Seleccione sus intereses: </td><td><input value='Juegos' type='checkbox' name='intereses1'>Juegos <input value='Programas' type='checkbox' name='intereses2'>Programas</td></tr>\n";
            html = html + "<tr><td>Escriba una breve descripcion personal:</td><td><textarea name='descripcion' cols='30' rows='5' value='&nbsp;'>&#32;</textarea></td></tr>";
            html = html + "<tr><td><input type='reset' value='Limpiar'></td><td><input type='submit' value='Enviar'></td></tr></table></form></body></html>\n";
        }
        else{
            html = "<html lang='es'>\n";
            html = html + "<head><title>Java Server</title>\n";
            html = html + "<link rel='icon' type='image/png' href='http://vignette2.wikia.nocookie.net/java/images/e/ef/Micro_Java_Logo.png'>\n";
            html = html + "</head>\n";
            html = html + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n";
            html = html + "<body bgColor='#eeeeee' text='black'><center><br><br>\n";
            html = html + "<table border='2px' bordercolor='#6382bf'>";
            html = html + "<tr><td>Nombre:</td><td>"+GET("nombre")+"</td></tr>";
            html = html + "<tr><td>Password:</td><td>"+GET("pass")+"</td></tr>";
            html = html + "<tr><td>Pais:</td><td>"+GET("pais")+"</td></tr>";
            html = html + "<tr><td>Rango de edad:</td><td>"+GET("edad")+"</td></tr>";
            html = html + "<tr><td>Intereses:</td><td>"+GET("intereses1")+" "+GET("intereses2")+"</td></tr>";
            html = html + "<tr><td>Breve Descripcion:</td><td>"+GET("descripcion")+"</td></tr>";
            html = html + "</table></center><br><a href='http://localhost:9000/?paginaweb=index'>Regresar atras</a></body></html>";
        }
        return html;
    }
}