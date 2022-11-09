import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class interfaz extends javax.swing.JFrame {
    /*inicializamos tabla*/
    DefaultTableModel modelo;
    clase_de_conexion database;
    public interfaz() {
        initComponents();
        database = new clase_de_conexion();
        cargar_tabla();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nuevo_nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nuevo_apellido = new javax.swing.JTextField();
        inserta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        busca = new javax.swing.JButton();
        borra = new javax.swing.JButton();
        edita = new javax.swing.JButton();
        actualiza = new javax.swing.JButton();
        restaurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MYSQL");
        setResizable(false);

        jLabel1.setText("Nombres:");

        jLabel2.setText("Apellidos:");

        inserta.setText("Insertar datos");
        inserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertaActionPerformed(evt);
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

        busca.setText("Buscar");
        busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaActionPerformed(evt);
            }
        });

        borra.setText("Borrar");
        borra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borraActionPerformed(evt);
            }
        });

        edita.setText("Editar");
        edita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editaActionPerformed(evt);
            }
        });

        actualiza.setText("Actualizar datos");
        actualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizaActionPerformed(evt);
            }
        });

        restaurar.setText("Recuperar datos borrados");
        restaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restaurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(busca)
                        .addGap(18, 18, 18)
                        .addComponent(borra)
                        .addGap(18, 18, 18)
                        .addComponent(edita)
                        .addGap(18, 18, 18)
                        .addComponent(restaurar, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(inserta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(nuevo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(nuevo_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(actualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nuevo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevo_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inserta)
                    .addComponent(actualiza))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(busca)
                    .addComponent(borra)
                    .addComponent(edita)
                    .addComponent(restaurar))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*actualizamos tabla*/
    private void actualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizaActionPerformed
        cargar_tabla();
    }//GEN-LAST:event_actualizaActionPerformed
    /*insertamos datos*/
    private void insertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertaActionPerformed
        String retorno = database.insertar(nuevo_nombre.getText(),nuevo_apellido.getText());
        cargar_tabla();
        JOptionPane.showMessageDialog(this,retorno);
        nuevo_nombre.setText(String.valueOf(""));
        nuevo_apellido.setText(String.valueOf(""));
    }//GEN-LAST:event_insertaActionPerformed
    /*busca datos*/
    private void buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaActionPerformed
        String data = JOptionPane.showInputDialog(this,"Ingrese dato a buscar:");
        String[] encabezados = {"id","nombre","apellido"};
        String[][] datos = database.buscar(data);
	modelo = new DefaultTableModel(datos,encabezados);
	tabla.setModel(modelo);
    }//GEN-LAST:event_buscaActionPerformed
    /*borra dato*/
    private void borraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borraActionPerformed
        int id = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),0));
        String retorno = database.borrar(id);
        cargar_tabla();
        JOptionPane.showMessageDialog(this,retorno);
    }//GEN-LAST:event_borraActionPerformed
    /*edita dato*/
    private void editaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editaActionPerformed
        int id = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),0));
        String[] data = database.mostrar(id);
        editor formulario = new editor(data);
        formulario.setLocationRelativeTo(null);
        formulario.setVisible(true);
    }//GEN-LAST:event_editaActionPerformed
    /*restaurar datos*/
    private void restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restaurarActionPerformed
        borrados formulario = new borrados();
        formulario.setLocationRelativeTo(null);
        formulario.setVisible(true);
    }//GEN-LAST:event_restaurarActionPerformed
    /*cargamos tabla*/
    private void cargar_tabla(){
        String[] encabezados = {"id","nombre","apellido"};
        String[][] datos = database.mostrar();
	modelo = new DefaultTableModel(datos,encabezados);
	tabla.setModel(modelo);	
    }
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
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                interfaz objeto = new interfaz();
                objeto.setVisible(true);
                objeto.setLocationRelativeTo(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualiza;
    private javax.swing.JButton borra;
    private javax.swing.JButton busca;
    private javax.swing.JButton edita;
    private javax.swing.JButton inserta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nuevo_apellido;
    private javax.swing.JTextField nuevo_nombre;
    private javax.swing.JButton restaurar;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
