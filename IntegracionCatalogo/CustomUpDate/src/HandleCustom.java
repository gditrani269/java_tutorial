import java.io.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//"C:\Program Files (x86)\Java\jre1.8.0_171\bin\java" -jar C:\Users\l0646482\n\mi_desa\Eclipse\CustomUpDate\CustomUpDate.jar

public class HandleCustom {
    @SuppressWarnings("null")
	public static void main(String[] args) {
//    	System.out.println("hola");
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String sCustom = null;
        String sOperacion = null;
        String sURI = null;

        Integer iCountOperacion = 0;
        for (String s: args) {
        }
        if (args.length > 2) {
	        sCustom = args [0];
	        sOperacion = "/"+args [1]+"<";
	        sURI = args [2];
        } else {
        	System.exit(9);
        }
        String sTmpCaracteresEspeciales = null;
        //sTmpCaracteresEspeciales = sURI.replaceAll("&","&amp;");
        
        //manejo de la cadena: "&amp%3b y &amp%3B", convierte las dos cadenas a "&"
        sTmpCaracteresEspeciales = sURI.replaceAll("&amp%3b","&");
        sTmpCaracteresEspeciales = sURI.replaceAll("&amp%3B","&");
        
      //manejo de la cadena: "&amp;", convierte la cadenaas a "&"
        sURI = sTmpCaracteresEspeciales.replaceAll("&amp;","&");
        
      //manejo de la cadena: "&", convierte la cadenaas a "&amp;"
        sTmpCaracteresEspeciales = sURI.replaceAll("&","&amp;");
        
      //manejo de la cadena: " ", convierte la cadenaas a "%20"
        sURI = sTmpCaracteresEspeciales.replaceAll(" ","%20");
       
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File (sCustom);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           String nombrecambiado;
           String sUriOriginal = null;
           boolean bReemplazar = false;

           while((linea=br.readLine())!=null) {
        	   if (iCountOperacion == 3 && bReemplazar != true) {
            	   nombrecambiado=linea;
            	   Integer iTagClose = nombrecambiado.indexOf('>');
        		   Integer iTagOpen = nombrecambiado.indexOf('<',iTagClose);
//                   System.out.println(nombrecambiado.substring(iTagClose+1, iTagOpen));
                   sUriOriginal = nombrecambiado.substring(iTagClose+1, iTagOpen);
                   bReemplazar = true;
        	   }
        	   if (iCountOperacion==2) iCountOperacion++;
        	   if (linea.indexOf(sOperacion)!=-1) iCountOperacion++;
           }
           if( null != fr ){   
               fr.close();     
            } 
 
           br.close();
         
           if (bReemplazar==true) {
//        	   System.out.println ("Si encontro la operacion");
	           File archivo2 = null;
	           FileReader fr2 = null;
	           BufferedReader br2 = null;
	           archivo2 = new File (sCustom);
	           fr2 = new FileReader (archivo2);
	           br2 = new BufferedReader(fr2);
	 
	           FileWriter fichero = null;
	           PrintWriter pw = null;
	           fichero = new FileWriter(sCustom+".bck");
	           pw = new PrintWriter(fichero);
	           
	           String sUriNew = null;
	           String sLinea2 = null;
	           int iCont = 0;
               while((sLinea2=br2.readLine())!=null) {
            	   iCont++;
            	   if (sLinea2.indexOf(">"+sUriOriginal+"<") != -1) {
//            		   System.out.println("Encontro la URI: "+sLinea2);
//            		   System.out.println (sLinea2.replaceAll(sUriOriginal,sURI));
//                     System.out.println(nombrecambiado.substring(iTagClose+1, iTagOpen));
//            		   System.out.println("URI ORIGINAL: "+sUriOriginal+"\nURI Nueva: "+sURI);
//            		   System.out.println("reemplazados: "+sLinea2.replaceAll(sUriOriginal,sURI));
//            		   pw.println(sLinea2.replaceAll(sUriOriginal,sURI));

                	   Integer iTagClose = sLinea2.indexOf('>');
            		   Integer iTagOpen = sLinea2.indexOf('<',iTagClose);
//                       System.out.println(nombrecambiado.substring(iTagClose+1, iTagOpen));
//            		   System.out.println(sLinea2.substring(0,iTagClose+1)+sURI+sLinea2.substring(iTagOpen));
            		   pw.println(sLinea2.substring(0,iTagClose+1)+sURI+sLinea2.substring(iTagOpen));

            		   
            		   
            		   pw.flush();
            	   } else {
            		   pw.println(sLinea2);
            		   pw.flush();
            	   }
                }
           } else {//si entra al else es porque: bReemplazar = false
//        	   System.out.println ("No encontro la operacion");
        	   System.exit(5);
           }
        }
        catch(Exception e){
           e.printStackTrace();
           System.out.println ("Problema con algun archivo");
           System.exit(1);
           
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
              System.exit(2);
           }
        }
        System.exit(0);
    }
    
}
