import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class editor_de_tipo extends javax.swing.JFrame {
    int id;
    String tipo;
    String[][] tipos;
    DefaultTableModel tabla_de_tipos;
    tipo objeto_tipo;
    public editor_de_tipo() {
        initComponents();
        objeto_tipo = new tipo();
        cargar_tipos();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        editar_tipo = new javax.swing.JButton();
        agregar_tipo = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("editor de tipos");
        setResizable(false);

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

        jLabel1.setText("Seleccione una fila:");

        editar_tipo.setText("editar");
        editar_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_tipoActionPerformed(evt);
            }
        });

        agregar_tipo.setText("agregar un nuevo tipo de numero");
        agregar_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_tipoActionPerformed(evt);
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
                        .addComponent(agregar_tipo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editar_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agregar_tipo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(editar_tipo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void editar_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_tipoActionPerformed
        tipo = ""+tabla.getValueAt(tabla.getSelectedRow(),1);
        String nuevo_tipo = JOptionPane.showInputDialog("Tipo a reemplazar: " + tabla.getValueAt(tabla.getSelectedRow(),1));
        String retorno = objeto_tipo.editar(tipo, nuevo_tipo);
        JOptionPane.showMessageDialog(this,retorno);
        cargar_tipos();
    }//GEN-LAST:event_editar_tipoActionPerformed
    private void agregar_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_tipoActionPerformed
        String dato = JOptionPane.showInputDialog("Ingrese un nuevo tipo:");
        objeto_tipo.insertar(dato);
        cargar_tipos();
    }//GEN-LAST:event_agregar_tipoActionPerformed
    private void cargar_tipos(){
        String[] encabezados = {"id_tipos","tipos"};
        String[][] datos = objeto_tipo.mostrar();
	tabla_de_tipos = new DefaultTableModel(datos,encabezados);
	tabla.setModel(tabla_de_tipos);	
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_tipo;
    private javax.swing.JButton editar_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
