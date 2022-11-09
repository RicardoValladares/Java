import javax.swing.*; 
import java.net.*;
import java.io.*;
public class formulario extends javax.swing.JFrame {
    /*variable globales para encender y apagar el servidor*/
    Thread mutiplesconexiones;
    ServerSocket servidor;
    Socket socket;
    int puerto=9000;
    PrintStream salida;
    BufferedReader entrada;
    public formulario(){initComponents();}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        ON_OFF = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor");
        setResizable(false);

        jLabel1.setText("Puerto oyente de la red:");

        jTextField1.setEditable(false);
        jTextField1.setText("9000");

        ON_OFF.setText("Pulsar para encender y apagar");
        ON_OFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ON_OFFActionPerformed(evt);
            }
        });

        textarea.setColumns(20);
        textarea.setRows(5);
        jScrollPane1.setViewportView(textarea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ON_OFF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ON_OFF)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void ON_OFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ON_OFFActionPerformed
        try{
            if(ON_OFF.isSelected()){
                /*iniciamos el servidor*/
		servidor = new ServerSocket(puerto);
		textarea.append("Servidor Encendido \n\n");
		/*construimos un hilo de proceso para atender a multiples clientes*/
		mutiplesconexiones = new Thread("Hilos de procesos"){
                    public void run(){
                        do{
                            try{
                                /*establecemos conexcion y esperamos algun cliente*/
				socket = new Socket();
				socket = servidor.accept();
				/*recibimos datos del cliente*/
				entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String mensajedelcliente = entrada.readLine();
                                salida = new PrintStream(socket.getOutputStream());
                                if(mensajedelcliente.equals("GET /favicon.ico HTTP/1.1")){
                                    /*le enviamos datos al cliente*/
                                    salida.print("Server Java");
                                }
                                else if(mensajedelcliente.equals("GET / HTTP/1.1")){
                                    /*le enviamos datos al cliente*/
                                    pagina_web html = new pagina_web("GET /?paginaweb=index HTTP/1.1");
                                    salida.println("HTTP/1.1 200 OK");
                                    salida.println("Content-Type: text/html");
                                    salida.println("Content-Length: " + (html.index()).length());
                                    salida.println();
                                    salida.print(html.index());
                                }
                                else{
                                    /*le enviamos datos al cliente*/
                                    pagina_web html = new pagina_web(mensajedelcliente);
                                    salida.println("HTTP/1.1 200 OK");
                                    salida.println("Content-Type: text/html");
                                    salida.println("Content-Length: " + (html.index()).length());
                                    salida.println();
                                    salida.print(html.index());
                                }
				/*mostramos los resultados en nuestra aplicacion*/
				textarea.append("Conexion Establecida\nCliente: "+mensajedelcliente+"\nServidor: "+"Responde con pagina web"+"\nConexion Apagada\n\n");
				/*cerramos el Buffer de datos para esperar otro cliente*/
				entrada.close();
				salida.close();
                            }catch(Exception error){}
			}while(true);
                    }
		};
		mutiplesconexiones.start();//ejecutamos hilo de proceso
            }
            else{
                /*detenemos el servidor*/
		mutiplesconexiones.stop();
		servidor.close();
		socket.close();
		textarea.append("Servidor Apagado \n\n");
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(this,"Error de Conexion","ERROR",0);
        }
    }//GEN-LAST:event_ON_OFFActionPerformed
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable(){public void run(){new formulario().setVisible(true);}});}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ON_OFF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea textarea;
    // End of variables declaration//GEN-END:variables
}
