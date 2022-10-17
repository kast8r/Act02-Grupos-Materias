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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        addItem = new javax.swing.JMenuItem();
        editItem = new javax.swing.JMenuItem();
        deleteItem = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        addGroup = new javax.swing.JMenuItem();
        editGroup = new javax.swing.JMenuItem();
        removeGroup = new javax.swing.JMenuItem();
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
        btn_exportarBGrupo = new javax.swing.JButton();
        btn_exportarBDepart = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        mn_import = new javax.swing.JMenuItem();
        mn_save = new javax.swing.JMenuItem();
        mn_restore = new javax.swing.JMenuItem();
        mn_exportarXML = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        addItem.setText("Añadir");
        addItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(addItem);

        editItem.setText("Editar");
        editItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(editItem);

        deleteItem.setText("Eliminar");
        deleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deleteItem);

        addGroup.setText("Añadir");
        addGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupActionPerformed(evt);
            }
        });
        jPopupMenu2.add(addGroup);

        editGroup.setText("Editar");
        editGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editGroupActionPerformed(evt);
            }
        });
        jPopupMenu2.add(editGroup);

        removeGroup.setText("Eliminar");
        removeGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGroupActionPerformed(evt);
            }
        });
        jPopupMenu2.add(removeGroup);

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
        lst_grupos.setComponentPopupMenu(jPopupMenu2);
        jScrollPane2.setViewportView(lst_grupos);

        lst_materias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "MATERIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        lst_materias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lst_materias.setToolTipText("Pulsa click derecho para realizar acciones");
        lst_materias.setComponentPopupMenu(jPopupMenu1);
        lst_materias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lst_materiasMouseClicked(evt);
            }
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
        btn_bdepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/icons8_arrow_20px_2.png"))); // NOI18N
        btn_bdepart.setBorder(null);
        btn_bdepart.setOpaque(false);
        btn_bdepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bdepartActionPerformed(evt);
            }
        });

        btn_buscar.setBackground(new java.awt.Color(0, 0, 0));
        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/icons8_arrow_20px_2.png"))); // NOI18N
        btn_buscar.setBorder(null);
        btn_buscar.setOpaque(false);
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        btn_exportarBGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/icons8_xml_file_20px.png"))); // NOI18N
        btn_exportarBGrupo.setBorderPainted(false);
        btn_exportarBGrupo.setContentAreaFilled(false);
        btn_exportarBGrupo.setEnabled(false);
        btn_exportarBGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportarBGrupoActionPerformed(evt);
            }
        });

        btn_exportarBDepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/images/icons8_xml_file_20px.png"))); // NOI18N
        btn_exportarBDepart.setBorderPainted(false);
        btn_exportarBDepart.setContentAreaFilled(false);
        btn_exportarBDepart.setEnabled(false);
        btn_exportarBDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportarBDepartActionPerformed(evt);
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

        mn_exportarXML.setText("Exportar a XML");
        mn_exportarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_exportarXMLActionPerformed(evt);
            }
        });
        jMenu3.add(mn_exportarXML);

        jMenuBar2.add(jMenu3);

        jMenu5.setText("Ventana");

        jMenuItem1.setText("Propiedades");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem1);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_depart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_bdepart)
                                        .addComponent(btn_buscar))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(171, 171, 171)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(150, 150, 150)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_exportarBGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btn_exportarBDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_depart)
                            .addComponent(btn_grupo))
                        .addGap(104, 104, 104))
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(btn_exportarBGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_depart)
                                    .addComponent(btn_exportarBDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txt_grupo))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_bdepart, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_depart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)))
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
           /**
     *
     *  Guarda los datos en un archivo binario
     */   
        
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
/**
     *
     *  Restaura el archivo de datos binarios de la lista.
     */
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

    private void mn_exportarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mn_exportarXMLActionPerformed
  
        exportarCursosAXML("C:\\Users\\Asier\\pene.xml");

    }//GEN-LAST:event_mn_exportarXMLActionPerformed
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
        if (!btn_exportarBDepart.isEnabled()) {
            btn_exportarBDepart.setEnabled(true);
        }
    }//GEN-LAST:event_btn_bdepartActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        buscarGrupos();
        if (!btn_exportarBGrupo.isEnabled()) {
            btn_exportarBGrupo.setEnabled(true);
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void lst_materiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lst_materiasMouseClicked

    }//GEN-LAST:event_lst_materiasMouseClicked

    private void btn_exportarBGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportarBGrupoActionPerformed
        if (dlmCurso.size() > 0) {
            exportarCursosAXML(generarPanelExportarXML());
        } else {
             JOptionPane.showMessageDialog(null, "No hay resultados en la búsqueda filtrada");
        }
    }//GEN-LAST:event_btn_exportarBGrupoActionPerformed

    private void btn_exportarBDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportarBDepartActionPerformed
        if (dlmMateria.size() > 0) {
            exportarMateriasAXML(generarPanelExportarXML());
        } else {
             JOptionPane.showMessageDialog(null, "No hay resultados en la búsqueda filtrada");
        }
    }//GEN-LAST:event_btn_exportarBDepartActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemActionPerformed
      añadirItem();
    }//GEN-LAST:event_addItemActionPerformed

    private void editItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemActionPerformed
    editarItem();
    }//GEN-LAST:event_editItemActionPerformed

    private void deleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteItemActionPerformed
    eliminarItem();        // TODO add your handling code here:
    }//GEN-LAST:event_deleteItemActionPerformed

    private void addGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupActionPerformed
    añadirGrupo();        // TODO add your handling code here:
    }//GEN-LAST:event_addGroupActionPerformed

    private void editGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editGroupActionPerformed
    editarGrupo();        // TODO add your handling code here:
    }//GEN-LAST:event_editGroupActionPerformed

    private void removeGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGroupActionPerformed
    eliminarGrupo();
    }//GEN-LAST:event_removeGroupActionPerformed

    /**
     * Genera un panel para guardar archivos que devuelve una ruta una vez cerrado
     * @return ruta
     */
    private String generarPanelExportarXML() {
        File archivoAGuardar = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Ruta para exportar XML");   

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            archivoAGuardar = fileChooser.getSelectedFile();
        }
        return archivoAGuardar.getAbsolutePath();
    }
 

    /**
     * Exporta las materias del dlm a la ruta especificada
     * @param ruta 
     */
    private void exportarMateriasAXML(String ruta){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Materias");
            doc.appendChild(rootElement);
            for (int i = 0; i < dlmMateria.size(); i++) {
                Materia m = (Materia) dlmMateria.get(i);
                Element elemento1 = doc.createElement("Materia");
                elemento1.setTextContent(m.getNombre());
                rootElement.appendChild(elemento1);
                
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            StreamResult result = new StreamResult(new File(ruta));
            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Exporta los cursos del dlm a la ruta especificada
     * @param ruta 
     */
    private void exportarCursosAXML(String ruta){
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
            
            StreamResult result = new StreamResult(new File(ruta));
            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
     *
     *  Filtra por grupos  
     */
    private void buscarGrupos(){
        
        dlmCurso.removeAllElements();
        for (int i = 0; i < listaCursos.size(); i++) { 
            Curso c=listaCursos.get(i); 
            for (int j = 0; j < c.getGrupos().size(); j++) {
                    if (c.getGrupos().get(j).toString().contains(txt_grupo.getText().toUpperCase())){
                      System.out.println("Coincidencia");

                    dlmCurso.addElement(listaCursos.get(i));
                    break;                               
                }
            }
         }   
    }
    
     /**
     *
     *  Filtra por departamentos
     */
    private void buscarDep(){
        dlmCurso.clear();
        List<Materia> listaMaterias  = new ArrayList();
        for (int i = 0; i < listaCursos.size(); i++) {
            Curso c = listaCursos.get(i);
            listaMaterias.addAll(c.getMaterias());
        }
        
        for (int i = 0; i < listaMaterias.size(); i++) {
            Materia m  = listaMaterias.get(i);
            if (m.getDepartamento().contains(txt_depart.getText())) {
                dlmMateria.addElement(m);

            }
        }
    
    
    
    }
    
    /**
     * Limpia todos los dlms
     */
    private void limpiarListas(){
        lst_cursos.setSelectedIndex(-1);
        lst_grupos.setSelectedIndex(-1);
        lst_materias.setSelectedIndex(-1);
        dlmGrupo.clear();
        dlmMateria.clear();
        dlmCurso.clear();
    }
    
     /**
     *
     *  Añade una materia a la lista
     */
    private void añadirItem(){   
    String materia = JOptionPane.showInputDialog("Escriba aqui la materia a introducir");
    JOptionPane.showMessageDialog(rootPane, "Añadido con exito");   
    
    Curso c = (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
    Materia m1 = new Materia();
    m1.setNombre(materia);
    c.addMaterias(m1);
    dlmMateria.addElement(m1);  
    listaCursos.add(c);
    }
    
    /**
     *
     *  Elimina una materia a la lista
     */
    private void eliminarItem(){
    Curso c = (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
    Materia m1 = (Materia) dlmMateria.getElementAt(lst_materias.getSelectedIndex());
    c.removeMaterias(m1);
    dlmMateria.removeElement(m1);
    }
    
    /**
     *
     *  Edita una materia a la lista
     */
    
    private void editarItem(){
     String materia = JOptionPane.showInputDialog("Escriba el nombre que se usara para sobreescribir la materia");
     JOptionPane.showMessageDialog(rootPane, "Editado con exito");  
     Curso c = (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
     Materia m1 = (Materia) dlmMateria.getElementAt(lst_materias.getSelectedIndex());
     m1.setNombre(materia);
     c.addMaterias(m1);
     
        for (int i = 0; i < listaCursos.size(); i++) {
            listaCursos.set(i, c);
        }
     
 
    }
    
    /**
     *
     *  Añade un grupo a la lista
     */
    private void añadirGrupo(){   
    String grupo = JOptionPane.showInputDialog("Escriba aqui el grupo a introducir");
    JOptionPane.showMessageDialog(rootPane, "Añadido con exito");   
    
    Curso c = (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
    Grupo g = new Grupo();
    g.setNombre(grupo);
    c.addGrupos(g);
    dlmGrupo.addElement(g);  
    listaCursos.add(c);
    }
    
    /**
     *
     *  Elimina un grupo de la lista
     */
    private void eliminarGrupo(){
    Curso c = (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
    Grupo g = (Grupo) dlmGrupo.getElementAt(lst_grupos.getSelectedIndex());
    c.removeGrupos(g);
    dlmGrupo.removeElement(g);
    }
    
    
    /**
     *
     *  Edita un grupo de la lista
     */
    private void editarGrupo(){
       String grupo = JOptionPane.showInputDialog("Escriba el nombre que se usara para sobreescribir el grupo");
     JOptionPane.showMessageDialog(rootPane, "Editado con exito");  
     Curso c = (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
     Grupo g = (Grupo) dlmGrupo.getElementAt(lst_materias.getSelectedIndex());
     g.setNombre(grupo);
     c.addGrupos(g);
     
        for (int i = 0; i < listaCursos.size(); i++) {
            listaCursos.set(i, c);
        }
    
    
    }
   
    
    /**
     * Refresca el dlm de grupos una vez que el curso cambie
     */
    private void refrescarGrupos() {
        dlmGrupo.clear();
        if(lst_cursos.getSelectedIndex() >= 0) {
            Curso c= (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
            dlmGrupo.addAll(c.getGrupos()); 
        }

    }
    /**
     * Refresca el dlm de refrescarMaterias una vez que el curso cambie
     */
    private void refrescarMaterias(){
        dlmMateria.clear();
        if(lst_cursos.getSelectedIndex() >= 0) {
            Curso c= (Curso) dlmCurso.getElementAt(lst_cursos.getSelectedIndex());
            dlmMateria.addAll(c.getMaterias());   
        }
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
    private javax.swing.JMenuItem addGroup;
    private javax.swing.JMenuItem addItem;
    private javax.swing.JButton btn_bdepart;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_depart;
    private javax.swing.JButton btn_exportarBDepart;
    private javax.swing.JButton btn_exportarBGrupo;
    private javax.swing.JButton btn_grupo;
    private javax.swing.JMenuItem deleteItem;
    private javax.swing.JMenuItem editGroup;
    private javax.swing.JMenuItem editItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
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
    private javax.swing.JMenuItem removeGroup;
    private javax.swing.JTextField txt_depart;
    private javax.swing.JTextField txt_grupo;
    // End of variables declaration//GEN-END:variables
}
