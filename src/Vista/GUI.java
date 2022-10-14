/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.UtilDOM;
import Controlador.UtilFBinario;
import Modelo.Curso;
import Modelo.Grupo;
import Modelo.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author usumaniana
 */
public class GUI extends javax.swing.JFrame {

    DefaultListModel dlmCurso;
    DefaultListModel dlmGrupo;
    DefaultListModel dlmMateria;
    List<Curso> listaCursos = new ArrayList();
    
    
    public GUI() {
        initComponents();
        dlmCurso = new DefaultListModel();
        dlmGrupo = new DefaultListModel();
        dlmMateria = new DefaultListModel();
        
        lst_cursos.setModel(dlmCurso);
        lst_grupos.setModel(dlmGrupo);
        lst_materias.setModel(dlmMateria);
        añadirCursos(listaCursos,"ExportacionGRUPOS-MATERIAS.xml");
        
        for (int i = 0; i < listaCursos.size(); i++) {
            Curso c = listaCursos.get(i);
            dlmCurso.addElement(c);
        }

               
    }
    

    /**
     * 
     * @param listaGrupos
     * @param ruta 
     */
    private void añadirCursos(List<Curso> listaGrupos, String ruta){
        UtilDOM u = new UtilDOM();
        Document doc = u.xml2dom(ruta);
        doc.normalize();
        Element raiz = doc.getDocumentElement();
        NodeList listas =  raiz.getElementsByTagName("listasal");
        
        List<Node> grupos = new ArrayList();
        List<Node> materias = new ArrayList();
        

        for (int i = 0; i < listas.getLength(); i++) {
            NamedNodeMap nnm = listas.item(i).getAttributes();
            if (nnm.item(0).getNodeValue().contains("MATERIAS_")) {
                
                materias.add(listas.item(i));
            }
        }
        
        for (int i = 0; i < listas.getLength(); i++) {
            NamedNodeMap nnm = listas.item(i).getAttributes();
            if (nnm.item(0).getNodeValue().contains("GRUPOS_")) {
                
                grupos.add(listas.item(i));
            }
        }
        
        for (int i = 0; i < grupos.size(); i++) {
            Node nNode = grupos.get(i);
            Curso c = new Curso();
            boolean otrosDatosAñadidos = false;
            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                
                c.setCodigoCurso(Integer.valueOf(eElement.getElementsByTagName("salida").item(2).getTextContent()));
                c.setNombre(eElement.getElementsByTagName("salida").item(1).getTextContent());
                c.setClave(Integer.valueOf(eElement.getElementsByTagName("salida").item(0).getTextContent()));
                
                
            }
            
            for (int j = 0; j < materias.size(); j++) {
                Node nNodeM = materias.get(j);
                Materia m = new Materia(); 
                int codCurso = 0;
                if(nNodeM.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNodeM;
                    m.setNombre(eElement.getElementsByTagName("salida").item(0).getTextContent());
                    m.setClave(Integer.valueOf(eElement.getElementsByTagName("salida").item(1).getTextContent()));
                    m.setAbreviatura(eElement.getElementsByTagName("salida").item(2).getTextContent());
                    codCurso = Integer.valueOf(eElement.getElementsByTagName("salida").item(3).getTextContent());
                    if (codCurso == c.getCodigoCurso()) {
                        c.addMaterias(m);
                        if (!otrosDatosAñadidos) {
                            c.setDescripcionCurso(eElement.getElementsByTagName("salida").item(4).getTextContent());
                            c.setDepartamento(eElement.getElementsByTagName("salida").item(6).getTextContent());
                            c.setAbreviaturaCurso(eElement.getElementsByTagName("salida").item(5).getTextContent());
                            otrosDatosAñadidos = true;
                        }
                    }
                    
                }
            }
            if (!listaGrupos.isEmpty()) {
                for (int j = 0; j < listaGrupos.size(); j++) {
                    Curso cac = listaGrupos.get(j);
                    if (c.getCodigoCurso() == cac.getCodigoCurso() && !c.getNombre().equals(cac.getNombre())) {
                        Grupo g1 = new Grupo(cac.getNombre());
                        Grupo g2 = new Grupo(c.getNombre());
                        cac.addGrupos(g1);
                        cac.addGrupos(g2);
                        char lastChar = cac.getNombre().charAt(cac.getNombre().length()-1);
                        if (!Character.isDigit(lastChar)) {
                            cac.setNombre(cac.getNombre().substring(0, cac.getNombre().length() - 1));
                        }
                        listaGrupos.set(j, cac);

                        

                    }else if(j == listaGrupos.size()-1){
                        listaGrupos.add(c);
                        break;
                    }
                }
            } else {
                listaGrupos.add(c);
            }
            

          
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst_cursos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst_grupos = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lst_materias = new javax.swing.JList<>();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        mn_import = new javax.swing.JMenuItem();
        mn_save = new javax.swing.JMenuItem();
        mn_restore = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lst_cursos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "CURSOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        lst_cursos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lst_cursos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lst_cursosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lst_cursos);

        lst_grupos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "GRUPOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        lst_grupos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lst_grupos);

        lst_materias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "MATERIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        lst_materias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lst_materias);

        jMenu3.setText("Archivo");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        mn_import.setText("Importar");
        mn_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_importActionPerformed(evt);
            }
        });
        jMenu3.add(mn_import);

        mn_save.setText("Guardar");
        mn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_saveActionPerformed(evt);
            }
        });
        jMenu3.add(mn_save);

        mn_restore.setText("Recuperar");
        mn_restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_restoreActionPerformed(evt);
            }
        });
        jMenu3.add(mn_restore);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lst_cursosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lst_cursosValueChanged
        refrescarMaterias();
        refrescarGrupos();
    }//GEN-LAST:event_lst_cursosValueChanged

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void mn_importActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_importActionPerformed
       dlmCurso.clear();
        
    }//GEN-LAST:event_mn_importActionPerformed

    private void mn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_saveActionPerformed
        UtilFBinario utf1= new UtilFBinario();
        ArrayList <Curso> cursos= new ArrayList();
        Curso c1 = new Curso();
        
        for (int i = 0; i < dlmCurso.getSize(); i++) {
            c1 = (Curso) dlmCurso.getElementAt(i);
            cursos.add(c1);
            System.out.println(c1);
            
        }
         utf1.guardarObjeto(cursos, "./exportcursos.dat");
         System.out.println(cursos);
        
       
        
        
        
        
    }//GEN-LAST:event_mn_saveActionPerformed

    private void mn_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_restoreActionPerformed
        UtilFBinario utf1= new UtilFBinario();
        ArrayList cursos = new ArrayList();
         
            cursos.add(utf1.leerObjeto("./exportcursos.dat"));
            dlmCurso.addElement(cursos);
        
        
    }//GEN-LAST:event_mn_restoreActionPerformed

 
    
    
    
    private void refrescarGrupos() {
        dlmGrupo.clear();
        Curso c= (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
        dlmGrupo.addAll(c.getGrupos());
    }
    private void refrescarMaterias(){
       // dlmMateria.clear();
        //Curso c= (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
        //dlmMateria.addAll(c.getMaterias());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
        

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> lst_cursos;
    private javax.swing.JList<String> lst_grupos;
    private javax.swing.JList<String> lst_materias;
    private javax.swing.JMenuItem mn_import;
    private javax.swing.JMenuItem mn_restore;
    private javax.swing.JMenuItem mn_save;
    // End of variables declaration//GEN-END:variables
}
