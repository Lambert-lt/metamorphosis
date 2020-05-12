package m0627;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class TxtJava {
public static void main(String[] args) {
	List list = new ArrayList<>();
	String content = JSONObject.toJSONString(list);
	FileOutputStream fop = null;
	  File file;
//	  String content = "This is the text content";
	  
	  try {
	  
	   file = new File("c:/839.txt");
	   fop = new FileOutputStream(file);
	  
	   // if file doesnt exists, then create it
	   if (!file.exists()) {
	    file.createNewFile();
	   }
	  
	   // get the content in bytes
	   byte[] contentInBytes = content.getBytes();
	  
	   fop.write(contentInBytes);
	   fop.flush();
	   fop.close();
	  
	   System.out.println("Done");
	  
	  } catch (IOException e) {
	   e.printStackTrace();
	  } finally {
	   try {
	    if (fop != null) {
	     fop.close();
	    }
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
}
}
