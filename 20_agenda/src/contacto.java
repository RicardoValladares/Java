import java.io.*;
import java.sql.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class contacto extends conexion_de_agenda_telefonica{
    private int id;
    private Image foto;
    private String nombre;
    protected contacto(){
        id = 0;
        foto = null;
        nombre = "";
    }
    protected String insertar(Image foto, String nombre){
        this.foto = foto;
        this.nombre = nombre;
        try{
            conectar();
            String sql = "insert into contactos(fotos,nombres) values(?,?);";
            PreparedStatement query = conector.prepareStatement(sql);
            InputStream input = imagetoinput(foto);
            query.setBlob(1,input);
            query.setString(2,nombre);
            query.executeUpdate();
            desconectar();
            return "Insercion Exitosa";
        }catch(Exception e){return "Insercion Fallida";}
    }
    protected String editar(int id, Image foto, String nombre){
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        try{
            conectar();
            String sql = "update contactos set fotos=?,nombres=? where(id_contactos=?);";
            PreparedStatement query = conector.prepareStatement(sql);
            InputStream input = imagetoinput(foto);
            query.setBlob(1,input);
            query.setString(2,nombre);
            query.setInt(3,id);
            query.executeUpdate();
            desconectar();
            return "Edicion Exitosa";
        }catch(Exception e){return "Edicion Fallida";}
    }
    protected String borrar(int id){
        try{
            conectar();
            String sql = "delete from contactos where(id_contactos=?)";
            PreparedStatement query = conector.prepareStatement(sql);
            query.setInt(1,id);
            query.executeUpdate();
            desconectar();
            conectar();
            sql = "delete from numeros where(id_contactos=?)";
            query = conector.prepareStatement(sql);
            query.setInt(1,id);
            query.executeUpdate();
            desconectar();
            return "Borrado Exitoso";
        }catch(Exception e){return "Borrado Fallido";}
    }
    protected Object[] mostrar(int id){
        this.id = id;
        try{
            conectar();
            Statement st = conector.createStatement();
            Object[] datos_de_contacto = new Object[3];
            ResultSet query = st.executeQuery("select * from contactos where id_contactos="+id+";");
            query.next();
            datos_de_contacto[0] = query.getInt("id_contactos");
            Blob blob = query.getBlob("fotos");
            int length = (int) blob.length();
            byte[] bytes = blob.getBytes(1, length);
            BufferedImage bufer = ImageIO.read(new ByteArrayInputStream(bytes));
            datos_de_contacto[1] = (Image) bufer;
            datos_de_contacto[2] = query.getString("nombres");
            desconectar();
            return datos_de_contacto;
        }catch(Exception e){return null;}
    }
    private InputStream imagetoinput(Image foto) throws IOException{
        BufferedImage bufer_foto = new BufferedImage(135, 170, BufferedImage.TYPE_INT_RGB);
        bufer_foto.getGraphics().drawImage(foto, 0, 0 , null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufer_foto, "png", output);
        byte[] byte_image = output.toByteArray();
        InputStream input = new ByteArrayInputStream(byte_image);
        return input;
    }
}
