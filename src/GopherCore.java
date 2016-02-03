import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GopherCore extends Thread
{
    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;

    public GopherCore(int port)
    {
        this.port = port;
    }

    public void StartServer()
    {
        try
        {
            serverSocket = new ServerSocket(port);
            this.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void StopServer()
    {
        running = false;
        this.interrupt();
    }

    @Override
    public void run()
    {
        running = true;
        while( running )
        {
            try
            {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket, port);
                requestHandler.start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
