import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
public class editor_de_contacto extends javax.swing.JFrame {
    int id;
    Image foto;
    String nombre;
    String[][] numeros;
    DefaultTableModel tabla_de_numeros;
    contacto objeto_contacto;
    numero objeto_numero;
    tipo objeto_tipo;
    public editor_de_contacto(int id) {
        initComponents();
        this.id = id;
        objeto_contacto = new contacto();
        objeto_numero = new numero();
        objeto_tipo = new tipo();
        Object[] data = objeto_contacto.mostrar(id);
        this.foto = (Image) data[1];
        this.nombre = (String) data[2];
        label_foto.setIcon(new ImageIcon(foto));
        textfield_nombre.setText(String.valueOf(nombre));
        cargar_numeros();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_foto = new javax.swing.JLabel();
        editar_foto = new javax.swing.JButton();
        textfield_nombre = new javax.swing.JTextField();
        editar_nombre = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        insertar_numero = new javax.swing.JButton();
        editar_numero = new javax.swing.JButton();
        editar_tipo = new javax.swing.JButton();
        borrar_numero = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor");
        setResizable(false);

        label_foto.setText("FOTO");

        editar_foto.setText("cambiar foto");
        editar_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_fotoActionPerformed(evt);
            }
        });

        textfield_nombre.setEditable(false);

        editar_nombre.setText("cambiar nombre");
        editar_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_nombreActionPerformed(evt);
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

        insertar_numero.setText("agregar numero");
        insertar_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertar_numeroActionPerformed(evt);
            }
        });

        editar_numero.setText("editar numero");
        editar_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_numeroActionPerformed(evt);
            }
        });

        editar_tipo.setText("editar tipo");
        editar_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_tipoActionPerformed(evt);
            }
        });

        borrar_numero.setText("borrar numero");
        borrar_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrar_numeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editar_numero)
                        .addGap(18, 18, 18)
                        .addComponent(editar_tipo)
                        .addGap(18, 18, 18)
                        .addComponent(borrar_numero))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(editar_foto)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textfield_nombre)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(insertar_numero))
                            .addComponent(editar_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editar_foto)
                        .addGap(18, 18, 18)
                        .addComponent(textfield_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editar_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insertar_numero))
                    .addComponent(label_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editar_numero)
                    .addComponent(editar_tipo)
                    .addComponent(borrar_numero))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void editar_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_fotoActionPerformed
        JFileChooser selectordearchivo = new JFileChooser();
	FileNameExtensionFilter filtro = new FileNameExtensionFilter("PNG","png");
	selectordearchivo.setFileFilter(filtro);
	int estado = selectordearchivo.showOpenDialog(this);
	if(estado==JFileChooser.APPROVE_OPTION){
            try{
                File archivo = selectordearchivo.getSelectedFile();
		foto = ImageIO.read(archivo);
                label_foto.setIcon(new ImageIcon(foto));
                objeto_contacto.editar(id, foto, nombre);
            }catch(Exception e){}
        }
    }//GEN-LAST:event_editar_fotoActionPerformed
    private void editar_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_nombreActionPerformed
        nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre: ");
        objeto_contacto.editar(id, foto, nombre);
        textfield_nombre.setText(String.valueOf(nombre));
    }//GEN-LAST:event_editar_nombreActionPerformed
    private void insertar_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertar_numeroActionPerformed
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo numero: "));
        String[][] id_y_tipos = objeto_tipo.mostrar();
        String[] ides = new String[id_y_tipos.length];
        String[] tipos = new String[id_y_tipos.length];
        for(int y=0; y<id_y_tipos.length; y++){ides[y] = id_y_tipos[y][0];tipos[y] = id_y_tipos[y][1];}
        String tipo_de_numero = (String) JOptionPane.showInputDialog(this,"Elija un tipo: ","",JOptionPane.QUESTION_MESSAGE,null,tipos,tipos[0]);
        int id_tipo = 0;
        for(int y=0; y<tipos.length; y++){if(tipos[y].equals(tipo_de_numero)){id_tipo = Integer.parseInt(ides[y]);}}
        objeto_numero.insertar(id, id_tipo, numero);
        cargar_numeros();
    }//GEN-LAST:event_insertar_numeroActionPerformed
    private void editar_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_numeroActionPerformed
        int id_numero = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),0));
        String tipo_de_numero = ""+tabla.getValueAt(tabla.getSelectedRow(),2);
        String[][] id_y_tipos = objeto_tipo.mostrar();
        String[] ides = new String[id_y_tipos.length];
        String[] tipos = new String[id_y_tipos.length];
        for(int y=0; y<id_y_tipos.length; y++){ides[y] = id_y_tipos[y][0];tipos[y] = id_y_tipos[y][1];}
        int id_tipo = 0;
        for(int y=0; y<id_y_tipos.length; y++){if(tipos[y].equals(tipo_de_numero)){id_tipo = Integer.parseInt(ides[y]);}}
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo numero: "));
        objeto_numero.editar(id_numero, numero, id_tipo);
        cargar_numeros();
    }//GEN-LAST:event_editar_numeroActionPerformed
    private void editar_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_tipoActionPerformed
        String[][] id_y_tipos = objeto_tipo.mostrar();
        String[] ides = new String[id_y_tipos.length];
        String[] tipos = new String[id_y_tipos.length];
        for(int y=0; y<id_y_tipos.length; y++){ides[y] = id_y_tipos[y][0];tipos[y] = id_y_tipos[y][1];}
        int id_numero = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),0));
        int numero = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),1));
        String tipo_de_numero = (String) JOptionPane.showInputDialog(this,"Elija un tipo: ","",JOptionPane.QUESTION_MESSAGE,null,tipos,tipos[0]);
        int id_tipo = 0;
        for(int y=0; y<id_y_tipos.length; y++){if(tipos[y].equals(tipo_de_numero)){id_tipo = Integer.parseInt(ides[y]);}}
        objeto_numero.editar(id_numero, numero, id_tipo);
        cargar_numeros();
    }//GEN-LAST:event_editar_tipoActionPerformed
    private void borrar_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrar_numeroActionPerformed
        int numero = Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(),1));
        objeto_numero.borrar(numero);
        cargar_numeros();
    }//GEN-LAST:event_borrar_numeroActionPerformed
    private void cargar_numeros(){
        String[] encabezados = {"id_numeros", "numeros", "tipos"};
        String[][] datos = objeto_numero.mostrar(id);
	tabla_de_numeros = new DefaultTableModel(datos,encabezados);
	tabla.setModel(tabla_de_numeros);	
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar_numero;
    private javax.swing.JButton editar_foto;
    private javax.swing.JButton editar_nombre;
    private javax.swing.JButton editar_numero;
    private javax.swing.JButton editar_tipo;
    private javax.swing.JButton insertar_numero;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_foto;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textfield_nombre;
    // End of variables declaration//GEN-END:variables
}
