import java.sql.*;
public class clase_de_conexion {
    /*variables globales*/
    private String base_de_datos;
    private String usuario;
    private String password;
    private Connection conector;
    /*definimos los datos de las variables globales*/
    protected clase_de_conexion(){
        base_de_datos = "usuarios";
        usuario = "root";
        password = "minino";
    }
    /*metodo para conectar*/
    private void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            conector = DriverManager.getConnection("jdbc:mysql://localhost/"+base_de_datos,usuario,password);
            System.out.println("Conexion Exitosa");
        }catch(Exception e){System.out.println("Error de Conexion");}
    }
    /*metodo para desconectar*/
    private void desconectar(){
        try{conector.close();System.out.println("Desconexion Exitosa");;
        }catch(Exception e){System.out.println("Error de Conexion");}
    }
    /*metodo para insertar datos*/
    protected String insertar(String nombre, String apellido){
        try{
            conectar();
            String sql = "insert into datos(nombre,apellido) values(?,?);";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setString(1,nombre);
            query.setString(2,apellido);
            query.executeUpdate();
            desconectar();
            return "Insercion Exitosa";
        }catch(Exception e){return "Insercion Fallida";}
    }
    /*metodo para editar datos*/
    protected String editar(int id, String nombre, String apellido){
        try{
            conectar();
            String sql = "update datos set nombre=?,apellido=? where(id=?)";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setString(1,nombre);
            query.setString(2,apellido);
            query.setInt(3,id);
            query.executeUpdate();
            desconectar();
            return "Edicion Exitosa";
        }catch(Exception e){return "Edicion Fallida";}
    }
    /*metodo para borrar datos*/
    protected String borrar(int id){
        try{
            conectar();
            String sql = "delete from datos where(id=?)";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setInt(1,id);
            query.executeUpdate();
            desconectar();
            return "Borrado Exitoso";
        }catch(Exception e){return "Borrado Fallido";}
    }
    /*metodo para mostrar toda la tabla en una matriz*/
    protected String[][] mostrar(){
        try{
            conectar();
            Statement st = conector.createStatement();
            ResultSet query = st.executeQuery("select count(id) from datos;");
            query.next();
            String[][] tabla = new String[Integer.parseInt(query.getString("count(id)"))][3];
            query = st.executeQuery("select * from datos;");
            int i = 0;
            while(query.next()){ 
                tabla[i][0]= query.getString("id");
                tabla[i][1]= query.getString("nombre");
                tabla[i][2]= query.getString("apellido");
		i = i + 1;
            }
            desconectar();
            return tabla;
        }catch(Exception e){return null;}
    }
    /*metodo para mostrar una fila de datos*/
    protected String[] mostrar(int id){
        try{
            conectar();
            Statement st = conector.createStatement();
            ResultSet query = st.executeQuery("select * from datos where(id='"+id+"');");  
            String[] datos = new String[3];
            query.next();
            datos[0]= query.getString("id");
            datos[1]= query.getString("nombre");
            datos[2]= query.getString("apellido");
            desconectar();
            return datos;
        }catch(Exception e){return null;}
    }
    /*metodo para mostrar todo el resultado de una busqueda en una matriz*/
    protected String[][] buscar(String dato){
        try{
            conectar();
            Statement st = conector.createStatement();
            ResultSet query = st.executeQuery("select count(id) from datos where(nombre LIKE '%"+dato+"%') OR (apellido LIKE '%"+dato+"%') OR (id LIKE '%"+dato+"%');");
            query.next();
            String[][] tabla = new String[Integer.parseInt(query.getString("count(id)"))][3];
            query = st.executeQuery("select * from datos where(nombre LIKE '%"+dato+"%') OR (apellido LIKE '%"+dato+"%') OR (id LIKE '%"+dato+"%');");
            int i = 0;
            while(query.next()){ 
                tabla[i][0]= query.getString("id");
                tabla[i][1]= query.getString("nombre");
                tabla[i][2]= query.getString("apellido");
		i = i + 1;
            }
            desconectar();
            return tabla;
        }catch(Exception e){return null;}
    }
    /*metodo para mostrar todos los datos borrados*/
    protected String[][] borrados(){
        try{
            conectar();
            Statement st = conector.createStatement();
            ResultSet query = st.executeQuery("select count(id) from borrados;");
            query.next();
            String[][] tabla = new String[Integer.parseInt(query.getString("count(id)"))][3];
            query = st.executeQuery("select * from borrados;");
            int i = 0;
            while(query.next()){
                tabla[i][0]= query.getString("id");
                tabla[i][1]= query.getString("nombre");
                tabla[i][2]= query.getString("apellido");
		i = i + 1;
            }
            desconectar();
            return tabla;
        }catch(Exception e){return null;}
    }
    /*metodo para restaurar datos*/
    protected String restaurar(int id){
        try{
            conectar();
            String sql = "call restaurardor(?);";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setInt(1,id);
            query.executeUpdate();
            desconectar();
            return "Dato Recuperado";
        }catch(Exception e){return "Recuperacion Fallida";}
    }
}
