import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
public class ventana extends javax.swing.JFrame {
    public ventana(){initComponents();}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        try{
            pagina_web = new javax.swing.JEditorPane("http://localhost:9000");

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            pagina_web.setEditable(false);
            final JEditorPane finalpane = pagina_web;
            pagina_web.addHyperlinkListener(new HyperlinkListener(){public void hyperlinkUpdate(HyperlinkEvent evlink){try{if(evlink.getEventType() == HyperlinkEvent.EventType.ACTIVATED){finalpane.setPage(evlink.getURL());}}catch(Exception e){}}});
            jScrollPane1.setViewportView(pagina_web);
        }

        catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Error de conexion","ERROR",2);
            System.exit(0);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane pagina_web;
    // End of variables declaration//GEN-END:variables
}
