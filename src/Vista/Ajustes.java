/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import Controlador.UtilDOM;
import java.awt.Button;
import java.awt.Color;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author kastor
 */
public class Ajustes extends javax.swing.JDialog {

    /**
     * Creates new form Ajustes2
     */
    GUI padre ;
    public Ajustes(java.awt.Frame parent, boolean modal) {
       
        super(parent, modal);
         padre = (GUI)parent;
        initComponents();
        leerAjustes();
    }
    
    /**
     * Recupera los ajustes del xml y se los asigna a los botones de la ventana
     */
    public void leerAjustes(){
                UtilDOM u = new UtilDOM();
                Document doc = u.xml2dom("ajustes.xml");
                doc.normalize();
                Element raiz = doc.getDocumentElement();
                NodeList nl =  raiz.getElementsByTagName("colorVentana");
                String[] rgb = nl.item(0).getTextContent().split(",");
                Color c = new Color(Integer.valueOf(rgb[0]),Integer.valueOf(rgb[1]),Integer.valueOf(rgb[2]));
                btn_cVentana.setBackground(c); 
                
                NodeList nl2 =  raiz.getElementsByTagName("colorBarraSuperior");
                String[] rgb2 = nl2.item(0).getTextContent().split(",");
                Color c2 = new Color(Integer.valueOf(rgb2[0]),Integer.valueOf(rgb2[1]),Integer.valueOf(rgb2[2]));
                btn_cBarra.setBackground(c2); 

                NodeList nl3 =  raiz.getElementsByTagName("colorPaneles");
                String[] rgb3 = nl3.item(0).getTextContent().split(",");
                Color c3 = new Color(Integer.valueOf(rgb3[0]),Integer.valueOf(rgb3[1]),Integer.valueOf(rgb3[2]));
                btn_cPaneles.setBackground(c3); 

                NodeList nl4 =  raiz.getElementsByTagName("rutaPorDefecto");
                jtf_ruta.setText( nl4.item(0).getTextContent());
    }
    
    
    /**
     * Establece los nuevos colores y rutas extrayendo la información de los componentes de la ventana
     */
    public void establecerNuevosAjustes(){
        try{

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    Document doc = docBuilder.newDocument();
                    Element rootElement = doc.createElement("Ajustes");
                    doc.appendChild(rootElement);

                    Element elemento1 = doc.createElement("colorVentana");
                    elemento1.setTextContent(btn_cVentana.getBackground().getRed() + "," + btn_cVentana.getBackground().getGreen()+ "," + btn_cVentana.getBackground().getBlue());
                    rootElement.appendChild(elemento1);

                    Element elemento2 = doc.createElement("colorBarraSuperior");
                    elemento2.setTextContent(btn_cBarra.getBackground().getRed() + "," + btn_cBarra.getBackground().getGreen()+ "," + btn_cBarra.getBackground().getBlue());
                    rootElement.appendChild(elemento2);

                    Element elemento3 = doc.createElement("colorPaneles");
                    elemento3.setTextContent(btn_cPaneles.getBackground().getRed() + "," + btn_cPaneles.getBackground().getGreen()+ "," + btn_cPaneles.getBackground().getBlue());
                    rootElement.appendChild(elemento3);

                    Element elemento4 = doc.createElement("rutaPorDefecto");
                    elemento4.setTextContent(jtf_ruta.getText());
                    rootElement.appendChild(elemento4);

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);

                    StreamResult result = new StreamResult(new File("ajustes.xml"));
                        transformer.transform(source, result);
                    } catch (TransformerConfigurationException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (TransformerException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParserConfigurationException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_cBarra = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btn_cPaneles = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtf_ruta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_cVentana = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Color de ventana: ");

        btn_cBarra.setBorderPainted(false);
        btn_cBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cBarraActionPerformed(evt);
            }
        });

        jLabel2.setText("Color de los paneles: ");

        btn_cPaneles.setBorderPainted(false);
        btn_cPaneles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cPanelesActionPerformed(evt);
            }
        });

        jLabel3.setText("Color de la barra superior: ");

        jLabel4.setText("Ruta por defecto al exportar:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/icons8_save_30px.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/icons8_cancel_40px.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_cVentana.setBackground(new java.awt.Color(255, 51, 153));
        btn_cVentana.setBorder(null);
        btn_cVentana.setFocusPainted(false);
        btn_cVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cVentanaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cPaneles, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cPaneles, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtf_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_cVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cVentanaActionPerformed
        eligeUnColor(evt);
        
    }//GEN-LAST:event_btn_cVentanaActionPerformed

    private void btn_cBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cBarraActionPerformed
        eligeUnColor(evt);
    }//GEN-LAST:event_btn_cBarraActionPerformed

    private void btn_cPanelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cPanelesActionPerformed
        eligeUnColor(evt);
    }//GEN-LAST:event_btn_cPanelesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        establecerNuevosAjustes();

        padre.leerAjustes();
        padre.repaint();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Elige un color y asigna ese mismo color al boton que activa este evento
     * @param evt 
     */
    private void eligeUnColor(java.awt.event.ActionEvent evt){
        JColorChooser colorChooser = new JColorChooser();
        Color color = JColorChooser.showDialog(null,"Elije un color" , Color.DARK_GRAY);
        JButton b = (JButton) evt.getSource();
        b.setBackground(color);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ajustes dialog = new Ajustes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cBarra;
    private javax.swing.JButton btn_cPaneles;
    private javax.swing.JButton btn_cVentana;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jtf_ruta;
    // End of variables declaration//GEN-END:variables
}
