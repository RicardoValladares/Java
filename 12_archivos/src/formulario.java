import java.io.*; //libreria para hacer entradas y salidas en archivos
import javax.swing.*; 
import javax.swing.filechooser.*;
public class formulario extends javax.swing.JFrame {
    public formulario() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editor de Texto");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Guardar como");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /*funcion para abrir y leer archivo de texto*/
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
	try{
            String linea = "", caracteres = "";
            JFileChooser selectordearchivo = new JFileChooser(); //creamos visor de archivos
            selectordearchivo.setFileSelectionMode(JFileChooser.FILES_ONLY); //hacemos un filtro para que seleccione solo archivo
            int estado = selectordearchivo.showOpenDialog(this); //mostramos el visor archivos open
            if(estado==JFileChooser.APPROVE_OPTION){ //validamos haya seleccionado un archivo
                File archivo = selectordearchivo.getSelectedFile(); //obtenemos la ruta del archivo
		/*creamos lector del archivo*/
		FileReader lectura = new FileReader(archivo);
		BufferedReader lector = new BufferedReader(lectura);
		/*leemos linea por linea*/
		while((linea=lector.readLine())!=null){
                    caracteres = caracteres + linea + "\n";
                }
                lector.close(); //cerramos el lector
                this.jTextArea1.setText(caracteres); //mostramos el texto plano
            }
	}catch(IOException ex){ //en caso de error al manipular el archivo
            JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo","ADVERTENCIA!!!",2);
	}
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /*funcion para guardar archivo de texto*/
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try{
            JFileChooser selectordearchivo = new JFileChooser(); //creamos visor de archivos
            FileNameExtensionFilter filtro=new FileNameExtensionFilter("*.TXT","txt"); //creamos filtro
            selectordearchivo.setFileFilter(filtro); //implementamos el filtro en el selectordearchivos
            int estado = selectordearchivo.showSaveDialog(this); //mostramos el visor de archivos save
            File archivo = selectordearchivo.getSelectedFile(); //obtenemos la ruta del archivo
            if(estado==JFileChooser.APPROVE_OPTION){ //validamos haya guardado
                /*creamos escritor del archivo en la ruta requerida con extencion txt*/
		FileWriter escritor = new FileWriter(archivo+".txt"); 
		/*escribimos el texto de la textarea en el archivo*/
		escritor.write(this.jTextArea1.getText());
		escritor.close(); //cerramos escritor
		JOptionPane.showMessageDialog(null,"Archivo Guardado");
            }
	}catch(IOException ex){ //en caso de error al manipular el archivo
            JOptionPane.showMessageDialog(null,"ERROR al guardar","ERROR",2);
	}
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formulario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
