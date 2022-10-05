/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act02.grupos.materias;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author usumaniana
 */
public class UtilDOM {
    
    public Document crearDOM() {
        Document result = null;
        try {
            
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            result = doc;
            return result;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public Document crearRaiz(Document doc, String etiquetaRaiz) {
        Element rootElement = doc.createElement(etiquetaRaiz);
        doc.appendChild(rootElement);
        return doc;
    }
        
    public Element getRaiz(Document doc) {
        Element result = null;
        result = doc.getDocumentElement();
        return result;
    }
    

    
    /**
     * Crea Elemento sin texto
     * @param padre
     * @param doc 
     */
    public void crearElemento(String etiqueta, Element padre, Document doc){
        Element hijo = doc.createElement(etiqueta);
        NodeList nl = doc.getElementsByTagName(padre.getTagName());
        nl.item(0).appendChild(hijo);
                
    }
    
    
    
    /**
     * Crea un elemento con texto
     * @param etiqueta
     * @param padre
     * @param doc 
     */
    public void crearElemento (String etiqueta, String texto, Element padre, Document doc){
        Element hijo = doc.createElement(etiqueta);
        hijo.setTextContent(texto);
        NodeList nl = doc.getElementsByTagName(padre.getTagName());
        nl.item(0).appendChild(hijo);
    }
    
    
        
    /**
     * Crea un element con texto y con un atributo
     * @param etiqueta
     * @param texto
     * @param padre
     * @param doc 
     */
    public void crearElemento (String etiqueta, String texto, String atributo, String textoAtr, Element padre,Document doc) {
        Element hijo = doc.createElement(etiqueta);
        Attr attr = doc.createAttribute(atributo);
        attr.setValue(textoAtr);
        hijo.setAttributeNode(attr);
        hijo.setTextContent(texto);
        NodeList nl = doc.getElementsByTagName(padre.getTagName());
        nl.item(0).appendChild(hijo);
    }
}
