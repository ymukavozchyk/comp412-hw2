public class GopherServer {
	
	public static void main( String[] args )
    {
        final int port = 5000;
        System.out.println("Start server on port: " + port);

        GopherCore server = new GopherCore(port);
        server.StartServer();
    }
}
