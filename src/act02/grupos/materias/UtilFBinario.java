/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act02.grupos.materias;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usumaniana
 */
public class UtilFBinario {
    
    public static void guardarObjeto(Object Objeto, String nombreFichero){
    File fichero = new File (nombreFichero);
    FileOutputStream fos = null;
    ObjectOutputStream salida = null;
    
        try {
            fos = new FileOutputStream(fichero);
            salida = new ObjectOutputStream(fos);
            salida.writeObject(Objeto);                       
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilFBinario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilFBinario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        try {
            if (fos != null){
                 fos.close();
         }
         
             if (salida != null){
                salida.close();
         }
        
        
        } catch (IOException ex) {
            Logger.getLogger(UtilFBinario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }
    
    }
    
    public static Object leerObjeto(String nombreFichero){
    File fichero = new File (nombreFichero);
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    Object objeto = null;
    
        try {
            fis= new FileInputStream(fichero);
            ois= new ObjectInputStream(fis);
            objeto = (Object) ois.readObject();
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilFBinario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilFBinario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UtilFBinario.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois!= null) {
                    ois.close();
                }
            } catch (IOException e) {
                     System.out.println(e.getMessage());
            }
        
    
    
    
    }
            return objeto;
        }

}