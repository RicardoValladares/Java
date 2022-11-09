import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class agenda extends javax.swing.JFrame {
    DefaultTableModel tabla_de_agenda;
    conexion_de_agenda_telefonica agenda;
    tipo objeto_tipo;
    public agenda() {
        initComponents();
        agenda = new conexion_de_agenda_telefonica();
        objeto_tipo = new tipo();
        cargar_agenda("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actualizar = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        insertar_y_editar_tipos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        insertar_contactos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        mostrar_y_editar_contacto_y_sus_numeros = new javax.swing.JButton();
        borrar_contacto = new javax.swing.JButton();
        borrar_numero = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("agenda telefonia");
        setResizable(false);

        actualizar.setText("actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        buscar.setText("buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        insertar_y_editar_tipos.setText("agregar tipos");
        insertar_y_editar_tipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertar_y_editar_tiposActionPerformed(evt);
            }
        });

        jLabel1.setText("No requieren seleccionar fila de la tabla:");

        insertar_contactos.setText("agregar contactos");
        insertar_contactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertar_contactosActionPerformed(evt);
            }
        });

        jLabel2.setText("Requieren seleccione una fila de la tabla:");

        mostrar_y_editar_contacto_y_sus_numeros.setText("mostrar y editar contactos y sus numeros");
        mostrar_y_editar_contacto_y_sus_numeros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrar_y_editar_contacto_y_sus_numerosActionPerformed(evt);
            }
        });

        borrar_contacto.setText("borrar contacto");
        borrar_contacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrar_contactoActionPerformed(evt);
            }
        });

        borrar_numero.setText("borrar numero");
        borrar_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrar_numeroActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mostrar_y_editar_contacto_y_sus_numeros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(borrar_contacto, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(insertar_y_editar_tipos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(insertar_contactos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(borrar_numero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actualizar)
                    .addComponent(buscar)
                    .addComponent(insertar_y_editar_tipos)
                    .addComponent(insertar_contactos))
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mostrar_y_editar_contacto_y_sus_numeros)
                    .addComponent(borrar_contacto)
                    .addComponent(borrar_numero))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        cargar_agenda("");
    }//GEN-LAST:event_actualizarActionPerformed
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        String dato = JOptionPane.showInputDialog("Ingrese dato a buscar:");
        cargar_agenda(dato);
    }//GEN-LAST:event_buscarActionPerformed
    private void insertar_y_editar_tiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertar_y_editar_tiposActionPerformed
        editor_de_tipo formulario_editar_tipos = new editor_de_tipo();
	formulario_editar_tipos.setLocationRelativeTo(null);
        formulario_editar_tipos.setVisible(true);
    }//GEN-LAST:event_insertar_y_editar_tiposActionPerformed
    private void insertar_contactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertar_contactosActionPerformed
        insercion_de_contacto formulario_insertar_contacto = new insercion_de_contacto();
	formulario_insertar_contacto.setLocationRelativeTo(null);
        formulario_insertar_contacto.setVisible(true);
    }//GEN-LAST:event_insertar_contactosActionPerformed
    private void mostrar_y_editar_contacto_y_sus_numerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrar_y_editar_contacto_y_sus_numerosActionPerformed
        int id = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),0));
        editor_de_contacto formulario_editor = new editor_de_contacto(id);
        formulario_editor.setVisible(true);
    }//GEN-LAST:event_mostrar_y_editar_contacto_y_sus_numerosActionPerformed
    private void borrar_contactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrar_contactoActionPerformed
        int id = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),0));
        contacto objeto_contacto = new contacto();
        objeto_contacto.borrar(id);
        cargar_agenda("");
    }//GEN-LAST:event_borrar_contactoActionPerformed
    private void borrar_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrar_numeroActionPerformed
        int numero = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),2));
        numero objeto_numero = new numero();
        objeto_numero.borrar(numero);
        cargar_agenda("");
    }//GEN-LAST:event_borrar_numeroActionPerformed
    private void cargar_agenda(String dato){
        String[] encabezados = {"id_contactos","nombres","numeros","tipos"};
        String[][] datos = agenda.mostrar(dato);
	tabla_de_agenda = new DefaultTableModel(datos,encabezados);
	tabla.setModel(tabla_de_agenda);	
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
            java.util.logging.Logger.getLogger(agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new agenda().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton borrar_contacto;
    private javax.swing.JButton borrar_numero;
    private javax.swing.JButton buscar;
    private javax.swing.JButton insertar_contactos;
    private javax.swing.JButton insertar_y_editar_tipos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mostrar_y_editar_contacto_y_sus_numeros;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
