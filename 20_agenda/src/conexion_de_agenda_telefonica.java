import java.sql.*;
public class conexion_de_agenda_telefonica {
    private String base_de_datos;
    private String usuario;
    private String password;
    protected Connection conector;
    protected conexion_de_agenda_telefonica(){
        base_de_datos = "agenda_telefonica";
        usuario = "root";
        password = "minino";
    }
    protected void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            conector = DriverManager.getConnection("jdbc:mysql://localhost/"+base_de_datos,usuario,password);
            System.out.println("Conexion Exitosa");
        }catch(Exception e){System.out.println("Error de Conexion");}
    }
    protected void desconectar(){
        try{conector.close();System.out.println("Desconexion Exitosa");;
        }catch(Exception e){System.out.println("Error de Conexion");}
    }
    protected String[][] mostrar(String dato){
        try{
            conectar();
            Statement st = conector.createStatement();
            ResultSet query = st.executeQuery("select count(contactos.nombres) from contactos left outer join numeros on contactos.id_contactos=numeros.id_contactos left outer join tipos on tipos.id_tipos=numeros.id_tipos where(contactos.nombres like '%"+dato+"%') or (numeros.numeros like '%"+dato+"%') or  (tipos.tipos like '%"+dato+"%');");
            query.next();
            String[][] tabla = new String[Integer.parseInt(query.getString("count(contactos.nombres)"))][4];
            query = st.executeQuery("select contactos.id_contactos, contactos.nombres, numeros.numeros, tipos.tipos from contactos left outer join numeros on contactos.id_contactos=numeros.id_contactos left outer join tipos on tipos.id_tipos=numeros.id_tipos where(contactos.nombres like '%"+dato+"%') or (numeros.numeros like '%"+dato+"%') or  (tipos.tipos like '%"+dato+"%');");
            int i = 0;
            while(query.next()){ 
                tabla[i][0]= query.getString("contactos.id_contactos");
                tabla[i][1]= query.getString("contactos.nombres");
                tabla[i][2]= query.getString("numeros.numeros");
                tabla[i][3]= query.getString("tipos.tipos");
		i = i + 1;
            }
            desconectar();
            return tabla;
        }catch(Exception e){return null;}
    }
}