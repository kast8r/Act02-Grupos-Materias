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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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


               
    }
    

    /**
     * Devuelve los grupos de un xml que es determinado por el parámetro ruta
     * @param listaGrupos
     * @param ruta 
     */
    private List getCursosFromXML(String ruta){
        UtilDOM u = new UtilDOM();
        Document doc = u.xml2dom(ruta);
        doc.normalize();
        Element raiz = doc.getDocumentElement();
        NodeList listas =  raiz.getElementsByTagName("listasal");
        
        List<Node> listaNodoGrupos = new ArrayList();
        List<Node> listaNodoMaterias = new ArrayList();
        List<Curso> listaCursos = new ArrayList();
        

        for (int i = 0; i < listas.getLength(); i++) {
            NamedNodeMap nnm = listas.item(i).getAttributes();
            if (nnm.item(0).getNodeValue().contains("MATERIAS_")) {
                
                listaNodoMaterias.add(listas.item(i));
            }
        }
        
        for (int i = 0; i < listas.getLength(); i++) {
            NamedNodeMap nnm = listas.item(i).getAttributes();
            if (nnm.item(0).getNodeValue().contains("GRUPOS_")) {
                
                listaNodoGrupos.add(listas.item(i));
            }
        }

        for (int i = 0; i < listaNodoMaterias.size(); i++) {
            Node nNode = listaNodoMaterias.get(i);
            Materia m = new Materia();
            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                m.setNombre(eElement.getElementsByTagName("salida").item(0).getTextContent());
                m.setClave(Integer.valueOf(eElement.getElementsByTagName("salida").item(1).getTextContent()));
                m.setAbreviatura(eElement.getElementsByTagName("salida").item(2).getTextContent());
                m.setDepartamento(eElement.getElementsByTagName("salida").item(6).getTextContent());
                
                
                int codigoCurso = Integer.valueOf(eElement.getElementsByTagName("salida").item(3).getTextContent());
                if (!listaCursos.isEmpty()) {
                    for (int j = 0; j < listaCursos.size(); j++) {
                        Curso c = listaCursos.get(j);
                        if (c.getCodigoCurso() == codigoCurso) {
                            c.addMaterias(m);
                            listaCursos.set(j, c);
                            break;
                        } else if  (j == listaCursos.size() -1) {
                            Curso c2 = new Curso();
                            c2.setCodigoCurso(Integer.valueOf(eElement.getElementsByTagName("salida").item(3).getTextContent()));
                            c2.setDescripcionCurso(eElement.getElementsByTagName("salida").item(4).getTextContent());
                            c2.setAbreviaturaCurso(eElement.getElementsByTagName("salida").item(5).getTextContent());
                            c2.addMaterias(m);
                            listaCursos.add(c2);
                            break;
                        }
                    } 
                } else {
                    Curso c2 = new Curso();
                    c2.setCodigoCurso(Integer.valueOf(eElement.getElementsByTagName("salida").item(3).getTextContent()));
                    c2.setDescripcionCurso(eElement.getElementsByTagName("salida").item(4).getTextContent());
                    c2.setAbreviaturaCurso(eElement.getElementsByTagName("salida").item(5).getTextContent());
                    c2.addMaterias(m);
                    listaCursos.add(c2);

                }  
            }
        }
        
        
        for (int j = 0; j < listaNodoGrupos.size(); j++) {
            Node nNodeGrupo = listaNodoGrupos.get(j);
            Grupo g = new Grupo();
            if(nNodeGrupo.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNodeGrupo;
                NodeList salida = eElement.getElementsByTagName("salida");
                g.setClave(Integer.valueOf(salida.item(0).getTextContent()));
                g.setNombre(salida.item(1).getTextContent());
                for (int i = 0; i < listaCursos.size(); i++) {
                    Curso c = listaCursos.get(i);
                    if (c.getCodigoCurso() == Integer.valueOf(salida.item(2).getTextContent())) {
                        c.addGrupos(g);
                        listaCursos.set(i, c);
                    }
                }

            
            }
            
        }
        
     
        return listaCursos;

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
        mn_exportarXML = new javax.swing.JMenuItem();
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

        mn_exportarXML.setText("Exportar a XML");
        mn_exportarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_exportarXMLActionPerformed(evt);
            }
        });
        jMenu3.add(mn_exportarXML);

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
        listaCursos = getCursosFromXML("ExportacionGRUPOS-MATERIAS.xml");
        for (int i = 0; i < listaCursos.size(); i++) {
            Curso c = listaCursos.get(i);

            dlmCurso.addElement(c);
        }
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
        
       
        //s
        
        
        
    }//GEN-LAST:event_mn_saveActionPerformed

    private void mn_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_restoreActionPerformed
        UtilFBinario utf1= new UtilFBinario();
        ArrayList cursos = new ArrayList();
         
            cursos.add(utf1.leerObjeto("./exportcursos.dat"));
            dlmCurso.addElement(cursos);
        
        
    }//GEN-LAST:event_mn_restoreActionPerformed

    private void mn_exportarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_exportarXMLActionPerformed

            
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Cursos");
            doc.appendChild(rootElement);
                
            for (int i = 0; i < dlmCurso.size(); i++) {
                
                Curso c = (Curso) dlmCurso.getElementAt(i);
                Element elemento1 = doc.createElement("Curso");
                Attr attr = doc.createAttribute("id");
                attr.setValue(c.getDescripcionCurso());
                elemento1.setAttributeNode(attr);
                rootElement.appendChild(elemento1);
                
                Element elemento2 = doc.createElement("Materias");
                elemento1.appendChild(elemento2);
                if (!c.getMaterias().isEmpty()) {
                    for (int j = 0; j < c.getMaterias().size();j++) {
                    Materia m = c.getMaterias().get(j);
                    Element elemento3 = doc.createElement("Materia");
                    elemento3.setTextContent(m.getNombre());
                    elemento2.appendChild(elemento3);
                }
                }

            }
            
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            StreamResult result = new StreamResult(new File("F:\\2DAM\\AD\\Act02-Grupos-Materias\\export.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_mn_exportarXMLActionPerformed

 
    
    
    /**
     * Refresca el dlm de grupos una vez que el curso cambie
     */
    private void refrescarGrupos() {
        dlmGrupo.clear();
        Curso c= (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
        dlmGrupo.addAll(c.getGrupos());
    }
    /**
     * Refresca el dlm de refrescarMaterias una vez que el curso cambie
     */
    private void refrescarMaterias(){
        dlmMateria.clear();
        Curso c= (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
        dlmMateria.addAll(c.getMaterias());
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
    private javax.swing.JMenuItem mn_exportarXML;
    private javax.swing.JMenuItem mn_import;
    private javax.swing.JMenuItem mn_restore;
    private javax.swing.JMenuItem mn_save;
    // End of variables declaration//GEN-END:variables
}
