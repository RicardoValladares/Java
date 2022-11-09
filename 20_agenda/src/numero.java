import java.sql.*;
public class numero extends conexion_de_agenda_telefonica{
    private int id;
    private int numero;
    private int id_contacto;
    private int id_tipo;
    protected numero(){
        id = 0;
        numero = 0;
        id_contacto = 0;
        id_tipo = 0;
    }
    protected String insertar(int id_contacto, int id_tipo, int numero){
        this.id_contacto = id_contacto;
        this.id_tipo = id_tipo;
        this.numero = numero;
        try{
            conectar();
            String sql = "insert into numeros(numeros,id_contactos,id_tipos) values(?,?,?);";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setInt(1,numero);
            query.setInt(2,id_contacto);
            query.setInt(3,id_tipo);
            query.executeUpdate();
            desconectar();
            return "Insercion Exitosa";
        }catch(Exception e){return "Insercion Fallida";}
    }
    protected String editar(int id, int numero, int id_tipo){
        this.id = id;
        this.numero = numero;
        try{
            conectar();
            String sql = "update numeros set numeros=?,id_tipos=? where(id_numeros=?)";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setInt(1,numero);
            query.setInt(2,id_tipo);
            query.setInt(3,id);
            query.executeUpdate();
            desconectar();
            return "Edicion Exitosa";
        }catch(Exception e){return "Edicion Fallida";}
    }
    protected String borrar(int numero){
        this.numero = numero;
        try{
            conectar();
            String sql = "delete from numeros where(numeros=?)";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setInt(1,numero);
            query.executeUpdate();
            desconectar();
            return "Borrado Exitoso";
        }catch(Exception e){return "Borrado Fallido";}
    }
    protected String[][] mostrar(int id_contacto){
        this.id_contacto = id_contacto;
        try{
            conectar();
            Statement st = conector.createStatement();
            ResultSet query = st.executeQuery("select count(numeros.id_numeros) from numeros inner join contactos on contactos.id_contactos=numeros.id_contactos inner join tipos on tipos.id_tipos=numeros.id_tipos where contactos.id_contactos="+id_contacto+";");
            query.next();
            String[][] telefonos = new String[Integer.parseInt(query.getString("count(numeros.id_numeros)"))][3];
            query = st.executeQuery("select numeros.id_numeros, numeros.numeros, tipos.tipos from numeros inner join contactos on contactos.id_contactos=numeros.id_contactos inner join tipos on tipos.id_tipos=numeros.id_tipos where contactos.id_contactos="+id_contacto+";");
            int i = 0;
            while(query.next()){ 
                telefonos[i][0]= query.getString("numeros.id_numeros");
                telefonos[i][1]= query.getString("numeros.numeros");
                telefonos[i][2]= query.getString("tipos.tipos");
		i = i + 1;
            }
            desconectar();
            return telefonos;
        }catch(Exception e){return null;}
    }
}
