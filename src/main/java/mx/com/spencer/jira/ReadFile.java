package mx.com.spencer.jira;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {

	 public List<String> getDatos(String archivo) {
		 List<String> registros= new ArrayList<String>();
		    try {
		      File myObj = new File(archivo);
		      Scanner myReader = new Scanner(myObj);
		      int i=0;
		      while (myReader.hasNextLine()) {
	    		  String data = myReader.nextLine();
		    	  if(i >= 0) {
		    		  registros.add(data);
		    		  System.out.println(data);
		    	  }
		    	  i++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
		    return registros;
		  }
}
