/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act02.grupos.materias;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usumaniana
 */
public class UtilFTexto {
    
    
    public void escribirTxt(String texto, String fichero){
    FileWriter fw;
        try {
            fw = new FileWriter(fichero);
            fw.write(texto);
            fw.close();
             
        } catch (IOException ex) {
            Logger.getLogger(UtilFTexto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
     
    public String leerTxt(String fichero){
        String result="";
        String linea=null;      
        FileReader fr=null;
        BufferedReader br=null;
        
             try {
                 fr=new FileReader(fichero);
                 br=new BufferedReader(fr);
                 
                while ((linea=br.readLine()) != null){                        
                 result=result+"\n"+linea;
            }
                                
                 
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(UtilFTexto.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
            Logger.getLogger(UtilFTexto.class.getName()).log(Level.SEVERE, null, ex);
             }finally {
                
                 try {
                   br.close();
             
             }catch (IOException ex) {
            Logger.getLogger(UtilFTexto.class.getName()).log(Level.SEVERE, null, ex);
             
             }
             }
        
                
           return result;
        }
            
           
    public String[] leerTxt(String fichero, String separador){
    FileReader fr=null;
    BufferedReader br=null;
    String[] result=null;
    String linea=null;      
    
    
             try {   
                 int cont = 0;
                 fr=new FileReader(fichero);
                 br=new BufferedReader(fr);
                 result = new String[br.readLine().length()+1];
                 
             while ((linea=br.readLine()) != null){      
                 result[cont]=linea+separador;
                 cont++;
            }
            br.close();
            
            } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilFTexto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilFTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    return result;
    }
    
    
    public String [][] FicheroTxt2String(String fichero, String separadorLin, String separadorCar){
   
   
   
     return null; 
   }
    
    
    }

           
    
    
    
    
    
    

