/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

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
    
    /**
     * Devuelve un documento en blanco
     * @return 
     */
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
    

    /**
     * Crea un elemento raiz en un elemento
     * @param doc
     * @param etiquetaRaiz
     * @return 
     */
    public Document crearRaiz(Document doc, String etiquetaRaiz) {
        Element rootElement = doc.createElement(etiquetaRaiz);
        doc.appendChild(rootElement);
        return doc;
    }
        
    /**
     * Devuelve el elemento raiz del documento
     * @param doc
     * @return 
     */
    public Element getRaiz(Document doc) {
        Element result;
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
     * @param atributo
     * @param textoAtr
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
    
    /**
     * Devuleve el valor de un elemento
     * @param doc
     * @param elem
     * @return 
     */
    public String getValorElemento(Document doc, Element elem) {
        String result = null;
        NodeList nl = doc.getElementsByTagName(elem.getTagName());
        result = nl.item(0).getNodeValue();
        return result;
        //TODO
    }
    
    /**
     * Busca un elemento con un cierto valor
     * @param doc
     * @param etiqueta
     * @param valor
     * @return 
     */
    public Node buscarElemento(Document doc, String etiqueta, String valor){
        Node result = null;
        NodeList nl = doc.getElementsByTagName(etiqueta);
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeValue().equals(valor)) {
                result = nl.item(i);
            }
        }
        return result;
    }
    
    /**
     * Crea un atributo dentro de un elemento específico
     * @param nombreAtr
     * @param valorAtr
     * @param elem
     * @param doc 
     */
    public void crearAtributo(String nombreAtr, String valorAtr, Element elem, Document doc){
        Attr attr = doc.createAttribute(nombreAtr);
        attr.setValue(valorAtr);
        elem.setAttributeNode(attr);
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
