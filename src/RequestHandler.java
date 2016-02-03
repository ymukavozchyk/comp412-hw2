import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class RequestHandler extends Thread
{
    private Socket socket;
    private int port;
    
    RequestHandler(Socket socket, int port)
    {
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            
            ResponseWriter rw = new ResponseWriter(port);

			ArrayList<String> initialResponse = rw.GetInitialResponse();		
			for (String responseLine : initialResponse) {
				out.println(responseLine);
			}
			out.flush();       
                         
			String line = in.readLine();
                                           
            if(line != null && line.length() > 0)
            {
				ArrayList<String> response = rw.GetResponseFromCommand(line);		
				for (String responseLine : response) {
					out.println(responseLine);
				}
				out.flush();
			}
			           
            in.close();
            out.close();
            socket.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
