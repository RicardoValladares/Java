import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class formulario extends javax.swing.JFrame {
    DefaultTableModel modelo;
    public formulario() throws Exception {
        initComponents();
        conexion_WEB("","");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        insertar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("conexion WEB");
        setResizable(false);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido:");

        insertar.setText("insertar");
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });

        actualizar.setText("actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertar)
                    .addComponent(actualizar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
        try{
            String nombre = this.nombre.getText();
            String apellido = this.apellido.getText();
            conexion_WEB(nombre, apellido);
            this.nombre.setText(String.valueOf(""));
            this.apellido.setText(String.valueOf(""));
        }catch(Exception ex){JOptionPane.showMessageDialog(this,"Error de Conexion","ERROR",2);}
    }//GEN-LAST:event_insertarActionPerformed
    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        try{conexion_WEB("", "");
        }catch(Exception ex){JOptionPane.showMessageDialog(this,"Error de Conexion","ERROR",2);}
    }//GEN-LAST:event_actualizarActionPerformed
    private void conexion_WEB(String nombre, String apellido) throws Exception{
        /*inicializamos la tabla*/
        String[] encabezados = {"id", "nombre", "apellido"};
        modelo = new DefaultTableModel(null,encabezados);
        tabla.setModel(modelo);
        /*creamos url de conexion al servidor web*/
	URL url = new URL("http://127.0.0.1/proyectos/"+"insertarymostrar.php");
	HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
	conexion.setDoInput(true); //conexion de entrada
	conexion.setDoOutput(true); //conexion de salida
	conexion.setUseCaches(false); //conexion sin usar cache
	conexion.setRequestMethod("POST"); //enviar datos via POST
	conexion.connect(); //ejecutamos la conexion
	/*creamos objeto de cadena de salida*/
	DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
	/*cadena de envio de datos*/
	String cadena_de_datos = "nombre=" + URLEncoder.encode(nombre) + "&apellido=" + URLEncoder.encode(apellido);
	/*enviamos datos*/
	salida.writeBytes(cadena_de_datos);
	salida.flush();
	salida.close();
	/*creamos objeto de cadena de entrada*/
	DataInputStream entrada = new DataInputStream(conexion.getInputStream());
	String linea = null; //halamos linea por linea
	while(null != (linea = entrada.readLine())) {
            String datos[] = linea.split("-"); //separamos los datos en un vector 
            modelo.addRow(datos); //agregamos el vector a la tabla modelo
        }
        entrada.close();
        tabla.setModel(modelo); //agregamos los datos a la tabla
    }        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JTextField apellido;
    private javax.swing.JButton insertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
