/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act02.grupos.materias;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
    
    public String getValorElemento(Document doc, Element elem, String etiqueta) {
        String result = null;
        
        return result;
    }
    
    public Node buscarElemento(Document doc, String etiqueta, String valor){
        Node result = null;
        
        return result;
    }
    
    public void crearAtributo(String nombreAtr, String valorAtr, Element elem, Document doc){
        
    }
    
    public  Document xml2dom (String nombreArchivoXML){ 
    File filexml;
    Document doc = null;
    DocumentBuilder db;
        try {
            filexml=new File(nombreArchivoXML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();           
            doc = db.parse(filexml);
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    
    
            return doc;
    }
    
    
    public void dom2xml(Document doc, String nombreFicheroXML){
        
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nombreFicheroXML));       
            transformer.transform(source, result);
            
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    
    public String dom2String(Document doc){
        StringWriter sw;
        
    try {
        sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(sw));
   
    } catch (TransformerException ex) {
        throw new RuntimeException("Error converting to String", ex);
    }
    return sw.toString();
}
    
   
   
    public Document string2Dom(String documentoXML) throws SAXException{
        Document doc = null;
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
             doc = builder.parse(new InputSource(new StringReader(documentoXML)));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result =  new StreamResult(new StringWriter());
            transformer.transform(source, result);
            String str1 = result.getWriter().toString();
       
                  
            
           
        } catch (TransformerException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UtilDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
      return doc;
    }
    
}
