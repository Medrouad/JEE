package Web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.net.URL;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet("/upload")
@MultipartConfig

public class VoteServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "static-access", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		Part filePart = request.getPart("file"); 
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
	    InputStream fileContent = filePart.getInputStream();
	    String result = getStringFromInputStream(fileContent);
	    PrintWriter  pr = response.getWriter();
	    
	    String[] arr = result.split(",");  
	    int cpt = 0 ; 
	    
	    for(int i = 1; i < arr.length; i++){
	    	
	    	if( isNumeric(arr[i]) ){
	    		cpt++;
	    	}
	    }
	    
	    pr.println("********************************************");
	    
	    for(int i = 1; i < arr.length - cpt ; i++) {
	    	
	    	int length = arr[i].valueOf(i).length();
	    	
	    	pr.println(i +" " +  arr[i].substring(0, (arr[i].length() - length) ) );
	    	pr.println("********************************************");
        }
	    
	    
	    
	    // System.out.println(result);
	    /* InputStream  is = new URL("http://www.preflib.org/data/election/dots/").openStream();
	       String resultUrl = getStringFromInputStream(is);
	       pr.println(resultUrl);
	       System.out.println(resultUrl); */
	    
	    
	}
     
	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
		

	}

}




