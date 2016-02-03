import java.net.InetAddress;
import java.net.UnknownHostException;


public class ResponseFormatter {
	
	private final String InfoPrefix = "i";
	private final String DirectoryMenuPrefix = "1";
	private final String PlainTextPrefix = "0";
	private final String ErrorPrefix = "3";
	
	private int port;
	private String hostAddress;
	
	public ResponseFormatter(int port){
		this.port = port;
		try
		{
			this.hostAddress = InetAddress.getLocalHost().getHostAddress();
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}		
	}
	
	public String PostInfo(String message){
		return InfoPrefix + message + "\tfake\t(NULL)\t0";
	}
	
	public String PostDirectory(String message){
		return DirectoryMenuPrefix + message + "\t" + hostAddress + "\t" + port;
	}
	
	public String PostTextFile(String message){
		return PlainTextPrefix + message + "\t" + hostAddress + "\t" + port;
	}
	
	public String PostError(String message){
		return ErrorPrefix + message + "\terror.host\t1";
	}
}

