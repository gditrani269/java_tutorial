package VerifInfo.BG;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;




public class verifInfoMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	System.out.println("Iniciando verificacion de datos");
	System.out.println("ID SOA a verificar: " + args [0]);
	
// -----------------------------------
	
    String sUriOriginal = null;
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
	    archivo = new File ("C:\\Users\\14198\\n\\java\\IntegracionCatalogo\\OperacionesCorporativas1711.xml");
	    fr = new FileReader (archivo);
	    br = new BufferedReader(fr);

        // Lectura del fichero
        String linea;
        String nombrecambiado;
//        String sUriOriginal = null;
        boolean bSectionUri = false;

        while((linea=br.readLine())!=null) {
        	if (linea.indexOf("d:Ficha_x0020_Completa m:type")!=-1) bSectionUri=true;
        	if (bSectionUri) {
        		if (linea.indexOf("d:Url")!=-1) {
        			sUriOriginal = linea.replaceAll("<d:Url>", "");
        			sUriOriginal = sUriOriginal.replaceAll("</d:Url>", "");
        			System.out.println ("URL a la ficha de la operacion: \n" + sUriOriginal);
        		}
        	}
        }

    }
    catch(Exception e){
        e.printStackTrace();
        System.out.println ("Problema con algun archivo");
        System.exit(1);
        
     }


 // -----------------------------------	

    {
    	// aca conseguiria el html: Page_operacion.html
    	sUriOriginal = "http://www.google.com/";
	      String results = doHttpUrlConnectionAction(sUriOriginal);
	      System.out.println(results);
//      new verifInfoMain();
    }


// -----------------------------------	
	
    String targetURL = "http://ecc.bancogalicia.com.ar/sites/ac/soa/_api/web/lists/getbytitle('Operaciones%20Corporativas')/items(1711)";
    System.out.println (targetURL);
    try {
    		FileWriter fichero = null;
    		PrintWriter pw = null;
    		fichero = new FileWriter("C:\\Users\\l0646482\\n\\mi_desa\\Eclipse\\cws\\OperacionesCorporativas1711.xml");
    		pw = new PrintWriter(fichero);
    		URL restServiceURL = new URL(targetURL);
    		HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
    		httpConnection.setRequestMethod("GET");
    		httpConnection.setRequestProperty("Accept", "application/json");
    		if (httpConnection.getResponseCode() != 200) {
    			throw new RuntimeException("HTTP GET Request Failed with Error code : "+ httpConnection.getResponseCode());
    		}
    		BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
    		String output;
    		while ((output = responseBuffer.readLine()) != null) {
//              System.out.println(output);
    			pw.println(output);
    		}
    		fichero.close();
    		httpConnection.disconnect();
        } 
        catch (MalformedURLException e) {
        	e.printStackTrace();
        } 
        catch (IOException e) {
        	e.printStackTrace();
        }

    System.out.println ("Analizando XML");
    
	}
	
	
	  public verifInfoMain()
	  {
	    try
	    {
	      String myUrl = "http://www.google.com/";
	      // if your url can contain weird characters you will want to 
	      // encode it here, something like this:
	      // myUrl = URLEncoder.encode(myUrl, "UTF-8");

	      String results = doHttpUrlConnectionAction(myUrl);
	      System.out.println(results);
	    }
	    catch (Exception e)
	    {
	      // deal with the exception in your "controller"
	    }
	  }
	
	  private static String doHttpUrlConnectionAction(String desiredUrl)
			  throws Exception
			  {
			    URL url = null;
			    BufferedReader reader = null;
			    StringBuilder stringBuilder;

			    try
			    {
			      // create the HttpURLConnection
			      url = new URL(desiredUrl);
			      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			      
			      // just want to do an HTTP GET here
			      connection.setRequestMethod("GET");
			      
			      // uncomment this if you want to write output to this url
			      //connection.setDoOutput(true);
			      
			      // give it 15 seconds to respond
			      connection.setReadTimeout(15*1000);
			      connection.connect();

			      // read the output from the server
			      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			      stringBuilder = new StringBuilder();

			      String line = null;
			      while ((line = reader.readLine()) != null)
			      {
			        stringBuilder.append(line + "\n");
			      }
			      return stringBuilder.toString();
			    }
			    catch (Exception e)
			    {
			      e.printStackTrace();
			      throw e;
			    }
			    finally
			    {
			      // close the reader; this can throw an exception too, so
			      // wrap it in another try/catch block.
			      if (reader != null)
			      {
			        try
			        {
			          reader.close();
			        }
			        catch (IOException ioe)
			        {
			          ioe.printStackTrace();
			        }
			      }
			    }
			  }

}
