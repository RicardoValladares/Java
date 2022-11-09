import javax.swing.*; 
import java.io.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;
import java.awt.Image;
public class insercion_de_contacto extends javax.swing.JFrame {
    Image foto;
    String nombre;
    contacto objeto_contacto;
    public insercion_de_contacto() {
        initComponents();
        insertar.setVisible(false);
        objeto_contacto = new contacto();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_foto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textfield_nombre = new javax.swing.JTextField();
        subir_foto = new javax.swing.JButton();
        insertar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("nuevo contacto");
        setResizable(false);

        label_foto.setBackground(new java.awt.Color(255, 255, 255));
        label_foto.setText("135 x 170 pixeles");

        jLabel2.setText("Nombre:");

        subir_foto.setText("subir foto");
        subir_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subir_fotoActionPerformed(evt);
            }
        });

        insertar.setText("agregar");
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(subir_foto, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(textfield_nombre)
                    .addComponent(insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(subir_foto)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(textfield_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(insertar))
                    .addComponent(label_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void subir_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subir_fotoActionPerformed
        JFileChooser selectordearchivo = new JFileChooser();
	FileNameExtensionFilter filtro = new FileNameExtensionFilter("PNG","png");
	selectordearchivo.setFileFilter(filtro);
	int estado = selectordearchivo.showOpenDialog(this);
	if(estado==JFileChooser.APPROVE_OPTION){
            try{
                File archivo = selectordearchivo.getSelectedFile();
		foto = ImageIO.read(archivo);
                label_foto.setIcon(new ImageIcon(foto));
                insertar.setVisible(true);
            }catch(Exception e){}
        }
    }//GEN-LAST:event_subir_fotoActionPerformed
    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
        nombre = textfield_nombre.getText();
        String retorno = objeto_contacto.insertar(foto, nombre);
        JOptionPane.showMessageDialog(this,retorno);
    }//GEN-LAST:event_insertarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel label_foto;
    private javax.swing.JButton subir_foto;
    private javax.swing.JTextField textfield_nombre;
    // End of variables declaration//GEN-END:variables
}
