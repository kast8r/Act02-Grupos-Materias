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


               
    }
    

    /**
     * 
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
        txt_depart = new javax.swing.JTextField();
        btn_grupo = new javax.swing.JButton();
        btn_depart = new javax.swing.JButton();
        txt_grupo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_bdepart = new javax.swing.JButton();
        btn_buscar = new javax.swing.JButton();
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

        txt_depart.setToolTipText("Escriba el departamento que desee buscar...");
        txt_depart.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        txt_depart.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_depart.setName(""); // NOI18N
        txt_depart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_departActionPerformed(evt);
            }
        });

        btn_grupo.setBackground(new java.awt.Color(0, 0, 0));
        btn_grupo.setBorder(null);
        btn_grupo.setOpaque(false);
        btn_grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grupoActionPerformed(evt);
            }
        });

        btn_depart.setBackground(new java.awt.Color(0, 0, 0));
        btn_depart.setBorder(null);
        btn_depart.setOpaque(false);

        txt_grupo.setToolTipText("Escriba el grupo que desee buscar...");
        txt_grupo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        txt_grupo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_grupo.setName(""); // NOI18N
        txt_grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_grupoActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar por grupo");

        jLabel2.setText("Buscar por departamento");

        btn_bdepart.setBackground(new java.awt.Color(0, 0, 0));
        btn_bdepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/icons8_arrow_20px_2.png"))); // NOI18N
        btn_bdepart.setBorder(null);
        btn_bdepart.setOpaque(false);
        btn_bdepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bdepartActionPerformed(evt);
            }
        });

        btn_buscar.setBackground(new java.awt.Color(0, 0, 0));
        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/icons8_arrow_20px_2.png"))); // NOI18N
        btn_buscar.setBorder(null);
        btn_buscar.setOpaque(false);
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_depart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_bdepart)
                                    .addComponent(btn_buscar)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(150, 150, 150)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(172, 172, 172)
                                        .addComponent(jLabel1)))
                                .addGap(135, 135, 135)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_grupo)
                            .addComponent(btn_depart))
                        .addGap(79, 79, 79))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btn_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btn_depart))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_buscar)
                                        .addGap(5, 5, 5)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_depart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_bdepart))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
                .addContainerGap())
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
        dlmCurso.addAll(listaCursos);
    }//GEN-LAST:event_mn_importActionPerformed

    private void mn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_saveActionPerformed
              UtilFBinario utf1= new UtilFBinario();
        List <Curso> cursos= new ArrayList();
        Curso c1 = new Curso();
        
        for (int i = 0; i < dlmCurso.getSize(); i++) {
            c1 = (Curso) dlmCurso.getElementAt(i);
            cursos.add(c1);        
        }
          
          System.out.println(cursos);
          utf1.guardarObjeto(cursos, "./exportcursos.dat");
          System.out.println(cursos.size());
        
        
        
    }//GEN-LAST:event_mn_saveActionPerformed

    private void mn_restoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_restoreActionPerformed
        UtilFBinario utf1= new UtilFBinario();
        List <Curso> cursos = new ArrayList();
        Curso c1 = new Curso();   
        
       cursos=(ArrayList) utf1.leerObjeto("./exportcursos.dat");
        System.out.println(cursos.size());
       
        for (int i = 0; i < cursos.size(); i++) {         
           c1 =  cursos.get(i);
           dlmCurso.addElement(c1);
        
        }      
    }//GEN-LAST:event_mn_restoreActionPerformed

    private void txt_departActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_departActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_departActionPerformed

    private void btn_grupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grupoActionPerformed
              // TODO add your handling code here:
    }//GEN-LAST:event_btn_grupoActionPerformed

    private void txt_grupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_grupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_grupoActionPerformed

    private void btn_bdepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bdepartActionPerformed
      buscarDep();
    }//GEN-LAST:event_btn_bdepartActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
      buscarGrupos();
    }//GEN-LAST:event_btn_buscarActionPerformed

 
    private void buscarGrupos(){
  
         for (int i = 0; i < listaCursos.size(); i++) { 
             Curso c=listaCursos.get(i); 
            for (int j = 0; j < c.getGrupos().size(); j++) {
                    System.out.println(c.getGrupos().get(j));
                    if (c.getGrupos().get(j).toString().equals(txt_grupo.getText())){
                      System.out.println("Coincidencia");
                      dlmCurso.clear();
                    dlmCurso.addElement(listaCursos.get(i));
                    break;                               
                }
            }
         }   
    }
    
    
    private void buscarDep(){
        
       for (int i = 0; i < listaCursos.size(); i++) { 
             Curso c=listaCursos.get(i);
             List <Materia> m = c.getMaterias();
             
             for (int j = 0; j < m.size(); j++) {
                    Materia m1 = m.get(j);
                    System.out.println(m1.getDepartamento());
             
                   if (m1.getDepartamento().equals(txt_depart.getText())){
                    dlmMateria.clear();
                    dlmMateria.addElement(m1.getNombre());
                
             
             }
               
           }
         
       }
    
    
    
    }
    
    
    
    private void refrescarGrupos() {
        dlmGrupo.clear();
        Curso c= (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
        dlmGrupo.addAll(c.getGrupos());
    }
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
    private javax.swing.JButton btn_bdepart;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_depart;
    private javax.swing.JButton btn_grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextField txt_depart;
    private javax.swing.JTextField txt_grupo;
    // End of variables declaration//GEN-END:variables
}
