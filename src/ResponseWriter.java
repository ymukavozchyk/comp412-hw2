import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResponseWriter {
	private ResponseFormatter rf;
	
	public ResponseWriter(int port){
		rf = new ResponseFormatter(port);
	}
	
	public ArrayList<String> GetInitialResponse() throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(rf.PostInfo("Welcome to Yauheni's Mukavozchyk Gopher server!"));		
		result.addAll(GetResponseFromCommand("."));
		
		return result;
	}
	
	public ArrayList<String> GetResponseFromCommand(String command) throws IOException	{			
		File fileObject = new File(command);
		ArrayList<String> result = new ArrayList<String>();
		
		if(fileObject.exists())
		{
			if(fileObject.isDirectory())
			{
				File[] subObjects = fileObject.listFiles();
						
				for (File obj : subObjects) {
					if(obj.isDirectory())
					{
						result.add(rf.PostDirectory(obj.getName() +"\t" + obj.getPath()));
					}
					else if (obj.isFile())
					{
						result.add(rf.PostTextFile(obj.getName() +"\t" + obj.getPath()));
					}			
				}	
			}
			else if(fileObject.isFile())
			{
				Scanner sc = new Scanner(fileObject);
				
				while(sc.hasNextLine()){
					result.add(sc.nextLine());
				}
				
				return result;
			}					
		}
		else
		{
			result.add(rf.PostError("Handler Not Found"));
		}
		
		return result;
	}	
}

