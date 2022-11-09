import java.sql.*;
public class tipo extends conexion_de_agenda_telefonica{
    private int id;
    private String tipo;
    protected tipo(){
        id = 0;
        tipo = "";
    }
    protected String insertar(String tipo){
        this.tipo = tipo;
        try{
            conectar();
            String sql = "insert into tipos(tipos) values(?);";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setString(1,tipo);
            query.executeUpdate();
            desconectar();
            return "Insercion Exitosa";
        }catch(Exception e){return "Insercion Fallida";}
    }
    protected String editar(String tipo, String nuevo_tipo){
        this.tipo = tipo;
        try{
            conectar();
            String sql = "update tipos set tipos=? where(tipos=?)";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setString(1,nuevo_tipo);
            query.setString(2,tipo);
            query.executeUpdate();
            desconectar();
            return "Edicion Exitosa";
        }catch(Exception e){return "Edicion Fallida";}
    }
    protected String borrar(int id){
        this.id = id;
        try{
            conectar();
            String sql = "delete from tipos where(id_tipos=?)";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setInt(1,id);
            query.executeUpdate();
            desconectar();
            return "Borrado Exitoso";
        }catch(Exception e){return "Borrado Fallido";}
    }
    protected String[][] mostrar(){
        try{
            conectar();
            Statement st = conector.createStatement();
            ResultSet query = st.executeQuery("select count(id_tipos) from tipos;");
            query.next();
            String[][] tipos = new String[Integer.parseInt(query.getString("count(id_tipos)"))][3];
            query = st.executeQuery("select * from tipos;");
            int i = 0;
            while(query.next()){ 
                tipos[i][0]= query.getString("id_tipos");
                tipos[i][1]= query.getString("tipos.tipos");
		i = i + 1;
            }
            desconectar();
            return tipos;
        }catch(Exception e){return null;}
    }
}