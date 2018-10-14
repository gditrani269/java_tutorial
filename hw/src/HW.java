
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.jar.Attributes;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

public class HW {
	
	 /* 
	public static Enumeration<JarEntry> listJarContent (String path ) throws Exception {
 

     // to access a Jar from the local file system
     //   URL url = new URL("jar:file:/c:/temp/mydb.jar!/");
     // to access a Jar from http
     URL url = new URL("jar:file:C:/Users/l0303216/Desktop/AdministracionDatosBiometricos-v1_20171227_1002.jar!/");
     JarURLConnection  conn = (JarURLConnection)url.openConnection();
     JarFile jarfile = conn.getJarFile();

     Enumeration<JarEntry> content = jarfile.entries();

     while (content.hasMoreElements()) {
        System.out.println(content.nextElement());
     }
     
     return content;
     /*
       output:
       META-INF/
       META-INF/MANIFEST.MF
       ExtractFromJAR.class
       mydb.mdb
     /
  }

*/
	
	
	/* Crear Directorios */
	
	static void createDir (String dir, String rootDir){
	
	    File folder = new File(rootDir.toString()+dir.toString()+"/");
	    
	    folder.mkdirs();
	    
	    /*	    
	    
	    // validacion de directorio creado 
	    boolean successful = folder.mkdirs();
	    
	    
	    if (successful)
	    {
	      // directorio creado exitoso
	      System.out.println("directorio creado correctamente" + rootDir.toString()+ dir.toString());
	    }
	    else
	    {
	      // crear directorio fallido
	      System.out.println("fallo al intentar crear directorio puede que ya exista el directorio" + rootDir.toString()+dir.toString());
	    }
	    
	    */
	    
	  }
	
	
	static String before(String value, String a) {
       // Retorna una cadena que contienen todo el string antes de un caracter especifico
       int posA = value.indexOf(a);
       if (posA == -1) {
           return "";
       }
       return value.substring(0, posA);
   }
	
	 static String after(String value, String a) {
	        // Retorna una cadena que contienen todo el string despues de un caracter especifico
	        int posA = value.lastIndexOf(a);
	        if (posA == -1) {
	            return "";
	        }
	        int adjustedPosA = posA + a.length();
	        if (adjustedPosA >= value.length()) {
	            return "";
	        }
	        return value.substring(adjustedPosA);
	    }

  public static void extractContentFromJar(String pathJar, String pathExtractJar) throws Exception {
     InputStream in = null;
     OutputStream out = null;     
     String folder;
     String [] parts;
     int cant;
     //mio
     String fullName;
     //fin mio
     
     
     try {
         	  
   	  URL url = new URL("jar:file:"+ pathJar +"!/");
         JarURLConnection  conn = (JarURLConnection)url.openConnection();
         JarFile jarfile = conn.getJarFile();
         
   	  
         Enumeration<JarEntry> content = jarfile.entries();  
         
         content.nextElement();
         
   	 while (content.hasMoreElements()){    		 
   	    		 
   		     		
   			 URL url2 = new URL("jar:file:"+ pathJar + "!/" + content.nextElement().toString());
   	         JarURLConnection  conn2 = (JarURLConnection)url2.openConnection();
   	         JarFile jarfile2 = conn2.getJarFile();
   	        
   	         JarEntry jarEntry = conn2.getJarEntry();
   	         //mio
Manifest manifest = jarfile.getManifest();
   	    		Attributes At = jarEntry.getAttributes();
   	         //fin mio
  	         in = new BufferedInputStream(jarfile2.getInputStream(jarEntry));
   	         
   	         /* Si se quiere obtener el solo el nombre del objeto sin la ruta absoluta
   	         System.out.println(url2.toString().substring(url2.toString().lastIndexOf("/")+1));
   	         */
   	         
   	         // System.out.println(after(url2.toString(), "!/"));
   	         
   	         folder = after(url2.toString(), ("!/"));
   	         parts = folder.split("\\/");
   	         
   	         /*Se limpia variable folder para luego armar el directorio de destino final correcto*/
   	         folder = "";
   	         
   	         for (int i = 0; i < parts.length-1; i++) {
   	        	 
   	        	 folder = folder + parts[i]+"/";
					
				}   	         
   	          
   	       
   	         /* Si se quiere extraer solo el objeto y no toda la ruta
   	         out = new BufferedOutputStream(new FileOutputStream(pathExtractJar.toString() + url2.toString().substring(url2.toString().lastIndexOf("/")+1)));
   	         */
   	         
   	         //System.out.println(folder);
   	         
   	         createDir(folder,pathExtractJar);
   	         
   	         out = new BufferedOutputStream(new FileOutputStream(pathExtractJar.toString() + folder + url2.toString().substring(url2.toString().lastIndexOf("/")+1)));
   	         
   	         byte[] buffer = new byte[2048];
   	         for (;;)  {
   		           int nBytes = in.read(buffer);
   		           if (nBytes <= 0) break;
   		           out.write(buffer, 0, nBytes);
   		         	}
   	         if (in != null) {
   	             in.close();
   	          }
   	          if (out != null) {
   	             out.flush();
   	             out.close();
   	          }
           	}
	    	
     }
     
     catch (Exception e) {
   	  System.out.println(e.toString());
	}
     
  }
	public static void main(String ... args) throws Exception {
		   
        
		 
		   //Prueba local desde eclipse 
		   
		 //String pathJar = "C:/Users/l0303216/Desktop/AdministracionDatosBiometricos-v1_20171227_1002.jar";
		 //String pathExtractJar = "C:/Users/l0303216/Desktop/PruebaJar/"; 
		   
		 String pathJar = args[0];
		 String pathExtractJar = args [1];
		 System.out.println("y" + GetSOA (pathJar, pathExtractJar));
		    String[] res = demo();
		    for(String str : res)
		        System.out.println(str);  //Will print the strings in the array that
//	      extractContentFromJar(pathJar, pathExtractJar);     
	           
	   }
	
//-----------------------------------------------------------------------------
	static String GetSOA (String value, String a) {
	       // Retorna una cadena que contienen todo el string antes de un caracter especifico
		System.out.println (value);
		System.out.println (a);
		return ("we are the champions");
	   }

	static Integer iLink (String value, String a) {
	       // Retorna una cadena que contienen todo el string antes de un caracter especifico
	       int posA = value.indexOf(a);
	       if (posA == -1) {
	           return 7;
	       }
	       return 8;
	   }
	
	public static String[] demo() //for the sake of example, I made it static.
	{
	     String[] xs = new String[] {"a","b","c","d"};
	     String[] ret = new String[4];
	     ret[0]=xs[0];
	     ret[1]=xs[1];
	     ret[2]=xs[2];
	     ret[3]=xs[3];
	     return ret;
	 }
//-----------------------------------------------------------------------------

	

}

//"C:\Program Files\Java\jre1.8.0_121\bin\java" -jar C:\Users\14198\n\java\hw\hw.jar